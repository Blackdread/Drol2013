package base.engine.entities.others.filters;

import org.newdawn.slick.Graphics;

import base.engine.EngineManager;
import base.engine.entities.others.outputs.ITeam;
import base.engine.entities.others.outputs.InputsAndOutputs;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public class FilterActivatorTeam extends Filter {

	private static final long serialVersionUID = 8371297026993903460L;

	protected int team;
	
	/**
	 * @IMPORTANT teamToCompareTo needs to be given before calling checkFilterConditions()
	 * Team of the activator
	 */
	protected int teamToCompareTo;
	
	public FilterActivatorTeam(EngineManager e,String name, int team2) {
		super(e,name);
		team = team2;
	}
	
	public FilterActivatorTeam(EngineManager e,String name, boolean negate, int team2){
		super(e,name, negate);
		team = team2;
	}
	
	@Override
	public void copy(InputsAndOutputs objetACopier){
		super.copy(objetACopier);
		team = ((FilterActivatorTeam)objetACopier).team;
	}
	
	@Override
	public void render(Graphics g, int x, int y) {
		//super.render("FilterTeam", g, x, y);
	}

	@Override
	public boolean checkFilterConditions() {
		boolean retour = (team == teamToCompareTo) ? true : false;
		
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
		if(entityToFilter instanceof ITeam)
			teamToCompareTo = ((ITeam)entityToFilter).getTeam();
		return checkFilterConditions();
	}

	@Override
	public void setCompare(Object entityToFilter) {
		if(entityToFilter instanceof ITeam)
			teamToCompareTo = ((ITeam)entityToFilter).getTeam();
	}

}
