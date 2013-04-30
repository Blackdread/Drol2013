package base.engine.entities;

import base.engine.EngineManager;

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
			return tmp;
		}
		return null;
	}
	
	public void jouerSon(){
		
	}

}
