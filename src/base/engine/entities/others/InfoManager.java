package base.engine.entities.others;


public class InfoManager extends Manager{

	private static InfoManager instance;
	
	public static InfoManager getInstance() {
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone) {
                if (null == instance) {
                    instance = new InfoManager();
                }
            }
        }
        return instance;
	 }
	
	 private InfoManager(){
		 
	 }
	 
	 private static Object objetSynchrone = new Object();

}
