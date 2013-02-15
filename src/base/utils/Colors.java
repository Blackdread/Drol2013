package base.utils;

import org.newdawn.slick.Color;
/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public final class Colors {

	public static final String[] COLORS = new String[] { "Yellow", "Red", "Green", "Blue", "Purple", "Pink", "Orange", "Cyan" };

	public static final int YELLOW = 0;
	public static final int RED = 1;
	public static final int GREEN = 2;
	public static final int BLUE = 3;
	public static final int PURPLE = 4;
	public static final int PINK = 5;
	public static final int ORANGE = 6;
	public static final int CYAN = 7;

	public static final Color PURPLE_COLOR = new Color(128, 0, 128);
	public static final Color GOLD = new Color(255, 215, 0);
	
	public static final Color TRANS_GREEN = new Color(0, 255, 0, 100);
	public static final Color TRANS_RED = new Color(255, 0, 0, 100);
	public static final Color TRANS_YELLOW = new Color(255, 255, 0, 100);
	public static final Color TRANS_BLACK = new Color(255, 255, 255, 100);
	public static final Color TRANS_CYAN = new Color(51, 51, 102, 200);

	public static int getColorId(String color) {
		for (int i = 0; i < COLORS.length; i++) {
			if (COLORS[i].equals(color))
				return i;
		}
		return -1;
	}

	public static Color getNewColorInstance(String colorId) {
		return new Color(getColor(getColorId(colorId)));
	}

	public static Color getColor(int color) {
		switch (color) {
		case YELLOW:
			return Color.yellow;
		case RED:
			return Color.red;
		case GREEN:
			return Color.green;
		case BLUE:
			return Color.blue;
		case PURPLE:
			return PURPLE_COLOR;
		case PINK:
			return Color.pink;
		case ORANGE:
			return Color.orange;
		case CYAN:
			return Color.cyan;
		default:
			return Color.gray;
		}
	}

}
