package base.engine.entities;

import org.newdawn.slick.geom.Vector2f;


public abstract class Tir extends MoveableEntity {
	
	public Tir(String name, int maxLife, Vector2f vitesse){
		super(name, maxLife);		
	}

	public Tir(String name, int maxLife) {
		super(name, maxLife);
		
	}

}
