package base.views;

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
	private MouseOverArea butDonnerIp, butRetour;
	
	private ListeDeroulante listeServer, listePartie;
	private Rectangle shapeSercer, shapePartie;
	
	private TextField ipServerToJoin;
	
	@Override
	public void initResources() {
		final int MARGIN = 30;
		int w = container.getWidth(), h = container.getHeight();;
		Image tmp = ResourceManager.getImage("butRetour");
		int larg = tmp.getWidth();
		int haut = tmp.getHeight();
		
		int yBut = h - haut - MARGIN;
		
		int hautServer = h/3 - MARGIN, largServer = w /2 - MARGIN;
		
	}


	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		
		
		
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
		
	}
	
	@Override
	public int getID() {
		return Game.MULTI_VIEW_ID;
	}

}
