package base.engine.gui;

import java.nio.IntBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.newdawn.slick.Image;

import base.engine.layers.entities.Data;
import base.utils.ResourceManager;

public class Cursors extends Cursor{

	public Cursors(int width, int height, int xHotspot, int yHotspot,
			int numImages, IntBuffer images, IntBuffer delays)
			throws LWJGLException {
		super(width, height, xHotspot, yHotspot, numImages, images, delays);
		
	}

	public static Image getCursor(int a){
		switch(a){
		case Data.CURSOR_NORMAL:
			return ResourceManager.getImage("");
		case Data.CURSOR_CRAYON:
			return ResourceManager.getImage("crayon");
		case Data.CURSOR_STYLO:
			return ResourceManager.getImage("stylo");
		case Data.CURSOR_EPEE:
			return ResourceManager.getImage("epee");
		}
		return null;
	}
	
	public static int getCursorHotSpotX(int a){
		return Data.CURSOR_X_Y[a][0];
	}
	
	public static int getCursorHotSpotY(int a){
		return Data.CURSOR_X_Y[a][1];
	}
	
}
