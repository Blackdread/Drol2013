package base.engine.entities;

import base.engine.EngineManager;
import base.engine.Message;
import base.engine.MessageKey;

public class WeaponTirLinear extends WeaponRanged {
	
	private static final long serialVersionUID = -5447043794061220257L;

	public WeaponTirLinear(EngineManager engineManager) {
		super(engineManager);
	}

	public WeaponTirLinear(EngineManager engineManager, int delay) {
		super(engineManager, delay);
	}

	public void copy(WeaponRanged objetACopier){
		super.copy(objetACopier);
		
	}
	
	@Override
	public Tir shoot() {
		if(delayBetweenShoot.isTimeComplete()){
			delayBetweenShoot.resetTime();
			jouerSon();
			return new TirLinear(null);
		}
		return null;
	}

	@Override
	public Tir shoot(int xPos, int yPos) {
		if(delayBetweenShoot.isTimeComplete()){
			delayBetweenShoot.resetTime();
			TirLinear tmp = new TirLinear(null);
			tmp.setX(xPos);tmp.setY(yPos);
			jouerSon();
			return tmp;
		}
		return null;
	}
	
	public void jouerSon(){
		// choisir un son alea
		String name = "piou";
		
		int alea = (int)(1+Math.random()*3);
		
		Message m = new Message();
		m.instruction = MessageKey.I_PLAY_SOUND;
		m.s_data.put(MessageKey.P_NAME, ""+name+""+alea);
		m.engine = EngineManager.SOUND_ENGINE;
		
		engineManager.receiveMessage(m);
	}

}
