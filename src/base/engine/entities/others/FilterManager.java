package base.engine.entities.others;

public class FilterManager extends Manager{

	private static FilterManager instance;

	public static FilterManager getInstance() {
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone) {
                if (null == instance) {
                    instance = new FilterManager();
                }
            }
        }
        return instance;
	 }
	
	 private FilterManager(){
		 
	 }
	 
	 
	 private static Object objetSynchrone = new Object();
}
