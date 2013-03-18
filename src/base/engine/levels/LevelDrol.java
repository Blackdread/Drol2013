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

import base.engine.entities.BasicEntity;
import base.tile.Scroll;
import base.tile.Tile;
import base.tile.TileSet;

public class LevelDrol extends Level {

	protected final int NB_LIGNE_MINIMUM_POUR_PARAMETRE_POUR_DROL = 2;
	
	protected TileSet  tileSet;
	
	protected int largeurNiveau;
	protected int hauteurNiveau;
	protected int largeurTile;
	protected int hauteurTile;
	protected Scroll scroll;
	
	//HashMap : id/Entity
	protected HashMap<Integer, BasicEntity> arrayEntite = new HashMap<Integer, BasicEntity>();
	
	protected Tile[][] tabNiveau;
	
	
	
	public LevelDrol(File file, TileSet tile) {
		super(file);
		
		scroll = new Scroll();
		
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
	
	//TODO
	public Graphics generateLevelGraphic()
	{
		ArrayList<Integer> entiteAffiche = new ArrayList<Integer>();
		
		
		Graphics graph = new Graphics(scroll.getWidth(), scroll.getHeight());
		graph.setColor(Color.blue);
		graph.fillRect(0, 0, scroll.getWidth(), scroll.getHeight());
		
		//Variable necessaire a l'affichage de la fenetre de scrolling
		int minX, minY, maxX, maxY, largeurTile, hauteurTile, indexTile;
		
		largeurTile = tileSet.getSpriteSource().getSprite(0, 0).getWidth();
		hauteurTile = tileSet.getSpriteSource().getSprite(0, 0).getHeight();
		
		
		minX = scroll.getxScroll() / largeurTile;
		maxX = (scroll.getxScroll() + scroll.getWidth())/largeurTile + 1;
		
		minY = scroll.getyScroll() / hauteurTile;
		maxY = (scroll.getyScroll() + scroll.getHeight())/hauteurTile + 1;
		
		for(int i = minY; i < maxY; i++)
		{
			for(int j = minX; j < maxX; j++)
			{
				//Affiche le decors
				indexTile = tabNiveau[i][j].getIndex();
				graph.drawImage(tileSet.getSpriteSource().getSubImage(indexTile % tileSet.getNbTileLargeur(), indexTile/tileSet.getNbTileLargeur()), j*largeurTile-scroll.getxScroll(), i*hauteurTile-scroll.getyScroll());
				
				//Affiche les entitees au voisinage de la tile et qui n'ont pas encore ete affiche
				if(!tabNiveau[i][j].getEntiteProche().isEmpty())
				{
					for(int k = 0; k < tabNiveau[i][j].getEntiteProche().size();k++){
						if(tabNiveau[i][j].getEntiteProche().get(k) != null)
						{
							if(!entiteAffiche.contains(tabNiveau[i][j].getEntiteProche().get(k).getId())){
								tabNiveau[i][j].getEntiteProche().get(k).render(graph, (int)tabNiveau[i][j].getEntiteProche().get(k).getX()-scroll.getxScroll(), (int)tabNiveau[i][j].getEntiteProche().get(k).getY()-scroll.getyScroll());
								entiteAffiche.add(tabNiveau[i][j].getEntiteProche().get(k).getId());
							}
						}
					}
				}
				
			}
		}
		
		return graph;
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

	public HashMap<Integer, BasicEntity> getArrayEntite() {
		return arrayEntite;
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

	/**
	 * Vider les arraylist, etc
	 * 
	 */
	public void releaseMemoryOfLevel(){
		super.releaseMemoryOfLevel();
	}
	
}
