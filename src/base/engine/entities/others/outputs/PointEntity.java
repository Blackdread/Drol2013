package base.engine.entities.others.outputs;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import base.engine.EngineManager;
import base.engine.entities.BasicEntity;
import base.utils.ResourceManager;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public abstract class PointEntity extends BasicEntity implements IRenderPointEntity{

	public PointEntity(String name, EngineManager e) {
		super(name, e);
	}
	
	@Override
	public void render(final String imageName, Graphics g, final int x, final int y) {
		Image tmp = ResourceManager.getImage(imageName);
		if(tmp != null)
			g.drawImage(tmp.getScaledCopy(0.6f), x, y);
	}

}
