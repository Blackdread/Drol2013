package base.views;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import base.engine.Game;
import base.engine.levels.LevelDrol;
import base.tile.TilePropriety;
import base.tile.TileSet;
import base.utils.ResourceManager;

public class TransitionView extends View {

	boolean doOnce = false;
	boolean doOnceLoadOver = false;
	
	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		
		/*
		 *  A SUPPRIMER PLUS TARD
		 * 
		 * 
		 */
		ArrayList<TilePropriety> tp = new ArrayList<TilePropriety>();
		tp.add(new TilePropriety(0, false, "fodfnd"));
		tp.add(new TilePropriety(1, false, "fond"));
		tp.add(new TilePropriety(2, true, "fodnd"));
		tp.add(new TilePropriety(3, false, "fonssd"));
		
		engineManager.setCurrentLevelUsed(new LevelDrol(new File("levels/lvl_0.lvl"), new TileSet(ResourceManager.getSpriteSheet("sprite"), tp), engineManager));
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		if(!doOnce)
			if(engineManager.getCurrentLevelUsed() != null){
				engineManager.getCurrentLevelUsed().loadLevel();
				doOnce = true;
			}
		if(!doOnceLoadOver)
			if(engineManager.getCurrentLevelUsed() != null)
				if(engineManager.getCurrentLevelUsed().isLoadOver()){
					engineManager.getNetworkEngine().envoyerLoadingIsFinished();
					doOnceLoadOver = true;
					System.out.println("Envoie chargement fini");
				}
		
		//engineManager.update(delta);
		// ou faire :
		engineManager.getNetworkEngine().processMessage();
			
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		g.setColor(Color.white);
		if(!doOnceLoadOver){
			g.drawString("Chargement en cours", container.getWidth()/2-50, container.getHeight()/2-10);
		}else
			g.drawString("Chargement terminé. Attente des autres joueurs", container.getWidth()/2-120, container.getHeight()/2-10);
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		switch(key){
		
		}
	}
	
	@Override
	public void mouseReleased(int button, int x, int y) {
		super.mouseReleased(button, x, y);
		
		
	}
	
	public void goToInGameMultiView(){
		container.setMouseGrabbed(false);
		game.enterState(Game.IN_GAME_MULTI_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public int getID() {
		return Game.TRANSITION_VIEW_ID;
	}

}
