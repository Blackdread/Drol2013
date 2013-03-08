package base.engine.entities.others.outputs;

import base.utils.Timer;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public class Outputs implements IFireOnce{

	/**
	 * Boolean qui passe a true lorsque cette output doit etre declenche (si timer vaut 0 alors fired immediatly)
	 * sinon l'uptade verifie ce boolean et update le timer de facon a que l'output soit declenche une fois le timer
	 * arrive a l'event puis redevient false
	 */
	private boolean outputHasBeenDeclenched;
	
	/**
	 * Only trigger once
	 */
	private boolean fireOnce;
	
	/**
	 * Count how many times this output has been fired
	 * Ou faire en sorte que cet Output soit detruit apres avoir ete fired -> preferable
	 */
	//private int counter;
	
	/**
	 * Temps avant que l'output envoie l'output (declenche l'input de l'objet)
	 */
	protected Timer timeBeforeDeclencheTrigger;
	
	/**
	 * Represents the name of the output
	 * Exemples: OnMapSpawn, OnTrigger, OnStartTouch, OnGreaterThan, OnEqual, ...
	 */
	protected String nameOfTheOutput;
	
	/**
	 * Ce nombre represente l'input a declenche sur le Target Entity
	 */
	private int outputNumber;
	/**
	 * Target Entity
	 */
	private Object objectToDeclencheInput;
	/*
	 * Peut-etre mettre une liste de parametre et ordonne
	 * sinon il faut mettre plusieurs output et ordonne -> preferable
	 */
	private Object parameter;
	
	public Outputs(int number,Object objectToDeclencheInput,int delay){
		timeBeforeDeclencheTrigger = new Timer(delay);
		outputNumber = number;
		this.objectToDeclencheInput = objectToDeclencheInput;
	}
	
	/**
	 * Update timer if the output has been triggered and fire output if timer is timeComplete (delay has passed)
	 * @param delta in milliseconds
	 */
	public void update(int delta){
		if(timeBeforeDeclencheTrigger != null && outputHasBeenDeclenched){
			timeBeforeDeclencheTrigger.update(delta);
			
			if(timeBeforeDeclencheTrigger.isTimeComplete()){
				fireOutput();
				
				timeBeforeDeclencheTrigger.resetTime();
			}
		}
	}
	
	/**
	 * if timer == 0 or is null -> output is fired
	 * otherwise outputHasBeenDeclenched is set to true then OutputsManager will increment timer
	 * Once an output is fired, this function does NOT reinitialize it -> means the output will be fired except
	 * if the output is deleted
	 */
	public void fireOutput(){
		
	}
	
	/**
	 * If the output has been triggered but not fired yet, this will cancel the output and restart timer
	 */
	public void cancelOutput(){
		if(outputHasBeenDeclenched){
			outputHasBeenDeclenched = false;
			timeBeforeDeclencheTrigger.resetTime();
		}
	}
	
	/**
	 * Represents the name of the output -> Key of the HashMap/HashTable
	 * Exemples: OnMapSpawn, OnTrigger, OnStartTouch, OnGreaterThan, OnEqual, ...
	 * @return
	 */
	public String getNameOfTheOutput() {
		return nameOfTheOutput;
	}

	public int getOutputNumber() {
		return outputNumber;
	}

	public Object getObjectToDeclencheInput() {
		return objectToDeclencheInput;
	}

	public Object getParameter() {
		return parameter;
	}

	public void setNameOfTheOutput(String nameOfTheOutput) {
		this.nameOfTheOutput = nameOfTheOutput;
	}

	public void setOutputNumber(int outputNumber) {
		this.outputNumber = outputNumber;
	}

	public void setObjectToDeclencheInput(Object objectToDeclencheInput) {
		this.objectToDeclencheInput = objectToDeclencheInput;
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}

	@Override
	public boolean isFireOnce() {
		return fireOnce;
	}

	@Override
	public void setFireOnce(boolean fireOnce) {
		this.fireOnce = fireOnce;
	}
}
