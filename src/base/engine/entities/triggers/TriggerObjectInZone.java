package base.engine.entities.triggers;

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
	
	public TriggerObjectInZone(int xx, int yy, int w, int h) {
		x=xx;
		y=yy;
		width = w;
		height = h;
	}
	
	public TriggerObjectInZone(int a,int xx, int yy, int w, int h) {
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
		int i = 0;
		Shape zone = new Rectangle(x,y,width,height);
		Shape enti;
		if(arrayEntity!=null)
			for(BasicEntity v : arrayEntity){
				if(v!=null){
					enti = new Rectangle(v.getX(),v.getY(),v.getWidth(),v.getHeight());
					if(enti.intersects(zone) || zone.contains(enti))
						i++;
					// Faire de l'heritage pour differentier == , > et <  ????? ou juste des boolean
					if(i==nbObjectInZoneToActivate){
						 this.isTriggerDeclenched = true;
						 return;
					}
				}
				this.isTriggerDeclenched = false;
			}
	}

}
