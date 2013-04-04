package base.engine.entities.others.filters;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

/**
 * @IMPORTANT unfinished
 * @author Yoann CAPLAIN
 */
public class FilterDamageType extends Filter {

	/**
	 * Contains damage type to filter
	 */
	ArrayList<Integer> arrayDamageTypeToFilter;
	
	public FilterDamageType(String name) {
		super(name);
		arrayDamageTypeToFilter = new ArrayList<Integer>();
	}
	public FilterDamageType(String name, ArrayList<Integer> array) {
		super(name);
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
