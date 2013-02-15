package base.engine.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;


public class Slider /*extends MouseOverArea  POURRA ETRE RAJOUTER SI VRAIMENT INTERESSANT*/ {
	
	private Image background;
	private SliderCursor cursor;
	private int x, y;
	/**
	 * int, float, double. boolean ??	
	 * ou mettre en Object
	 */
	private float value;
	private float maxValue;
	private float minValue;
	/**
	 * false = vertical
	 */
	private boolean sliderHorizontal, stillDragging;
	
	/**
	 * @deprecated
	 * @param background
	 * @param cursor
	 * @param value
	 * @param sliderHorizontal
	 */
	public Slider(Image background, SliderCursor cursor, float value, int x,int y, boolean sliderHorizontal) {
		this.background = background;
		this.cursor = cursor;
		this.value = value;
		this.x = x;
		this.y = y;
		this.sliderHorizontal = sliderHorizontal;
		stillDragging = false;
	}
	/**
	 * 
	 * @param container
	 * @param background image du slider
	 * @param imageCursor image du curseur
	 * @param x position du slider sur les x
	 * @param y position du slider sur les y
	 * @param value La valeur en float en fonction de la position du curseur
	 * @param minValue Ne peut pas etre < 0 et doit valoir 0
	 * @param maxValue valeur maximal
	 * @param sliderHorizontal false = vertical sinon horizontal
	 */
	public Slider(GUIContext container, Image background, Image imageCursor, int x,int y, float value, float minValue, float maxValue, boolean sliderHorizontal) {
		this.background = background;
		
		this.value = value;
		this.maxValue = maxValue;
		this.minValue = minValue;
		
		checkValue();	// evite que, si la valeur est sauvegarder dans un fichier, l'utilisateur ne puisse mettre une valeur non souhaite 
		
		// Placement du curseur qui ne marche pas pour  0 < value < 1
		if(sliderHorizontal){
			int tempX = (int) ((float)(background.getWidth()*this.value)/(float)100);	// calculer le pourcentage
			this.cursor = new SliderCursor(container, imageCursor, x-imageCursor.getWidth()/2 + tempX, y-imageCursor.getHeight()/2+background.getHeight()/2);
		}else{
			int tempY = (int) ((float)(background.getHeight()*this.value)/(float)100);	// calculer le pourcentage
			this.cursor = new SliderCursor(container, imageCursor, x-imageCursor.getWidth()/2 + background.getWidth()/2, y-imageCursor.getHeight()/2 + tempY);
		}
		//	value - background.getWidth() -cursor.getWidth()/2 + 2*this.x = cursor.getX()	
		this.x = x;
		this.y = y;
		this.sliderHorizontal = sliderHorizontal;
		stillDragging=false;
	}
	
	public void render(GUIContext container, Graphics g){
		if(background!=null)
			background.draw(x,y);
		cursor.render(container, g);
	}
	/**
	 * put stillDragging=false
	 * @return true si stillDragging valait false. Permet de updater ce qu'il faut si le cursor a ete relache
	 */
	public boolean mouseReleased(){
		if(stillDragging){
			stillDragging=false;
			return true;
		}
		return false;
	}
	
	public void isMouseGrabbed(int oldx, int oldy, int newx, int newy){
		//cursor.mouseDragged(oldx, oldy, newx, newy);
		if((cursor.isMouseOver() || stillDragging)){	// Probleme si jamais on va trop vite pour bouger le cursor RESOLUE AVEC LE BOOLEAN stillDragging
			stillDragging = true;
			if(sliderHorizontal){
				cursor.setX(cursor.getX()+newx-oldx);
				
				updateValue();
				
				if(cursor.getX()+cursor.getWidth()/2 > this.x+background.getWidth())
					cursor.setX(this.x+background.getWidth()-cursor.getWidth()/2);
				if(cursor.getX()+cursor.getWidth()/2 < this.x)
					cursor.setX(this.x-cursor.getWidth()/2);
			}else{
				cursor.setY(cursor.getY()+newy-oldy);
				
				updateValue();

				if(cursor.getY()+cursor.getHeight()/2 > this.y+background.getHeight())
					cursor.setY(this.y+background.getHeight()-cursor.getHeight()/2);
				if(cursor.getY()+cursor.getHeight()/2 < this.y)
					cursor.setY(this.y-cursor.getHeight()/2);
			}
			checkValue();
		}
	}
	
	/**
	 * Check value. Make sure that it fits maxValue and minValue.
	 */
	public void checkValue(){
		if(value>maxValue)
			value=maxValue;
		if(value<minValue)
			value=minValue;
	}
	/**
	 * Change value depending on the position of the cursor on the slider
	 */
	public void updateValue(){
		if(sliderHorizontal){
			value=minValue+(float)((float)(cursor.getX()-this.x+cursor.getWidth()/2)/(float)background.getWidth())*((float)Math.abs(maxValue)+(float)Math.abs(minValue));
		}else{
			value=(float)((float)(cursor.getY()-this.y+cursor.getHeight()/2)/(float)background.getHeight())*((float)maxValue+(float)minValue);
		}
	}
	
	public void checkCursorPosition(){
		if(sliderHorizontal){
			this.cursor.setX(x-cursor.getWidth()/2 + (int) ((float)(background.getWidth()*this.value)/(float)100));	// calculer le pourcentage
		}else{
			this.cursor.setY( y-cursor.getHeight()/2 + (int) ((float)(background.getHeight()*this.value)/(float)100));// calculer le pourcentage
		}
	}
	
	public SliderCursor getCursor() {
		return cursor;
	}
	public float getValue() {
		return value;
	}
	public float getValuePrecision2() {
		String a = ""+this.value;
		if(a.length()>3)
			a=a.substring(0, 3);
		
		return Float.valueOf(a);
	}
	public void setCursor(SliderCursor cursor) {
		this.cursor = cursor;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public Object getMaxValue() {
		return maxValue;
	}
	public Object getMinValue() {
		return minValue;
	}
	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}
	public void setMinValue(float minValue) {
		this.minValue = minValue;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Image getBackground() {
		return background;
	}
	public void setBackground(Image background) {
		this.background = background;
	}
	public boolean isSliderHorizontal() {
		return sliderHorizontal;
	}
	public void setSliderHorizontal(boolean sliderHorizontal) {
		this.sliderHorizontal = sliderHorizontal;
	}
	
	
	
}
