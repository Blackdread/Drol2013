package base.engine.entities;


public abstract class Tir extends MoveableEntity {
	
	public Tir(String name, int maxLife, int vx, int vy){
		super(name, maxLife,vx,vy);
	}

	public Tir(String name, int maxLife) {
		super(name, maxLife);
		
	}

}
