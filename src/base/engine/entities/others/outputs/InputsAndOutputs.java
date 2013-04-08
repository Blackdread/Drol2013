package base.engine.entities.others.outputs;

import java.util.ArrayList;

import base.engine.entities.others.OutputManager;

/**
 * TODO Pour le moment toutes les entites herite de cette classe, plus tard certaines entites auront besoin d'une zone (comme les TriggerObjectInZone)
 * mais d'autres n'auront pas besoin de ca (brush) on pourra donc faire heriter les entiter de 2 classes filles differentes
 * l'une Brush (Tout ce qui est Trigger, etc) et l'autre PointEntity (Logic, Light, Filter, etc)
 * 
 * @author Yoann CAPLAIN
 */
public abstract class InputsAndOutputs implements IInputsAndOutputs, ITargetName, IOrigin, IPosition {

	/**
	 * The targetname that other entities refer to this entity by
	 * Should be unique	-> depends, it cas be useful sometimes to not be unique
	 * Everywhere the name will be supposed not unique
	 */
	protected String name;
	
	public static int currentId = 0;
	protected int id;
	
	protected float x;
	protected float y;
	
	protected float xOrigin;
	protected float yOrigin;
	
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
		removeHierarchy = false;
		id = InputsAndOutputs.currentId;
		InputsAndOutputs.currentId++;
		System.out.println("instance "+InputsAndOutputs.currentId);
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
		// TODO enlever les outputs du manager (ceux qui appartiennent a CETTE entite)
		
		if(array_outputs != null)
			array_outputs.clear();
	}
	
	public void removeChildrenThatEntityHas(){
		// TODO ceux qui sont parent mais c'est pas fait encore et ce ne sera pas cette fonction ou il faut la definir dans
		// IParent
		
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
	
	/**
	 * Don't forget to call it !!!
	 * Ex: If an entity enter a trigger and that entity implements IActivator then the trigger must call this function
	 * and the entity in the parameter
	 * Triggers must call this function if needed
	 * Ex: func_tracktrain and path_track -> see Valve User And Outputs
	 * @param activator
	 */
	public void setActivatorToAllOutputs(InputsAndOutputs activator){
		//*
		for(Outputs v : array_outputs){
			if(v != null)
				v.setActivator(activator);
		}
		//*/	ou faire :	TODO il serait preferable de faire ca mais c'est pas encore fait
		/*
		OutputManager.getInstance().setActivatorForThe_IO_chain(activator);
		//*/
	}
	
	@Override
	public float getX() {
		return x;
	}
	@Override
	public float getY() {
		return y;
	}
	@Override
	public float getXorigin() {
		return xOrigin;
	}

	@Override
	public float getYorigin() {
		return yOrigin;
	}

	@Override
	public void setXorigin(float x) {
		this.xOrigin = x;
	}

	@Override
	public void setYorigin(float y) {
		this.yOrigin = y;
	}

	@Override
	public void setLocationOrigin(float x, float y) {
		this.xOrigin = x;
		this.yOrigin = y;
	}

	@Override
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @param id of the Output
	 */
	public void removeOutputsThatMatchOutputId(final int id){
		for(int i=0 ; i < array_outputs.size();i++){
			if(array_outputs.get(i) != null)
				if(array_outputs.get(i).getId() == id){
					array_outputs.remove(i);
					break;
				}
		}
	}
}
