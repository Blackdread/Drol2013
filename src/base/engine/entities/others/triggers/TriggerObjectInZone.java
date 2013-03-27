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
	
	
	/*
	 * Outputs
	 */
	
	
	
	
	public ArrayList<BasicEntity> getArrayEntityToActON(){
		return arrayEntityToActON;
	}
	public void setArrayEntityToActON(ArrayList<BasicEntity> arrayEntity) {
		this.arrayEntityToActON = arrayEntity;
	}

}
