package base.engine.levels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import base.engine.EngineManager;
import base.engine.entities.ActiveEntity;
import base.engine.entities.BasicEntity;
import base.tile.Scroll;
import base.tile.Tile;
import base.tile.TileSet;

public class LevelDrol extends Level {

	private static final long serialVersionUID = 570127467730370283L;

	protected final int NB_LIGNE_MINIMUM_POUR_PARAMETRE_POUR_DROL = 2;
	
	protected transient TileSet  tileSet;
	
	protected int largeurNiveau;
	protected int hauteurNiveau;
	protected int largeurTile;
	protected int hauteurTile;
	protected transient Scroll scroll;
	
	//HashMap : id/Entity
	protected HashMap<Integer, BasicEntity> arrayEntite = new HashMap<Integer, BasicEntity>();
	
	protected Tile[][] tabNiveau;
	
	public LevelDrol(File file, TileSet tile, EngineManager engineManager) {
		super(file);
		
		scroll = new Scroll(engineManager);
		
		tileSet = tile;
		
		largeurTile = tile.getLargeurTile();
		hauteurTile = tile.getHauteurTile();
		
		
		InputStream ips;
		InputStreamReader ipsr;
		BufferedReader br=null;
		try{
			ips=new FileInputStream(file);
			ipsr=new InputStreamReader(ips);
			br=new BufferedReader(ipsr);
			String ligne;
			for(int i=0;i<NB_LIGNE_MINIMUM_POUR_PARAMETRE;i++){
				ligne=br.readLine();
			}
			
			for(int i=0;i<NB_LIGNE_MINIMUM_POUR_PARAMETRE_POUR_DROL;i++){
				ligne=br.readLine();
				if(ligne!=null)
					switch(i){
					case 0:
						this.largeurNiveau = Integer.valueOf(ligne);
						break;
					case 1:
						this.hauteurNiveau = Integer.valueOf(ligne);
						/*
						 * ========== *
						 */
						tabNiveau = new Tile[hauteurNiveau][largeurNiveau];
						break;
					}
			}
			/*
			 * Le chargement du reste se fait avec le Thread loadLevel()
			 */
			//while ((ligne=br.readLine())!=null)
				//System.out.println(ligne);
				
			//*/
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(br!=null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void copy(LevelDrol level){
		super.copy(level);
		largeurNiveau = level.largeurNiveau;
		hauteurNiveau = level.hauteurNiveau;
		largeurTile = level.largeurTile;
		hauteurTile = level.hauteurTile;
		
		// A voir
		arrayEntite = level.arrayEntite;
		
		// pas sur
		tabNiveau = level.tabNiveau;
	}
	
	/**
	 * Vider les arraylist, etc
	 * 
	 */
	@Override
	public void releaseMemoryOfLevel(){
		super.releaseMemoryOfLevel();
	}
	
	/**
	 * 
	 * @param graph graphics to draw
	 * @param xPos position x to start drawing (top left corner)
	 * @param yPos position y to start drawing (top left corner)
	 */
	public void generateLevelGraphic(Graphics graph, final int xPos, final int yPos)
	{
		
		//TODO :
		ArrayList<Integer> entiteAffiche = new ArrayList<Integer>();
		
		graph.setClip(xPos, yPos, scroll.getWidth(), scroll.getHeight());
		
		//Variable necessaire a l'affichage de la fenetre de scrolling
		int minX, minY, maxX, maxY, largeurTile, hauteurTile, indexTile;
		
		largeurTile = tileSet.getSpriteSource().getSprite(0, 0).getWidth();
		hauteurTile = tileSet.getSpriteSource().getSprite(0, 0).getHeight();
		
		minX = scroll.getxScroll() / largeurTile;
		maxX = (scroll.getxScroll() + scroll.getWidth())/largeurTile + 1;
		
		minY = scroll.getyScroll() / hauteurTile;
		maxY = (scroll.getyScroll() + scroll.getHeight())/hauteurTile + 1;
		
		if(maxY > hauteurNiveau)
			maxY = hauteurNiveau;
		if(maxX > largeurNiveau)
			maxX = largeurNiveau;
		if(minX < 0)
			minX = 0;
		if(minY < 0)
			minY = 0;
		
		for(int i = minY; i < maxY; i++)
		{
			for(int j = minX; j < maxX; j++)
			{
				//Affiche le decors
				indexTile = tabNiveau[i][j].getIndex();
				graph.drawImage(tileSet.getSpriteSource().getSubImage(indexTile % tileSet.getNbTileLargeur(), indexTile/tileSet.getNbTileLargeur()), xPos + j*largeurTile-scroll.getxScroll(), yPos + i*hauteurTile-scroll.getyScroll());
			}
		}
		//afficherGrilleTile(graph, xPos, yPos);
		
		for(int i = minY; i < maxY; i++)
		{
			for(int j = minX; j < maxX; j++)
			{
				//Affiche les entitees au voisinage de la tile et qui n'ont pas encore ete affiche
				if(!tabNiveau[i][j].isEntiteProcheEmpty()){
					
					for(int k = 0; k < tabNiveau[i][j].getEntiteProcheSize();k++){
						
						if(tabNiveau[i][j].getEntiteProcheAt(k) != null){
							
							if(!entiteAffiche.contains(tabNiveau[i][j].getEntiteProcheAt(k).getId())){
								tabNiveau[i][j].getEntiteProcheAt(k).render(graph, xPos + (int)tabNiveau[i][j].getEntiteProcheAt(k).getX()-scroll.getxScroll(), yPos + (int)tabNiveau[i][j].getEntiteProcheAt(k).getY()-scroll.getyScroll());
								
								entiteAffiche.add(tabNiveau[i][j].getEntiteProcheAt(k).getId());
								
							}
						}
					}
					//System.out.println("fin ("+i+","+j+")");
				}
			}
		}
		
		graph.clearClip();
	}
	
	/**
	 * Afficher juste une grille pour debug
	 * @param g
	 * @param x
	 * @param y
	 */
	@SuppressWarnings("unused")
	private void afficherGrilleTile(Graphics g, int x, int y){
		g.setColor(Color.red);
		for(int i=0;i<500/32;i++){
			for(int j=0;j<500/32;j++)
					g.drawString(""+i+" "+j, j*32+x, i*32+y);
			g.drawLine(x, y+i*32, x+500, y+i*32);
			g.drawLine(x+i*32, y, x+i*32, y+500);
		}
	}
	

	public void loadLevel(){
		LoadLevelThreadDrol load = new LoadLevelThreadDrol(this);
		Thread monThread;
		monThread = new Thread(load);
		monThread.start();
	}
	
	public void saveLevel(){
		/*
		SaveLevelThread load = new SaveLevelThread(this);
		Thread monThread;
		monThread = new Thread(load);
		monThread.start();
		*/
	}
	
	synchronized public void addEntity(BasicEntity entity){
		arrayEntite.put(entity.getId(), entity);
	}
	
	synchronized public void removeEntity(BasicEntity entity){
		removeEntity(entity.getId());
	}
	synchronized public void removeEntity(int id){
		BasicEntity e = arrayEntite.get(id);
		if(e != null){
			int largeur = e.getWidth();
			int hauteur = e.getHeight();
			int ex = (int) e.getX();
			int ey = (int) e.getY();

			for(int j = ey/getHauteurTile(); j <= (ey + hauteur)/getHauteurTile(); j++)
			{
				for(int i = ex/getLargeurTile(); i <= (ex + largeur)/getLargeurTile(); i++)
				{
					tabNiveau[j][i].enleverEntite(id);
					//System.out.println("enl "+j+" "+i);
				}
			}
		}
		arrayEntite.remove(id);
	}
	
	
	public int getLargeurNiveau() {
		return largeurNiveau;
	}

	public void setLargeurNiveau(int largeurNiveau) {
		this.largeurNiveau = largeurNiveau;
	}

	public int getHauteurNiveau() {
		return hauteurNiveau;
	}

	public void setHauteurNiveau(int hauteurNiveau) {
		this.hauteurNiveau = hauteurNiveau;
	}
	
	

	public int getLargeurTile() {
		return largeurTile;
	}

	public void setLargeurTile(int largeurTile) {
		this.largeurTile = largeurTile;
	}

	public int getHauteurTile() {
		return hauteurTile;
	}

	public void setHauteurTile(int hauteurTile) {
		this.hauteurTile = hauteurTile;
	}

	public Tile[][] getTabNiveau() {
		return tabNiveau;
	}

	public void setTabNiveau(Tile[][] tabNiveau) {
		this.tabNiveau = tabNiveau;
	}

	public TileSet getTileSet() {
		return tileSet;
	}

	public void setTileSet(TileSet tileSet) {
		this.tileSet = tileSet;
	}

	synchronized public HashMap<Integer, BasicEntity> getArrayEntite() {
		return arrayEntite;
	}
	
	public BasicEntity getEntity(final int id){
		return arrayEntite.get(id);
	}

	public void setArrayEntite(HashMap<Integer, BasicEntity> arrayEntite) {
		this.arrayEntite = arrayEntite;
	}

	public Scroll getScroll() {
		return scroll;
	}

	public void setScroll(Scroll scroll) {
		this.scroll = scroll;
	}

	
}
