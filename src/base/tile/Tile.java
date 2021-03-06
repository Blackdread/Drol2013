package base.tile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import base.engine.entities.BasicEntity;

@SuppressWarnings("unused")
public class Tile implements Serializable{
	
	private static final long serialVersionUID = 8903044779751945594L;

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
	
	synchronized public void enleverEntite(int idEntite){
		//*
		for(int i=0;i<entiteProche.size();i++)
			if(entiteProche.get(i).getId() == idEntite){
				entiteProche.remove(i);
				//return;
				break;
			}
		entiteProche.trimToSize();
		//*/
		/*
		entiteProche.remove(idEntite);
		//*/
	}
	
	
	synchronized public void ajouterEntite(BasicEntity a){
		//if(!entiteProche.contains(a))
		entiteProche.add(a);
		//entiteProche.put(a.getId(), a);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int id) {
		this.index = id;
	}

	synchronized public boolean isEntiteProcheEmpty(){
		return entiteProche.isEmpty();
	}
	synchronized public BasicEntity getEntiteProcheAt(final int i){
		if(i >= 0 && i < entiteProche.size())
			return entiteProche.get(i);
		return entiteProche.get(0);
	}
	synchronized public BasicEntity getEntiteProcheID(final int id){
		for(int i=0;i<entiteProche.size();i++)
			if(entiteProche.get(i) != null)
				if(entiteProche.get(i).getId() == id)
					return entiteProche.get(i);
		return null;
	}
	
	synchronized public int getEntiteProcheSize(){
		return entiteProche.size();
	}
	
	synchronized public void clear(){
		entiteProche.clear();
	}
	
	/*
	public HashMap<Integer, BasicEntity> getEntiteProche() {
		return entiteProche;
	}

	public void setEntiteProche(HashMap<Integer, BasicEntity> entiteProche) {
		this.entiteProche = entiteProche;
	}//*/
	/*
	public ArrayList<BasicEntity> getEntiteProche() {
		return entiteProche;
	}

	public void setEntiteProche(ArrayList<BasicEntity> entiteProche) {
		this.entiteProche = entiteProche;
	}
	//*/
}
