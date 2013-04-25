package base.engine.entities.others.info;

import org.newdawn.slick.Graphics;

import base.engine.EngineManager;
import base.engine.entities.ActiveEntity;
import base.engine.entities.Monster;

/**
 * Represent a location where player (monster) may spawn
 * @author Yoann CAPLAIN
 * @since 24 04 2013
 */
public class InfoPlayerMonster extends InfoPlayerStart {

	
	public InfoPlayerMonster(EngineManager e, String name) {
		super(e, name);
	}

	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	public ActiveEntity spawn(){
		return new Monster("monster", engineManager, 500);
	}
}
