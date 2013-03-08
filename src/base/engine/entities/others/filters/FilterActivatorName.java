package base.engine.entities.others.filters;

/**
 *  It is a filter that filters by the name of the activator.
 *  The name to compare to needs to be given before checking filter
 *  
 *  To filter int, long, etc you need to use LogicCompare
 *  
 * @author Yoann CAPLAIN
 *
 */
public class FilterActivatorName extends Filter{

	/**
	 * The class name to filter by. If the filter mode is Allow, only entities whose class name matches 
	 * the given string will pass the filter. If the filter mode is Disallow, all entities 
	 * EXCEPT those whose class name matches the given string will pass the filter.
	 */
	protected String classNameFilter;
	
	/**
	 * @IMPORTANT NameToCompareTo needs to be given before calling checkFilterConditions()
	 * Name of the activator
	 */
	protected String NameToCompareTo;
	
	/**
	 * @see Filter
	 * @param String classname Name this filter will compare to
	 */
	public FilterActivatorName(String name, String Classname){
		super(name);
	}
	/**
	 * @see Filter
	 * @param negate invert the filter
	 * @param String classname Name this filter will compare to
	 */
	public FilterActivatorName(String name, boolean negate, String Classname){
		super(name, negate);
	}
	
	/**
	 * Compare names and ignore case
	 * Peut-etre qu'il faudrait faire en sorte que le filter aille chercher lui meme NameToCompareTo
	 * Mais pour ca il faut que le filter est un pointeur vers ce qui l'active
	 */
	@Override
	public boolean checkFilterConditions() {
		boolean retour = false;
		if(classNameFilter != null && NameToCompareTo != null){
			retour = classNameFilter.equalsIgnoreCase(NameToCompareTo);
		}
		
		// On gere ici la negation du resultat
		if(this.negateFilter){
			if(retour)
				return false;
			else
				return true;
		}
		return retour;
	}
	
	public void setNameToCompareTo(String classNameToCompareTo) {
		this.NameToCompareTo = classNameToCompareTo;
	}
}