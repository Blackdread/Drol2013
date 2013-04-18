package base;

import java.io.IOException;

import org.newdawn.slick.SlickException;

import base.engine.Game;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 09 10 2012
 */
public class Launch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//Game g = new Game("config/config.properties", "libs/resources.jar");
			Game g = new Game("config/config.properties", "resources/");
			g.launch();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
