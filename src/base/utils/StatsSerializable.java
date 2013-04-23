package base.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class StatsSerializable implements Serializable{

	private String fileLocation;
	
	/**
	 * Le 1er String peut etre le nom du stats (Nb de morts, Nb de fois gagné, Nb de personne sauvé, temps de jeu total, etc)
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
            //this = oos.readObject();

            oos.close();
        }catch(Exception e){e.printStackTrace();}
	}
	
	public void addStat(String name, String value){
		hashStats.put(name, value);
	}
	public String getStat(String name){
		return hashStats.get(name);
	}
	public HashMap<String, String> getHashStats(){
		return hashStats;
	}
}
