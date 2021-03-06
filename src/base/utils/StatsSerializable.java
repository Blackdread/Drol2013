package base.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Des stats (nb de fois morts, nb de fois gagner, perdu, ...)
 * Peut aussi servir pour sauvegarder des scores, etc
 * @author Yoann CAPLAIN
 *
 */
public class StatsSerializable implements Serializable{

	private static final long serialVersionUID = -5606180233240844408L;

	private String fileLocation;
	
	/**
	 * Le 1er String peut etre le nom du stats (Nb de morts, Nb de fois gagn�, Nb de personne sauv�, temps de jeu total, etc)
	 * Le 2eme est la valeur (int, float, double, ...)
	 */
	private HashMap<String, String> hashStats = new HashMap<String, String>();
	
	public StatsSerializable(){
	}
	public StatsSerializable(String fileLocation){
		this.fileLocation = fileLocation;
	}
	
	/**
	 * Sauvegarde les stats dans fileLocation
	 */
	public void saveStats(){
        try{
            FileOutputStream fos = new FileOutputStream(fileLocation);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(hashStats);
            //oos.writeObject(this);

            oos.close();
        }catch(Exception e){e.printStackTrace();}
	}
	/**
	 * Charge les stats dans le hasmap
	 */
	public void loadStats(){
        try{
            FileInputStream fos = new FileInputStream(fileLocation);
            ObjectInputStream oos = new ObjectInputStream(fos);
            
            hashStats = (HashMap<String, String>) oos.readObject();
            //StatsSerializable s = oos.readObject();

            oos.close();
        }catch(Exception e){e.printStackTrace();}
	}
	
	/**
	 * Ajoute au hashMap le stats en ecrasant ce qui y avait avant
	 * @param name
	 * @param value
	 */
	public void addStat(String name, String value){
		hashStats.put(name, value);
	}
	
	/**
	 * Ajoute a la valeur actuel la value passe en parametre
	 * @param name nom du stat
	 * @param value la valeur en int
	 */
	public void addStat(String name, int value){
		//hashStats.put(name, value);
		String val = hashStats.get(""+name);
		
		try{
			if(val != null)
				hashStats.put(""+name, ""+(Integer.valueOf(val) + value));
			else
				hashStats.put(""+name, ""+value);

		}catch(Exception e){}
	}
	
	public String getStat(String name){
		return hashStats.get(name);
	}
	public HashMap<String, String> getHashStats(){
		return hashStats;
	}
}
