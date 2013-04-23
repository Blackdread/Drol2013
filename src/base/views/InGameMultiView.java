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

import base.utils.ResourceManager;

public class InGameMultiView extends View {

	private int xPosVueJeu;
	private int yPosVueJeu;
	
	@Override
	public void initResources() {
		int w = container.getWidth(), h = container.getHeight();;
		int minimumPlaceG = 150, minimumPlaceD = 150;
		int minimumPlaceBas = 100, minimumPlaceHaut = 100;
		
		int largeurVueDuJeu = w - minimumPlaceG - minimumPlaceD;
		int hauteurVueDuJeu = h - minimumPlaceBas - minimumPlaceHaut;
		
		int xPosVueJeu = minimumPlaceG;
		int yPosVueJeu = minimumPlaceHaut;
		
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
		
	}


	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		
		
		if(engineManager.getCurrentLevelUsed() != null){
			engineManager.getCurrentLevelUsed().generateLevelGraphic(g, xPosVueJeu, yPosVueJeu);
		}
		
		super.render(container, sbgame, g);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		switch(key){
		case Input.KEY_ESCAPE:
			
			break;
		}
	}
	
	@Override
	public void mouseReleased(int button, int x, int y) {
		super.mouseReleased(button, x, y);
		
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
