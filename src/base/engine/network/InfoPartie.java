package base.engine.network;

import java.io.Serializable;

/**
 * Represente les infos sur une partie, c'est pour eviter d'avoir a envoyer l'objet Partie (ce qui serait surement plus pratique mais on garde comme ca pour le moment)
 * @author Yoann CAPLAIN
 *
 */
public class InfoPartie implements Serializable{

	private static final long serialVersionUID = 5539331756661699323L;
	
	public int idPartie = -1; 
	public String pseudoHost = "";
	
	public boolean enCoursDeJeu = false;
	
	public int nbJoueur;
	
	public InfoPartie(){
		
	}
	
	// on met aussi la liste des joueurs ??
	
	
	public String toString(){
		return "id: "+idPartie+" host: "+pseudoHost+" type: "+((enCoursDeJeu == true) ? "Jeu" : "Salon")+" nb joueurs: "+nbJoueur;
	}
}
