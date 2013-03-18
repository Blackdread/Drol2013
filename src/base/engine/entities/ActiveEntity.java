package base.engine.entities;

import base.engine.Engine;
import base.utils.Timer;

public abstract class ActiveEntity extends BasicEntity /*implements */ {

	private static final int TIME_BEFORE_MOUSEOVER = 1000;
	
	
	protected int maxLife;
	protected int life;
	protected boolean mouseOver;
	protected boolean weak;
	protected boolean dying;
	protected boolean visible;
	
	private String name;
	private Timer mouseOverTimer;
	private boolean remove;

	public ActiveEntity(int type, int maxLife) {
		super();
		this.type = type;
		this.maxLife = maxLife;
		this.life = maxLife;
		this.visible = true;
		this.mouseOverTimer = new Timer(TIME_BEFORE_MOUSEOVER);
	//	this.name = EData.NAMES[type];
	}

/*
	public boolean mouseOver() {
		return engine.getMouseX() > x && engine.getMouseX() < x + width && engine.getMouseY() > y && engine.getMouseY() < y + height;
	}//*/
	
	public void addLife(int bonus) {
		life = (life + bonus <= maxLife) ? life + bonus : maxLife;
		if ((float) life / (float) maxLife > 0.5f) {
			weak = false;
			dying = false;
		} else {
			if ((float) life / (float) maxLife > 0.2f) {
				dying = false;
			}
		}
		remove = false;
	}

	public void removeLife(int damage) {
		life -= damage;
		if (life <= 0) {
			if (!remove) {
				//remove();
				remove = true;
			}
			life = 0;
		} else {
			if ((float) life / (float) maxLife <= 0.2f) {
				dying = true;
			} else {
				if ((float) life / (float) maxLife < 0.5f) {
					weak = true;
				}
			}
		}
	}

	public boolean isAlive() {
		return life > 0;
	}

	public int getLife() {
		return life;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setLife(int life) {
		this.life = life;
	}
	public boolean isVisible() {
		return visible;
	}
	
	@Override
	public String save() {
		return super.save()+"_"+type+"_"+maxLife+"_"+life+"_"+name;
	}
	@Override
	public Object load(String s){
		
		return super.load(s);
	}
}
