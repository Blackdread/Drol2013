package base.engine.entities;

import org.newdawn.slick.geom.Vector2f;

import base.engine.entities.others.outputs.InputsAndOutputs;



public abstract class BasicEntity extends InputsAndOutputs implements IEntity, ISave {
	
	// TODO Sera transformee en shape
	protected int width;
	protected int height;
	//protected int direction;
	protected Vector2f direction;
	
	public static final Vector2f HAUT = new Vector2f(0,1);
	public static final Vector2f BAS = new Vector2f(0,-1);
	public static final Vector2f GAUCHE = new Vector2f(-1,0);
	public static final Vector2f DROITE = new Vector2f(1,0);
	
	public BasicEntity(String name) {
		super(name);
		direction = HAUT;
	}

	public boolean onEntity(int mx, int my) {
		return mx >= x && mx <= x + width && my >= y && my <= y + height;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}
	
	public String save() {
		return ""+"_"+x+"_"+y+"_"+width+"_"+height;
	}
	
	public Object load(String s){
		
		return null;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Vector2f getDirection() {
		return direction;
	}

	public void setDirection(Vector2f direction) {
		this.direction = direction;
	}
	
}
