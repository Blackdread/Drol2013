package base.engine.entities;

import base.engine.entities.others.outputs.InputsAndOutputs;



public abstract class BasicEntity extends InputsAndOutputs implements IEntity, ISave {
	
	// TODO Sera transformee en shape
	protected int width;
	protected int height;
	protected int direction;
	
	public static final int HAUT = 0;
	public static final int BAS = 1;
	public static final int GAUCHE = 2;
	public static final int DROITE = 3;
	
	public BasicEntity(String name) {
		super(name);
		direction = 0;
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

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
}
