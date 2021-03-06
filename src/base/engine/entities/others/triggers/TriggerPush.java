/**
 * 
 */
package base.engine.entities.others.triggers;

import base.engine.EngineManager;
import base.engine.entities.BasicEntity;
import base.engine.entities.others.outputs.InputsAndOutputs;

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
	
	private static final long serialVersionUID = -656133020689450778L;

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
	
	public TriggerPush(EngineManager e, String name, int xx, int yy, int w, int h) {
		super(e, name, xx, yy, w, h);
		//direction = PUSH_XY;
	}

	@Override
	public void copy(InputsAndOutputs objetACopier){
		super.copy(objetACopier);
		angleDirection = ((TriggerPush)objetACopier).angleDirection;
		speedOfPush = ((TriggerPush)objetACopier).speedOfPush;
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
