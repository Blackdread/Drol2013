package base.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

/**
 * This class is link to the configuration file and provide static
 * getters/setters to use the current game configuration.
 * 
 * @author 
 * @since 12 10 2012
 */
public class Configuration {

	private static String fileLocation;
	private static Properties configurationFile;
	
	public static final int WIDTH_WINDOW_PREFERER = 1024;
	public static final int HEIGHT_WINDOW_PREFERER = 768;
	/**
	 * Pour ne pas avoir des images trop grandes et qui se pixelisent
	 */
	public static final float MAX_SCALE = 3;
	/**
	 * Pour ne pas avoir des images trop petites
	 */
	public static final float MIN_SCALE = 0.3f;
	
	/**
	 * Retourne un scale par rapport a une dimension de fenetre approprie a la taille des images de base pour le projet
	 * @return float taille du scale
	 */
	public static float getScaleFenetre(){
		float t = (float)((float)getWidth()/(float)getHeight()) / (float)(WIDTH_WINDOW_PREFERER/HEIGHT_WINDOW_PREFERER);
		if(t < MAX_SCALE && t > MIN_SCALE)
			return t;
		if(t >= MAX_SCALE)
			return MAX_SCALE;
		return MIN_SCALE;
	}

	/**
	 * Initialize the configuration file with the given path.
	 * 
	 * @param fileLocation
	 *            The path of the file.
	 * @throws IOException
	 *             If the file can't be loaded.
	 */
	public static void init(String fileLocation) throws IOException {
		Configuration.fileLocation = fileLocation;
		updateConfigFile();
	}

	/**
	 * Update the current configuration settings.
	 * 
	 * @throws IOException
	 *             If the file can't be loaded.
	 */
	public static void updateConfigFile() throws IOException {
		InputStreamReader is = new InputStreamReader(new FileInputStream(fileLocation));
		configurationFile = new Properties();
		configurationFile.load(is);
		is.close();
	}

	/**
	 * Save the current set configuration to the configuration file.
	 * 
	 * @throws IOException
	 */
	public static void saveNewConfig() throws IOException {
		OutputStream os = new FileOutputStream(fileLocation);
		configurationFile.store(os, "");
		os.flush();
		os.close();
		updateConfigFile();
	}

	// Getters and Setters

	/**
	 * Get the game frame width.
	 */
	public static int getWidth() {
		return Integer.parseInt(configurationFile.getProperty("width", "1000"));
	}

	/**
	 * Set the game frame width.
	 * 
	 * @param width
	 *            The new width.
	 */
	public static void setWidth(int width) {
		configurationFile.setProperty("width", width + "");
	}

	/**
	 * Get the game frame height.
	 */
	public static int getHeight() {
		return Integer.parseInt(configurationFile.getProperty("height", "700"));
	}

	/**
	 * Set the game frame height.
	 * 
	 * @param height
	 *            The new height.
	 */
	public static void setHeight(int height) {
		configurationFile.setProperty("height", height + "");
	}

	/**
	 * Check if the full screen is on.
	 * 
	 * @return true is the frame is in full screen, false otherwise.
	 */
	public static boolean isFullScreen() {
		return configurationFile.getProperty("fullscreen").equals("true");
	}

	/**
	 * Set the on/off full screen for game frame.
	 * 
	 * @param fullscreen
	 *            true to put the full screen on, false to put it off.
	 */
	public static void setFullScreen(boolean fullscreen) {
		configurationFile.setProperty("fullscreen", (fullscreen) ? "true" : "false");
	}

	/**
	 * Get the game target FPS.
	 */
	public static int getTargetFPS() {
		return Integer.parseInt(configurationFile.getProperty("targetFps", "100"));
	}

	/**
	 * Set the game target FPS.
	 * 
	 * @param fps
	 *            The new target fps.
	 */
	public static void setTargetFPS(int fps) {
		configurationFile.setProperty("targetFps", fps + "");
	}

	/**
	 * Check if smooth deltas is on.
	 * 
	 * @return true is smooth deltas is on, false otherwise.
	 */
	public static boolean isSmoothDeltas() {
		return configurationFile.getProperty("smoothDeltas").equals("true");
	}

	/**
	 * Set the on/off smooth deltas.
	 * 
	 * @param smoothDeltas
	 *            true to put smooth deltas on, false to put it off.
	 */
	public static void setSmoothDeltas(boolean smoothDeltas) {
		configurationFile.setProperty("smoothDeltas", (smoothDeltas) ? "true" : "false");
	}

	/**
	 * Check if VSynch is on.
	 * 
	 * @return true is VSynch is on, false otherwise.
	 */
	public static boolean isVSynch() {
		return configurationFile.getProperty("vsynch").equals("true");
	}

	/**
	 * Set the on/off VSynch.
	 * 
	 * @param vsynch
	 *            true to put VSynch on, false to put it off.
	 */
	public static void setVSynch(boolean vsynch) {
		configurationFile.setProperty("vsynch", (vsynch) ? "true" : "false");
	}

	/**
	 * Get the current music volume.
	 * 
	 * @return the current music volume (0 - 1).
	 */
	public static float getMusicVolume() {
		return Float.parseFloat(configurationFile.getProperty("musicVol", "1"));
	}

	/**
	 * Set the music volume.
	 * 
	 * @param volume
	 *            the new music volume, must be between 0 and 1.
	 */
	public static void setMusicVolume(float volume) {
		configurationFile.setProperty("musicVol", volume + "");
	}
	
	public static boolean isMusicOn() {
		//return configurationFile.getProperty("musicOn").equals("true");
		return Boolean.parseBoolean(configurationFile.getProperty("musicOn", "true"));
	}

	public static void setMusicOn(boolean on) {
		configurationFile.setProperty("musicOn", (on) ? "true" : "false");
	}

	/**
	 * Get the current sound volume.
	 * 
	 * @return the current sound volume (0 - 1).
	 */
	public static float getSoundVolume() {
		return Float.parseFloat(configurationFile.getProperty("soundVol", "1"));
	}

	/**
	 * Set the sound volume.
	 * 
	 * @param volume
	 *            the new sound volume, must be between 0 and 1.
	 */
	public static void setSoundVolume(float volume) {
		configurationFile.setProperty("soundVol", volume + "");
	}

	/**
	 * Check if debug is on.
	 * 
	 * @return true is debug is on, false otherwise.
	 */
	public static boolean isDebug() {
		return configurationFile.getProperty("debug").equals("true");
	}

	/**
	 * Set the on/off debug.
	 * 
	 * @param debug
	 *            true to put debug on, false to put it off.
	 */
	public static void setDebug(boolean debug) {
		configurationFile.setProperty("debug", (debug) ? "true" : "false");
	}

}

