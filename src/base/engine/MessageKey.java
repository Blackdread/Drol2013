package base.engine;

/**
 * 
 * 
 *
 */
public abstract class MessageKey {
	
	/*
	 * 
	 * Sound Engine
	 * 
	 */
	// Instructions
	public static final int I_PLAY_SOUND = 0;
	
	public static final int I_PLAY_MUSIC = 1;
	public static final int I_PAUSE_MUSIC = 2;
	public static final int I_FADE_MUSIC = 3;
	public static final int I_PLAY_AT_MUSIC = 4;
	public static final int I_STOP_MUSIC = 5;
	
	public static final int I_CHANGE_VOLUME_MUSIC = 6;
	public static final int I_CHANGE_LOOP_MUSIC = 7;
	
	public static final int I_CHANGE_LOOP_SOUND = 8;
	public static final int I_STOP_SOUND = 9;
	
	// Parameters
	public static final int P_NAME = 0;
	public static final int P_LOOP = 1;
	public static final int P_VOLUME = 2;
	public static final int P_POSITION = 3;
	public static final int P_FADE_DURATION = 4;
	
	/*
	 * 
	 * Logic Engine
	 * 
	 */
	// Instructions
	public static final int I_MOVE_ENTITY = 10;
	
	
	// Parameters
	public static final int P_ID = 5;
	public static final int P_X = 6;
	public static final int P_Y = 7;
	
	
	/*
	 * 
	 * Network Engine
	 * 
	 */
	// Instructions
	
	
	// Parameters
	
}
