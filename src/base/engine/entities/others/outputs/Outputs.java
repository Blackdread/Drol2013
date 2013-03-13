package base.engine.entities.others.outputs;

import base.engine.entities.others.OutputManager;
import base.utils.Timer;

/**
 * An output fires an input on an Object that can receive inputs
 * Outputs may be delayed or triggered immediatly
 * Outputs may be triggered once then destroyed
 * @author Yoann CAPLAIN
 *
 */
public class Outputs implements IFireOnce, IUpdatable{

	//TODO: Un outputs a besoin de savoir a quelle entite elle appartient car elle peut avoir besoin d'execute un input sur 
	// elle-meme (pas sur l'output mais sur l'entite)
	// Soit faire une recherche la liste des entites et chercher celle qui contient cette output, soit avoir 
	// une reference dans l'output pour savoir a qui elle appartient
	/*
	 * Ou vu que l'appel du FireOutput se fait dans le InputsAndOutputs, je peux passer en parametre l'activateur
	 */
	
	/**
	 * If this output has to fire input on his entity owner, it mays do it using this
	 */
	private InputsAndOutputs entityWhoHasThisOutput;
	
	/**
	 * Boolean qui passe a true lorsque cette output doit etre declenche (si timer vaut 0 alors fired immediatly)
	 * sinon l'update verifie ce boolean et update le timer de facon a que l'output soit declenche une fois le timer
	 * arrive a l'event puis redevient false
	 */
	private boolean outputHasBeenDeclenched;
	
	/**
	 * Only trigger once
	 */
	private boolean fireOnce;
	
	/**
	 * If it's a triggerOnce, if true then this entity will be deleted
	 */
	private boolean hasBeenFiredAtleastOnce;
	
	/**
	 * Temps avant que l'output envoie l'output (declenche l'input de l'objet)
	 */
	protected Timer timeBeforeDeclencheTrigger;
	
	/**
	 * Represents the name of the output
	 * Examples: OnMapSpawn, OnTrigger, OnStartTouch, OnGreaterThan, OnEqual, ...
	 */
	protected String nameOfTheOutput;
	
	/**
	 * Represents the name of the input
	 * Examples: Trigger, Close, Enable, Toggle, FlyAway, Test ...
	 */
	protected String nameOfTheInput;
	
	/*
	 * Target Entity
	 * TODO: May becomes a String
	 */
	//private Object objectToDeclencheInput;
	
	/**
	 * Target Entity name
	 */
	/*
	 * J'utilise le nom de l'entite car il est possible que plusieurs entite est le meme nom, ainsi on peut declencher le meme
	 * output sur plusieurs entite. Le probleme est que l'on est oblige de parcourir la liste des entites pour voir si on doit
	 * lui declencher l'input
	 */
	private String nameOfTheEntityToFireInput;
	/*
	 * Peut-etre mettre une liste de parametre et ordonne
	 * sinon il faut mettre plusieurs output et ordonne -> preferable
	 */
	private Object parameter;
	
	public Outputs(){
		timeBeforeDeclencheTrigger = new Timer(0);
		nameOfTheEntityToFireInput = "";
		hasBeenFiredAtleastOnce = false;
		outputHasBeenDeclenched = false;
		fireOnce = false;
		nameOfTheOutput = "";
		nameOfTheInput = "";
		parameter = null;
	}
	
	public Outputs(String nameEntity){
		timeBeforeDeclencheTrigger = new Timer(0);
		nameOfTheEntityToFireInput = nameEntity;
		hasBeenFiredAtleastOnce = false;
		outputHasBeenDeclenched = false;
		fireOnce = false;
		nameOfTheOutput = "";
		nameOfTheInput = "";
		parameter = null;
	}
	
