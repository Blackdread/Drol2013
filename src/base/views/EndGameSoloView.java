package base.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import base.engine.Game;
import base.engine.Player;
import base.utils.ResourceManager;
import base.utils.StatsSerializable;


public class EndGameSoloView extends View {

	private Player player;
	private MouseOverArea butRejouer, butMenu;
	
	@Override
	public void initResources() {
		Image tmp = ResourceManager.getImage("MenuJouer");
		
		int larg = tmp.getWidth();
		int haut = tmp.getHeight();
		
		int x = container.getWidth() / 2 - larg;
		int y = container.getHeight() - haut - 50;
		
		butRejouer = new MouseOverArea(container, tmp, x, y, larg, haut);
		butRejouer.setMouseOverImage(ResourceManager.getImage("MenuJouerOver"));
		butRejouer.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		butMenu = new MouseOverArea(container, ResourceManager.getImage("MenuSolo"), x + tmp.getWidth(), y,  ResourceManager.getImage("MenuSolo").getWidth(),  ResourceManager.getImage("MenuSolo").getHeight());
		butMenu.setMouseOverImage(ResourceManager.getImage("MenuSoloOver"));
		butMenu.setMouseDownSound(ResourceManager.getSound("butClick"));
	}

	@Override
	public void enter(GameContainer container, StateBasedGame sbgame) throws SlickException {
		super.enter(container, game);
		player = ((TestView)Game.getStateByID(Game.TEST_STATE_ID)).getPlayer();
		
		StatsSerializable scores = new StatsSerializable("config/scoreSolo.seria");
		scores.loadStats();
		
		String ss = scores.getStat(""+player.getPseudo());
		int tmp = 0;
		if(ss != null)
			try{
			tmp = Integer.valueOf(ss);
			}catch(Exception e){}
		
		if(tmp < player.getScore())
			scores.addStat(""+player.getPseudo(), ""+player.getScore());
		
		scores.saveStats();
		
		
		StatsSerializable stats = new StatsSerializable("config/statsSolo.seria");
		stats.loadStats();
		
		stats.addStat("Nb de parties", 1);
		stats.addStat("Score cumulŽ", player.getScore());
		
		stats.saveStats();
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		
		if(player != null)
			synchronized(player){
				String text = "Vous avez perdu ! "+player.getPseudo()+" avec un score de "+player.getScore();
				g.drawString(""+text, container.getWidth()/2-container.getDefaultFont().getWidth(text)/2, container.getHeight()/2-20);
			}
		
		butRejouer.render(container, g);
		butMenu.render(container, g);
		
		super.render(container, sbgame, g);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		
		
	}
	@Override
	public void keyReleased(int key, char c) {
		super.keyReleased(key, c);
		
		
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		
		if(butRejouer.isMouseOver())
			rejouer();
		else if(butMenu.isMouseOver())
			goToMenu();
	}
	
	
	public void rejouer(){
		container.setMouseGrabbed(false);
		game.enterState(Game.TEST_STATE_ID, new FadeOutTransition(), new FadeInTransition());
	}
	
	public void goToMenu(){
		container.setMouseGrabbed(false);
		game.enterState(Game.MAIN_MENU_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	
	@Override
	public int getID() {
		return Game.END_GAME_SOLO_VIEW_ID;
	}

}
