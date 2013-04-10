package base.engine.entities.others.outputs;

import org.newdawn.slick.Graphics;

/**
 * Classe a part qui sert juste centrer une methode render de toutes les Point entity 
 * @author Yoann CAPLAIN
 *
 */
public interface IRenderPointEntity {

	public void render(final String imageName, Graphics g, final int x, final int y);
	
}
