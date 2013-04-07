package base.engine.entities.others.triggers;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import base.engine.entities.BasicEntity;
import base.engine.entities.ICollidableObject;
import base.engine.entities.others.outputs.IActivator;
import base.utils.ResourceManager;
/**
 * Classe qui verifie si la liste des entites qu'elle possede se trouvent dans la zone definie
 * Ne pas oublier d'initialiser l'array et de le "remplir"
 * 
 * @author Yoann CAPLAIN
 *
 */
public class TriggerObjectInZone extends Trigger implements ICollidableObject{
	
	/**
	 * ArrayList qui contient les entites sur lesquelles le trigger peut agir (pourrait)
	 * Cet array contient les objets dans la zone
	 * Donc l'engine doit ajouter les entites et les enlever quand elles bougent, ce n'est pas au trigger de verifier ca
	 */
	// Peut devenir un HashMap
	protected ArrayList<BasicEntity> arrayEntityToActON = new ArrayList<BasicEntity>();
	
	/**
	 * 
	 * @param name TargetName
	 * @param xx x position
	 * @param yy y position
	 * @param w width
	 * @param h height
	 */
	public TriggerObjectInZone(String name, int xx, int yy, int w, int h) {
		super(name);
		x=xx;
		y=yy;
		shape = new Rectangle(0,0,w,h);
	}
	
	/*
	 * OnStartTouch() et OnEndTouchAll() et OnEndTouch() sont declenche lors de l'ajout ou du retrait d'une entite
	 * L'update ne verifie pas ca, c'est l'engine qui le gere
	 * @see base.engine.entities.others.outputs.IUpdatable#update(int)
	 */
	@Override
	public void update(int delta) {
		delayBeforeReset.update(delta);
	}
	
	@Override
	public void render(Graphics g, int x, int y) {
		//*
		Color col = new Color(255,180,0,90);
		g.setColor(col);
		g.fill(shape.transform(Transform.createTranslateTransform(x, y)));
		//g.fill(getCollisionShape());
		//*/
		Image tmp = ResourceManager.getImage("Trigger");
		if(tmp != null){
			tmp = tmp.getScaledCopy(0.6f);
			g.setWorldClip(x,y,shape.getWidth(),shape.getHeight());
			for(int i=0 ; i< shape.getHeight() / 64 + 1; i++)
				for(int j=0 ; j< shape.getWidth() / 64 + 1; j++)
					g.drawImage(tmp, x+64*j, y+64*i);
			g.clearWorldClip();
		}
		
	}
	
	@Override
	public ArrayList<String> get_list_outputs(){
		ArrayList<String> list_outputs = new ArrayList<String>();
		list_outputs.addAll(super.get_list_outputs());
		list_outputs.add("OnEndTouch");
		list_outputs.add("OnEndTouchAll");
		list_outputs.add("OnTouching");
		list_outputs.add("OnNotTouching");
		list_outputs.add("OnStartTouch");
		list_outputs.add("OnTrigger");
		
		return list_outputs;
	}
	
	@Override
	public ArrayList<String> get_list_inputs() {
		ArrayList<String> list_inputs = new ArrayList<String>();
		list_inputs.addAll(super.get_list_inputs());
		list_inputs.add("TouchTest");
		
		return list_inputs;
	}
	
	@Override
	public void fireOutputs(String nameOfOutput) {
		if(nameOfOutput != null){
			if(nameOfOutput.equalsIgnoreCase("OnEndTouch"))
				OnEndTouch();
			else if(nameOfOutput.equalsIgnoreCase("OnEndTouchAll"))
				OnEndTouchAll();
			else if(nameOfOutput.equalsIgnoreCase("OnTouching"))
				OnTouching();
			else if(nameOfOutput.equalsIgnoreCase("OnNotTouching"))
				OnNotTouching();
			else if(nameOfOutput.equalsIgnoreCase("OnStartTouch"))
				OnStartTouch();
			else if(nameOfOutput.equalsIgnoreCase("OnTrigger"))
				OnTrigger();
			else
				super.fireOutputs(nameOfOutput);
		}
	}

	@Override
	public void fireInputs(String nameOfInput) {
		if(nameOfInput != null){
			 if(nameOfInput.equalsIgnoreCase("TouchTest"))
				 TouchTest();
			else
				super.fireInputs(nameOfInput);
		}
		
	}
	
	/*
	 * Inputs
	 */
	/**
	 * Tests if the trigger is being touched and fires an output based on whether the value is true or false
	 * Triggers either the OnTouching or OnNotTouching outputs for whether anything is touching this entity
	 */
	public void TouchTest(){
			if(arrayEntityToActON.size() == 0)
				OnNotTouching();
			else
				OnTouching();
	}

	
	/*
	 * Outputs
	 */
	/**
	 * Fired when an entity stops touching this trigger
	 * Only entities that passed this trigger's filters will cause this output to fire. (activator is the exiting entity)
	 */
	protected void OnEndTouch(){
		if(isTriggerable()){
			fireOutput("OnEndTouch");
			//OnTrigger();	called in removeAnEntityToActON
		}
	}
	/**
	 * Fires when the last entity in the entity's area exits this trigger or when this entity is disabled
	 * Only entities that passed this trigger's filters are considered. (activator is the last exiting entity)
	 */
	protected void OnEndTouchAll(){
		if(isTriggerable()){
			fireOutput("OnEndTouchAll");
			//OnTrigger();	called in removeAnEntityToActON
		}
	}
	/**
	 * Fired when the TouchTest input is called
	 */
	protected void OnTouching(){
		if(isTriggerable()){
			fireOutput("OnTouching");
			OnTrigger();
		}
	}
	/**
	 * Fired when the TouchTest input is called
	 */
	protected void OnNotTouching(){
		if(isTriggerable()){
			fireOutput("OnNotTouching");
			OnTrigger();
		}
	}
	/**
	 * Fired when an entity starts touching this trigger
	 * The touching entity must pass this trigger's filters to cause this output to fire. (activator is the toucher)
	 */
	protected void OnStartTouch(){
		if(isTriggerable()){
			fireOutput("OnStartTouch");
			OnTrigger();
		}
	}
	/**
	 * Fired whenever the trigger is activated. (activator is the activator)
	 * So if OnStartTouch() or OnTouching() or OnEndTouchAll() or ... is fired, this is fired
	 */
	protected void OnTrigger(){
		if(isTriggerable())	// TODO ERREUR ERREUR !!! car dans isTriggerable() le timer est remis a zero hors cette output est appele sur la 2eme fois donc jamais fired
			fireOutput("OnTrigger");
		delayBeforeReset.resetTime();
	}
	
