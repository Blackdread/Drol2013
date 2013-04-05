package base.tile;

import java.util.ArrayList;
import java.util.HashMap;

import base.engine.entities.BasicEntity;

public class Tile {
	protected int index;
	
	protected ArrayList<BasicEntity> entiteProche = new ArrayList<BasicEntity>();
	//protected HashMap<Integer, BasicEntity> entiteProche = new HashMap<Integer, BasicEntity>();
	
	public Tile()
	{
		index = -1;
	}
	
	public Tile(int i){
		index = i;
	}
	
	public void enleverEntite(int idEntite){
		//*
		for(int i=0;i<entiteProche.size();i++)
			if(entiteProche.get(i).getId() == idEntite){
				System.out.println("enlever entite "+entiteProche.get(i).getId()+" pos "+i+" taille"+entiteProche.size());
				entiteProche.remove(i);
				System.out.println("enlever entite pos "+i+" taille"+entiteProche.size());
				return;
				//break;
			}
		//entiteProche.trimToSize();
		//*/
		/*
		entiteProche.remove(idEntite);
		//*/
	}
	
	
	public void ajouterEntite(BasicEntity a){
		entiteProche.add(a);
		//entiteProche.put(a.getId(), a);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int id) {
		this.index = id;
	}

	/*
	public HashMap<Integer, BasicEntity> getEntiteProche() {
		return entiteProche;
	}

	public void setEntiteProche(HashMap<Integer, BasicEntity> entiteProche) {
		this.entiteProche = entiteProche;
	}//*/
	//*
	public ArrayList<BasicEntity> getEntiteProche() {
		return entiteProche;
	}

	public void setEntiteProche(ArrayList<BasicEntity> entiteProche) {
		this.entiteProche = entiteProche;
	}
	//*/
}
