package base.engine.levels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import base.engine.entities.ActiveEntity;

public abstract class Level implements ILevel{
	
	protected final int NB_LIGNE_MINIMUM_POUR_PARAMETRE = 3;
	protected final int NB_OBJET_MIN_SUPPOSER_PAR_LEVEL = 50;
	
	// Nom d'un level
	// lvl_numero.lvl
	
	/**
	 * ligne 0 du fichier
	 */
	protected String nom;
	/**
	 * Le numero dans le nom du fichier
	 */
	protected int numero;
	/**
	 * Permet de savoir combien de ligne il y a dans le fichier
	 * Puis d'avoir une bar d'avancement
	 * ligne 2 du fichier
	 */
	protected int nbLigneFichier;
	/**
	 * Numero de la ligne où le LoadLevelThread est en cours
	 */
	protected int nbLigneFichierLuPourLeLoading;
	/**
	 * ligne 1 du fichier
	 */
	protected boolean reussi;
	
	protected String nomFichier;
	protected String cheminFichier;
	protected boolean isLoadOver;
	
	/**
	 * Afficher sur la vue que la sauvegarde a ete faite.
	 * Par defaut il doit tout le temps etre a false
	 * Il est mis a true, gerer puis remis a false
	 */
	protected boolean isSaveOver;
	
	public Level(File file){
		isLoadOver = false;
		isSaveOver = false;
		
		String chemin = file.getPath();
		this.nomFichier = file.getName();
		this.cheminFichier = chemin.substring(0, (chemin.length()-file.getName().length()));
		this.numero = Integer.valueOf(file.getName().split("_")[1].substring(0, file.getName().split("_")[1].length()-4));
		this.nbLigneFichierLuPourLeLoading = 0;
		//*
		
		//*/
		InputStream ips;
		InputStreamReader ipsr;
		BufferedReader br=null;
		try {
			ips=new FileInputStream(file);
			ipsr=new InputStreamReader(ips);
			br=new BufferedReader(ipsr);
			String ligne;
			for(int i=0;i<NB_LIGNE_MINIMUM_POUR_PARAMETRE;i++){
				ligne=br.readLine();
				if(ligne!=null)
					switch(i){
					case 0:
						this.nom = ligne;
						break;
					case 1:
						this.reussi = Boolean.valueOf(""+ligne);
						break;
					case 2:
						this.nbLigneFichier = Integer.valueOf(""+ligne);
						break;
					}
			}
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
	
	/**
	 * ********* Sera surement supprimer du a l'interface ISave avec le Load(String s) **********  
	 *
	 * Gere ce que lis LoadLevelThread. Ainsi si Level devient une classe abstraite
	 * Cette methode deviendra abstraite aussi et pourra être gérer différement par les classes filles
	 * @param ligne
	 */
	@Deprecated
	public void gererStringLuDansFichier(String ligne){
		
	}
	
	public void loadLevel(){
		/*
		 * juste comme exemple
		 */
		LoadLevelThread load = new LoadLevelThread(this);
		Thread monThread;
		monThread = new Thread(load);
		monThread.start();
	}
	
	public void saveLevel(){
		/*
		 * juste comme exemple
		 */
		SaveLevelThread load = new SaveLevelThread(this);
		Thread monThread;
		monThread = new Thread(load);
		monThread.start();
	}
	
	/**
	 * Vider les arraylist, etc
	 * 
	 */
	public void releaseMemoryOfLevel(){
		this.isLoadOver = false;
		this.isSaveOver = false;
		this.nbLigneFichierLuPourLeLoading = 0;
	}

	public String getNom() {
		return nom;
	}
	public int getNumero() {
		return numero;
	}
	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public String getCheminFichier() {
		return cheminFichier;
	}

	public void setCheminFichier(String cheminFichier) {
		this.cheminFichier = cheminFichier;
	}
	/**
	 * 
	 * @return 1 si nbLigneFichier == 0
	 */
	synchronized public int getNbLigneFichier() {
		if(nbLigneFichier==0)
			return 1;
		return nbLigneFichier;
	}

	public void setNbLigneFichier(int nbLigneFichier) {
		this.nbLigneFichier = nbLigneFichier;
	}
	/**
	 * 
	 * @return 1 si nbLigneFichierLuPourLeLoading == 0
	 */
	synchronized public int getNbLigneFichierLuPourLeLoading() {
		if(nbLigneFichierLuPourLeLoading==0)
			return 1;
		return nbLigneFichierLuPourLeLoading;
	}

	synchronized public void setNbLigneFichierLuPourLeLoading(int nbLigneFichierLuPourLeLoading) {
		this.nbLigneFichierLuPourLeLoading = nbLigneFichierLuPourLeLoading;
	}

	synchronized public boolean isLoadOver() {
		return isLoadOver;
	}

	synchronized public void setLoadOver(boolean is) {
		this.isLoadOver = is;
	}
	synchronized public boolean isSaveOver() {
		return this.isSaveOver;
	}
	synchronized public void setSaveOver(boolean is) {
		this.isSaveOver = is;
	}

	public boolean isReussi() {
		return reussi;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public void setReussi(boolean reussi) {
		this.reussi = reussi;
	}
	
	public String toString(){
		return "nom: "+nom+" lvl: "+numero+" fait: "+reussi;
	}
}
