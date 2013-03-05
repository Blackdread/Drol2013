package base.tile;

import java.util.ArrayList;

import org.newdawn.slick.SpriteSheet;

import base.engine.entities.BasicEntity;

public class Tile {

	protected SpriteSheet tileSet;
	protected int id;
	protected boolean isWall;
	
	protected ArrayList<BasicEntity> entiteProche = new ArrayList<BasicEntity>();
	
	public Tile(SpriteSheet sp, int i, boolean wall){
		tileSet = sp;
		id =i;
		isWall = wall;
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

	public int getId() {
		return id;
	}

	public boolean isWall() {
		return isWall;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setWall(boolean isWall) {
		this.isWall = isWall;
	}
}
