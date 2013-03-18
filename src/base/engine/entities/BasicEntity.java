package base.engine.entities;



public abstract class BasicEntity implements IEntity, ISave {
	
	protected int id;
	protected int type;
	
	
	protected int width;
	protected int height;
	protected float x;
	protected float y;
	
	
	public BasicEntity() {
	}

	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
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

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	

}
