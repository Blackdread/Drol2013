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
	synchronized public boolean processMessage(){
		Message mes;
		//while(!this.message_queue.isEmpty()){
		if(!this.message_queue.isEmpty()){
			mes = this.message_queue.poll();
			switch(mes.instruction){
			
			case MessageKey.I_CHANGE_LOOP_MUSIC:
				if(music != null)
					if(mes.b_data.containsKey(MessageKey.P_LOOP)){
						boolean a = mes.b_data.get(MessageKey.P_LOOP);
						if(!a){
							float temp = music.getPosition();
							music.stop();
							music.play(1, music.getVolume());
							music.setPosition(temp);
						}else{
							float temp = music.getPosition();
							music.stop();
							music.loop(1, music.getVolume());
							music.setPosition(temp);
						}
					}
				break;
			
			case MessageKey.I_CHANGE_VOLUME_MUSIC:
				if(music != null){
					if(mes.f_data.containsKey(MessageKey.P_VOLUME)){
						music.setVolume((mes.f_data.get(MessageKey.P_VOLUME)));
					}else
						music.setVolume(Configuration.getMusicVolume());
				}
				break;
			
			case MessageKey.I_PLAY_AT_MUSIC:
				if(music != null)
					if(mes.f_data.containsKey(MessageKey.P_POSITION)){
						music.setPosition(mes.f_data.get(MessageKey.P_POSITION));
					}
				break;
				
			case MessageKey.I_FADE_MUSIC:
				if(music != null){
					int duration = mes.i_data.get(MessageKey.P_FADE_DURATION);
					float endVolume = mes.f_data.get(MessageKey.P_VOLUME);
					//boolean stopAfterFade = true;
					
					music.fade(duration, endVolume, true);
				}
				break;
				
			case MessageKey.I_STOP_MUSIC:
				if(music != null)
					music.stop();
				break;
				
			case MessageKey.I_PAUSE_MUSIC:
				if(music != null)
					music.pause();
				break;
				
			case MessageKey.I_PLAY_MUSIC:
				String name3 = mes.s_data.get(MessageKey.P_NAME);
				
				if(name3!=null){
					float vol;
					boolean loop;
					
					if(mes.f_data.containsKey(MessageKey.P_VOLUME))
						vol = mes.f_data.get(MessageKey.P_VOLUME);
					else
						vol = Configuration.getMusicVolume();
					
					if(mes.b_data.containsKey(MessageKey.P_LOOP))
						loop = mes.b_data.get(MessageKey.P_LOOP);
					else
						loop = true;
					
					playMusic(name3,vol,loop);
				}
				break;
				
			case MessageKey.I_PLAY_SOUND:
				String name = mes.s_data.get(MessageKey.P_NAME);
				
				if(name!=null){
					float vol;
					boolean loop;
					
					if(mes.f_data.containsKey(MessageKey.P_VOLUME))
						vol = mes.f_data.get(MessageKey.P_VOLUME);
					else
						vol = Configuration.getSoundVolume();
					
					if(mes.b_data.containsKey(MessageKey.P_LOOP))
						loop = mes.b_data.get(MessageKey.P_LOOP);
					else
						loop = false;
					
					playSound(name,vol, loop);
					
				}
				break;
				
			case MessageKey.I_CHANGE_LOOP_SOUND:
				String name2 = mes.s_data.get(MessageKey.P_NAME);
				
				if(name2 != null){
					float vol;
					boolean loop;
					
					if(mes.f_data.containsKey(MessageKey.P_VOLUME))
						vol = mes.f_data.get(MessageKey.P_VOLUME);
					else
						vol = Configuration.getSoundVolume();
					
					if(mes.b_data.containsKey(MessageKey.P_LOOP))
						loop = mes.b_data.get(MessageKey.P_LOOP);
					else
						loop = false;
					
					playSound(name2,vol, loop);
				}
				break;
				
			case MessageKey.I_STOP_SOUND:
				String name4 = mes.s_data.get(MessageKey.P_NAME);
				
				if(name4 != null){
					Sound sound = ResourceManager.getSound(name4);
					
					if(sound != null)
						sound.stop();
				}
				break;
			}
		}else
			return false;
		return true;
	}
	
	/**
	 * 
	 * @param name
	 * @param volume specify it or Configuration.getSoundVolume() is used by default in processMessage
	 */
	private void playSound(final String name, final float volume, final boolean loop){
		
		Sound sound = ResourceManager.getSound(name);
		
		if(sound != null)
			if(loop)
				sound.loop(1, volume);
			else
				sound.play(1, volume);
	}
	
	private void playMusic(final String name, final float volume, final boolean loop){
		if(music != null)
			music.fade(2000, 0, true);
		
		music = ResourceManager.getMusic(name);
		if(music != null){
			if(loop)
				music.loop();
			else
				music.play();
			music.setVolume(volume);
		}
	}

	public Music getMusic() {
		return music;
	}
	
}
