package base.engine.entities.others.info;

/**
 * info_target is a point entity available in all Source games. 
 * It can be used by many different entities, and is a great generic target for any entity 
 * that needs to point, shoot, look at, or walk to a specific target.
 * @author Yoann CAPLAIN
 *
 */
public class InfoTarget extends Info {
	// TODO La classe InputsAndOutputs devrait avoir une position car toutes les entites ont une position
	// info target a besoin d'une position
	
	public InfoTarget(String name) {
		super(name);
	}

}
