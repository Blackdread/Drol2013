package base.views;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

import base.engine.Game;
import base.engine.MessageTchat;
import base.engine.Player;
import base.utils.Configuration;
import base.utils.Timer;

public class InGameMultiView extends View {

	private int xPosVueJeu;
	private int yPosVueJeu;
	private int largeurVueDuJeu;
	private int hauteurVueDuJeu;
	private int tailleFont;
	
	/**
	 * Time before message fadeOut
	 */
	private Timer timerShowMessageTchat;
	private boolean showMessageTchat = false;
	private ArrayList<MessageTchat> arrayMessageTchat = new ArrayList<MessageTchat>();
	private TextField textTchat;
	private int maxMessageToDraw = 20;
	
	/**
	 * Sert a afficher les joueurs et leur score
	 */
	private ArrayList<Player> arrayPlayer = new ArrayList<Player>();

	private Player player;
	
	@Override
	public void initResources() {
		int w = container.getWidth(), h = container.getHeight();;
		int minimumPlaceG = 150, minimumPlaceD = 150;
		int minimumPlaceBas = 100, minimumPlaceHaut = 100;
		
		tailleFont = container.getDefaultFont().getLineHeight();
		
		largeurVueDuJeu = w - minimumPlaceG - minimumPlaceD;
		hauteurVueDuJeu = h - minimumPlaceBas - minimumPlaceHaut;
		
		xPosVueJeu = minimumPlaceG;
		yPosVueJeu = minimumPlaceHaut;
		
		if(engineManager.getCurrentLevelUsed() != null){
			int largNiveau = engineManager.getCurrentLevelUsed().getLargeurNiveau() * engineManager.getCurrentLevelUsed().getLargeurTile();
			int hautNiveau = engineManager.getCurrentLevelUsed().getHauteurNiveau() * engineManager.getCurrentLevelUsed().getHauteurTile();
			
			if(largNiveau < largeurVueDuJeu){
				largeurVueDuJeu = largNiveau - 2;
				
			}
			if(hautNiveau < hauteurVueDuJeu){
				hauteurVueDuJeu = hautNiveau - 2;
				
			}
			
			engineManager.getCurrentLevelUsed().getScroll().setWidth(largeurVueDuJeu);
			engineManager.getCurrentLevelUsed().getScroll().setHeight(hauteurVueDuJeu);
		}
		
		timerShowMessageTchat = new Timer(3000);
		
		textTchat = new TextField(container, container.getDefaultFont(), xPosVueJeu+largeurVueDuJeu/2-90, yPosVueJeu+hauteurVueDuJeu, 180, 22);
		textTchat.setBackgroundColor(Color.darkGray);
		
		arrayMessageTchat.clear();
	}


	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		if(engineManager.getCurrentLevelUsed() != null){
			engineManager.getCurrentLevelUsed().generateLevelGraphic(g, xPosVueJeu, yPosVueJeu);
		}
		
		if(showMessageTchat){
			g.setColor(Color.red);
			synchronized(arrayMessageTchat){
				for(int i=0;i< arrayMessageTchat.size() &&  i<maxMessageToDraw;i++)
					if(arrayMessageTchat.get(i)!=null)
						g.drawString(""+arrayMessageTchat.get(i).toString(), xPosVueJeu+largeurVueDuJeu/2-container.getDefaultFont().getWidth(""+arrayMessageTchat.get(i).toString())/2, yPosVueJeu+hauteurVueDuJeu-30-i*tailleFont);
			}
			
			textTchat.render(container, g);
		}
		
		g.setColor(Color.red);
		synchronized(arrayPlayer){
			for(int i=0;i< arrayPlayer.size();i++)
				if(arrayPlayer.get(i)!=null)
					g.drawString(""+arrayPlayer.get(i).getPseudo()+" "+arrayPlayer.get(i).getScore()+" id: "+arrayPlayer.get(i).getIdEntityHePlays(), 10, 50+i*tailleFont);
		}
		if(player != null)
			synchronized(player){
				g.drawString(""+player.getPseudo()+" "+player.getScore()+" id: "+player.getIdEntityHePlays(), container.getWidth()/2-20, 10);
			}
		
		super.render(container, sbgame, g);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
		if(showMessageTchat && !textTchat.hasFocus()){
			timerShowMessageTchat.update(delta);
			if(timerShowMessageTchat.isTimeComplete()){
				showMessageTchat = false;
				timerShowMessageTchat.resetTime();
			}
				
		}
		if(player != null){
			player.setIdEntityHePlays(15);
			engineManager.getCurrentLevelUsed().getScroll().mettreAJourScroll(player.getIdEntityHePlays());
		}
		//engineManager.update(delta);
		engineManager.getNetworkEngine().processMessage();
		engineManager.getLogicEngine().processMessage();
		//engineManager.getSoundEngine().processMessage();
		
	}
	
	@Override
	public void keyReleased(int key, char c) {
		super.keyReleased(key, c);
		if(player != null)
			player.keyReleased(key, c);
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		
		if(player != null)
			player.keyPressed(key, c);
		
		switch(key){
		case Input.KEY_ESCAPE:
			
			break;
		case Input.KEY_Z:
			showMessageTchat = true;
			timerShowMessageTchat.resetTime();
			break;
		case Input.KEY_ENTER:
			if(showMessageTchat){
				// TODO envoyer message
				envoyerMessageTchat();
			}else{
				showMessageTchat = true;
				timerShowMessageTchat.resetTime();	
				textTchat.setFocus(true);
				//textTchat.setText("");
			}
			break;
		}
	}
	
	@Override
	public void mouseReleased(int button, int x, int y) {
		super.mouseReleased(button, x, y);
		
		
	}

	@Override
	public int getID() {
		return Game.IN_GAME_MULTI_VIEW_ID;
	}
	
	/**
	 * Ajoute un message au chat
	 * @param message
	 */
	public void tchatReceiveMessage(MessageTchat message){
		synchronized(arrayMessageTchat){
			arrayMessageTchat.add(message);
			if(arrayMessageTchat.size() > maxMessageToDraw)
				arrayMessageTchat.remove(0);
		}
	}

	private void envoyerMessageTchat(){
		if(!textTchat.getText().equalsIgnoreCase("")){
			//engineManager.getNetworkEngine().receiveMessage(new MessageTchat(Configuration.getPseudo(), textTchat.getText()));
			engineManager.getNetworkEngine().sendObject(new MessageTchat(Configuration.getPseudo(), textTchat.getText()));
			textTchat.setText("");
		}
	}
	
	public Player getPlayer() {
		if(player != null)
			synchronized(player){
				return player;
			}
		return null;
	}

	public void setPlayer(Player player) {
		if(player != null)
			synchronized(player){
				this.player = player;
			}
	}
	
	public void clearListePlayer(){
		synchronized(arrayPlayer){
			arrayPlayer.clear();
		}
	}
	
	public void remplacerArrayPlayer(ArrayList<Player> array){
		synchronized(arrayPlayer){
			arrayPlayer.clear();
			arrayPlayer.addAll(array);
		}
	}
}
