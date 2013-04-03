package base.engine.entities;

import base.engine.entities.others.outputs.IActivator;
import base.engine.entities.others.outputs.InputsAndOutputs;



public abstract class BasicEntity extends InputsAndOutputs implements IEntity, ISave, IActivator {
	
	protected int type;
	
	// TODO Sera transformee en shape
	protected int width;
	protected int height;
	
	public BasicEntity(String name) {
		super(name);
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
	

	public int getType() {
		return type;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public BasicEntity getActivator() {
		return this;
	}
	

}
