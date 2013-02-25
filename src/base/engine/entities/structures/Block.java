package base.engine.entities.structures;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import base.engine.Engine;
import base.engine.entities.Data;
import base.engine.entities.ICollidableObject;
import base.engine.entities.ISave;
import base.engine.entities.StaticEntity;
import base.engine.entities.triggers.Trigger;

public abstract class Block extends StaticEntity implements ICollidableObject, IGravityBlock, IEntityAcideBlock {

	Shape shape;
	int enleverVie;
	/**
	 * true : les fonctions de l'interface gravite sont appellees
	 */
	protected boolean gravityON;
	protected int damagedDoneOnImpact;
	protected int acceleration;
	protected int vitesse;
	protected int rotation;
	protected boolean damagedFixedOrDamageMultiVitesse;
	
	protected boolean acideON;
	
	ArrayList<Trigger> arrayTrigger;
	
	
	public Block(Engine engine, int layer, int type, int maxLife) {
		super(engine, layer, type, maxLife);
	}
	
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		//if(this.type != Data.BLOCK_STYLO){
		if(this.life != Data.BLOCK_INCASSABLE){
			if(true){
			//if(this.engine.getPlayer().isCollidingWith(shape)){
				this.life -= this.enleverVie;
				if(this.life < 0)
					this.life = 0;
			}
		}
		if(gravityON)
			this.updateGravity();
		
		if(acideON)
			this.detruireBlockFurAMesure();
		
		if(arrayTrigger != null)
			if(arrayTrigger.size()!=0)
				for(Trigger v : arrayTrigger)
					if(v!=null){
						//v.checkTrigger();
						//v.isTrigger();
					}
	}
	
	@Override
	public Shape getNormalCollisionShape() {
		if(shape!=null)
			return shape.transform(Transform.createTranslateTransform(-x, -y));
		return null;
	}

	@Override
	public Shape getCollisionShape() {
		return shape;
	}
	
	@Override
	public boolean isCollidingWith(ICollidableObject collidable) {
		return this.shape.intersects(collidable.getCollisionShape());
	}
	
	@Override
	public void detruireBlockFurAMesure() {
		
	}
	
	@Override
	public boolean isGravityON() {
		return false;
	}

	@Override
	public void setGravityON(boolean gravity) {
		this.gravityON = gravity;
	}
	@Override
	public int getDamagedDoneOnImpact() {
		return this.damagedDoneOnImpact;
	}

	@Override
	public void setDamagedDoneOnImpact(int damage) {
		this.damagedDoneOnImpact = damage;
	}

	@Override
	public int getvitesse() {
		// TODO Auto-generated method stub
		return vitesse;
	}

	@Override
	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	@Override
	public int getAcceleration() {
		// TODO Auto-generated method stub
		return this.acceleration;
	}

	@Override
	public void setAcceleration(int acceleration) {
		this.acceleration = acceleration;
	}

	@Override
	public int getRotation() {
		return this.rotation;
	}

	@Override
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	public boolean isDamagedFixedOrDamageMultiVitesse(){
		return this.damagedFixedOrDamageMultiVitesse;
	}
	public void setDamagedFixedOrDamageMultiVitesse(boolean a){
		this.damagedFixedOrDamageMultiVitesse = a;
	}
	@Override
	public boolean isAcideBlock() {
		return acideON;
	}

	@Override
	public void setAcideBlock(boolean isAcide) {
		this.acideON = isAcide;
	}
	
	/**
	 * Manque la sauvegarde des trigger
	 */
	@Override
	public String save() {
		return super.save()+"_"+gravityON+"_"+damagedDoneOnImpact+"_"+acceleration+"_"+vitesse+"_"+rotation+"_"+damagedFixedOrDamageMultiVitesse+"_"+acideON;
	}
	@Override
	public Object load(String s){
		
		return super.load(s);
	}
	
}
