package base.engine;

import java.util.LinkedList;
import java.util.Queue;



/**
 * The entry point to all game mechanics.
 * 
 * This class regroup collision, network, etc and link every
 * module to make the game working.
 * 
 * @author Yoann CAPLAIN
 * @author Nicolas DUPIN
 * 
 */
public abstract class Engine {
	
	protected Queue<Message> message_queue = new LinkedList<Message>(); 
	
	/**
	 * Instance du serveur et peut etre une instance du client
	 */
	protected EngineManager engineManager;
	
	public Engine(EngineManager engineManager){
		this.engineManager = engineManager;
	}
	
	synchronized public void receiveMessage(Message mes){
		this.message_queue.add(mes);
	}
	
	/**
	 * Need to be synchronized
	 * @return Boolean true if message_queue is not empty
	 */
	public abstract boolean processMessage();

	public EngineManager getEngineManager() {
		return engineManager;
	}

	public void setEngineManager(EngineManager engineManager) {
		this.engineManager = engineManager;
	}
	
}
