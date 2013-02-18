/**
 * 
 */
package engine2;

import java.util.Hashtable;
import base.utils.Vector2df;

/**
 * @author Nico
 *
 */
public class Message {

	public int type; //Type du message
	
	public Hashtable<String, Integer> i_data;
	public Hashtable<String, String> s_data;
	public Hashtable<String, Vector2df> v2d_data;
}
