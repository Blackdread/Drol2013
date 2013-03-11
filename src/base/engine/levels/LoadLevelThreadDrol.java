package base.engine.levels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import base.tile.Tile;
	
public class LoadLevelThreadDrol implements Runnable {
	private Level level;
	
	public LoadLevelThreadDrol(Level level){
		this.level = level;
	}
	
	@Override
	public void run() {
		File file = new File(""+level.getCheminFichier()+""+level.getNomFichier());
		InputStream ips;
		InputStreamReader ipsr;
		BufferedReader br=null;
		try {
			ips=new FileInputStream(file);
			ipsr=new InputStreamReader(ips);
			br=new BufferedReader(ipsr);
			String ligne;
			
			int nombreASauter = level.NB_LIGNE_MINIMUM_POUR_PARAMETRE + ((LevelDrol)level).NB_LIGNE_MINIMUM_POUR_PARAMETRE_POUR_DROL;
			for(int i=0;i<nombreASauter;i++){
				br.readLine();
			}
			
			int i=0;
			String t[];
			while ((ligne=br.readLine())!=null){
				System.out.println("load: "+ligne);
				
				if(( i < ((LevelDrol)level).hauteurNiveau) ){
					t = ligne.split(" ");
					for(int j=0 ; j< ((LevelDrol)level).largeurNiveau ; j++){
						((LevelDrol)level).tabNiveau[i][j] = new Tile();
						((LevelDrol)level).tabNiveau[i][j].setIndex(Integer.valueOf(t[j]));
					}
					i++;
				}
				
				level.setNbLigneFichierLuPourLeLoading(level.getNbLigneFichierLuPourLeLoading()+1);
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(br!=null)
					br.close();
			} catch (IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			level.setNbLigneFichierLuPourLeLoading(level.getNbLigneFichier());
			level.setLoadOver(true);
		}
		System.out.println("Chargement du level fini");
	}

}
