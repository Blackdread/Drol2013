package base.engine.entities;

import base.engine.CollisionManager;
import base.engine.EngineManager;
import base.engine.Message;
import base.engine.MessageKey;


public abstract class PlayableEntity extends MoveableEntity {

	public PlayableEntity(String name, int maxLife) {
		super(name, maxLife);
	}
	
	public void jump(){
		//if((int)vitesse.y == 0)
		if(CollisionManager.isEntityCollidingWithGround(this)){
			//vitesse.y = -4.4f;
			//vitesse.y += -5; // ou
			Message m = new Message();
			m.instruction = MessageKey.I_SET_VARIABLES_ENTITY;
			m.i_data.put(MessageKey.P_ID, id);
			m.f_data.put(MessageKey.P_VITESSE_Y, -4.4f);
			m.engine = EngineManager.LOGIC_ENGINE;
			//*/
			
			EngineManager.getInstance().receiveMessage(m);	
		}
	}

}
