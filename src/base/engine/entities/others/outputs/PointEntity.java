package base.engine.entities.others.outputs;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import base.engine.EngineManager;
import base.engine.entities.BasicEntity;
import base.utils.ResourceManager;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public abstract class PointEntity extends BasicEntity implements IRenderPointEntity{

	public PointEntity(EngineManager e, String name) {
		super(e,name);
		shape = new Rectangle(0,0,32,32);
	}
	
	@Override
	public void render(final String imageName, Graphics g, final int x, final int y) {
		Image tmp = ResourceManager.getImage(imageName);
		if(tmp != null)
			g.drawImage(tmp.getScaledCopy(0.6f), x, y);
	}

}
