package base.engine.entities.others.filters;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import base.engine.EngineManager;
import base.engine.entities.others.outputs.InputsAndOutputs;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public class FilterActivatorClass extends Filter {

	private static final long serialVersionUID = -6166239447649683318L;

	/**
	 * ClassName
	 */
	protected String classNameFilter;
	
	/**
	 * @IMPORTANT NameToCompareTo needs to be given before calling checkFilterConditions()
	 * Name of the activator
	 */
	protected ArrayList<String> NameToCompareTo;
	
	public FilterActivatorClass(EngineManager e,String name, String classname) {
		super(e, name);
		classNameFilter = classname;
		NameToCompareTo = new ArrayList<String>();
	}
	
	public FilterActivatorClass(EngineManager e,String name, boolean negate, String classname){
		super(e, name, negate);
		classNameFilter = classname;
		NameToCompareTo = new ArrayList<String>();
	}
	
	@Override
	public void copy(InputsAndOutputs objetACopier){
		super.copy(objetACopier);
		classNameFilter = ((FilterActivatorClass)objetACopier).classNameFilter;
		NameToCompareTo = ((FilterActivatorClass)objetACopier).NameToCompareTo;
	}
	
	@Override
	public void render(Graphics g, int x, int y) {
		super.render("FilterClass", g, x, y);
	}

	@Override
	public boolean checkFilterConditions() {
		boolean retour = false;
		if(classNameFilter != null && NameToCompareTo != null){
			for(String v : NameToCompareTo)
				if(v != null)
					if(v.equalsIgnoreCase(classNameFilter)){
						retour = true;
						break;
					}
		}
		
		// On gere ici la negation du resultat
		if(negateFilter){
			if(retour)
				return false;
			else
				return true;
		}
		return retour;
	}

	@Override
	public boolean checkFilterConditions(Object entityToFilter) {
		setCompare(entityToFilter);
		return checkFilterConditions();
	}
	
	@Override
	public void setCompare(Object entityToFilter) {
		NameToCompareTo.clear();
		//Class c = Class.forName("maClasse");
		int securite = 0;
		Class<?> tmp = null;	// Pas de bug ??
		try {
			tmp = Class.forName(entityToFilter.getClass().getName());
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		NameToCompareTo.add(entityToFilter.getClass().getName());
		if(tmp != null)
			while((tmp=tmp.getSuperclass()) != null && securite < 100)	// Securite a supprimer plus tard
			{
				NameToCompareTo.add(tmp.getName());
				securite++;
			}
	}
}
