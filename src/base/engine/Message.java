/**
 * 
 */
package base.engine;

import java.io.Serializable;
import java.util.Hashtable;

import org.newdawn.slick.geom.Vector2f;

/**
 * 
 * TODO La classe Message peut devenir obsolete mais ca demande de changer la facons d'envoyer nos messages,
 * On verra si on a le temps de faire en sorte d'envoyer directement les objects
 * Pour le moment, on a ajouter un hashTable qui contient des Object ce qui permet de faire passer tout de meme des Object
 * 
 * @author Nico
 * @author yoann
 */
public class Message implements Serializable{
	
	public static final char NO_ENGINE = 255;
	
	/**
	 * Definit quelle instruction on desire executee
	 */
	public int instruction;
	public char engine = NO_ENGINE;
	
	public Hashtable<Integer, Boolean> b_data = new Hashtable<Integer, Boolean>();
	public Hashtable<Integer, Float> f_data = new Hashtable<Integer, Float>();
	public Hashtable<Integer, Integer> i_data = new Hashtable<Integer, Integer>();
	public Hashtable<Integer, String> s_data = new Hashtable<Integer, String>();
	public Hashtable<Integer, Vector2f> v2d_data = new Hashtable<Integer, Vector2f>();
	public Hashtable<Integer, Object> o_data = new Hashtable<Integer, Object>();
}
