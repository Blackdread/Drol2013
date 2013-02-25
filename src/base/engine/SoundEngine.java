/**
 * 
 */
package base.engine;

import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;

import base.utils.Configuration;
import base.utils.ResourceManager;

/**
 * 
 *
 */
public class SoundEngine extends Engine {
	
	private Music music;
	
	/**
	 * 
	 */
	public SoundEngine() {
		
	}

	/* (non-Javadoc)
	 * @see base.engine.Engine#processMessage()
	 */
	@Override
	public void processMessage() {
		Message mes;
		while(!this.message_queue.isEmpty()){
			mes = this.message_queue.poll();
			switch(mes.instruction){
			
			case MessageKey.I_PLAY_AT:
				
				break;
				
			case MessageKey.I_FADE:
				
				break;
				
			case MessageKey.I_PAUSE:
				music.pause();
				break;
				
			case MessageKey.I_PLAY_MUSIC:
				String a = mes.s_data.get(MessageKey.P_NAME);
				
				if(a!=null){
					float vol;
					boolean loop;
					
					if(mes.f_data.containsKey(MessageKey.P_VOLUME)){
						vol = mes.f_data.get(MessageKey.P_VOLUME);
						System.out.println("vol "+vol);
					}else
						vol = Configuration.getMusicVolume();
					
					if(mes.b_data.containsKey(MessageKey.P_LOOP))
						loop = mes.b_data.get(MessageKey.P_LOOP);
					else
						loop = true;
					
					playMusic(a,vol,loop);
				}
				break;
				
			case MessageKey.I_PLAY_SOUND:
				
				break;
			}
		}
	}
	
	/**
	 * 
	 * @param name
	 * @param volume specify it or Configuration.getSoundVolume() is used by default in processMessage
	 */
	private void playSound(final String name, final float volume){
		
		Sound sound = ResourceManager.getSound(name);
		
		if(sound != null)
			sound.play(1, volume);
	}
	
	private void playMusic(final String name, final float volume, final boolean loop){
		if(music != null)
			music.fade(2000, 0, true);
		
		music = ResourceManager.getMusic(name);
		if(music != null){
			music.setVolume(volume);
			if(loop)
				music.loop();
			else
				music.play();
		}
	}
	
}
