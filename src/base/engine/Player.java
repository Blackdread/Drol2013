package base.engine;

import java.io.Serializable;

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

	/**
	 * -1 means no allies, FFA
	 */
	private int team = -1;
	
	public Player(){
		
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
