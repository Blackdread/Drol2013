package base.engine.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import base.engine.EngineManager;
import base.utils.ResourceManager;

public class Zombi extends Monster {
	
	private static final long serialVersionUID = 5436637529258587139L;
	
	protected transient Animation anim_mv_d;
	protected transient Animation anim_mv_g;
	protected transient Animation anim_static;
	protected transient Animation current_anim;
	
	public Zombi(String name, EngineManager en, int maxLife) {
		super(name, en, maxLife);
		shape = new Rectangle(0,0,32,56);
		moving = true;
		direction = BasicEntity.DROITE;
		init();
	}
	
	@Override
	public void init() {
		anim_mv_d = new Animation(ResourceManager.getSpriteSheet("zombi"), 0, 0, 6, 0, true, 300, true);
		anim_mv_g = new Animation(ResourceManager.getSpriteSheet("zombi"), 0, 1, 6, 1, true, 300, true);
		anim_static = new Animation(ResourceManager.getSpriteSheet("zombi"), 0, 2, 0, 2, true, 300, true);
		current_anim = anim_mv_d;
	}

	
	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		//super.render(g, x, y);
		if(anim_mv_d == null || anim_mv_g == null || anim_static == null)
			init();
		
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
