package base.engine.entities;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import base.engine.entities.others.outputs.IActivator;
import base.engine.entities.others.outputs.IUpdatable;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public abstract class ActiveEntity extends BasicEntity implements IActivator, IUpdatable, ICollidableObject {
	
	protected int maxLife;
	protected int life;
	protected boolean mouseOver;
	protected boolean weak;
	protected boolean dying;
	protected boolean visible;
	
	private boolean remove;
	
	protected boolean collisionON;

	public ActiveEntity(String name, int maxLife) {
		super(name);
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
	public BasicEntity getActivator() {
		return this;
	}
	
	//*
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
		return getCollisionShape().contains(collidable.getCollisionShape()) || collidable.getCollisionShape().contains(getCollisionShape()) || getCollisionShape().contains(collidable.getCollisionShape());
	}

	@Override
	public boolean isCollisionON() {
		return collisionON;
	}

	@Override
	public void setCollisionON(boolean collision) {
		collisionON = collision;
	}

}
