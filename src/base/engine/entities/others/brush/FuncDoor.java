package base.engine.entities.others.brush;

import org.newdawn.slick.geom.Rectangle;

import base.engine.EngineManager;
import base.engine.entities.ICollidableObject;
import base.engine.entities.others.outputs.IUpdatable;
/**
 * It creates a simple sliding door
 * @author Yoann CAPLAIN
 *
 */
public class FuncDoor extends FuncBrush implements IUpdatable{
	
	private static final long serialVersionUID = 394138811576601972L;

	private boolean closing = false;
	
	private boolean closed = true;
	
	private int speed = 1;
	
	private float xDebutClosed;
	private float yDebutClosed;
	
	public FuncDoor(EngineManager e, String name, int x, int y) {
		super(e, name);
		shape = new Rectangle(x,y,32,60);
		this.x = x;
		this.y = y;
		xDebutClosed = x;
		yDebutClosed = y;
		shape.setLocation(x, y);
	}

	@Override
	public void update(int delta) {
		if(closing){
			this.y -= speed * delta / 10;
		}else{
			this.y += speed * delta / 10;
		}
		
		if((int)y < (int)yDebutClosed){
			y = yDebutClosed;
			if(closing)
				closing = false;
		}
		if((int)y > (int)yDebutClosed+(int)shape.getHeight()+1){
			y = yDebutClosed+shape.getHeight();
			
			if(!closing)
				closing = true;
		}
		shape.setLocation(x, y);
		this.setLocation(x, y);
	}
	
	@Override
	public void onCollision(ICollidableObject collideWith) {
		super.onCollision(collideWith);
		
	}


	

}
