package base.tile;


public class TilePropriety {
	private int index;
	private boolean mur;
	private String nom;
		
	public TilePropriety(int ind, boolean m, String nom){
		index = ind;
		mur = m;
		this.nom = nom;
	}

	public int getIndex() {
		return index;
	}

	public boolean isMur() {
		return mur;
	}

	public String getNom() {
		return nom;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setMur(boolean mur) {
		this.mur = mur;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
		

}
