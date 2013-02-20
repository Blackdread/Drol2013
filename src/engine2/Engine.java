/**
 * 
 */
package engine2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Nico
 *
 */
public abstract class Engine{

	private Game game_parent;
	
	private Queue<Message> message_queue = new LinkedList<Message>(, ConcurrentLinkedQueue, DelayQueue, LinkedBlockingQueue, LinkedList, PriorityBlockingQueue, PriorityQueue, SynchronousQueue
	// je sais pas trop comment tu veux gerer tes messages.
	private Queue<Message> message_queue = new Queue<Message>(0); 
	
	
	public Engine(Game game_parent){
		this.game_parent = game_parent;
	}
	
	/* Chaque Engine doit pouvoir communiquer gr�ce � des messages
	 * Engine doit pouvoir en recevoir et en envoyer. La m�thode permettant de 
	 * traiter ces messages est sp�cifique � chaque engine, elle doit �tre red�fini.
	 */
	
	//Recoit
	public void push_message(Message m){
		this.message_queue.add(m);
	}
	
	/*
	 * Methode � red�finir dans chacun des Engines, traite 