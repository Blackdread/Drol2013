package base.engine.entities.others.info;

import base.engine.EngineManager;
import base.engine.entities.others.outputs.PointEntity;

/**
 * 
 * @author Yoann CAPLAIN
 * @TODO Pourrait heriter de BasicEntity de facons a avoir une zone (ainsi un render est possible, meme si dans le principe
 * �a n'a pas besoin d'etre render, c'est juste pour l'editeur et le debugage)
 */
public abstract class Info extends PointEntity {

	private static final long serialVersionUID = -3244342247079846755L;

	public Info(EngineManager e,String name) {
		super(e,name);
	}

}
