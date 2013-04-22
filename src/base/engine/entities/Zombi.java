package base.engine.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import base.engine.EngineManager;
import base.utils.ResourceManager;

public class Zombi extends Monster {
	protected Animation anim_mv_d;
	protected Animation anim_mv_g;
	protected Animation anim_static;
	protected Animation current_anim;

	public Zombi(String name, EngineManager en, int maxLife) {
		super(name, en, maxLife);
		
		anim_mv_d = new Animation(ResourceManager.getSpriteSheet("zombi"), 0, 0, 6, 0, true, 300, true);
		anim_mv_g = new Animation(ResourceManager.getSpriteSheet("zombi"), 0, 1, 6, 1, true, 300, true);
		anim_static = new Animation(ResourceManager.getSpriteSheet("zombi"), 0, 2, 0, 2, true, 300, true);
		current_anim = anim_mv_d;
		shape = new Rectangle(0,0,32,56);
		moving = true;
		direction = BasicEntity.DROITE;
	}
	
	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		/*
		g.setColor(Color.red);
		g.fillRect(x, y, this.getWidth(), this.getHeight());*/
		if(moving)
		{
			if(this.getDirection() == BasicEntity.DROITE)
				anim_mv_d.draw(x, y);
			else
				anim_mv_g.draw(x, y);
		}
		else
		{
			anim_static.draw(x, y);
		}
	}

}
