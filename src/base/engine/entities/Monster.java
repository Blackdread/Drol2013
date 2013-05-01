package base.engine.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import base.engine.CollisionManager;
import base.engine.EngineManager;
import base.engine.Game;
import base.utils.StatsSerializable;
import base.views.TestView;

public class Monster extends PlayableEntity {

	private static final long serialVersionUID = 7869440638311123825L;

	public Monster(String name, EngineManager en, int maxLife) {
		super(name, en, maxLife);
		// TODO Auto-generated constructor stub
		shape = new Rectangle(0,0,32,32);
		gravityON = true;
		vitesse.x = 1;
		setLocation(64, 64);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillRect(x, y, this.getWidth(), this.getHeight());
		//System.out.println("render monstre");
	}
	
	@Override
	public void update(int delta) {
		super.update(delta);
		
	}

	@Override
	public void onCollision(ICollidableObject collideWith){
		super.onCollision(collideWith);
		
		if(collideWith != null)
		{
			if(collideWith instanceof Tir){
				dying = true;
				this.onDying();
				((TestView)Game.getStateByID(Game.TEST_STATE_ID)).getPlayer().setScore(((TestView)Game.getStateByID(Game.TEST_STATE_ID)).getPlayer().getScore() + 10);
				if(!engineManager.isPlayingMulti()){
					StatsSerializable stats = new StatsSerializable("config/statsSolo.seria");
					stats.loadStats();
					
					stats.addStat("Nb de monstres tués", 1);
					
					stats.saveStats();
				}
			
			}else if(collideWith instanceof ActiveEntity)
				((ActiveEntity)collideWith).onDying();
		}
		else if(CollisionManager.isEntityCollidingWithLeftOrRight(this))
		{	
			
			//System.out.println("Coll monstre avec mur");
			//Si il tape un mur il part dans le sens inverse
			vitesse.x *= -1;
			if(direction == BasicEntity.DROITE)
				direction = BasicEntity.GAUCHE;
			else
				direction = BasicEntity.DROITE;
		}
	}



}
