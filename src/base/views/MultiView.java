package base.views;

import java.io.IOException;
import java.net.UnknownHostException;
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
import base.engine.MessageKey;
import base.engine.Player;
import base.engine.gui.ListeDeroulante;
import base.engine.network.InfoPartie;
import base.utils.Configuration;
import base.utils.ResourceManager;
import base.utils.Timer;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public class MultiView extends View {

	/*
	 * Une liste de server connu
	 * Une liste de partie deja creer -> possibiliter de cliquer dessus pour rejoindre
	 * Un bouton pour rejoindre une partie via l'ip
	 * (bouton retour)
	 * 
	 */
	private MouseOverArea butRejoindreServerAvecIp, butRetour, butCreerPartie, butRejoindrePartieAvecID;
	
	private ListeDeroulante listeServer, listePartie;
	private Rectangle shapeServer, shapePartie;
	
	private TextField ipServerToJoin;
	
	private TextField textIDPartieARejoindre;
	private int idPartieSelectionner;
	
	private ArrayList<InfoPartie> arrayPartie = new ArrayList<InfoPartie>();
	private int maxPartieToDraw;
	private Timer refreshListePartie = new Timer(5000);
	
	/**
	 * C'est l'instance de player qui sera utilise lorsque l'utilisateur entre dans une partie
	 */
	private Player player;
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		engineManager.setPlayingMulti(true);
		player.setPseudo(Configuration.getPseudo());
	}
	
	@Override
	public void initResources() {
		player = new Player(engineManager, ""+Configuration.getPseudo());
		
		final int MARGIN = 30;
		int w = container.getWidth(), h = container.getHeight();;
		Image tmp = ResourceManager.getImage("butRetour");
		int larg = tmp.getWidth();
		int haut = tmp.getHeight();
		
		int yBut = h - haut - MARGIN;
		
		int hautServer = h/2 - MARGIN, largServer = w/2 - MARGIN;
		int hautPartie = h/2 - MARGIN, largPartie = w/2 - MARGIN;
		
		shapeServer = new Rectangle(30, 50, largServer, hautServer);
		shapePartie = new Rectangle(30+largServer+MARGIN, 50, largPartie-MARGIN, hautPartie);
		
		maxPartieToDraw = (int) (shapePartie.getHeight()/20);
		
		textIDPartieARejoindre = new TextField(container, container.getDefaultFont(), (int)shapePartie.getX(), (int)shapePartie.getY()+(int)shapePartie.getHeight()+10, 50, 22);
		textIDPartieARejoindre.setBackgroundColor(Color.darkGray);
		
		ipServerToJoin = new TextField(container, container.getDefaultFont(), 30, hautServer+50+MARGIN*2, 180, 22);
		ipServerToJoin.setBackgroundColor(Color.darkGray);
		
		
		butRetour = new MouseOverArea(container, ResourceManager.getImage("butRetour"), MARGIN, yBut, larg, haut);
		butRetour.setMouseOverImage(ResourceManager.getImage("butRetourOver"));
		
		butRejoindreServerAvecIp = new MouseOverArea(container, ResourceManager.getImage("MenuJouer"), MARGIN+larg+MARGIN, yBut, larg, haut);
		butRejoindreServerAvecIp.setMouseOverImage(ResourceManager.getImage("MenuJouerOver"));
		
		butCreerPartie = new MouseOverArea(container, ResourceManager.getImage("MenuJouer"), MARGIN+larg*2+MARGIN*2, yBut, larg, haut);
		butCreerPartie.setMouseOverImage(ResourceManager.getImage("MenuJouerOver"));
		
		butRejoindrePartieAvecID = new MouseOverArea(container, ResourceManager.getImage("butRetour"), MARGIN+larg*3+MARGIN*3, yBut, larg, haut);
		butRejoindrePartieAvecID.setMouseOverImage(ResourceManager.getImage("butRetourOver"));
	}


	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		g.setColor(Color.white);
		g.drawString("Liste des serveurs :", shapeServer.getX()+shapeServer.getWidth()/2-container.getDefaultFont().getWidth("Liste des serveurs :")/2, shapeServer.getY()-container.getDefaultFont().getHeight("Liste des serveurs :") - 2);
		g.drawString("Liste des parties :", shapePartie.getX()+shapePartie.getWidth()/2-container.getDefaultFont().getWidth("Liste des parties :")/2, shapePartie.getY()-container.getDefaultFont().getHeight("Liste des parties :") - 2);
		g.drawString("Rejoindre un server avec l'ip :", ipServerToJoin.getX(), ipServerToJoin.getY()-container.getDefaultFont().getHeight("Rejoindre un server avec l'ip :") - 2);
		g.drawString("ID de la partie a rejoindre",textIDPartieARejoindre.getX()+textIDPartieARejoindre.getWidth()+5,textIDPartieARejoindre.getY());
		
		g.setDrawMode(Graphics.MODE_COLOR_MULTIPLY);
		g.setColor(Color.gray);
		g.fill(shapeServer);
		g.fill(shapePartie);
		g.setDrawMode(Graphics.MODE_NORMAL);
		
		g.setColor(Color.white);
		g.draw(shapeServer);
		g.draw(shapePartie);
		
		synchronized(arrayPartie){
			for(int i=0 ; i < arrayPartie.size() && i < maxPartieToDraw;i++)
				if(arrayPartie.get(i)!=null)
					g.drawString(""+arrayPartie.get(i).toString(), shapePartie.getX()+5, shapePartie.getY()+5+i*20);
		}
		
		textIDPartieARejoindre.render(container, g);
		ipServerToJoin.render(container, g);
		
		butRetour.render(container, g);
		butRejoindreServerAvecIp.render(container, g);
		butCreerPartie.render(container, g);
		butRejoindrePartieAvecID.render(container, g);
		
		super.render(container, sbgame, g);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
		refreshListePartie.update(delta);
		
		if(refreshListePartie.isTimeComplete()){
			refreshListePartie.resetTime();
			
			engineManager.getNetworkEngine().demanderUnRefreshListePartieServer();
		}
		
		//engineManager.update(delta);
		// ou faire :
		engineManager.getNetworkEngine().processMessage();
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		switch(key){
		case Input.KEY_ESCAPE:
			gotoPreviousView();
			break;
		}
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		
		if(butRetour.isMouseOver())
			gotoPreviousView();
		else if(butRejoindreServerAvecIp.isMouseOver())
			rejoindreServerViaIp();
		else if(butCreerPartie.isMouseOver())
			creerPartie();
		else if(butRejoindrePartieAvecID.isMouseOver()){
			try{
				int id = Integer.valueOf(textIDPartieARejoindre.getText());
				rejoindrePartieViaID(id);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
	}
	
	private void creerPartie(){
		if(engineManager.getNetworkEngine().creerPartie()){
			((SalonView)Game.getStateByID(Game.SALON_VIEW_ID)).clearListePlayer();
			engineManager.getNetworkEngine().sendObject(player);
			gotoSalonView();
		}else{
			// TODO pas connecte
			System.err.println("Creer Partie FALSE");
		}
	}
	
	private void rejoindreServerViaIp(){
		String ip = ipServerToJoin.getText();
		if(!ip.equalsIgnoreCase("")){
			try {
				engineManager.getNetworkEngine().connect(ip, Configuration.getPort());
				System.out.println("Connecter au serveur");
				engineManager.getNetworkEngine().demanderUnRefreshListePartieServer();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void rejoindrePartieViaID(final int id){
		//if(id != -1)
		((SalonView)Game.getStateByID(Game.SALON_VIEW_ID)).clearListePlayer();
			engineManager.getNetworkEngine().rejoindrePartieViaID(id);
			engineManager.getNetworkEngine().sendObject(player);
	}
	
	public void remplacerArrayPartie(ArrayList<InfoPartie> array){
		synchronized(arrayPartie){
			arrayPartie.clear();
			arrayPartie.addAll(array);
		}
	}
	
	public void gotoSalonView(){
		container.setMouseGrabbed(false);
		game.enterState(Game.SALON_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	
	@Override
	public int getID() {
		return Game.MULTI_VIEW_ID;
	}


	public synchronized Player getPlayer() {
		return player;
	}


	public synchronized void setPlayer(Player player) {
		this.player = player;
	}
}
