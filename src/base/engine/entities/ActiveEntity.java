package base.engine.entities;

public abstract class ActiveEntity extends BasicEntity /*implements */ {
	
	protected int maxLife;
	protected int life;
	protected boolean mouseOver;
	protected boolean weak;
	protected boolean dying;
	protected boolean visible;
	
	private boolean remove;

	public ActiveEntity(String name, int type, int maxLife) {
		super(name);
		this.type = type;
		this.maxLife = maxLife;
		this.life = maxLife;
		this.visible = true;
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
