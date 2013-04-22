package base.engine.entities;

import base.engine.EngineManager;


public abstract class Tir extends MoveableEntity {
	
	protected BasicEntity expediteur;

	public Tir(String name, EngineManager en, int maxLife, BasicEntity e) {
		super(name, en, maxLife);
		expediteur = e;
	}

	public BasicEntity getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(BasicEntity expediteur) {
		this.expediteur = expediteur;
	}
	

}
