package base.engine.entities;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import base.engine.EngineManager;
import base.engine.Message;
import base.engine.MessageKey;
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
	protected boolean dying;
	protected boolean dead;
	protected boolean visible;
	
	protected boolean collisionON;
	
	//protected boolean remove; se trouve deja dans InputAndOutputs

	public ActiveEntity(String name, EngineManager e, int maxLife) {
		super(name, e);
		this.maxLife = maxLife;
		this.life = maxLife;
		this.visible = true;
		dying = false;
		dead = false;
	}

/*
	public boolean mouseOver() {
		return engine.getMouseX() > x && engine.getMouseX() < x + width && engine.getMouseY() > y && engine.getMouseY() < y + height;
	}//*/
	
	public void addLife(int bonus) {
		life = (life + bonus <= maxLife) ? life + bonus : maxLife;
	}

	public void removeLife(int damage) {
		//Il vient d'être tuer. On peut executer des choses avant de supprimer l'unité : boolean dying
		life -= damage;
		if (life <= 0 && !dying) {
			life = 0;
			dying = true;
			onDying();
		}
	}
	
	//A redéfinir si on veut faire quelque chose avec sa mort
	public void onDying()
	{
		dead = true;
		kill();
	}
	
	public void kill()
	{
		super.kill();
		Message mes = new Message();;
		mes.engine = EngineManager.LOGIC_ENGINE;
		mes.instruction = MessageKey.I_REMOVE_ENTITY;
		mes.i_data.put(MessageKey.P_ID, id);
		engineManager.receiveMessage(mes);
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

}
