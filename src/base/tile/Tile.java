package base.tile;

import java.util.ArrayList;
import base.engine.entities.BasicEntity;

public class Tile {
	protected int index;
	
	protected ArrayList<BasicEntity> entiteProche = new ArrayList<BasicEntity>();
	
	public Tile()
	{
		index = -1;
	}
	
	public Tile(int i){
		index = i;
	}
	
	public void enleverEntite(int idEntite){
		for(int i=0;i<entiteProche.size();i++)
			if(entiteProche.get(i).getId() == idEntite){
				System.out.println("enlever entite "+entiteProche.get(i).getId()+" pos "+i+" taille"+entiteProche.size());
				entiteProche.remove(i);
				System.out.println("enlever entite pos "+i+" taille"+entiteProche.size());
				break;
			}
	}
	
	
	public void ajouterEntite(BasicEntity a){
		entiteProche.add(a);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int id) {
		this.index = id;
	}

	public ArrayList<BasicEntity> getEntiteProche() {
		return entiteProche;
	}

	public void setEntiteProche(ArrayList<BasicEntity> entiteProche) {
		this.entiteProche = entiteProche;
	}
}
