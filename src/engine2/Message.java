/**
 * 
 */
package engine2;

import java.util.Hashtable;

import org.newdawn.slick.geom.Vector2f;

/**
 * @author Nico
 *
 */
public class Message {

	public int type; //Type du message
	
	public Hashtable<String, Integer> i_data;
	public Hashtable<String, String> s_data;
	public Hashtable<String, Vector2f> v2d_data;
}
