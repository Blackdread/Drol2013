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

import base.engine.Game;
import base.engine.gui.ListeDeroulante;
import base.utils.ResourceManager;
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
	private ArrayList<String> arrayMessageTchat = new ArrayList<String>();
	private TextField textTchat;
	
	private ArrayList<String> arrayScores = new ArrayList<String>();
	
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
	}


	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		
		
		if(engineManager.getCurrentLevelUsed() != null){
			engineManager.getCurrentLevelUsed().generateLevelGraphic(g, xPosVueJeu, yPosVueJeu);
		}
		
		if(showMessageTchat){
			g.setColor(Color.red);
			synchronized(arrayMessageTchat){
				for(int i=0;i< arrayMessageTchat.size();i++)
					if(arrayMessageTchat.get(i)!=null)
						g.drawString(""+arrayMessageTchat.get(i), xPosVueJeu+largeurVueDuJeu/2-container.getDefaultFont().getWidth(""+arrayMessageTchat.get(i))/2, yPosVueJeu+hauteurVueDuJeu-30-i*tailleFont);
			}
			textTchat.render(container, g);
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
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
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

}