	public Outputs(String nameEntity,int delay){
		timeBeforeDeclencheTrigger = new Timer(delay);
		nameOfTheEntityToFireInput = nameEntity;
		hasBeenFiredAtleastOnce = false;
		outputHasBeenDeclenched = false;
		fireOnce = false;
		nameOfTheOutput = "";
		nameOfTheInput = "";
		parameter = null;
	}
	
	/**
	 * Update timer if the output has been triggered and fire output if timer is timeComplete (delay has passed)
	 * @param delta in milliseconds
	 */
	@Override
	public void update(int delta){
		if(timeBeforeDeclencheTrigger != null && outputHasBeenDeclenched){
			timeBeforeDeclencheTrigger.update(delta);
			
			if(timeBeforeDeclencheTrigger.isTimeComplete()){
				
				if(!fireOnce){
					fireOutput();
					timeBeforeDeclencheTrigger.resetTime();
					outputHasBeenDeclenched = false;
					hasBeenFiredAtleastOnce= true;
				}else{
					if(!hasBeenFiredAtleastOnce){
						fireOutput();
						hasBeenFiredAtleastOnce= true;
					}
					nameOfTheEntityToFireInput = "";
					parameter = null;
					outputHasBeenDeclenched = true;	// A voir si c'est bien true qu'il faut mettre
				}
			}
		}
	}
	
	/**
	 * if timer == 0 or is null -> output is fired
	 * otherwise outputHasBeenDeclenched is set to true then OutputsManager will increment timer
	 * Once an output is fired, this function does NOT reinitialize it -> means the output will be fired except
	 * if the output is deleted
	 * @param self Represent the object that 
	 */
	public void fireOutput(){
		/*
		 * Pas de verification que ca implement bien IInputsAndOutputs, de toute facons c'est necessaire
		 * A voir
		 */
		/*
		if(parameter == null)
			((IInputsAndOutputs)objectToDeclencheInput).fireInputs(nameOfTheInput);
		else
			((IInputsAndOutputs)objectToDeclencheInput).fireInputs(nameOfTheInput, parameter);
		//*/
		if(nameOfTheEntityToFireInput != null)
			if(nameOfTheEntityToFireInput.equalsIgnoreCase("!self")){	// il peut y avoir !self, !activator, ...
				if(parameter == null)
					entityWhoHasThisOutput.fireInputs(nameOfTheInput);
				else
					entityWhoHasThisOutput.fireInputs(nameOfTheInput, parameter);
			}else
				OutputManager.getInstance().triggerInputsOnEntity(nameOfTheEntityToFireInput, nameOfTheInput, parameter);
	}
	
	/**
	 * If the output has been triggered but not fired yet, this will cancel the output and restart timer
	 * Means -> a trigger once may have been triggered but not fired yet, this will "save" that output
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

	public Object getParameter() {
		return parameter;
	}

	public void setNameOfTheOutput(String nameOfTheOutput) {
		this.nameOfTheOutput = nameOfTheOutput;
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

	public boolean isHasBeenFiredAtleastOnce() {
		return hasBeenFiredAtleastOnce;
	}
	/**
	 * 
	 * @param event in milliseconds
	 */
	public void changeEventTime(int event){
		timeBeforeDeclencheTrigger.setEventTime(event);
	}
	
	public Timer getTimeBeforeDeclencheTrigger() {
		return timeBeforeDeclencheTrigger;
	}

	public String getNameOfTheInput() {
		return nameOfTheInput;
	}

	public void setNameOfTheInput(String nameOfTheInput) {
		this.nameOfTheInput = nameOfTheInput;
	}

	public String getNameOfTheEntityToFireInput() {
		return nameOfTheEntityToFireInput;
	}

	public void setNameOfTheEntityToFireInput(String nameOfTheEntityToFireInput) {
		this.nameOfTheEntityToFireInput = nameOfTheEntityToFireInput;
	}

	public void setEntityWhoHasThisOutput(InputsAndOutputs entityWhoHasThisOutput) {
		this.entityWhoHasThisOutput = entityWhoHasThisOutput;
	}

}
