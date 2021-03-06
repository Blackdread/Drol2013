package base.engine.entities.others.info;

import org.newdawn.slick.Graphics;

import base.engine.EngineManager;
import base.engine.entities.ActiveEntity;
import base.engine.entities.HeroEntity;

/**
 * Represent a location where player (robot) may spawn
 * @author Yoann CAPLAIN
 * @since 24 04 2013
 */
public class InfoPlayerRobot extends InfoPlayerStart {
	
	private static final long serialVersionUID = -5422337581423270122L;

	public InfoPlayerRobot(EngineManager e, String name) {
		super(e, name);
	}

	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	public ActiveEntity spawn(){
		return new HeroEntity("hero", engineManager, 500);
	}
}
