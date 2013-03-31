package base.utils;

/**
 * 
 * @author Yoann CAPLAIN
 * @deprecated Utiliser un vector2f est surement mieux
 */
public class Angle {

	private int xAngle;	// rotation around YZ plan
	private int yAngle;	// rotation around XZ plan
	private int zAngle;	// rotation around XY plan
	
	public Angle(){
		
	}
	
	public Angle(int XY, int XZ, int YZ){
		zAngle = XY;
		yAngle = XZ;
		xAngle = YZ;
	}

	public void setAngles(int XY, int XZ, int YZ){
		zAngle = XY;
		yAngle = XZ;
		xAngle = YZ;
	}
	
	public int getxAngle() {
		return xAngle;
	}

	public int getyAngle() {
		return yAngle;
	}

	public int getzAngle() {
		return zAngle;
	}

	public void setxAngle(int xAngle) {
		this.xAngle = xAngle;
	}

	public void setyAngle(int yAngle) {
		this.yAngle = yAngle;
	}

	public void setzAngle(int zAngle) {
		this.zAngle = zAngle;
	}
	
	
	
}
