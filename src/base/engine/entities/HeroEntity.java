package base.engine.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import base.engine.EngineManager;
import base.utils.ResourceManager;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */

/*
 * TODO : Gérer correctement les animations
 * 
 */

public class HeroEntity extends PlayableEntity {
	
	protected Weapon weapon;
	
	//Peut être à disposer plus haut dans la hierarchie des classes (ie moveable entity)
	protected Animation anim_mv_d;
	protected Animation anim_mv_g;
	protected Animation anim_static;
	protected Animation anim_dying;
	protected Animation current_anim;
	
	public HeroEntity(String name, EngineManager en, int maxLife) {
		super(name, en, maxLife);
		// TODO Auto-generated constructor stub
		shape = new Rectangle(0,0,32,48);
		weapon = new Weapon("TirLinear", 500);
		//
		anim_mv_d = new Animation(ResourceManager.getSpriteSheet("rob"), 4, 0, 5, 0, true, 200, true);
		anim_mv_g = new Animation(ResourceManager.getSpriteSheet("rob"), 6, 0, 7, 0, true, 200, true);
		anim_static = new Animation(ResourceManager.getSpriteSheet("rob"), 0, 0, 3, 0, true, 200, true);
		anim_dying = new Animation(ResourceManager.getSpriteSheet("zombi"), 0, 0, 3, 0, true, 200, true);
		anim_dying.setLooping(false);
		current_anim = anim_static;
		moving = false;
	}

	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		/*
		g.setColor(Color.red);
		g.fillRect(x, y, this.getWidth(), this.getHeight());*/
		if(dying)
		{
			current_anim = anim_dying;
		}
		else
		{
			if(moving)
			{
				if(this.getDirection() == BasicEntity.DROITE)
					current_anim = anim_mv_d;
				else
					current_anim = anim_mv_g;
			}
			else
				current_anim = anim_static;
		}
		
		current_anim.draw(x, y);
	}
	
	@Override
	public void update(int delta) {
		super.update(delta);
		
		if(dying)
		{
			timer.update(delta);
			System.out.println(timer.getDeltaStock());
		}
	}
	
	public void onDying()
	{
		dying = true;
		int duration = 0;
		int tab[] = anim_dying.getDurations();
		
		for(int i = 0; i < tab.length; i++)
			duration += tab[i];
		
		System.out.println("dur :" + duration);
		timer.setEventTime(duration);
		timer.reset();
	}

	@Override
	public void onCollision(ICollidableObject collideWith) {
		super.onCollision(collideWith);
		
		if(collideWith instanceof Monster)
		{
			if(!dying)
				this.onDying();
		}
	}

}
