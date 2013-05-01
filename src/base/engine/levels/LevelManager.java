package base.engine.levels;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import base.engine.EngineManager;
import base.tile.TilePropriety;
import base.tile.TileSet;
import base.utils.ResourceManager;

/**
 * Contient l'ensemble des level precharger (toutes la partie parametre mais pas les objets etc)
 * @author Yoann CAPLAIN
 *
 */
public class LevelManager {
	
	private HashMap<Integer, Level> hashLevel = new HashMap<Integer, Level>();
	
	private static EngineManager engineManager;
	
	public static LevelManager getInstance(EngineManager engineManager) {
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone) {
            	LevelManager.engineManager = engineManager;
                if (null == instance) {
                    instance = new LevelManager();
                }
            }
        }
        return instance;
	 }
	
	 private LevelManager(){
		 
	 }
	 
	/**
	 * Precharge les levels
	 * @param cheminDossier dossier qui contient les levels
	 */
	public void addLevels(String cheminDossier){
		ArrayList<TilePropriety> tp = new ArrayList<TilePropriety>(0);
		tp.add(new TilePropriety(0, false, "fodfnd"));
		tp.add(new TilePropriety(1, false, "fond"));
		tp.add(new TilePropriety(2, true, "fodnd"));
		tp.add(new TilePropriety(3, false, "fonssd"));
		
		
		TileSet t = new TileSet(ResourceManager.getSpriteSheet("sprite"), tp);
		
		File repertoire = new File(cheminDossier);
		File[] list = repertoire.listFiles();
		for(int i=0; i<list.length;i++)
			if(!list[i].isDirectory() && !list[i].getName().startsWith(".")){
				hashLevel.put(i,new LevelDrol(list[i], t , engineManager));
			}
	}
	
	public Level getLevel(int i){
		return hashLevel.get(i);
	}
	
	public int size(){
		return hashLevel.size();
	}
	
	public void ajouterEntiteNecessaireSurLeLevel(LevelDrol level){
		
	}
	 
	private static LevelManager instance;
	private static Object objetSynchrone = new Object();
	
}
