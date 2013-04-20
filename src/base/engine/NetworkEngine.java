/**
 * 
 */
package base.engine;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import base.engine.network.ThreadNetworkListener;
import base.engine.network.ThreadNetworkSender;

/**
 * @author Blackdread
 *
 */
public class NetworkEngine extends Engine {
	private boolean serveur;
	private Socket sock;
	Runnable threadList;
	Runnable threadSend;
	
	public NetworkEngine(EngineManager engineManager)
	{
		super(engineManager);
		this.serveur = false;
	}
	
	@Override
	public synchronized boolean processMessage() {
		//while(!this.message_queue.isEmpty()){
		if(!this.message_queue.isEmpty())
			engineManager.receiveMessage(this.message_queue.poll());
		else
			return false;
		
		return true;
	}

	public boolean connect(String ip, int port) throws UnknownHostException, IOException
	{
		sock = new Socket(ip, port);
		threadList = new ThreadNetworkListener(sock, this);
		threadList = new Thread(threadList);
		((Thread) threadList).start();
		threadSend = new ThreadNetworkSender(sock, this);
		threadSend = new Thread(threadSend);
		((Thread) threadSend).start();
		
		return true;
	}
	
	public boolean disconnect()
	{
		//Appeler les fonctions pour desactiver
		((ThreadNetworkListener)threadList).desactive();
		
		return true;
	}

	public boolean isServeur() {
		return serveur;
	}


	public void setServeur(boolean serveur) {
		this.serveur = serveur;
	}

}
