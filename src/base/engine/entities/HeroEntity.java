package base.engine.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import base.engine.EngineManager;
import base.engine.entities.others.outputs.InputsAndOutputs;
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
	
	private static final long serialVersionUID = 797100072213590077L;

	protected WeaponRanged weapon;

	//Peut être à disposer plus haut dans la hierarchie des classes (ie moveable entity)
	protected transient Animation anim_mv_d;
	protected transient Animation anim_mv_g;
	protected transient Animation anim_static;
	protected transient Animation anim_dying;
	protected transient Animation current_anim;
	
	public HeroEntity(String name, EngineManager en, int maxLife) {
		super(name, en, maxLife);
		// TODO Auto-generated constructor stub
		shape = new Rectangle(0,0,32,48);
		weapon = new WeaponTirLinear(en,500);
		init();
		moving = false;
	}

	@Override
	public void copy(InputsAndOutputs objetACopier){
		super.copy(objetACopier);
		weapon = ((HeroEntity)objetACopier).weapon;
	}
	
	@Override
	public void init() {
		anim_mv_d = new Animation(ResourceManager.getSpriteSheet("rob"), 4, 0, 5, 0, true, 200, true);
		anim_mv_g = new Animation(ResourceManager.getSpriteSheet("rob"), 6, 0, 7, 0, true, 200, true);
		anim_static = new Animation(ResourceManager.getSpriteSheet("rob"), 0, 0, 3, 0, true, 200, true);
		anim_dying = new Animation(ResourceManager.getSpriteSheet("zombi"), 0, 0, 3, 0, true, 200, true);
		anim_dying.setLooping(false);
		current_anim = anim_static;
	}
	
	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		/*
		g.setColor(Color.red);
		g.fillRect(x, y, this.getWidth(), this.getHeight());
		//*/
		
		if(anim_mv_d == null || anim_mv_g == null || anim_static == null || current_anim == null)
			init();
		
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
		
		g.setColor(Color.red);
		g.drawString(""+id, x+5, y-10);
	}
	
	@Override
	public void update(int delta) {
		super.update(delta);

		weapon.update(delta);
		
		if(dying)
		{
			timer.update(delta);
		}
	}
	
	public void onDying()
	{
		dying = true;
		int duration = 0;
		int tab[] = anim_dying.getDurations();
		
		for(int i = 0; i < tab.length; i++)
			duration += tab[i];
		
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

	public WeaponRanged getWeapon() {
		return weapon;
	}

	public void setWeapon(WeaponRanged weapon) {
		this.weapon = weapon;
	}


}
