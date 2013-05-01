package base.engine.entities;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import base.engine.EngineManager;
import base.engine.Message;
import base.engine.MessageKey;
import base.engine.entities.others.outputs.IActivator;
import base.engine.entities.others.outputs.ITeam;
import base.engine.entities.others.outputs.IUpdatable;
import base.engine.entities.others.outputs.InputsAndOutputs;
import base.utils.Timer;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public abstract class ActiveEntity extends BasicEntity implements IActivator, IUpdatable, ICollidableObject, ITeam {
	
	private static final long serialVersionUID = 513325436764223435L;
	
	protected int maxLife;
	protected int life;
	protected boolean dying;
	protected boolean dead;
	
	public boolean isDying() {
		return dying;
	}

	public void setDying(boolean dying) {
		this.dying = dying;
	}

	protected boolean visible;
	protected Timer timer;
	
	protected boolean collisionON;
	
	protected int team;
	
	//protected boolean remove; se trouve deja dans InputAndOutputs

	public ActiveEntity(String name, EngineManager e, int maxLife) {
		super(e,name);
		this.maxLife = maxLife;
		this.life = maxLife;
		this.visible = true;
		dying = false;
		dead = false;
		timer = new Timer(0);
	}
	
	@Override
	public void copy(InputsAndOutputs objetACopier){
		super.copy(objetACopier);
		maxLife = ((ActiveEntity)objetACopier).maxLife;
		life = ((ActiveEntity)objetACopier).life;
		dying = ((ActiveEntity)objetACopier).dying;
		dead = ((ActiveEntity)objetACopier).dead;
		visible = ((ActiveEntity)objetACopier).visible;
		timer = ((ActiveEntity)objetACopier).timer;
		
		collisionON = ((ActiveEntity)objetACopier).collisionON;
		
		team = ((ActiveEntity)objetACopier).team;
	}
	
	@Override
	public void update(int delta)
	{
		if(dying && timer.isTimeComplete())
		{	
			
			//System.out.println("COMPLETE");
			kill();
		}
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
		
		timer.reset();
	}
	
	public void kill()
	{
		super.kill();
		Message mes = new Message();
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
	@Override
	public int getTeam() {
		return team;
	}

	@Override
	public void setTeam(int team) {
		this.team = team;
	}

}
