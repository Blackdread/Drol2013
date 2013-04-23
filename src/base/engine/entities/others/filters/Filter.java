package base.engine.entities.others.filters;

import java.util.ArrayList;

import base.engine.EngineManager;
import base.engine.entities.others.outputs.PointEntity;

/**
 * If the filter mode is Allow, only entities whose matches the given conditions 
 * will pass the filter. If the filter mode is Disallow, all entities EXCEPT those whose matches 
 * conditions will pass the filter
 * @author Yoann CAPLAIN
 *
 */
public abstract class Filter extends PointEntity{
	
	/**
	 * Inverts the filter, making the specified concept fail and all others pass
	 * @value true -> invert the filter
	 * @Default value false
	 */
	protected boolean negateFilter = false;
	
	/**
	 * 
	 * @param String targetname that other entities refer to this entity by
	 */
	public Filter(EngineManager e,String name){
		super(e, name);
	}
	/**
	 * 
	 * @param String targetname that other entities refer to this entity by
	 * @param Boolean negate invert result
	 */
	public Filter(EngineManager e, String name,boolean negate){
		super(e, name);
		this.negateFilter = negate;
	}
	
	public ArrayList<String> get_list_outputs(){
		ArrayList<String> list_outputs = new ArrayList<String>();
		list_outputs.addAll(super.get_list_outputs());
		list_outputs.add("OnPass");
		list_outputs.add("OnFail");
		
		return list_outputs;
	}
	
	public ArrayList<String> get_list_inputs() {
		ArrayList<String> list_inputs = new ArrayList<String>();
		list_inputs.addAll(super.get_list_inputs());
		list_inputs.add("TestActivator");
		
		return list_inputs;
	}
	
	@Override
	public void fireOutputs(String nameOfOutput) {
		if(nameOfOutput != null){
			if(nameOfOutput.equalsIgnoreCase("OnPass"))
				OnPass();
			else if(nameOfOutput.equalsIgnoreCase("OnFail"))
				OnFail();
			else
				super.fireOutputs(nameOfOutput);
		}
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.InputsAndOutputs#fireInputs(java.lang.String)
	 */
	@Override
	public void fireInputs(final String nameOfInput){
		if(nameOfInput != null){
			
			super.fireInputs(nameOfInput);
		}
		
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.InputsAndOutputs#fireInputs(java.lang.String, java.lang.Object)
	 */
	@Override
	public void fireInputs(final String nameOfInput, Object parameter) {
		if(nameOfInput != null && parameter != null){
			if(nameOfInput.equalsIgnoreCase("TestActivator"))
				testActivator(parameter);
			else
				super.fireInputs(nameOfInput, parameter);		
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public abstract boolean checkFilterConditions();

	/**
	 * 
	 * @param entityToFilter The entity that triggered the trigger
	 * @return 
	 */
	public abstract boolean checkFilterConditions(Object entityToFilter);
	
	/**
	 * Will set what the filter needs to set with that entity (compare int or compare String ...)
	 * @param entityToFilter
	 */
	public abstract void setCompare(Object entityToFilter);
	
	// Inputs
	protected void testActivator(Object parameter){ 
		boolean re = checkFilterConditions(parameter);
		if(re)
			OnPass();
		else
			OnFail();
	}
	
	// Outputs
	/**
	 * Fired if in response to the TestActivator input.
	 */
	protected void OnPass(){
		fireOutput("OnPass");
	}
	/**
	 * Fired if in response to the TestActivator input.
	 */
	protected void OnFail(){
		fireOutput("OnFail");
	}
	
	public boolean isNegateFilter() {
		return negateFilter;
	}
	public void setNegateFilter(boolean negateFilter) {
		this.negateFilter = negateFilter;
	}
}
