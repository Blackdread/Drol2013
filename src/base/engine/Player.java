package base.engine;

import java.io.Serializable;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;

import base.engine.entities.BasicEntity;
import base.engine.entities.others.outputs.ITeam;

/**
 * Classe qui represente un joueur, ce sera plus simple pour le reseau car on envoie directement l'objet Player
 * Et on gere les differentes actions possibles pour un joueur
 * @author Yoann CAPLAIN
 *
 */
public class Player implements Serializable, ITeam, Cloneable{

	public static final int NOT_CONTROLING_UNIT = -1;
	
	private static final long serialVersionUID = 5052989075642158186L;

	private String pseudo = "";
	
	//private PlayableEntity heroEntityHeIsPlaying;
	/**
	 * -1 means he has no unit (don't control anything)
	 */
	private int idEntityHePlays = NOT_CONTROLING_UNIT;

	/**
	 * Nom de l'entite
	 * Ex: robot ou zombi
	 */
	private String choixEntiteAJouer = "robot";
	
	private transient EngineManager engineManager;
	
	/**
	 * -1 means no allies, FFA
	 */
	private int team = -1;
	
	private int score;
	
	public Player(EngineManager engineManager, String pseudo){
		this.pseudo = pseudo;
		this.engineManager = engineManager;
	}
	
	public Player(EngineManager engineManager, String pseudo, int idEntityHePlays){
		this.pseudo = pseudo;
		this.idEntityHePlays = idEntityHePlays;
		this.engineManager = engineManager;
	}
	
	public void copy(Player objetACopier){
		pseudo = ""+objetACopier.getPseudo();
		team = objetACopier.getTeam();
		idEntityHePlays = objetACopier.getIdEntityHePlays();
		choixEntiteAJouer = objetACopier.getChoixEntiteAJouer();
		score = objetACopier.score;
		
	}
	
	public void update(final int delta){
		BasicEntity hero = null;
		
		if(idEntityHePlays != NOT_CONTROLING_UNIT)
			//hero = engineManager.getCurrentLevelUsed().getArrayEntite().get(idEntityHePlays);
			hero = engineManager.getCurrentLevelUsed().getEntity(idEntityHePlays);
		
		if(hero != null){
			if(engineManager.getCurrentLevelUsed() != null)
				engineManager.getCurrentLevelUsed().getScroll().mettreAJourScroll(hero);
			
			if(Keyboard.isKeyDown(Input.KEY_LSHIFT))
			{
				Message m = new Message();
				m.instruction = MessageKey.I_SHOOT;
				
				if(hero.getDirection() == BasicEntity.HAUT)
				{
					//haut
					//TODO: Gérer direction, vecteur du tir, update du tir
					m.i_data.put(MessageKey.P_X, (int) (hero.getX() + hero.getHeight()/2));
					m.i_data.put(MessageKey.P_Y, (int) (hero.getY() - 5));
					m.i_data.put(MessageKey.P_VITESSE_X, 0);
					m.i_data.put(MessageKey.P_VITESSE_Y, -5);
					
				}
				else if(hero.getDirection() == BasicEntity.BAS)
				{
					//BAS
					m.i_data.put(MessageKey.P_X, (int) hero.getX() + hero.getHeight()/2);
					m.i_data.put(MessageKey.P_Y, (int) (hero.getY() + hero.getHeight() + 1));
					m.i_data.put(MessageKey.P_VITESSE_X, 0);
					m.i_data.put(MessageKey.P_VITESSE_Y, 5);
				}
				else if(hero.getDirection() == BasicEntity.GAUCHE)
				{
					//GAUCHE
					m.i_data.put(MessageKey.P_X, (int) (hero.getX() - 5));
					m.i_data.put(MessageKey.P_Y, (int) (hero.getY() + hero.getWidth()/2));
					m.i_data.put(MessageKey.P_VITESSE_X, -5);
					m.i_data.put(MessageKey.P_VITESSE_Y, 0);
					
				}
				else if(hero.getDirection() == BasicEntity.DROITE)
				{
					//DROITE
					m.i_data.put(MessageKey.P_X, (int) (hero.getX() + hero.getWidth() + 5));
					m.i_data.put(MessageKey.P_Y, (int) (hero.getY() + hero.getWidth()/2));
					m.i_data.put(MessageKey.P_VITESSE_X, 5);
					m.i_data.put(MessageKey.P_VITESSE_Y, 0);
				}
				
				m.o_data.put(MessageKey.P_ENTITY, hero);
				m.engine = EngineManager.LOGIC_ENGINE;
				
				engineManager.receiveMessage(m);
			}	
		}
	}
	
