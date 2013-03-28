package base.engine.entities.others.triggers;

import java.util.ArrayList;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import base.engine.entities.BasicEntity;
/**
 * Classe qui verifie si la liste des entites qu'elle possede se trouvent dans la zone definie
 * Ne pas oublier d'initialiser l'array et de le "remplir"
 * 
 * @author Yoann CAPLAIN
 *
 */
public class TriggerObjectInZone extends Trigger {
	
	protected int x, y;
	protected int width, height;
	
	/**
	 * ArrayList qui contient les entites sur lesquelles le trigger agit
	 * Cet array contient les objets dans la zone
	 * Donc l'engine doit ajouter
	 */
	protected ArrayList<BasicEntity> arrayEntityToActON = new ArrayList<BasicEntity>();
	
	
	public TriggerObjectInZone(String name, int xx, int yy, int w, int h) {
		super(name);
		x=xx;
		y=yy;
		width = w;
		height = h;
	}
	
	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		delayBeforeReset.update(delta);
		
	}
	
	/**
	 * Pour le moment, des qu'il y a un nb suffisant d'entite dans la zone, le trigger est declenche
	 * 
	 * Voir pour differencier == et non superieur,  > , < etc
	 * Voir s'il faut verifier par rapport a un shape rectangle ou cercle, un point, etc
	 * 
	 */
	@Override
	public void checkTrigger(){
		
	}
	
	/*
	 * Inputs
	 */
	/**
	 * Tests if the trigger is being touched and fires an output based on whether the value is true or false
	 * Triggers either the OnTouching or OnNotTouching outputs for whether anything is touching this entity
	 */
	public void TouchTest(){
			
	}

	
	/*
	 * Outputs
	 */
	/**
	 * Fired when an entity stops touching this trigger
	 * Only entities that passed this trigger's filters will cause this output to fire. (activator is the exiting entity)
	 */
	protected void OnEndTouch(){
		
	}
	/**
	 * Fires when the last entity in the entity's area exits this trigger or when this entity is disabled
	 * Only entities that passed this trigger's filters are considered. (activator is the last exiting entity)
	 */
	protected void OnEndTouchAll(){
		
	}
	/**
	 * Fired when the TouchTest input is called
	 */
	protected void OnTouching(){
		
	}
	/**
	 * Fired when the TouchTest input is called
	 */
	protected void OnNotTouching(){
		
	}
	/**
	 * Fired when an entity starts touching this trigger
	 * The touching entity must pass this trigger's filters to cause this output to fire. (activator is the toucher)
	 */
	protected void OnStartTouch(){
		
	}
	/**
	 * Fired whenever the trigger is activated. (activator is the activator)
	 */
	protected void OnTrigger(){
		
	}
	
	
	
	/**
	 * Devra ajouter l'entite et declencler les outputs OnStartTouch
	 * @param entity
	 */
	public void addAnEntityToActON(BasicEntity entity) {
		// TODO
		arrayEntityToActON.add(entity);
	}
	/**
	 * Devra enlever l'entite et declencler les outputs OnEndTouch
	 * @param entity
	 */
	public void removeAnEntityToActON(BasicEntity entity) {
		// TODO
		arrayEntityToActON.add(entity);
	}

}
