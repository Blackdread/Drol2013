package base.tile;

import java.util.ArrayList;

import org.newdawn.slick.SpriteSheet;

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
		int i=0;
		for(BasicEntity v : entiteProche){
			if(v.getId() == idEntite){
				entiteProche.remove(i);
				break;
			}
			i++;
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
