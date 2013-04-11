package base.engine.entities;

import base.engine.CollisionManager;


public abstract class PlayableEntity extends MoveableEntity {

	public PlayableEntity(String name, int maxLife) {
		super(name, maxLife);
		// TODO Auto-generated constructor stub
	}
	
	public void jump(){
		//if((int)vitesse.y == 0)
		if(CollisionManager.isEntityCollidingWithGround(this))
			vitesse.y = -4.4f;
			//vitesse.y += -5; // ou
	}

}
