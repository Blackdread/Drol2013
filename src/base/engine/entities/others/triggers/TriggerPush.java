/**
 * 
 */
package base.engine.entities.others.triggers;

import base.engine.entities.BasicEntity;

/**
 * It is a trigger volume that pushes entities that touch it, except those with a parent
 * @author Yoann CAPLAIN
 * @unfinished
 */
public class TriggerPush extends TriggerObjectInZone {

	//private final char PUSH_XY = 0;
	// TODO pour le moment on travaille que en 2D
	//private final char PUSH_XZ = 1;
	//private final char PUSH_YZ = 2;
	
	/**
	 * Angles indicating the direction to push touched entities
	 * 0 to 360 degrees
	 */
	private int angleDirection;
	
	/**
	 * Default XY
	 */
	@Deprecated	// car finalement on pousse avec un angle
	private char direction;
	
	/**
	 * The speed at which to push entities away, in inches / second
	 */
	private int speedOfPush;
	
	public TriggerPush(String name, int xx, int yy, int w, int h) {
		super(name, xx, yy, w, h);
		//direction = PUSH_XY;
	}

	@Override
	public void update(int delta) {
		super.update(delta);
		
		int nbMsg = speedOfPush / delta;
		int deltaX = (int) (speedOfPush * Math.cos((double)angleDirection*Math.PI/180));
		int deltaY = (int) (speedOfPush * Math.sin((double)angleDirection*Math.PI/180));
		
		for(BasicEntity v : arrayEntityToActON)	// TODO peut changer en InputsAndOutputs
			if(v != null)
				if(testFilter(v)){
					
				}
	}
	
	
}
