package base.engine.entities.others.triggers;

import java.util.ArrayList;

import base.engine.entities.BasicEntity;
/**
 * Classe qui verifie si la liste des entites qu'elle possede se trouvent dans la zone definie
 * Ne pas oublier d'initialiser l'array et de le "remplir"
 * 
 * @author Yoann CAPLAIN
 *
 */
public class TriggerObjectInZone extends Trigger {
	
	//protected int x, y;
	
	// TODO Pourra devenir un shape de facons a avoir des trigger de forme quelconque
	protected int width, height;
	
	/**
	 * ArrayList qui contient les entites sur lesquelles le trigger peut agir (pourrait)
	 * Cet array contient les objets dans la zone
	 * Donc l'engine doit ajouter les entites et les enlever quand elles bougent, ce n'est pas au trigger de verifier ca
	 */
	// Peut devenir un HashMap
	protected ArrayList<BasicEntity> arrayEntityToActON = new ArrayList<BasicEntity>();
	
	/**
	 * 
	 * @param name TargetName
	 * @param xx x position
	 * @param yy y position
	 * @param w width
	 * @param h height
	 */
	public TriggerObjectInZone(String name, int xx, int yy, int w, int h) {
		super(name);
		x=xx;
		y=yy;
		width = w;
		height = h;
	}
	
	/*
	 * OnStartTouch() et OnEndTouchAll() et OnEndTouch() sont declenche lors de l'ajout ou du retrait d'une entite
	 * L'update ne verifie pas ca, c'est l'engine qui le gere
	 * @see base.engine.entities.others.outputs.IUpdatable#update(int)
	 */
	@Override
	public void update(int delta) {
		delayBeforeReset.update(delta);
	}
	public ArrayList<String> get_list_outputs(){
		ArrayList<String> list_outputs = new ArrayList<String>();
		list_outputs.addAll(super.get_list_outputs());
		list_outputs.add("OnEndTouch");
		list_outputs.add("OnEndTouchAll");
		list_outputs.add("OnTouching");
		list_outputs.add("OnNotTouching");
		list_outputs.add("OnStartTouch");
		list_outputs.add("OnTrigger");
		
		return list_outputs;
	}
	
	public ArrayList<String> get_list_inputs() {
		ArrayList<String> list_inputs = new ArrayList<String>();
		list_inputs.addAll(super.get_list_inputs());
		list_inputs.add("TouchTest");
		
		return list_inputs;
	}
	
	@Override
	public void fireOutputs(String nameOfOutput) {
		if(nameOfOutput != null){
			if(nameOfOutput.equalsIgnoreCase("OnEndTouch"))
				OnEndTouch();
			else if(nameOfOutput.equalsIgnoreCase("OnEndTouchAll"))
				OnEndTouchAll();
			else if(nameOfOutput.equalsIgnoreCase("OnTouching"))
				OnTouching();
			else if(nameOfOutput.equalsIgnoreCase("OnNotTouching"))
				OnNotTouching();
			else if(nameOfOutput.equalsIgnoreCase("OnStartTouch"))
				OnStartTouch();
			else if(nameOfOutput.equalsIgnoreCase("OnTrigger"))
				OnTrigger();
			else
				super.fireOutputs(nameOfOutput);
		}
	}

	@Override
	public void fireInputs(String nameOfInput) {
		if(nameOfInput != null){
			 if(nameOfInput.equalsIgnoreCase("TouchTest"))
				 TouchTest();
			else
				super.fireInputs(nameOfInput);
		}
		
	}
	
