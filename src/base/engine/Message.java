/**
 * 
 */
package base.engine;

import java.util.Hashtable;

import org.newdawn.slick.geom.Vector2f;

/**
 * @author Nico
 *
 */
public class Message {
	
	/**
	 * Definit quelle instruction on desire executee
	 */
	public int instruction;
	
	public Hashtable<Integer, Boolean> b_data = new Hashtable<Integer, Boolean>();
	public Hashtable<Integer, Float> f_data = new Hashtable<Integer, Float>();
	public Hashtable<Integer, Integer> i_data = new Hashtable<Integer, Integer>();
	public Hashtable<Integer, String> s_data = new Hashtable<Integer, String>();
	public Hashtable<Integer, Vector2f> v2d_data = new Hashtable<Integer, Vector2f>();
}
