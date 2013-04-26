package base.engine;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import base.views.InGameMultiView;
import base.views.SalonView;
import base.engine.network.ThreadNetworkListener;
import base.engine.network.ThreadNetworkSender;

/**
 * NetworkEngine client
 * @author Yoann CAPLAIN
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
			Object mes = message_queue.poll();
			
			if(mes instanceof MessageTchat){
				// TODO envoyer l'objet aux 2 vues qui s'en servent
				((SalonView)Game.getStateByID(Game.SALON_VIEW_ID)).tchatReceiveMessage((MessageTchat) mes);
				((InGameMultiView)Game.getStateByID(Game.IN_GAME_MULTI_VIEW_ID)).tchatReceiveMessage((MessageTchat) mes);
				System.out.println("tchat network client");
			}else{
				if(mes instanceof Message){
					engineManager.receiveMessage((Message)mes);		
					System.out.println("message network client");
				}else
					System.out.println("rien network client");
			}
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
		
		return true;
	}

	public boolean rejoindrePartieViaID(final int id){
		if(runSend != null){
			Message mes = new Message();
			mes.instruction = MessageKey.I_JOIN_GAME;
			mes.i_data.put(MessageKey.P_ID, id);
			
			engineManager.getNetworkEngine().sendObject(mes);
			return true;
		}
		return false;
	}
	
	/**
	 * Envoie un object au server
	 * @param ob
	 */
	public void sendObject(Object ob){
		runSend.ajoutMessage(ob);
	}
	
	public boolean disconnect()
	{
		//Appeler les fonctions pour desactiver
		runList.desactive();
		runSend.desactive();
		
		return true;
	}

}
