package base.engine.gui;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 16 10 2012
 * @version 1.3
 */
public class ListeDeroulante extends MouseOverArea {
	
	/**
	 * Decalage de l'image pour la liste deroulante
	 * A voir si on met un boolean pour decaler aussi les elements (Les elements ont aussi leurs propres variables de decalage)
	 * car les elements ont normalement une image differente de celle de base
	 */
	private int decalageY, decalageX;
	/**
	 * Not used. A faire pour plus tard
	 * Decale les elements par rapport a ceux de la liste deroulante
	 */
	private boolean decalerElements;
	
	private boolean autoriserWheel = true;
	
	private ArrayList<Elements> elements;
	private Elements currentElementUsed;
	//private String name;
	private boolean scrolled;
	/**
	 * -1 = infini
	 */
	private int maxElementsToDraw = -1;
	
	public ListeDeroulante(GUIContext container, Image image, Shape shape){
		super(container, image, shape);
		elements = new ArrayList<Elements>();
		scrolled=false;
	}

	public ListeDeroulante(GUIContext container, Image image, int x, int y){
		super(container, image, x, y);
		elements = new ArrayList<Elements>();
		scrolled=false;
	}

	public ListeDeroulante(GUIContext container, Image image, int x, int y, ComponentListener listener) {
		super(container, image, x, y, listener);
		elements = new ArrayList<Elements>();
		scrolled=false;
	}

	public ListeDeroulante(GUIContext container, Image image, int x, int y, int width, int height) {
		super(container, image, x, y, width, height);
		elements = new ArrayList<Elements>();
		scrolled=false;
	}

	public ListeDeroulante(GUIContext container, Image image, int x, int y, int width, int height, ComponentListener listener) {
		super(container, image, x, y, width, height, listener);
		elements = new ArrayList<Elements>();
		scrolled=false;
	}
	
	@Override
	public boolean isMouseOver(){
		if(!scrolled){
			if(super.isMouseOver())
					scrolled=true;
		}else{
			if(elementsOver())
				scrolled=true;
			else
				scrolled=false;
		}
		//return super.isMouseOver();
		return scrolled;
	}
	
	private boolean elementsOver(){
		int o=0;
		for(Elements v : elements)
			if(v!=null){
				if(maxElementsToDraw != -1){
					o++;
					if(o>maxElementsToDraw)
						break;
				}
				if(v.isMouseOver())
					return true;
			}
		return false;
	}
	
	public Elements getElementMouseOver(){
		Elements temp;
		int o=0;
		for(int i=0;i<elements.size();i++){
			temp = elements.get(i);
			if(temp!=null){
				if(maxElementsToDraw != -1){
					o++;
					if(o>maxElementsToDraw)
						break;
				}
				if(temp.isMouseOver())
					return temp;
			}
		}
		return null;
	}
	
	private void update(){
		int i=0;
		for(Elements v : elements)
			if(v!=null){
				if(decalerElements)
					v.setLocation(super.getX() + decalageX, super.getY() + i*v.getHeight() + super.getHeight() + decalageY);
				else
					v.setLocation(super.getX(), super.getY() + i*v.getHeight() + super.getHeight());
				i++;
			}
	}
	
	@Override
	public void render(GUIContext container, Graphics g){
		super.render(container, g);
		if(currentElementUsed!=null){
			//currentElementUsed.render(container, g, super.getX(),super.getY());	// foireux car render aussi l'image
			currentElementUsed.renderString(container, g, super.getX(),super.getY());
		}
		//int i=1;
		if(scrolled){
			int o=0;
			for(Elements v : elements)
				if(v!=null){
					v.render(container, g);
					//v.render(container, g, v.getX(), v.getY());
					//v.render(container, g, super.getX() + DELAGE_SUR_X_DES_ELEMENT, super.getY() + i*v.getHeightElement());
					//i++;
					if(maxElementsToDraw != -1){
						o++;
						if(o>=maxElementsToDraw)
							break;
					}
				}
		}
	}
	public void renderString(GUIContext container, Graphics g){
		super.render(container, g);
		if(currentElementUsed!=null){
			//currentElementUsed.render(container, g, super.getX(),super.getY());	// foireux car render aussi l'image
			currentElementUsed.renderString(container, g, super.getX(),super.getY());
		}
		//int i=1;
		if(scrolled){
			int o=0;
			for(Elements v : elements)
				if(v!=null){
					v.renderString(container, g, v.getX(), v.getY());
					//v.render(container, g, v.getX(), v.getY());
					//v.render(container, g, super.getX() + DELAGE_SUR_X_DES_ELEMENT, super.getY() + i*v.getHeightElement());
					//i++;
					if(maxElementsToDraw != -1){
						o++;
						if(o>=maxElementsToDraw)
							break;
					}
				}
		}
	}
	
	public void mouseWheelMoved(final int change){
		//System.out.println(""+change);
		if(autoriserWheel){
			elements.add(elements.get(0));
			elements.remove(0);
			/*
			for(int i=0;i<elements.size()-1;i++){
				if(elements.get(i)==null){
					elements.remove(i);
					continue;
				}
				elements.set(i, elements.get(i+1));
				}
			//*/
			update();
		}
	}

	public boolean isScrolled() {
		return scrolled;
	}

	public void setScrolled(boolean scrolled) {
		this.scrolled = scrolled;
	}
	public int getMaxElementsToDraw() {
		return maxElementsToDraw;
	}
	public Elements getElement(int a){
		return elements.get(a);
	}
	public void setMaxElementsToDraw(int maxElementsToDraw) {
		this.maxElementsToDraw = maxElementsToDraw;
	}

	public Elements getCurrentElementUsed(){
		return this.currentElementUsed;
	}
	public void setCurrentElementUsed(Elements ele){
		this.currentElementUsed = ele;
	}

	public int getDecalageY() {
		return decalageY;
	}

	public int getDecalageX() {
		return decalageX;
	}

	public void setDecalageY(int decalageY) {
		this.decalageY = decalageY;
	}

	public void setDecalageX(int decalageX) {
		this.decalageX = decalageX;
	}

	public boolean isDecalerElements() {
		return decalerElements;
	}

	public void setDecalerElements(boolean decalerElements) {
		this.decalerElements = decalerElements;
	}

	public void applyImageAllElement(final Image image){
		for(Elements v : elements)
			if(v!=null)
				v.setNormalImage(image);
	}
	
	public void applyImageOverAllElement(final Image image){
		for(Elements v : elements)
			if(v!=null)
				v.setMouseOverImage(image);
	}
	
	public void addElement(Elements element){
		elements.add(element);
		update();
	}
	
	public void chercherElementUsed(){
		for(Elements v : elements)
			if(v!=null)
				if(v.isElementUsed()){
					this.currentElementUsed = v;
					break;
				}
	}

	@Deprecated
	public void addElementResolution(GUIContext container, Image image, int withReso, int heightReso,
			int width, int height){
		elements.add(new ElementResolution(container, image, 0, 0, withReso, heightReso, width, height));
		update();
	}
	
	@Deprecated
	public void addElementResolution(GUIContext container, Image image, int x, int y, int withReso, int heightReso,
			int width, int height){
		elements.add(new ElementResolution(container, image, x, y, withReso, heightReso, width, height));
		update();	// pas obliger
	}

	public boolean isAutoriserWheel() {
		return autoriserWheel;
	}

	public void setAutoriserWheel(boolean autoriserWheel) {
		this.autoriserWheel = autoriserWheel;
	}
}
