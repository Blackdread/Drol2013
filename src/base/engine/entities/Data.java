package base.engine.entities;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import base.utils.Configuration;

public final class Data {

	// PLAYER ENTITY
	public static final int NB_ANIMATION = 6;
	public static final int NB_SHAPE = 4;
	
	public static final int FACE = 0;
	public static final int DROITE = 1;
	public static final int GAUCHE = 2;
	public static final int ACCROUPI_FACE = 3;
	public static final int ACCROUPI_DROITE = 4;
	public static final int ACCROUPI_GAUCHE = 5;
	
	public static final int SHAPE_FACE = 0;
	public static final int SHAPE_COTE = 1;
	public static final int SHAPE_ACCROUPI_FACE = 3;
	public static final int SHAPE_ACCROUPI_COTE = 4;
	
	// TYPE ENTITIES
	// Walk (and JUMP)
	public static final int TYPE_PLAYER = 0;
	// FLY
	public static final int TYPE_FLY = 1;
	
	// ENTITIES
	public static final int PLAYER = 0;
	public static final int ENNEMIE_1 = 1;
	public static final int ENNEMIE_2 = 2;
	
	// PIEGES
	public static final int PIEGE_1 = 60;
	
	// Blocks
	public static final int BLOCK_INCASSABLE = -1;
	public static final int BLOCK_CRAYON = 80;
	public static final int BLOCK_STYLO = 81;
	
	
	// COLLISION TYPE
	public static final int COLLISION_NO_COLLISION = 0;
	public static final int COLLISION_TOUS_BORDS_TOUS_UNITS = 1;
	
	
	public static final int[] MAX_LIFE = new int[]{
		100,
		50,
		100
	};
	// ENTITIES DEFAULT SPEED
	public static final float[] SPEED = new float[]{
		0.03f,
		0.05f,
		0.12f
	};
	
	// CURSORS
	public static final int CURSOR_NORMAL = 0;
	public static final int CURSOR_CRAYON = 1;
	public static final int CURSOR_STYLO = 2;
	public static final int CURSOR_EPEE = 3;
	/**
	 * DŽcaler image curseur pour coincider avec la zone du clique
	 */
	public static final int[][] CURSOR_X_Y = {
		{0,0},
		{0,0},
		{0,0},
		{0,0}
	};
	
	public static Shape getPlayerShape(int a){
		Polygon shapePolygon = new Polygon();;
		float scale = Configuration.getScaleFenetre();
		switch(a){
		case SHAPE_FACE:
			shapePolygon.addPoint(0, 0);
			shapePolygon.addPoint(90*scale, 0);
			shapePolygon.addPoint(90*scale, 60*scale);
			shapePolygon.addPoint(60*scale, 60*scale);
			shapePolygon.addPoint(60*scale, 85*scale);
			shapePolygon.addPoint(120*scale, 85*scale);
			shapePolygon.addPoint(120*scale, 240*scale);
			shapePolygon.addPoint(90*scale, 240*scale);
			shapePolygon.addPoint(90*scale, 350*scale);
			shapePolygon.addPoint(120*scale, 350*scale);
			shapePolygon.addPoint(120*scale, 387*scale);
			shapePolygon.addPoint(-30*scale, 387*scale);
			shapePolygon.addPoint(-30*scale, 350*scale);
			shapePolygon.addPoint(0, 350*scale);
			shapePolygon.addPoint(0, 240*scale);
			shapePolygon.addPoint(-36*scale, 240*scale);
			shapePolygon.addPoint(-36*scale, 85*scale);
			shapePolygon.addPoint(30*scale, 85*scale);
			shapePolygon.addPoint(30*scale, 60*scale);
			shapePolygon.addPoint(0, 60*scale);
			break;
		}
		return shapePolygon;
	}
	
}
