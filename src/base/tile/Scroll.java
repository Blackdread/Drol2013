package base.tile;

public class Scroll {
	private int xScroll, yScroll;
	private int width, height;

	public Scroll() {
		xScroll = 0;
		yScroll = 0;
		width = 500;
		height = 500;
	}
	
	public Scroll(int xScroll, int yScroll, int width, int height) {
		this.xScroll = xScroll;
		this.yScroll = yScroll;
		this.width = width;
		this.height = height;
	}

	public int getxScroll() {
		return xScroll;
	}

	public void setxScroll(int xScroll) {
		this.xScroll = xScroll;
	}

	public int getyScroll() {
		return yScroll;
	}

	public void setyScroll(int yScroll) {
		this.yScroll = yScroll;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
