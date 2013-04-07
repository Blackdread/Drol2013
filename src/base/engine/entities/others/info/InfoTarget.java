package base.engine.entities.others.info;

import org.newdawn.slick.Graphics;

/**
 * info_target is a point entity available in all Source games. 
 * It can be used by many different entities, and is a great generic target for any entity 
 * that needs to point, shoot, look at, or walk to a specific target.
 * 
 * It is primarily used for a target for other entities, similar to an info_null. 
 * An info_null is preferable to use as a point at target for lights, because an info_null is 
 * removed on map spawn, whereas an info_target remains
 * @author Yoann CAPLAIN
 *
 * TODO Ajouter un vecteur pour orienter cet entite, par exemple: une entite est teleporter a cette entite et si besoin l'entite teleporter
 * peut s'orienter selon cette entite (InfoTarget)
 * TODO creer une classe pour orienter correctement les entite via x y (puis z si l'engine passe en 3D)
 */
public class InfoTarget extends Info {
	
	public InfoTarget(String name) {
		super(name);
	}
	
	public InfoTarget(String name, float xx, float yy) {
		super(name);
		x=xx;
		y=yy;
		/* N'a pas besoin d'origin
		xOrigin=xx;
		yOrigin=yy;
		*/
	}
	
	@Override
	public void render(Graphics g, int x, int y) {
		super.render("InfoTarget", g, x, y);
	}

}
