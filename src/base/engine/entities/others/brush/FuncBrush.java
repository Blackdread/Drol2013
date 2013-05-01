package base.engine.entities.others.brush;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import base.engine.EngineManager;
import base.engine.entities.ActiveEntity;
import base.engine.entities.BasicEntity;
import base.engine.entities.ICollidableObject;

public abstract class FuncBrush extends ActiveEntity implements ICollidableObject{

	private static final long serialVersionUID = -8607596640013462422L;

	protected Image textureDuBrush;
	
	protected boolean collisionON = true;
	
	public FuncBrush(EngineManager e, String name) {
		super(name,e, 1);
	}
	
	public FuncBrush(EngineManager e, String name, Image textureBrush) {
		super(name,e, 1);
		textureDuBrush = textureBrush;
	}

	@Override
	public void render(Graphics g, int x, int y) {
		
		if(textureDuBrush != null){
			int w = textureDuBrush.getWidth(), h = textureDuBrush.getHeight();
			
			g.setWorldClip(x ,y , shape.getWidth(),shape.getHeight());
			for(int i=0 ; i< shape.getHeight() / h + 1; i++)
				for(int j=0 ; j< shape.getWidth() / w + 1; j++)
					g.drawImage(textureDuBrush, x+w*j, y+h*i);
			g.clearWorldClip();
		}else{
			//System.out.println("render brush");
			g.setWorldClip(x ,y , shape.getWidth(),shape.getHeight());
			g.setColor(Color.red);
			g.fillRect(x,y,shape.getWidth(),shape.getHeight());
			g.clearWorldClip();
		}
	}
	
	/**
	 * le onCollision de FuncBrush appel le onCollision de collideWith en lui envoyant null, ainsi il agit comme etant un mur
	 * si collisionON == true
	 */
	@Override
	public void onCollision(ICollidableObject collideWith) {
		// permet de faire en sorte que le brush agit comme un mur
		if(collideWith != null && collisionON)
			collideWith.onCollision(null);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public Shape getNormalCollisionShape() {
		if(shape.getX() == 0 && shape.getY() == 0)
			return shape;
		return shape.transform(Transform.createTranslateTransform((x > 0) ? -x : x, (y > 0) ? -y : y));
	}
	//*/

	@Override
	public Shape getCollisionShape() {
		if(shape.getX() != 0 || shape.getY() != 0)
			return shape;
		return shape.transform(Transform.createTranslateTransform(x, y));
	}

	@Override
	public boolean isCollidingWith(ICollidableObject collidable) {
		if(collidable != null)
			return getCollisionShape().contains(collidable.getCollisionShape()) || collidable.getCollisionShape().contains(getCollisionShape()) || getCollisionShape().contains(collidable.getCollisionShape());
		return false;
	}

	@Override
	public boolean isCollisionON() {
		return collisionON;
	}

	@Override
	public void setCollisionON(boolean collision) {
		collisionON = collision;
	}

	public Image getTextureDuBrush() {
		return textureDuBrush;
	}

	public void setTextureDuBrush(Image textureDuBrush) {
		this.textureDuBrush = textureDuBrush;
	}

}
