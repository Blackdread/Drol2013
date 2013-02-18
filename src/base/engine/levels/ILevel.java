package base.engine.levels;

public interface ILevel {
	 /*
	public ImageObject getBackground();
 
	public List<Ball> getBalls();
 
	public Paddle getPaddle();
 
	public CollidableImageObject getLeftBumper();
 
	public CollidableImageObject getRightBumper();
 
	public CollidableImageObject getTopBumper();
 
	public List<Brick> getBricks();	
 
	public Ball addNewBall();
*/
	public void releaseMemoryOfLevel();
	public void saveLevel();
	public void loadLevel();
}
