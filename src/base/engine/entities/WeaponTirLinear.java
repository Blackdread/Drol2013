package base.engine.entities;

public class WeaponTirLinear extends WeaponRanged {
	
	

	public WeaponTirLinear() {
		super();
	}

	public WeaponTirLinear(int delay) {
		super(delay);
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

}
