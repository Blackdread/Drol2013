package base.engine.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import base.utils.Vector2f;
import base.engine.EngineManager;
import base.engine.Message;
import base.engine.MessageKey;

public class TirLinear extends Tir {
	
	private static final long serialVersionUID = -2610195202139822039L;

	public TirLinear(EngineManager e){
		super("TirLinear", e, 1, null);
		gravityON = false;
		shape = new Rectangle(0,0,10,10);
	}
	
	public TirLinear(int maxLife, EngineManager en, Vector2f vitesse, BasicEntity e) {
		super("TirLinear", en, maxLife, e);
		this.vitesse = vitesse;
		shape = new Rectangle(0,0,10,10);
		gravityON = false;
	}
	
	public TirLinear(String name, EngineManager en, int maxLife, BasicEntity e) {
		super(name, en, maxLife, e);
		shape = new Rectangle(0,0,10,10);
		gravityON = false;
	}
	
	public TirLinear(String name, EngineManager en, int maxLife, Vector2f vitesse, BasicEntity e) {
		super(name, en, maxLife, e);
		this.vitesse = vitesse;
		shape = new Rectangle(0,0,10,10);
		gravityON = false;
	}

	@Override
	public void render(Graphics g, int x, int y) {
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
		
		if(collideWith != expediteur)
		{
			if(collideWith instanceof ActiveEntity)
				collideWith.onCollision(this);
			/*
			if(collideWith instanceof ActiveEntity)
			{
				Message m2 = new Message();
				m2.instruction = MessageKey.I_REMOVE_ENTITY;
				m2.i_data.put(MessageKey.P_ID, ((ActiveEntity)collideWith).getId());
				m2.engine = EngineManager.LOGIC_ENGINE;
				
				engineManager.receiveMessage(m2);
			}*/
			
			Message m = new Message();
			m.instruction = MessageKey.I_REMOVE_ENTITY;
			m.i_data.put(MessageKey.P_ID, id);
			m.engine = EngineManager.LOGIC_ENGINE;
			
			engineManager.receiveMessage(m);
		}
	}

}
