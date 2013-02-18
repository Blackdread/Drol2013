package base.engine.levels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoadLevelThread implements Runnable {
	
	private Level level;
	
	public LoadLevelThread(Level level){
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
			while ((ligne=br.readLine())!=null){
				/*
				 * Parti non fini, c'est juste pour tester
				 */
				System.out.println("load: "+ligne);
				level.setNbLigneFichierLuPourLeLoading(level.getNbLigneFichierLuPourLeLoading()+1);
				level.gererStringLuDansFichier(ligne);
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
