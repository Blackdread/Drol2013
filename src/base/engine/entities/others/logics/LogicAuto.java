package base.engine.entities.others.logics;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import base.engine.entities.others.outputs.IUpdatable;
import base.utils.ResourceManager;

/**
 * It fires outputs immediately after a map loads, unlike most other entities that wait for input first. 
 * It can be set to check a global state before firing, which allows you to fire events based on what 
 * took place in a previous map. 
 * If the "Remove on fire" flag is set, the logic_auto is deleted after firing. 
 * To do: This may cause problems with delayed outputs.
 * 
 * This is a singleton (Only one by map/level)	TODO Not sure
 * 
 * @author Yoann CAPLAIN
 *
 */
public class LogicAuto extends Logic implements IUpdatable{
	
	/*
	 * Flags
	 */
	/**
	 * Remove on fire
	 */
	private boolean removeOnFire;
	
	private static LogicAuto instance;
	
	public static LogicAuto getInstance(String name) {
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone__) {
                if (null == instance) {
                    instance = new LogicAuto(name);
                }
            }
        }
        return instance;
	}
	 
	private static Object objetSynchrone__;

	private LogicAuto(String name) {
		super(name);
	}
	
	@Override
	public void update(int delta) {
		
	}
	
	@Override
	public void render(Graphics g, int x, int y) {
		Image tmp = ResourceManager.getImage("logicAuto");
		if(tmp != null)
			g.drawImage(tmp, x, y);
	}
	
	public ArrayList<String> get_list_outputs(){
		ArrayList<String> list_outputs = new ArrayList<String>();
		list_outputs.addAll(super.get_list_outputs());
		list_outputs.add("OnMapSpawn");
		list_outputs.add("OnNewGame");
		list_outputs.add("OnLoadGame");
		list_outputs.add("OnMapTransition");
		list_outputs.add("OnBackgroundMap");
		list_outputs.add("OnMultiNewMap");
		list_outputs.add("OnMultiNewRound");
		
		return list_outputs;
	}
	
	public ArrayList<String> get_list_inputs() {
		ArrayList<String> list_inputs = new ArrayList<String>();
		list_inputs.addAll(super.get_list_inputs());
		list_inputs.add("InputValue");
		list_inputs.add("CompareValues");
		
		return list_inputs;
	}
	
	/*
	 * Outputs
	 */
	/**
	 *  Fired when the map is loaded for any reason
	 */
	private void OnMapSpawn(){
		fireOutput("OnMapSpawn");
	}
	
	/**
	 * Fired when the map is loaded to start a new game
	 */
	private void OnNewGame(){
		fireOutput("OnNewGame");	
	}
	
	/**
	 * Fired when the map is loaded from a saved game
	 */
	private void OnLoadGame(){
		fireOutput("OnLoadGame");	
	}
	
	/**
	 *  Fired when the map is loaded due to a level transition
	 */
	private void OnMapTransition(){
		fireOutput("OnMapTransition");
	}
	/**
	 *  Fired when the map is loaded as a background to the main menu
	 */
	private void OnBackgroundMap(){
		fireOutput("OnBackgroundMap");
	}
	/**
	 * Fired only in multiplayer, when a new map is loaded 
	 */
	private void OnMultiNewMap(){
		fireOutput("OnMultiNewMap");
	}
	/**
	 * Fired only in multiplayer, when a new round is started. 
	 * Only fired in multiplayer games that use round-based gameplay 
	 */
	private void OnMultiNewRound(){
		fireOutput("OnMultiNewRound");
	}

	public boolean isRemoveOnFire() {
		return removeOnFire;
	}
	 
}
