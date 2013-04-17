package base.engine.entities;


public abstract class Tir extends MoveableEntity {
	
	protected BasicEntity expediteur;

	public Tir(String name, int maxLife, BasicEntity e) {
		super(name, maxLife);
		expediteur = e;
	}

	public BasicEntity getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(BasicEntity expediteur) {
		this.expediteur = expediteur;
	}
	

}
