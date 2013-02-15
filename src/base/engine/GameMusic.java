package base.engine;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class GameMusic {
	private static Music mainTheme;

	public static void initMainTheme() {
		/*
		try {
			mainTheme = new Music("ressources/musics/main.wav");
		} catch (SlickException e) {
			e.printStackTrace();
		}//*/
	}

	public static void initMusics() {
		//ResourceManager.getMusic("music_1").addListener(new GameMusicListener(2));
		//ResourceManager.getMusic("music_2").addListener(new GameMusicListener(3));
		//ResourceManager.getMusic("music_3").addListener(new GameMusicListener(1));
	}

	public static void loopMainTheme() {
		if(mainTheme!=null)
			mainTheme.loop();
	}
	public static void stopMainTheme() {
		if(mainTheme!=null)
			mainTheme.stop();
	}
	public static void setMusicVolume(float volume){
		if(mainTheme!=null)
			mainTheme.setVolume(volume);
	}
	public static void setMusicPostion(float pos){
		if(mainTheme!=null)
			mainTheme.setPosition(pos);
	}
	public static float getMusicPosition(){
		if(mainTheme!=null)
			return mainTheme.getPosition();
		return 0;
	}

	public static void playMusic() {
		if(mainTheme!=null)
			mainTheme.stop();
		//ResourceManager.getMusic("music_1").play();
	}
}
