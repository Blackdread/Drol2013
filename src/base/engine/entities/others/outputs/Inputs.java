package base.engine.entities.others.outputs;

/**
 * Pas sur que ce soit garder 
 * Permet a celui qui recoit des outputs de savoir qui lui en envoie et qu'elle input il 
 * declenchera mais on peut le savoir avec une simple fonction de recherche base sur le TargetName
 * @author Yoann CAPLAIN
 *
 */
@Deprecated
public class Inputs {
	
	/**
	 * Entity that fire the output
	 */
	private Object entityThatSendOutput;
	
	/**
	 * Represents the name of the output
	 * Exemples: OnMapSpawn, OnTrigger, OnStartTouch, OnGreaterThan, OnEqual, ...
	 */
	private String nameOfTheOutput;
}
