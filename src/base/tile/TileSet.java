package base.tile;

import java.util.ArrayList;

import org.newdawn.slick.SpriteSheet;

public class TileSet {
	private SpriteSheet spriteSource;
	private ArrayList<TilePropriety> correspondanceTile;
	private int nbTileLargeur;
	private int nbTileHauteur;
	
	public TileSet(SpriteSheet img, ArrayList<TilePropriety> t)
	{
		spriteSource = img;
		correspondanceTile = t;
		
		nbTileLargeur = img.getWidth()/img.getSubImage(0, 0).getWidth();
		nbTileHauteur = img.getHeight()/img.getSubImage(0, 0).getHeight();
	}

	public SpriteSheet getSpriteSource() {
		return spriteSource;
	}

	public ArrayList<TilePropriety> getCorrespondanceTile() {
		return correspondanceTile;
	}

	public void setSpriteSource(SpriteSheet spriteSource) {
		this.spriteSource = spriteSource;
	}

	public void setCorrespondanceTile(ArrayList<TilePropriety> correspondanceTile) {
		this.correspondanceTile = correspondanceTile;
	}

	public int getNbTileLargeur() {
		return nbTileLargeur;
	}

	public void setNbTileLargeur(int nbTileLargeur) {
		this.nbTileLargeur = nbTileLargeur;
	}

	public int getNbTileHauteur() {
		return nbTileHauteur;
	}

	public void setNbTileHauteur(int nbTileHauteur) {
		this.nbTileHauteur = nbTileHauteur;
	}
	
	
}
