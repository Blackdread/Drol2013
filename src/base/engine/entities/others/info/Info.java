package base.engine.entities.others.info;

import base.engine.entities.others.outputs.PointEntity;

/**
 * 
 * @author Yoann CAPLAIN
 * @TODO Pourrait heriter de BasicEntity de facons a avoir une zone (ainsi un render est possible, meme si dans le principe
 * ça n'a pas besoin d'etre render, c'est juste pour l'editeur et le debugage)
 */
public abstract class Info extends PointEntity {

	public Info(String name) {
		super(name);
	}

}
