package base.engine.entities.triggers.filters;

import java.util.ArrayList;

/**
 * Whether to negate the result of the subfilters, after combining them using the Logic Type chosen.
 * Negating the outcome using the AND logic type means that any subfilter must fail for this filter to pass.
 * Negating the outcome using the OR logic type means that all subfilters must fail for this filter to pass.
 * @author Yoann CAPLAIN
 *
 */
public class FilterMulti extends Filter {

	protected ArrayList<Filter> arrayFilter = new ArrayList<Filter>();
	
	/**
	 * 0: AND (all filters must pass)
	 * 1: OR (any filter must pass)
	 */
	protected boolean logicType;
	
	/**
	 * @see Filter
	 */
	public FilterMulti(String name){
		super(name);
	}
	/**
	 * @see Filter
	 */
	public FilterMulti(String name, boolean negate){
		super(name, negate);
	}
	
	/**
	 * Whether to negate the result of the subfilters, after combining them using the Logic Type chosen.
 	 * Negating the outcome using the AND logic type means that any subfilter must fail for this filter to pass.
 	 * Negating the outcome using the OR logic type means that all subfilters must fail for this filter to pass.
	 */
	@Override
	public boolean checkFilterConditions() {
		boolean retour = false;
		
		for(Filter v : arrayFilter)
			if(v != null)
				if(v.checkFilterConditions()){
					if(!logicType)
						retour = true;
					else{
						retour = true;
						break;
					}
				}else{
					if(!logicType){
						retour = false;
						break;
					}else{
						// Non necessaire car initialise a false
						retour = false;
					}
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

}
