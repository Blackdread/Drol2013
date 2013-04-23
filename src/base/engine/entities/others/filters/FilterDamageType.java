package base.engine.entities.others.filters;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import base.engine.EngineManager;

/**
 * @IMPORTANT unfinished
 * @author Yoann CAPLAIN
 */
public class FilterDamageType extends Filter {

	/**
	 * Contains damage type to filter
	 */
	ArrayList<Integer> arrayDamageTypeToFilter;
	
	public FilterDamageType(EngineManager e,String name) {
		super(e,name);
		arrayDamageTypeToFilter = new ArrayList<Integer>();
	}
	public FilterDamageType(EngineManager e,String name, ArrayList<Integer> array) {
		super(e,name);
		arrayDamageTypeToFilter = array;
	}

	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean checkFilterConditions() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean checkFilterConditions(Object entityToFilter) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setCompare(Object entityToFilter) {
		// TODO Auto-generated method stub
		
	}


}
