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
	/**
	 * Teleport entity and don't check collisions
	 */
	public static final int I_MOVE_ENTITY_TO = 11;
	public static final int I_SHOOT = 12;
	public static final int I_REMOVE_ENTITY = 13;
	/**
	 * Mettre vitesse a 0 et acceleration ?
	 */
	public static final int I_STOP_ENTITY = 14;
	/**
	 * Met la vitesse de l'entite a default vitesse et met sa direction
	 */
	public static final int I_START_ENTITY_MOVE = 15;
	public static final int I_JUMP = 16;
	
	// Parameters
	public static final int P_ID = 5;
	public static final int P_X = 6;
	public static final int P_Y = 7;
	public static final int P_LIFE = 8;
	public static final int P_MAX_LIFE = 9;
	public static final int P_VITESSE_X = 10;
	public static final int P_VITESSE_Y = 11;
	public static final int P_DIRECTION = 12;
	public static final int P_CHANGE_DIRECTION = 13;
	public static final int P_ENTITY = 14;
	public static final int P_BOOLEAN = 15;
	
	/*
	 * 
	 * Network Engine
	 * 
	 */
	// Instructions
	public static final int I_START_NEW_GAME = 100;
	public static final int I_JOIN_GAME = 101;
	public static final int I_LEAVE_GAME = 102;
	
	public static final int I_LAUNCH_GAME = 103;
	
	// Parameters
	public static final int P_ID_CLIENT = 200;
}
