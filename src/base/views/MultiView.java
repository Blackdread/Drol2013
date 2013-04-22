package base.views;

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

import base.engine.Game;
import base.engine.gui.ListeDeroulante;
import base.utils.ResourceManager;

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
	private MouseOverArea butRejoindreAvecIp, butRetour;
	
	private ListeDeroulante listeServer, listePartie;
	private Rectangle shapeServer, shapePartie;
	
	private TextField ipServerToJoin;
	
	@Override
	public void initResources() {
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
		
		ipServerToJoin = new TextField(container, container.getDefaultFont(), 30, hautServer+50+MARGIN*2, 100, 20);
		
		butRetour = new MouseOverArea(container, ResourceManager.getImage("butRetour"), MARGIN, yBut, larg, haut);
		butRetour.setMouseOverImage(ResourceManager.getImage("butRetourOver"));
		
		butRejoindreAvecIp = new MouseOverArea(container, ResourceManager.getImage("MenuJouer"), MARGIN+larg+MARGIN, yBut, larg, haut);
		butRetour.setMouseOverImage(ResourceManager.getImage("MenuJouerOver"));
		
	}


	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		g.setColor(Color.white);
		g.drawString("Liste des serveurs :", shapeServer.getX()+shapeServer.getWidth()/2-container.getDefaultFont().getWidth("Liste des serveurs :")/2, shapeServer.getY()-container.getDefaultFont().getHeight("Liste des serveurs :") - 2);
		g.drawString("Liste des parties :", shapePartie.getX()+shapePartie.getWidth()/2-container.getDefaultFont().getWidth("Liste des parties :")/2, shapePartie.getY()-container.getDefaultFont().getHeight("Liste des parties :") - 2);
		g.drawString("Rejoindre une partie avec l'ip :", ipServerToJoin.getX(), ipServerToJoin.getY()-container.getDefaultFont().getHeight("Rejoindre une partie avec l'ip :") - 2);
		
		
		g.setDrawMode(Graphics.MODE_COLOR_MULTIPLY);
		g.setColor(Color.gray);
		g.fill(shapeServer);
		g.fill(shapePartie);
		g.setDrawMode(Graphics.MODE_NORMAL);
		
		g.setColor(Color.white);
		g.draw(shapeServer);
		g.draw(shapePartie);
		
		super.render(container, sbgame, g);
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
		else if(butRejoindreAvecIp.isMouseOver())
			rejoindrePartieViaIp();
	}
	
	private void rejoindrePartieViaIp(){
		
	}
	
	@Override
	public int getID() {
		return Game.MULTI_VIEW_ID;
	}

}
