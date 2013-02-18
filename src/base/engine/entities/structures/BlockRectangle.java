package base.engine.entities.structures;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import base.engine.Engine;
import base.engine.layers.entities.Data;

public class BlockRectangle extends Block {
	
	public BlockRectangle(Engine engine, int layer, int type, int x, int y, int width, int height, int life) {
		super(engine, layer, type, life);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public BlockRectangle(Engine engine, int layer, int type, int x, int y, int width, int height) {
		super(engine, layer, type, width*height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		if(type == Data.BLOCK_CRAYON)
			g.setColor(Color.black);
		else
			g.setColor(Color.red);
		g.drawRect(x, y, width, height);
	}
	
	@Override
	public void updateShape() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCollisionType() {
		return Data.COLLISION_TOUS_BORDS_TOUS_UNITS;
	}

	@Override
	public void updateGravity() {
		if(this.gravityON){
			
		}
	}

	@Override
	public void changerShapeEnPolygon() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String save() {
		return super.save()+"_"+type;
	}

	@Override
	public Object load(String s) {
		String s2[] = s.split("_");
		int t = Integer.valueOf(s2[s2.length-1]);
		if(t == Data.BLOCK_CRAYON || t == Data.BLOCK_STYLO){
			// pas fini
			return super.load(s);
		}
		return null;
	}

}
