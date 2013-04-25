/**
 * 
 */
package base.engine;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import base.views.InGameMultiView;
import base.engine.network.ThreadNetworkListener;
import base.engine.network.ThreadNetworkSender;

/**
 * @author Blackdread
 *
 */
public class NetworkEngine extends Engine {
	private Socket sock;
	ThreadNetworkListener runList;
	ThreadNetworkSender runSend;
	Thread threadList;
	Thread threadSend;
	
	public NetworkEngine(EngineManager engineManager)
	{
		super(engineManager);
	}
	
	
	@Override
	synchronized public boolean processMessage() {
		//while(!this.message_queue.isEmpty()){
		if(!this.message_queue.isEmpty()){
			Message mes = message_queue.poll();
			if(!mes.o_data.isEmpty())
				for(Object v : mes.o_data.values())
					if(v != null)
						if(v instanceof MessageTchat){
							// TODO envoyer le l'objet aux 2 vues qui s'en servent
							((InGameMultiView)Game.getStateByID(Game.SALON_VIEW_ID)).tchatReceiveMessage((MessageTchat) v);
							((InGameMultiView)Game.getStateByID(Game.IN_GAME_MULTI_VIEW_ID)).tchatReceiveMessage((MessageTchat) v);
						}
							
			
			engineManager.receiveMessage(mes);
		}else
			return false;
		
		return true;
	}
	
	public boolean creerPartie(){
		if(runSend != null){
			Message mes = new Message();
			mes.instruction = MessageKey.I_START_NEW_GAME;
			
			runSend.ajoutMessage(mes);
			return true;
		}
		return false;
	}

	public boolean connect(String ip, int port) throws UnknownHostException, IOException
	{
		sock = new Socket(ip, port);
		runList = new ThreadNetworkListener(sock, this);
		threadList = new Thread(runList);
		threadList.start();
		runSend = new ThreadNetworkSender(sock, this);
		threadSend = new Thread(runSend);
		threadSend.start();
		
		System.out.println("fin thread");
		return true;
	}
	
	public boolean disconnect()
	{
		//Appeler les fonctions pour desactiver
		runList.desactive();
		runSend.desactive();
		
		return true;
	}

}