	/**
	 * Devra ajouter l'entite et declencler les outputs OnStartTouch
	 * @param entity to add
	 */
	public void addAnEntityToActON(BasicEntity entity) {
		if(!isEntityAlreadyInArray(entity.getId()))	// par rapport a l'id c'est mieux car des entites peuvent avoir le meme nom mais de classe differente etc
			if(entity instanceof IActivator){	// TODO un activator n'est pas un Info ni un filter mais peut etre un tir, un monstre etc
				arrayEntityToActON.add(entity);	// TODO On ajoute seulement si l'entite passe le filter ??
				
				if(testFilter(entity)){	// entity must pass the filter
					//
					setActivatorToAllOutputs(entity);	// TODO faire isTriggerable() ainsi il n'y aurait peut-etre plus besoin de faire la verification des les OnTrigger etc
					OnStartTouch();
				}
			}
	}
	/**
	 * Devra enlever l'entite et declencler les outputs OnEndTouch et/ou OnEndTouchAll
	 * @param entity to remove
	 */
	public void removeAnEntityToActON(BasicEntity entity) {
		if(entity instanceof IActivator)	// TODO un activator n'est pas un Info ni un filter mais peut etre un tir, un monstre etc
			if(testFilter(entity)){	// entity must pass the filter
				setActivatorToAllOutputs(entity);
				OnEndTouch();
				if(howManyEntityPassFilter() == 1)
					OnEndTouchAll();
				OnTrigger();
			}
		
		arrayEntityToActON.remove(entity);
		arrayEntityToActON.trimToSize();
	}
	
	public boolean isEntityAlreadyInArray(final int id){
		for(BasicEntity v : arrayEntityToActON)
			if(v != null)
				if(v.getId() == id)
					return true;
		return false;
	}
	
	public boolean isEntityAlreadyInArray(String entity){
		for(BasicEntity v : arrayEntityToActON)
			if(v != null)
				if(v.getTargetName().equalsIgnoreCase(entity))
					return true;
		return false;
	}
	
	public boolean isEntityAlreadyInArray(BasicEntity entity){
		for(BasicEntity v : arrayEntityToActON)
			if(v != null)
				if(v.equals(entity))
					return true;
		return false;
	}
	
	protected int howManyEntityPassFilter(){
		int tmp = 0;
		for(BasicEntity v : arrayEntityToActON){
			if(v != null)
				if(testFilter(v))
					tmp++;
		}
		return tmp;
	}
	protected boolean isThereEntityThatPassFilter(){
		for(BasicEntity v : arrayEntityToActON){
			if(v != null)
				if(testFilter(v))
					return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return true if enabled and delay is complete and is not fireOnce
	 */
	protected boolean isTriggerable(){
		if(!enabled)
			return false;
		else{
			if(delayBeforeReset.isTimeComplete()){
				//delayBeforeReset.resetTime(); FAIT dans OnTrigger() sinon erreur
				if(fireOnce){
					if(!hasbeenFired){
						hasbeenFired = true;
						return true;
					}
				}else
					return true;
			}
			return false;
		}	// fin enabled
	}

	@Override
	public void setEnabled(boolean enabled) {
		if(!enabled){
			setActivatorToAllOutputs(null);	// TODO A voir
			OnEndTouchAll();
		}
		this.enabled = enabled;
	}
	@Override
	public void toggle() {
		if(enabled){
			setActivatorToAllOutputs(null);	// TODO A voir
			OnEndTouchAll();
		}
		enabled = !enabled;
	}

	@Override
	public void enable() {
		enabled = true;
	}

	@Override
	public void disable() {
		enabled = false;
	}

	@Override
	public Shape getNormalCollisionShape() {
		if(shape.getX() == 0 && shape.getY() == 0)
			return shape;
		return shape.transform(Transform.createTranslateTransform((x > 0) ? -x : x, (y > 0) ? -y : y));
		//return new Rectangle(0,0,shape.getWidth(),shape.getHeight());
	}

	@Override
	public Shape getCollisionShape() {
		if(shape.getX() != 0 || shape.getY() != 0)
			return shape;
		//return new Rectangle(x,y,shape.getWidth(),shape.getHeight());
		return shape.transform(Transform.createTranslateTransform(x, y));
	}

	@Override
	public void updateShape() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCollidingWith(ICollidableObject collidable) {
		return getCollisionShape().contains(collidable.getCollisionShape()) || collidable.getCollisionShape().contains(getCollisionShape()) || getCollisionShape().contains(collidable.getCollisionShape());
	}

	@Override
	public boolean isCollisionON() {
		return true;
	}

	@Override
	public void setCollisionON(boolean collision) {
		
	}

	@Override
	public void onCollision(ICollidableObject collideWith) {
		// TODO Auto-generated method stub
		
	}
	
}
