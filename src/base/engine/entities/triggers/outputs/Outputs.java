package base.engine.entities.triggers.outputs;

import base.utils.Timer;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public class Outputs {

	/**
	 * Boolean qui passe a true lorsque cette output doit etre declenche (si timer vaut 0 alors fired immediatly)
	 * sinon l'uptade verifie ce boolean et update le timer de facon a que l'output soit declenche une fois le timer
	 * arrive a l'event puis redevient false
	 */
	private boolean outputHasBeenDeclenched;
	
	/**
	 * Output qui sera declenche qu'une seul fois
	 */
	private boolean doOnlyOnce;
	/**
	 * Count how many times this output has been fired
	 * Ou faire en sorte que cet Output soit detruit apres avoir ete fired -> preferable
	 */
	private int counter;
	
	/**
	 * Temps avant que l'output envoie l'output (declenche l'input de l'objet)
	 */
	protected Timer TimeBeforeDeclencheTrigger;
	
	/**
	 * Represents the name of the output -> Key of the HashMap/HashTable
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
	 * sinon il faut mettre plusieurs output et ordonne
	 */
	private Object parameter;
	
	public Outputs(int number,Object objectToDeclencheInput,int delay){
		TimeBeforeDeclencheTrigger = new Timer(delay);
		outputNumber = number;
		this.objectToDeclencheInput = objectToDeclencheInput;
	}
	
	public void update(int delta){
		if(TimeBeforeDeclencheTrigger != null)
			TimeBeforeDeclencheTrigger.update(delta);
	}
	
	/**
	 * if timer == 0 or is null -> output is fired
	 * otherwise outputHasBeenDeclenched is set to true then OutputsManager will increment timer
	 * Once an output is fired, this function does NOT reinitialize it -> means the output will be fired except
	 * if the output is deleted
	 */
	public void fireOutput(){
		
	}

	public boolean isDoOnlyOnce() {
		return doOnlyOnce;
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

	public void setDoOnlyOnce(boolean doOnlyOnce) {
		this.doOnlyOnce = doOnlyOnce;
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
}
