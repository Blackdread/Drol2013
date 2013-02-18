/**
 * 
 */
package engine2;

import java.util.Queue;

/**
 * @author Nico
 *
 */
public abstract class Engine{

	private Game game_parent;
	
	//Interface à modifier
	// Faudra choisir entre : AbstractQueue, ArrayBlockingQueue, ConcurrentLinkedQueue, DelayQueue, LinkedBlockingQueue, LinkedList, PriorityBlockingQueue, PriorityQueue, SynchronousQueue
	// je sais pas trop comment tu veux gerer tes messages.
	private Queue<Message> message_queue = new Queue<Message>(0); 
	
	
	public Engine(Game game_parent){
		this.game_parent = game_parent;
	}
	
	/* Chaque Engine doit pouvoir communiquer grâce à des messages
	 * Engine doit pouvoir en recevoir et en envoyer. La méthode permettant de 
	 * traiter ces messages est spécifique à chaque engine, elle doit être redéfini.
	 */
	
	//Recoit
	public void push_message(Message m){
		this.message_queue.add(m);
	}
	
	/*
	 * Methode à redéfinir dans chacun des Engines, traite les messages
	 */
	public void process_first_message(){
		
		if(!message_queue.isEmpty()){
			Message m = message_queue.remove();
			
			//Traite le message
		}
		
	}
	
	abstract public void frame(); //Executé à chaque frame
	
}
