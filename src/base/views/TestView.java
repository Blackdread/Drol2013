import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class TestView extends View{
	
	private TileSet t;
	private LevelDrol lvl;
	ArrayList<TilePropriety> tp = new ArrayList<TilePropriety>(0);
	
	public void initResources()
	{
		
	}
	
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		if(lvl.isLoadOver())
			lvl.generateLevelGraphic(500, 500).flush();
	}
	
	
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException 
	{
		tp.add(new TilePropriety(0, true, "mur"));
		tp.add(new TilePropriety(0, false, "fond"));
		
		
		t = new TileSet(ResourceManager.getSpriteSheet("turret"), tp);
		
		lvl = new LevelDrol(new File("levels/lvl_0.lvl"), t);
		lvl.loadLevel();
	}
	
	public void keyPressed(int key, char c) {
	}
	
	public void mousePressed(int button, int x, int y) {
	}
}
