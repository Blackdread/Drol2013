package base.engine.entities.others.outputs;

import java.util.ArrayList;

/**
 * 
 * @author Yoann CAPLAIN
 */
public abstract class InputsAndOutputs implements IInputsAndOutputs, ITargetName {

	/**
	 * The targetname that other entities refer to this entity by
	 * Should be unique
	 * Everywhere the name will be supposed not unique
	 */
	protected String name;
	
	/**
	 * true -> entity will be deleted
	 * OnKilled() has already been triggered
	 */
	protected boolean removeEntityFromWorld;
	
	/**
	 * true -> Removes this entity and all its children from the world
	 */
	protected boolean removeHierarchy;
	
	protected ArrayList<Outputs> array_outputs = new ArrayList<Outputs>();
	
	public InputsAndOutputs(String name){
		this.name = name;
		removeEntityFromWorld = false;
	}
	
	public ArrayList<String> get_list_outputs(){
		ArrayList<String> list_outputs = new ArrayList<String>();
		list_outputs.add("OnUser1");
		list_outputs.add("OnUser2");
		list_outputs.add("OnUser3");
		list_outputs.add("OnUser4");
		list_outputs.add("OnKilled");
		
		return list_outputs;
	}
	
	public ArrayList<String> get_list_inputs() {
		ArrayList<String> list_inputs = new ArrayList<String>();
		list_inputs.add("FireUser1");
		list_inputs.add("FireUser2");
		list_inputs.add("FireUser3");
		list_inputs.add("FireUser4");
		list_inputs.add("Kill");
		list_inputs.add("KillHierarchy");
		
		return list_inputs;
	}
	
	public ArrayList<Outputs> get_array_outputs() {
		return array_outputs;
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.IInputsAndOutputs#fireOutputs(java.lang.String)
	 */
	@Override
	public void fireOutputs(String nameOfOutput){
		if(nameOfOutput.equalsIgnoreCase("OnUser1"))
			fireOutput("OnUser1");
		else if(nameOfOutput.equalsIgnoreCase("OnUser2"))
			fireOutput("OnUser2");
		else if(nameOfOutput.equalsIgnoreCase("OnUser3"))
			fireOutput("OnUser3");
		else if(nameOfOutput.equalsIgnoreCase("OnUser4"))
			fireOutput("OnUser4");
		else if(nameOfOutput.equalsIgnoreCase("OnKilled"))
			OnKilled();
	}
	
	@Override
	public void fireOutput(final String nameOfOutput){
		for(Outputs v : array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase(""+nameOfOutput))
					v.fireOutput();
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.IInputsAndOutputs#fireInputs(java.lang.String)
	 */
	@Override
	public void fireInputs(final String nameOfInput){
		if(nameOfInput.equalsIgnoreCase("FireUser1"))
			fireOutput("OnUser1");
		else if(nameOfInput.equalsIgnoreCase("FireUser2"))
			fireOutput("OnUser2");
		else if(nameOfInput.equalsIgnoreCase("FireUser3"))
			fireOutput("OnUser3");
		else if(nameOfInput.equalsIgnoreCase("FireUser4"))
			fireOutput("OnUser4");
		else if(nameOfInput.equalsIgnoreCase("Kill"))
			kill();
		else if(nameOfInput.equalsIgnoreCase("KillHierarchy"))
			killHierarchy();
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.IInputsAndOutputs#fireInputs(java.lang.String, java.lang.Object)
	 */
	@Override
	public void fireInputs(final String nameOfInput, Object parameter){
		
	}
	
	public void addOutput(Outputs output){
		output.setEntityWhoHasThisOutput(this);
		if(array_outputs != null)
			array_outputs.add(output);
	}
	
	@Override
	public String getTargetName() {
		return name;
	}
	@Override
	public void setTargetName(String name) {
		this.name = name;
	}

	/**
	 * Clear the array that contains outputs and remove outputs from OutputsManager
	 */
	public void removeAllOutputsThatEntityHas(){
		// TODO enlever les outputs du manager (ceux qui appartiennent a cette entite)
		
		if(array_outputs != null)
			array_outputs.clear();
	}
	
	public void removeChildrenThatEntityHas(){
		// TODO
		
	}
	
	// Inputs
	/**
	 * Set a boolean to true to remove this entity later and fire outputs OnKilled
	 * Remove all outputs that matches outputs of this entity
	 * BE CAREFUL outputs with a delay won't be fired because it mays be deleted before it fires
	 */
	public void kill(){
		OnKilled();
		removeAllOutputsThatEntityHas();
		removeEntityFromWorld = true;
	}
	
	/**
	 * Delete this entity and its children and fire output OnKilled (and children's outputs OnKilled)
	 * @see InputsAndOutputs#kill()
	 */
	public void killHierarchy(){
		kill();
		removeChildrenThatEntityHas();
		removeHierarchy = true;
	}
	
	// Outputs
	/**
	 * Fired when the entity is killed and removed from the game
	 */
	private void OnKilled(){
		fireOutput("OnKilled");
	}

	public boolean isRemoveEntityFromWorld() {
		return removeEntityFromWorld;
	}

	public boolean isRemoveHierarchy() {
		return removeHierarchy;
	}
	
}
