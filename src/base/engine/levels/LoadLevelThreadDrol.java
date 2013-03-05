
	package base.engine.levels;

	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	
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
			
			int nombreASauter = level.NB_LIGNE_MINIMUM_POUR_PARAMETRE + level.NB_OBJET_MIN_SUPPOSER_PAR_LEVEL;
			for(int i=0;i<nombreASauter;i++){
				ligne=br.readLine();
			}
			int i=0;
			while ((ligne=br.readLine())!=null){
				System.out.println("load: "+ligne);
				
				if(( i < ((LevelDrol)level).hauteurNiveau) ){
					String t[] = ligne.split(" ");
					for(int j=0 ; j< ((LevelDrol)level).largeurNiveau ; j++){
						((LevelDrol)level).tabNiveau[i][j] = Integer.valueOf(t[j]);
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
