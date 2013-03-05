package base.engine.entities.triggers;

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
	
	protected int nbObjectInZoneToActivate = 1;
	protected int x, y;
	protected int width, height;
	
	/**
	 * ArrayList qui contient les entites sur lesquelles le trigger agit
	 * cet array contient les objets dans la zone
	 */
	protected ArrayList<BasicEntity> arrayEntityToActON = new ArrayList<BasicEntity>();
	
	
	public TriggerObjectInZone(String name, int xx, int yy, int w, int h) {
		super(name);
		x=xx;
		y=yy;
		width = w;
		height = h;
	}
	
	public TriggerObjectInZone(String name, int a,int xx, int yy, int w, int h) {
		super(name);
		nbObjectInZoneToActivate = a;
		x=xx;
		y=yy;
		width = w;
		height = h;
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
	
	public ArrayList<BasicEntity> getArrayEntityToActON(){
		return arrayEntityToActON;
	}
	public void setArrayEntityToActON(ArrayList<BasicEntity> arrayEntity) {
		this.arrayEntityToActON = arrayEntity;
	}

}
