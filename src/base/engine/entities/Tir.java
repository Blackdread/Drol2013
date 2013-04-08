package base.engine.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import base.engine.EngineManager;
import base.engine.Message;
import base.engine.MessageKey;

public class Tir extends MoveableEntity {
	
	public Tir(int maxLife, Vector2f vitesse)
	{
		super("tir", maxLife);
		this.vitesse = vitesse;
		shape = new Rectangle(0,0,10,10);
	}

	public Tir(String name, int maxLife) {
		super(name, maxLife);
		shape = new Rectangle(0,0,10,10);
	}

	@Override
	public void render(Graphics g, int xx, int yy) {
		// TODO Auto-generated method stub
		g.setColor(Color.cyan);
		g.fillRect(x, y, this.getWidth(), this.getHeight());
	}

	@Override
	public void update(int delta) {
		super.update(delta);
		
	}

	@Override
	public void onCollision(ICollidableObject collideWith) {
		// TODO envoyer un message pour declencher les outputs OnKilled()
		
		Message m = new Message();
		m.instruction = MessageKey.I_REMOVE_ENTITY;
		m.i_data.put(MessageKey.P_ID, id);
		m.engine = EngineManager.LOGIC_ENGINE;
		
		EngineManager.getInstance().receiveMessage(m);
		
		
	}


}
