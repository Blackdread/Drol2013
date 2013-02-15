package base.engine.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.GUIContext;

public abstract class ProgressBar {
	protected final int MAX_VALUE = 100;
	protected final int DECALAGE_RENDER_VALUE = 5;
	
	protected int x, y;
	/**
	 * min 0 et max 100
	 */
	protected float value;
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public float getValue() {
		return value;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public void setLocation(int x,int y){
		this.x=x;
		this.y=y;
	}
	public abstract int getWidht();
	public abstract int getHeight();
	
	public abstract void render(GUIContext container, Graphics g);
	public abstract void renderAndValue(GUIContext container, Graphics g);
	
	public boolean isComplete(){
		return value>=100;
	}
	public void reset(){
		value=0;
	}
}
