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
	/**
	 * If this output has to fire input on his entity owner, it mays do it using this
	 */
	private InputsAndOutputs entityWhoHasThisOutput;
	
	/**
	 * The entity (InputsAndOutputs, not the class InputsAndOutputs) must implements IActivator
	 * So the activator is the entity who has triggered this output (or may)
	 */
	private InputsAndOutputs activator;
	
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
	
	/**
	 * Target Entity name
	 */
	/*
	 * J'utilise le nom de l'entite car il est possible que plusieurs entite est le meme nom, ainsi on peut declencher le meme
	 * output sur plusieurs entite. Le probleme est que l'on est oblige de parcourir la liste des entites pour voir si on doit
	 * lui declencher l'input
	 */
	private String nameOfTheEntityToFireInput;

	private Object parameter;
	
	public Outputs(InputsAndOutputs entityWhoHasThisOutput){
		this.entityWhoHasThisOutput = entityWhoHasThisOutput;
		timeBeforeDeclencheTrigger = new Timer(0);
		nameOfTheEntityToFireInput = "";
		hasBeenFiredAtleastOnce = false;
		outputHasBeenDeclenched = false;
		fireOnce = false;
		nameOfTheOutput = "";
		nameOfTheInput = "";
		parameter = null;
	}
	
	public Outputs(InputsAndOutputs entityWhoHasThisOutput, String nameEntity){
		this.entityWhoHasThisOutput = entityWhoHasThisOutput;
		timeBeforeDeclencheTrigger = new Timer(0);
		nameOfTheEntityToFireInput = nameEntity;
		hasBeenFiredAtleastOnce = false;
		outputHasBeenDeclenched = false;
		fireOnce = false;
		nameOfTheOutput = "";
		nameOfTheInput = "";
		parameter = null;
	}
	
	public Outputs(InputsAndOutputs entityWhoHasThisOutput, String nameEntity,int delay){
		this.entityWhoHasThisOutput = entityWhoHasThisOutput;
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
					//hasBeenFiredAtleastOnce = false;	// C bien false ? ou il sert a rien vu que c'est pour le IFireOnce
				}else{
					if(!hasBeenFiredAtleastOnce){
						fireOutput();
						hasBeenFiredAtleastOnce= true;
					}
					nameOfTheEntityToFireInput = "";
					parameter = null;
					outputHasBeenDeclenched = false;	// A voir si c'est bien true ou false qu'il faut mettre
				}
			}
		}
	}
	
	/**
	 * if timer == 0 -> output is fired
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
		if(timeBeforeDeclencheTrigger.isTimeComplete()){	// si l'eventTime est 0 alors true de toute facons
			if(fireOnce && hasBeenFiredAtleastOnce)
				return;
			if(fireOnce)
				hasBeenFiredAtleastOnce= true;
			
			if(nameOfTheEntityToFireInput != null)
				if(nameOfTheEntityToFireInput.equalsIgnoreCase("!self")){	// il peut y avoir !self, !activator, ...
					if(entityWhoHasThisOutput != null){
						if(parameter == null)
							entityWhoHasThisOutput.fireInputs(nameOfTheInput);
						else
							entityWhoHasThisOutput.fireInputs(nameOfTheInput, parameter);
					}else
						System.err.println("In Outputs.java entityWhoHasThisOutput = null");
				}else if(nameOfTheEntityToFireInput.equalsIgnoreCase("!activator")){
					if(activator != null){
						// TODO A voir s'il faut passer l'activator afin que l'activator soit donnee dans tout les outputs
						// suivant mais ca peut faire des choses pas forcement souhaitait (imprevue)
						if(parameter == null){	
							activator.fireInputs(nameOfTheInput);
						}else{
							activator.fireInputs(nameOfTheInput, parameter);
						}
					}else
						System.err.println("In Outputs.java activator = null");
				}else
					OutputManager.getInstance().triggerInputsOnEntity(nameOfTheEntityToFireInput, nameOfTheInput, parameter);
		}else
			outputHasBeenDeclenched = true;
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

	/**
	 * TODO activator peut devenir une "Queue" => si par exemple cette output a un delay mais que d'autres entite veulent
	 * declencher cette output, ils peuvent ecraser l'activator et on n'a plus le bon activator pour l'ancienne activation
	 * Donc il faudrait mettre une sorte de Queue pour declencher cet output avec les bon parametre (bon activator, bon 
	 * parameter et bon targetName et etc)
	 * @param activator
	 * 
	 * TODO voir si faut aussi passer l'activator a la prochaine entite car la I/O chain peut etre plus longue que ca et les
	 * entites suivante n'auront pas le bon Activator
	 */
	public void setActivator(InputsAndOutputs activator) {
		this.activator = activator;
	}

	@Override
	public boolean isHasbeenFired() {
		return hasBeenFiredAtleastOnce;
	}

	@Override
	public void setHasbeenFired(boolean hasBeenFired) {
		hasBeenFiredAtleastOnce = hasBeenFired;
	}

}
