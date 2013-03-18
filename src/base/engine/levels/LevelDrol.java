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
import base.tile.Tile;
import base.tile.TileSet;

public class LevelDrol extends Level {

	protected final int NB_LIGNE_MINIMUM_POUR_PARAMETRE_POUR_DROL = 2;
	
	protected TileSet  tileSet;
	
	protected int largeurNiveau;
	protected int hauteurNiveau;
	protected int largeurTile;
	protected int hauteurTile;
	
	//HashMap : id/Entity
	protected HashMap<Integer, BasicEntity> arrayEntite = new HashMap<Integer, BasicEntity>();
	
	protected Tile[][] tabNiveau;
	
	
	
	public LevelDrol(File file, TileSet tile) {
		super(file);
		
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
	public Graphics generateLevelGraphic(int width, int height)
	{
		ArrayList<Integer> entiteAffiche = new ArrayList<Integer>();
		
		
		Graphics graph = new Graphics(width, height);
		graph.setColor(Color.blue);
		graph.fillRect(0, 0, width, height);
		
		//Variable necessaire a l'affichage de la fenetre de scrolling
		int minX, minY, maxX, maxY, largeurTile, hauteurTile, indexTile;
		
		largeurTile = tileSet.getSpriteSource().getSprite(0, 0).getWidth();
		hauteurTile = tileSet.getSpriteSource().getSprite(0, 0).getHeight();
		
		
		minX = xScroll / largeurTile;
		maxX = (xScroll + width)/largeurTile + 1;
		
		minY = yScroll / hauteurTile;
		maxY = (yScroll + height)/hauteurTile + 1;
		
		
		for(int i = minY; i < maxY; i++)
		{
			for(int j = minX; j < maxX; j++)
			{
				//Affiche le decors
				indexTile = tabNiveau[i][j].getIndex();
				graph.drawImage(tileSet.getSpriteSource().getSubImage(indexTile % tileSet.getNbTileLargeur(), indexTile/tileSet.getNbTileLargeur()), j*largeurTile-xScroll, i*hauteurTile-yScroll);
				
				//Affiche les entitees au voisinage de la tile et qui n'ont pas encore ete affiche
				if(!tabNiveau[i][j].getEntiteProche().isEmpty())
				{
					for(int k = 0; k < tabNiveau[i][j].getEntiteProche().size();k++){
						if(tabNiveau[i][j].getEntiteProche().get(k) != null)
						{
							if(!entiteAffiche.contains(tabNiveau[i][j].getEntiteProche().get(k).getId())){
								tabNiveau[i][j].getEntiteProche().get(k).render(graph, (int)tabNiveau[i][j].getEntiteProche().get(k).getX()-xScroll, (int)tabNiveau[i][j].getEntiteProche().get(k).getY()-yScroll);
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

	/**
	 * Vider les arraylist, etc
	 * 
	 */
	public void releaseMemoryOfLevel(){
		super.releaseMemoryOfLevel();
	}
	
}
