package base.views;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import base.engine.Game;
import base.engine.Player;
import base.engine.entities.HeroEntity;
import base.engine.entities.Zombi;
import base.engine.entities.others.brush.FuncDoor;
import base.engine.entities.others.filters.FilterActivatorName;
import base.engine.entities.others.info.InfoTarget;
import base.engine.entities.others.triggers.TriggerTeleport;
import base.engine.levels.LevelDrol;
import base.engine.levels.LevelManager;
import base.engine.logics.Deplacement;
import base.tile.TilePropriety;
import base.tile.TileSet;
import base.utils.Configuration;
import base.utils.ResourceManager;
import base.utils.StatsSerializable;


public class TestView extends View{
	
	private TileSet t;
	ArrayList<TilePropriety> tp = new ArrayList<TilePropriety>(0);
	
	private Player player;
	private StatsSerializable stat = new StatsSerializable("config/scoreSolo.seria");
	
	private int choixLevel = 0;
	
	public void initResources(){
		/*
		tp.add(new TilePropriety(0, false, "fodfnd"));
		tp.add(new TilePropriety(1, false, "fond"));
		tp.add(new TilePropriety(2, true, "fodnd"));
		tp.add(new TilePropriety(3, false, "fonssd"));
		
		
		t = new TileSet(ResourceManager.getSpriteSheet("sprite"), tp);
		*/
		//LevelDrol lvl = new LevelDrol(new File("levels/lvl_"+choixLevel+".lvl"), t, Game.getEngineManager());
		//lvl.loadLevel();
		//engineManager.setCurrentLevelUsed(lvl);
		
		//player = new Player(engineManager, ""+Configuration.getPseudo());
		
		/*
		while(!lvl.isLoadOver())
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		*/
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame sbgame) throws SlickException {
		super.enter(container, game);
		
		player = new Player(engineManager, ""+Configuration.getPseudo());
		
		//if(engineManager.getCurrentLevelUsed() == null)
		//engineManager.setCurrentLevelUsed(new LevelDrol(new File("levels/lvl_"+choixLevel+".lvl"), t, engineManager));
		engineManager.setCurrentLevelUsed((LevelDrol) LevelManager.getInstance(engineManager).getLevel(choixLevel));
		
		if(!engineManager.getCurrentLevelUsed().isLoadOver()){
			engineManager.getCurrentLevelUsed().loadLevel();
			while(!engineManager.getCurrentLevelUsed().isLoadOver())
			{
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
			
		init();
		
	}
	
	public void init(){
		LevelDrol lvl = engineManager.getCurrentLevelUsed();
		
		engineManager.clearEverything();
		lvl.clear();
		
		HeroEntity hero = new HeroEntity("bla", engineManager, 500);
		hero.setLocation(70, 70);
		
		Zombi z = new Zombi("zombi", engineManager, 10);
		z.setLocation(240, 40);
		
		TriggerTeleport trGauche = new TriggerTeleport(engineManager,"teleport", lvl.getLargeurTile()+2,lvl.getHauteurTile()+2,5,lvl.getHauteurTile()*(lvl.getHauteurNiveau()-2));
		TriggerTeleport trTeleportDroite = new TriggerTeleport(engineManager,"teleport2", lvl.getLargeurTile()*(lvl.getLargeurNiveau()-1)-5,lvl.getHauteurTile()+2,5,lvl.getHauteurTile()*(lvl.getHauteurNiveau()-2));
		trGauche.setRemoteDestination("infotargetDroite");	
		trTeleportDroite.setRemoteDestination("infotargetGauche");
		
		
		InfoTarget inf = new InfoTarget(engineManager,"infotargetMilieuHaut", 700, 40);
		
		InfoTarget infGauche[] = new InfoTarget[6];
		for(int i=0;i<6;i++){
			infGauche[i] = new InfoTarget(engineManager,"infotargetGauche", lvl.getLargeurTile()*2, (lvl.getHauteurTile()+5)+(lvl.getHauteurTile()*4*i));
			engineManager.getInfoManager().addEntity(infGauche[i]);
			//Deplacement.deplacerEntity(engineManager,0, 0, infGauche[i].getId());
			Deplacement.ajouterEntiteDansTiles(infGauche[i]);
		}
		
		InfoTarget infDroite[] = new InfoTarget[6];
		for(int i=0;i<6;i++){
			infDroite[i] = new InfoTarget(engineManager,"infotargetDroite", lvl.getLargeurTile()*lvl.getLargeurNiveau()-lvl.getLargeurTile()*3, (lvl.getHauteurTile()+5)+(lvl.getHauteurTile()*4*i));
			engineManager.getInfoManager().addEntity(infDroite[i]);
			//Deplacement.deplacerEntity(engineManager,0, 0, infDroite[i].getId());
			Deplacement.ajouterEntiteDansTiles(infDroite[i]);
		}
		
		//InfoTarget inf = new InfoTarget(engineManager,"infotarget", 200, 36);
		FilterActivatorName fil = new FilterActivatorName(engineManager,"filtername",true,"tirlinear");
		
		trGauche.setFilterEntityThatActivate(fil);	trTeleportDroite.setFilterEntityThatActivate(fil);
		engineManager.getInfoManager().addEntity(inf);
		engineManager.getFilterManager().addEntity(fil);
		//lvl.addEntity(tr);
		engineManager.addEntity(trGauche);
		engineManager.addEntity(trTeleportDroite);
		lvl.addEntity(inf);
		//Deplacement.deplacerEntity(engineManager,0, 0, tr.getId());
		Deplacement.ajouterEntiteDansTiles(trGauche);	Deplacement.ajouterEntiteDansTiles(trTeleportDroite);
		Deplacement.deplacerEntity(engineManager,0, 0, inf.getId());
		lvl.addEntity(hero);
		lvl.addEntity(z);
		Deplacement.deplacerEntity(engineManager,0, 0, hero.getId());
		Deplacement.deplacerEntity(engineManager,0, 0, z.getId());
		engineManager.getIA().addEntity(hero);
		engineManager.getIA().addEntity(z);
		
		System.out.println("hero : " + hero);
		
		player = new Player(engineManager, ""+Configuration.getPseudo(), hero.getId());
		
		FuncDoor d = new FuncDoor(engineManager, "door", 540, 180);
		engineManager.addEntity(d);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		// lvl.getScroll().mettreAJourScroll(hero); fait dans Player
		//if()
		if(engineManager.getCurrentLevelUsed() != null && engineManager.getCurrentLevelUsed().isLoadOver())
			engineManager.getCurrentLevelUsed().generateLevelGraphic(g, 200, 100);
			
			g.setColor(Color.red);
			if(player != null)
				//synchronized(player){
					g.drawString(""+player.getPseudo()+" "+player.getScore()+" id: "+player.getIdEntityHePlays(), container.getWidth()/2-20, 10);
				//}
			
		super.render(container, sbgame, g);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {	
		player.update(delta);
		
		engineManager.update(delta);
	}
	
	public void goToEndGameSoloView()
	{
		container.setMouseGrabbed(false);
		game.enterState(Game.END_GAME_SOLO_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		player.keyPressed(key, c);
		
	}
	@Override
	public void keyReleased(int key, char c) {
		super.keyReleased(key, c);
		player.keyReleased(key, c);
		
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
	}

	@Override
	public int getID() {
		return Game.TEST_STATE_ID;
	}

	public int getChoixLevel() {
		return choixLevel;
	}

	public void setChoixLevel(int choixLevel) {
		this.choixLevel = choixLevel;
	}
}
