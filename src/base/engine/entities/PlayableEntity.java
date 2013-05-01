package base.engine.entities;

import base.engine.CollisionManager;
import base.engine.EngineManager;
import base.engine.entities.others.outputs.InputsAndOutputs;
import base.utils.Timer;


public abstract class PlayableEntity extends MoveableEntity {

	private static final long serialVersionUID = -6493722952972541357L;

	protected boolean controlledByIA = true;
	protected Timer sautAleatoire = new Timer(2000);
	protected Timer directionAleatoire = new Timer(5000);
	
	public PlayableEntity(String name, EngineManager e, int maxLife) {
		super(name, e, maxLife);
	}
	
	@Override
	public void copy(InputsAndOutputs objetACopier){
		super.copy(objetACopier);
		controlledByIA = ((PlayableEntity)objetACopier).controlledByIA;
		sautAleatoire = ((PlayableEntity)objetACopier).sautAleatoire;
	}
	
	@Override
	public void update(int delta) {
		super.update(delta);
		
		if(controlledByIA){
			sautAleatoire.update(delta);
			directionAleatoire.update(delta);
		}
		
		if(sautAleatoire.isTimeComplete()){
			jump();
			sautAleatoire.resetTime();
		}
		if(directionAleatoire.isTimeComplete()){
			if((int)(Math.random()*100) > 20){
				vitesse.x *= -1;
				if(direction == BasicEntity.DROITE)
					direction = BasicEntity.GAUCHE;
				else
					direction = BasicEntity.DROITE;
			}
			directionAleatoire.resetTime();
		}
	}
	
	public void jump(){
		//if((int)vitesse.y == 0)
		if(CollisionManager.isEntityCollidingWithGround(this)){
			vitesse.y = -5.3f;
			//vitesse.y += -5; // ou
			/*
			Message m = new Message();
			m.instruction = MessageKey.I_SET_VARIABLES_ENTITY;
			m.i_data.put(MessageKey.P_ID, id);
			m.f_data.put(MessageKey.P_VITESSE_Y, -5f);
			m.engine = EngineManager.LOGIC_ENGINE;
			
			EngineManager.getInstance().receiveMessage(m);	
			//*/
		}
	}

	public boolean isControlledByIA() {
		return controlledByIA;
	}

	public void setControlledByIA(boolean controlledByIA) {
		this.controlledByIA = controlledByIA;
	}

}
