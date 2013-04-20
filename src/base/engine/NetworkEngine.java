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
	Thread threadList;
	Thread threadSend;
	
	public NetworkEngine(EngineManager engineManager)
	{
		super(engineManager);
		this.serveur = false;
	}
	
	@Override
	public synchronized boolean processMessage() {

		return false;
	}

	public boolean connect(String ip, int port) throws UnknownHostException, IOException
	{
		sock = new Socket(ip, port);
		Runnable r1 = new ThreadNetworkListener(sock, this);
		threadList = new Thread(r1);
		threadList.start();
		Runnable r2 = new ThreadNetworkSender(sock, this);
		threadSend = new Thread(r2);
		threadSend.start();
		
		return true;
	}
	
	public boolean disconnect()
	{
		//Appeler les fonctions pour desactiver
		threadList.
		
		return true;
	}

	public boolean isServeur() {
		return serveur;
	}


	public void setServeur(boolean serveur) {
		this.serveur = serveur;
	}

}
