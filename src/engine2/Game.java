/**
 * 
 */
package engine2;

import java.util.ArrayList;

/**
 * @author Nico
 *
 */
public class Game {
	
	private ArrayList<Engine> l_modules = new ArrayList<Engine>(0);
	boolean running;
	
	
	public Game(){
		/* Peut prendre en param�tre des elements de configuration :
		 * R�solution, OS, etc
		 */
		running = true;
	}
	
	void stop(){
		running = false;
	}

}
