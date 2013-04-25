package base.engine;

import java.io.Serializable;

/**
 * Represents a message that a player can send to others players.
 * Contains the nickname and the sentence
 * @author Yoann CAPLAIN
 * @since 25 04 2013
 *
 */
public class MessageTchat implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5060961466137200863L;
	
	private String pseudo;
	private String message;
	
	/*
	 * Si dans le jeu, y a un compteur de temps, on peut se referencer par rapport a lui
	 */
	/*
	private String tempsEnvoi;
	private String tempsRecu;
	//*/
	
	public MessageTchat(String pseudo, String message){
		this.pseudo = pseudo;
		this.message = message;
	}

	public String getPseudo() {
		return pseudo;
	}

	public String getMessage() {
		return message;
	}
	
	public String toString(){
		return ""+pseudo+" : "+message;
	}
}
