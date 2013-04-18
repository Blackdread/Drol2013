package base.views;

import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import base.utils.Resolution;
import base.engine.gui.ListeDeroulante;
import base.engine.gui.Slider;
import base.engine.Game;
import base.utils.Configuration;
import base.utils.ResourceManager;


/**
 * Menu associated to the options.
 * 
 * @author Yoann CAPLAIN
 * 
 */
public class OptionsView extends View {

	private Image background, listeElement, listeElementOver;;

	MouseOverArea butQuitter, butFullscreen, butSonDesacti, butSonActi;
	private ListeDeroulante listeDerTailleScreen;
	private Slider sliderMusic;
	private RoundedRectangle zone;

	@Override
	public void initResources() {
		int zoneX1 = container.getWidth()/10;
		int zoneX2 = zoneX1*2 + 10;
		int zoneX3 = zoneX1*3 + 10;
		
		int zoneY1 = container.getHeight()/10;
		int zoneY1max = container.getHeight()*3/4;
		int zoneY2 = zoneY1;
		
		zone = new RoundedRectangle(zoneX1, zoneY1, zoneX2 - 10,zoneY1max, 5);
		
		
		background = ResourceManager.getImage("options_view_background").getScaledCopy(container.getWidth(), container.getHeight());
		
		butQuitter = new MouseOverArea(container, ResourceManager.getImage("MenuQuitter"), container.getWidth()/10, container.getHeight()-container.getHeight()/10 - 50, ResourceManager.getImage("MenuQuitterOver").getWidth(), ResourceManager.getImage("MenuQuitterOver").getHeight());
		butQuitter.setMouseOverImage(ResourceManager.getImage("MenuQuitterOver"));
		
		listeElement = ResourceManager.getImage("listeElement");
		listeElementOver = ResourceManager.getImage("listeElementOver");
		
		butFullscreen = new MouseOverArea(container, ResourceManager.getImage("fullscreen"), 50, 50, 150, 50);
		butFullscreen.setMouseOverImage(ResourceManager.getImage("fullscreenOver"));
		
		butSonActi = new MouseOverArea(container, ResourceManager.getImage("activer"), 50, 250, 150, 50);
		butSonActi.setMouseOverImage(ResourceManager.getImage("activerOver"));
		butSonDesacti = new MouseOverArea(container, ResourceManager.getImage("desactiver"), 50, 250, 150, 50);
		butSonDesacti.setMouseOverImage(ResourceManager.getImage("desactiverOver"));
		
		listeDerTailleScreen = new ListeDeroulante((AppGameContainer)container, ResourceManager.getImage("listeDeroulante").getScaledCopy(150, 40), 250, 55);
		listeDerTailleScreen.setMouseOverImage(ResourceManager.getImage("listeDeroulanteOver").getScaledCopy(150, 40));
		
		DisplayMode modes[] = Resolution.getAvailableResolution();
		if(modes!=null)
		for (int i=0;i<modes.length;i++) {
            DisplayMode current = modes[i];
            if(current.getHeight() >= Game.MINIMUM_SCREEN_HAUTEUR)
            	if(!(new Resolution(current.getWidth(), current.getHeight()).equals(new Resolution(container.getWidth(), container.getHeight()))))
            		listeDerTailleScreen.addElementResolution(container, listeElement, current.getWidth(), current.getHeight(), 150, 25);
        }
		listeDerTailleScreen.chercherElementUsed();
		listeDerTailleScreen.applyImageOverAllElement(listeElementOver);
		
		sliderMusic = new Slider(container, ResourceManager.getImage("slider"), ResourceManager.getImage("sliderCursor"), container.getWidth()/14, 190, Configuration.getMusicVolume(), 0, 1, true);
		sliderMusic.getCursor().setMouseOverImage( ResourceManager.getImage("sliderCursorOver"));

	}

	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		g.setColor(Color.red);
		g.fill(zone);
		
		butFullscreen.render(container, g);
		g.setColor(Color.white);
		sliderMusic.render(container, g);
		g.drawString("Volume de la musique :"+sliderMusic.getValuePrecision2(), sliderMusic.getX()+10, sliderMusic.getY()-32);
		
		listeDerTailleScreen.render(container, g);
		
		if(Configuration.isMusicOn())
			butSonDesacti.render(container, g);
		else
			butSonActi.render(container, g);
		
		butQuitter.render(container, g);
		super.render(container, game, g);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		switch(key){
		case Input.KEY_ESCAPE:
			goToMenu();
			break;
		}
	}
	@Override
	public void mouseReleased(int but, int x, int y) {
		super.mouseReleased(but, x, y);
		if(butQuitter.isMouseOver())
			goToMenu();
		if(butFullscreen.isMouseOver())
			inverseFullscreen();
		if(butSonDesacti.isMouseOver()){
			if(Configuration.isMusicOn()){
				Configuration.setMusicOn(false);
				GameMusic.stopMainTheme();
			}else{	//(butSonActi.isMouseOver())
				Configuration.setMusicOn(true);
				GameMusic.loopMainTheme();
			}
		}
		
		listeDerTailleScreen.isMouseOver();
		if(sliderMusic.mouseReleased()){
			Configuration.setMusicVolume(sliderMusic.getValuePrecision2());
			float temp1 = GameMusic.getMusicPosition();
			GameMusic.setMusicVolume(sliderMusic.getValuePrecision2());
			GameMusic.setMusicPostion(temp1);
		}
	}

	private void goToMenu() {
		container.setMouseGrabbed(false);
		game.enterState(Game.MAIN_MENU_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public int getID() {
		return Game.OPTIONS_VIEW_ID;
	}
	
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy){
		super.mouseDragged(oldx, oldy, newx, newy);
		sliderMusic.isMouseGrabbed(oldx, oldy, newx, newy);
	}
	
	private void inverseFullscreen(){
		if(!container.isFullscreen())
			try{
				container.setFullscreen(true);
				Configuration.setFullScreen(true);
			}catch(Exception e){
				e.printStackTrace();
			}
		else
			try{
				container.setFullscreen(false);
				Configuration.setFullScreen(false);
			}catch(Exception e){
				e.printStackTrace();
			}
	}

}