	public void keyPressed(int key, char c) {
		Message m = new Message();
		if(idEntityHePlays != NOT_CONTROLING_UNIT)
			switch(key){
				case Input.KEY_SPACE:
					//hero.jump();
					m.instruction = MessageKey.I_JUMP;
					m.i_data.put(MessageKey.P_ID, idEntityHePlays);
					m.engine = EngineManager.LOGIC_ENGINE;
					
					engineManager.receiveMessage(m);
					break;
				case Input.KEY_RIGHT:
					m.instruction = MessageKey.I_START_ENTITY_MOVE;
					m.i_data.put(MessageKey.P_ID, idEntityHePlays);
					m.i_data.put(MessageKey.P_DIRECTION, BasicEntity.DROITE);
					m.b_data.put(MessageKey.P_BOOLEAN, true);//On mets en déplacement
					m.engine = EngineManager.LOGIC_ENGINE;
			
					engineManager.receiveMessage(m);
					break;
				case Input.KEY_LEFT:
					m.instruction = MessageKey.I_START_ENTITY_MOVE;
					m.i_data.put(MessageKey.P_ID, idEntityHePlays);
					m.i_data.put(MessageKey.P_DIRECTION, BasicEntity.GAUCHE);
					m.b_data.put(MessageKey.P_BOOLEAN, true);//On mets en déplacement
					m.engine = EngineManager.LOGIC_ENGINE;
					
					engineManager.receiveMessage(m);
				break;
			}
		
	}
	
	
	public void keyReleased(int key, char c) {
		Message m = new Message();
		if(idEntityHePlays != NOT_CONTROLING_UNIT)
			switch(key){
			case Input.KEY_SPACE:
				//heroEntityHeIsPlaying.jump();
				break;
			case Input.KEY_RIGHT:
				
				m.instruction = MessageKey.I_START_ENTITY_MOVE;
				m.i_data.put(MessageKey.P_ID, idEntityHePlays);
				m.i_data.put(MessageKey.P_DIRECTION, BasicEntity.GAUCHE);
				m.b_data.put(MessageKey.P_CHANGE_DIRECTION, false);
				m.b_data.put(MessageKey.P_BOOLEAN, false);//On arrête le déplacement
				m.engine = EngineManager.LOGIC_ENGINE;
				
				engineManager.receiveMessage(m);
				break;
			case Input.KEY_LEFT:
				Message m2 = new Message();
				m2.instruction = MessageKey.I_START_ENTITY_MOVE;
				m2.i_data.put(MessageKey.P_ID, idEntityHePlays);
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

	public int getIdEntityHePlays() {
		return idEntityHePlays;
	}

	public void setIdEntityHePlays(int idEntityHePlays) {
		this.idEntityHePlays = idEntityHePlays;
	}

	public EngineManager getEngineManager() {
		return engineManager;
	}

	public void setEngineManager(EngineManager engineManager) {
		this.engineManager = engineManager;
	}

	public String getChoixEntiteAJouer() {
		return choixEntiteAJouer;
	}

	public void setChoixEntiteAJouer(String choixEntiteAJouer) {
		this.choixEntiteAJouer = choixEntiteAJouer;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
