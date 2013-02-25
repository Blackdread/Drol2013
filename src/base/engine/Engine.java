package base.engine;

import java.util.LinkedList;
import java.util.Queue;



/**
 * The entry point to all game mechanics.
 * 
 * This class regroup collision, rendering, network, etc and link every
 * module to make the game working.
 
 * 
 * @author Yoann CAPLAIN
 * @author Nicolas DUPIN
 * 
 */
public abstract class Engine {
	
	protected Queue<Message> message_queue = new LinkedList<Message>(); 
	
	public void receiveMessage(Message mes){
		this.message_queue.add(mes);
	}
	
	public abstract void processMessage();
	
}
