package base.views;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import base.engine.Game;
import base.engine.Message;
import base.engine.MessageTchat;
import base.engine.gui.ListeDeroulante;
import base.utils.Configuration;
import base.utils.ResourceManager;

public class SalonView extends View {

	private MouseOverArea butTchatSend, butRetour, butLancerPartie;
	
	private ListeDeroulante listeJoueurs, listeMessage;
	private Rectangle shapeTchat, shapeListeJoueur;
	private ArrayList<MessageTchat> arrayMessageTchat = new ArrayList<MessageTchat>();
	private TextField textTchat;
	private int maxMessageToDraw;
	
	@Override
	public void initResources() {
		final int MARGIN = 30;
		int w = container.getWidth(), h = container.getHeight();;
		Image tmp = ResourceManager.getImage("butRetour");
		int larg = tmp.getWidth();
		int haut = tmp.getHeight();
		
		int yBut = h - haut - MARGIN;
		
		int hautServer = yBut - MARGIN*4, largServer = w/2 - MARGIN;
		int hautPartie = h/2 - MARGIN, largPartie = w/2 - MARGIN;
		
		shapeListeJoueur = new Rectangle(30, 50, largServer, hautServer);
		shapeTchat = new Rectangle(30+largServer+MARGIN, 50, largPartie-MARGIN, hautPartie);
		
		textTchat = new TextField(container, container.getDefaultFont(), (int)shapeTchat.getX(), (int)shapeTchat.getY()+(int)shapeTchat.getHeight()+2, (int)shapeTchat.getWidth(), 20);
		textTchat.setBackgroundColor(Color.darkGray);
		
		butRetour = new MouseOverArea(container, ResourceManager.getImage("butRetour"), MARGIN, yBut, larg, haut);
		butRetour.setMouseOverImage(ResourceManager.getImage("butRetourOver"));
		
		butLancerPartie = new MouseOverArea(container, ResourceManager.getImage("MenuJouer"), MARGIN+larg*2+MARGIN*2, yBut, larg, haut);
		butLancerPartie.setMouseOverImage(ResourceManager.getImage("MenuJouerOver"));
		
		butTchatSend = new MouseOverArea(container, ResourceManager.getImage("MenuJouer"), textTchat.getX()+textTchat.getWidth()/2, textTchat.getY()+textTchat.getHeight()+2, larg, haut);
		butTchatSend.setMouseOverImage(ResourceManager.getImage("MenuJouerOver"));
		
		arrayMessageTchat.clear();
		maxMessageToDraw = (int) (shapeTchat.getHeight()/20);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
		synchronized(arrayMessageTchat){
			if(arrayMessageTchat.size() > maxMessageToDraw)
				arrayMessageTchat.remove(0);
		}
		
		//engineManager.update(delta);
		// ou faire :
		engineManager.getNetworkEngine().processMessage();
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		g.setColor(Color.white);
		g.drawString("Liste des joueurs :", shapeListeJoueur.getX()+shapeListeJoueur.getWidth()/2-container.getDefaultFont().getWidth("Liste des joueurs :")/2, shapeListeJoueur.getY()-container.getDefaultFont().getHeight("Liste des joueurs :") - 2);
		
		
		g.setDrawMode(Graphics.MODE_COLOR_MULTIPLY);
		g.setColor(Color.gray);
		g.fill(shapeListeJoueur);
		g.fill(shapeTchat);
		g.setDrawMode(Graphics.MODE_NORMAL);
		
		g.setColor(Color.white);
		g.draw(shapeListeJoueur);
		g.draw(shapeTchat);
		
		synchronized(arrayMessageTchat){
			for(int i=0 ; i < arrayMessageTchat.size() && i < maxMessageToDraw;i++)
				if(arrayMessageTchat.get(i)!=null)
					g.drawString(""+arrayMessageTchat.get(i).toString(), shapeTchat.getX()+5, shapeTchat.getY()+5+i*20);
		}
		textTchat.render(container, g);
		
		textTchat.render(container, g);
		butTchatSend.render(container, g);
		butRetour.render(container, g);
		butLancerPartie.render(container, g);
		
		super.render(container, sbgame, g);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		switch(key){
		case Input.KEY_ESCAPE:
			gotoPreviousView();
			break;
		case Input.KEY_ENTER:
			envoyerMessageTchat();
			break;
		}
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		if(butTchatSend.isMouseOver()){
			envoyerMessageTchat();
		}else if(butLancerPartie.isMouseOver()){
			engineManager.getNetworkEngine().lancerPartie();
		}
	}
	
	private void envoyerMessageTchat(){
		if(!textTchat.getText().equalsIgnoreCase("")){
			//engineManager.getNetworkEngine().receiveMessage(new MessageTchat(Configuration.getPseudo(), textTchat.getText()));
			engineManager.getNetworkEngine().sendObject(new MessageTchat(Configuration.getPseudo(), textTchat.getText()));
			textTchat.setText("");
			textTchat.setFocus(true);
		}
	}
	
	public void goToTransitionView(){
		container.setMouseGrabbed(false);
		game.enterState(Game.TRANSITION_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public int getID() {
		return Game.SALON_VIEW_ID;
	}
	
	/**
	 * Ajoute un message au chat
	 * @param message
	 */
	public void tchatReceiveMessage(MessageTchat message){
		synchronized(arrayMessageTchat){
			arrayMessageTchat.add(message);
		}
	}

}
