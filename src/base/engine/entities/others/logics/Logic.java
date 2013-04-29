package base.engine.entities.others.logics;

import base.engine.EngineManager;
import base.engine.entities.others.outputs.PointEntity;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public abstract class Logic extends PointEntity {

	private static final long serialVersionUID = 299348676409392066L;

	public Logic(EngineManager e,String name){
		super(e,name);
	}
	
}
