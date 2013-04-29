package base.engine.entities;

import base.engine.EngineManager;


public abstract class Tir extends MoveableEntity {
	
	private static final long serialVersionUID = -8447422863970866892L;
	
	protected BasicEntity expediteur;

	public Tir(String name, EngineManager en, int maxLife, BasicEntity e) {
		super(name, en, maxLife);
		expediteur = e;
	}
	
	@Override
	public void init() {
		
	}

	public BasicEntity getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(BasicEntity expediteur) {
		this.expediteur = expediteur;
	}
	

}
