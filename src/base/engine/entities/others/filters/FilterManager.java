package base.engine.entities.others.filters;

import java.util.ArrayList;


public class FilterManager {

	private static FilterManager instance;
	
	private ArrayList<Filter> arrayFilterInstancie = new ArrayList<Filter>();
	/*
	 * Je sais pas trop si c'est interessant de faire ca comme ca. Ca permet de chercher par rapport au targetname
	 */
	//private static HashMap<String, Filter> hashTrigger = new HashMap<String, Filter>();
	
	public static FilterManager getInstance() {
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone__) {
                if (null == instance) {
                    instance = new FilterManager();
                }
            }
        }
        return instance;
	    }
	
	synchronized public void addFilter(Filter a){
		if(arrayFilterInstancie != null)
			arrayFilterInstancie.add(a);
	}
	synchronized public void getFilterAt(int a){
		if(arrayFilterInstancie != null)
			if(arrayFilterInstancie.size() > a)
				arrayFilterInstancie.get(a);
	}
	
	 private FilterManager(){
		 
	 }
	 
	 private static Object objetSynchrone__;
}
