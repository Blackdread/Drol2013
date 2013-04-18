package base.engine.levels;

import java.io.File;
import java.util.ArrayList;

/**
 * Contient l'ensemble des level precharger (toutes la partie parametre mais pas les objets etc)
 * @author Yoann CAPLAIN
 *
 */
public class LevelManager {
	
	private ArrayList<Level> arrayLevel = new ArrayList<Level>();
	
	public static LevelManager getInstance() {
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone) {
                if (null == instance) {
                    instance = new LevelManager();
                }
            }
        }
        return instance;
	 }
	
	 private LevelManager(){
		 
	 }
	 
	 
	private void addLevels(String cheminDossier){
		File repertoire = new File(cheminDossier);
		File[] list = repertoire.listFiles();
		for(int i=0; i<list.length;i++)
			if(!list[i].isDirectory()){
				arrayLevel.add(new LevelDrol(list[i]));
			}
	}
	 
	private static LevelManager instance;
	private static Object objetSynchrone = new Object();
	
}