	/*
	 * Inputs
	 */
	/**
	 * Tests if the trigger is being touched and fires an output based on whether the value is true or false
	 * Triggers either the OnTouching or OnNotTouching outputs for whether anything is touching this entity
	 */
	public void TouchTest(){
			if(arrayEntityToActON.size() == 0)
				OnNotTouching();
			else
				OnTouching();
	}

	
	/*
	 * Outputs
	 */
	/**
	 * Fired when an entity stops touching this trigger
	 * Only entities that passed this trigger's filters will cause this output to fire. (activator is the exiting entity)
	 */
	protected void OnEndTouch(){
		if(isTriggerable()){
			fireOutput("OnEndTouch");
			//OnTrigger();	called in removeAnEntityToActON
		}
	}
	/**
	 * Fires when the last entity in the entity's area exits this trigger or when this entity is disabled
	 * Only entities that passed this trigger's filters are considered. (activator is the last exiting entity)
	 */
	protected void OnEndTouchAll(){
		if(isTriggerable()){
			fireOutput("OnEndTouchAll");
			//OnTrigger();	called in removeAnEntityToActON
		}
	}
	/**
	 * Fired when the TouchTest input is called
	 */
	protected void OnTouching(){
		if(isTriggerable()){
			fireOutput("OnTouching");
			OnTrigger();
		}
	}
	/**
	 * Fired when the TouchTest input is called
	 */
	protected void OnNotTouching(){
		if(isTriggerable()){
			fireOutput("OnNotTouching");
			OnTrigger();
		}
	}
	/**
	 * Fired when an entity starts touching this trigger
	 * The touching entity must pass this trigger's filters to cause this output to fire. (activator is the toucher)
	 */
	protected void OnStartTouch(){
		if(isTriggerable()){
			fireOutput("OnStartTouch");
			OnTrigger();
		}
	}
	/**
	 * Fired whenever the trigger is activated. (activator is the activator)
	 * So if OnStartTouch() or OnTouching() or OnEndTouchAll() or ... is fired, this is fired
	 */
	protected void OnTrigger(){
		if(isTriggerable())	// TODO ERREUR ERREUR !!! car dans isTriggerable() le timer est remis a zero hors cette output est appele sur la 2eme fois donc jamais fired
			fireOutput("OnTrigger");
	}
	
	
	
	/**
	 * Devra ajouter l'entite et declencler les outputs OnStartTouch
	 * @param entity to add
	 */
	public void addAnEntityToActON(BasicEntity entity) {
		arrayEntityToActON.add(entity);	// TODO On ajoute seulement si l'entite passe le filter ??
		
		if(testFilter(entity)){	// entity must pass the filter
			setActivatorToAllOutputs(entity);	// TODO faire isTriggerable() ainsi il n'y aurait peut-etre plus besoin de faire la verification des les OnTrigger etc
			OnStartTouch();
		}
	}
	/**
	 * Devra enlever l'entite et declencler les outputs OnEndTouch et/ou OnEndTouchAll
	 * @param entity to remove
	 */
	public void removeAnEntityToActON(BasicEntity entity) {
		if(testFilter(entity)){	// entity must pass the filter
			setActivatorToAllOutputs(entity);
			OnEndTouch();
			if(howManyEntityPassFilter() == 1)
				OnEndTouchAll();
			OnTrigger();
		}
		
		arrayEntityToActON.remove(entity);
	}
	
	protected int howManyEntityPassFilter(){
		int tmp = 0;
		for(BasicEntity v : arrayEntityToActON){
			if(v != null)
				if(testFilter(v))
					tmp++;
		}
		return tmp;
	}
	protected boolean isThereEntityThatPassFilter(){
		for(BasicEntity v : arrayEntityToActON){
			if(v != null)
				if(testFilter(v))
					return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return true if enabled and delay is complete and is not fireOnce
	 */
	protected boolean isTriggerable(){
		if(!enabled)
			return false;
		else{
			if(delayBeforeReset.isTimeComplete()){
				delayBeforeReset.resetTime();
				if(fireOnce){
					if(!hasbeenFired){
						hasbeenFired = true;
						return true;
					}
				}else
					return true;
			}
			return false;
		}	// fin enabled
	}

	@Override
	public void setEnabled(boolean enabled) {
		if(!enabled){
			setActivatorToAllOutputs(null);	// TODO A voir
			OnEndTouchAll();
		}
		this.enabled = enabled;
	}
	@Override
	public void toggle() {
		if(enabled){
			setActivatorToAllOutputs(null);	// TODO A voir
			OnEndTouchAll();
		}
		enabled = !enabled;
	}

	@Override
	public void enable() {
		enabled = true;
	}

	@Override
	public void disable() {
		enabled = false;
	}
}
