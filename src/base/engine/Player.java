package base.engine;

import java.io.Serializable;

import org.newdawn.slick.Input;

import base.engine.entities.BasicEntity;
import base.engine.entities.PlayableEntity;
import base.engine.entities.others.outputs.ITeam;

/**
 * Classe qui represente un joueur, ce sera plus simple pour le reseau car on envoie directement l'objet Player
 * @author Yoann CAPLAIN
 *
 */
public class Player implements Serializable, ITeam{

	private String pseudo = "";
	
	private PlayableEntity heroEntityHeIsPlaying;

	private EngineManager engineManager;
	
	/**
	 * -1 means no allies, FFA
	 */
	private int team = -1;
	
	public Player(EngineManager engineManager, String pseudo, PlayableEntity entity){
		this.pseudo = pseudo;
		heroEntityHeIsPlaying = entity;
		this.engineManager = engineManager;
	}
	
	
	public void keyPressed(int key, char c) {
		Message m = new Message();
		switch(key){
			case Input.KEY_SPACE:
				//hero.jump();
				m.instruction = MessageKey.I_JUMP;
				m.i_data.put(MessageKey.P_ID, heroEntityHeIsPlaying.getId());
				m.engine = EngineManager.LOGIC_ENGINE;
				
				engineManager.receiveMessage(m);
				break;
			case Input.KEY_RIGHT:
				m.instruction = MessageKey.I_START_ENTITY_MOVE;
				m.i_data.put(MessageKey.P_ID, heroEntityHeIsPlaying.getId());
				m.i_data.put(MessageKey.P_DIRECTION, BasicEntity.DROITE);
				m.b_data.put(MessageKey.P_BOOLEAN, true);//On mets en déplacement
				m.engine = EngineManager.LOGIC_ENGINE;
		
				engineManager.receiveMessage(m);
				break;
			case Input.KEY_LEFT:
				m.instruction = MessageKey.I_START_ENTITY_MOVE;
				m.i_data.put(MessageKey.P_ID, heroEntityHeIsPlaying.getId());
				m.i_data.put(MessageKey.P_DIRECTION, BasicEntity.GAUCHE);
				m.b_data.put(MessageKey.P_BOOLEAN, true);//On mets en déplacement
				m.engine = EngineManager.LOGIC_ENGINE;
				
				engineManager.receiveMessage(m);
			break;
		}
		
	}
	
	
	public void keyReleased(int key, char c) {
		Message m = new Message();
		switch(key){
		case Input.KEY_SPACE:
			heroEntityHeIsPlaying.jump();
			break;
		case Input.KEY_RIGHT:
			
			m.instruction = MessageKey.I_START_ENTITY_MOVE;
			m.i_data.put(MessageKey.P_ID, heroEntityHeIsPlaying.getId());
			m.i_data.put(MessageKey.P_DIRECTION, BasicEntity.GAUCHE);
			m.b_data.put(MessageKey.P_CHANGE_DIRECTION, false);
			m.b_data.put(MessageKey.P_BOOLEAN, false);//On arrête le déplacement
			m.engine = EngineManager.LOGIC_ENGINE;
			
			engineManager.receiveMessage(m);
			break;
		case Input.KEY_LEFT:
			Message m2 = new Message();
			m2.instruction = MessageKey.I_START_ENTITY_MOVE;
			m2.i_data.put(MessageKey.P_ID, heroEntityHeIsPlaying.getId());
			m2.i_data.put(MessageKey.P_DIRECTION, BasicEntity.DROITE);
			m2.b_data.put(MessageKey.P_CHANGE_DIRECTION, false);
			m2.b_data.put(MessageKey.P_BOOLEAN, false);//On arrête le déplacement
			m2.engine = EngineManager.LOGIC_ENGINE;
			
			engineManager.receiveMessage(m2);
		break;
		}
	}
	
	@Override
	public int getTeam() {
		return team;
	}

	@Override
	public void setTeam(int team) {
		this.team = team;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public PlayableEntity getHeroEntityHeIsPlaying() {
		return heroEntityHeIsPlaying;
	}

	public void setHeroEntityHeIsPlaying(
			PlayableEntity heroEntityHeIsPlaying) {
		this.heroEntityHeIsPlaying = heroEntityHeIsPlaying;
	}
	
	
	
}
