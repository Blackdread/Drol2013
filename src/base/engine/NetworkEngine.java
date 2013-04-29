package base.engine;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import base.views.InGameMultiView;
import base.views.SalonView;
import base.views.MultiView;
import base.views.TransitionView;
import base.engine.entities.BasicEntity;
import base.engine.levels.LevelDrol;
import base.engine.network.InfoPartie;
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
			}else if(mes instanceof BasicEntity){
			
				((BasicEntity) mes).setEngineManager(engineManager);	// car l'engine est celui du serveur donc on met le bon
				engineManager.addEntity((BasicEntity) mes);	// TODO la fonction est a jour ??
				
			}else if(mes instanceof Player){
				Player tmp = ((InGameMultiView)Game.getStateByID(Game.IN_GAME_MULTI_VIEW_ID)).getPlayer();
				if(tmp == null)
					tmp = new Player(engineManager, "");
				tmp.copy((Player) mes);
				tmp.setEngineManager(engineManager);
				
				//((SalonView)Game.getStateByID(Game.SALON_VIEW_ID)).addNewPlayer(tmp);
				
				//((MultiView)Game.getStateByID(Game.MULTI_VIEW_ID)).setPlayer(tmp); TODO pas sur
			
			}else if(mes instanceof ArrayList){
				/*
				 * On suppose que les ArrayList envoyer ne contiennent que des objets du meme type
				 */
				ArrayList<?> array = (ArrayList<?>)mes;
				
				if(!array.isEmpty()){
					if(array.get(0) instanceof Player){
						((SalonView)Game.getStateByID(Game.SALON_VIEW_ID)).remplacerArrayPlayer((ArrayList<Player>) array);
						((InGameMultiView)Game.getStateByID(Game.IN_GAME_MULTI_VIEW_ID)).remplacerArrayPlayer((ArrayList<Player>) array);
					}
					if(array.get(0) instanceof InfoPartie)
						((MultiView)Game.getStateByID(Game.MULTI_VIEW_ID)).remplacerArrayPartie((ArrayList<InfoPartie>) array);
				}
			}else if(mes instanceof LevelDrol){
				//*
				if(engineManager.getCurrentLevelUsed() == null){
					engineManager.setCurrentLevelUsed((LevelDrol)mes);
					System.out.println("level remplacer client");
				}else{
					engineManager.getCurrentLevelUsed().copy((LevelDrol)mes);
					System.out.println("level copy client");
					
					
					// CECI EST UNE HORREUR, IL VAUT ENVOYER UNE BASICENTITY A LA FOIS ET LA REMPLACER CAR ON ENVOIE
					// UNE ENTITE SEULEMENT SI ELLE A CHANGER D'ETAT
					for(BasicEntity e : engineManager.getCurrentLevelUsed().getArrayEntite().values())
						if(e != null)
							e.setEngineManager(engineManager);
				}//*/
				
				
			}else{
				if(mes instanceof Message){
					// TODO faudrait mettre en place le RMI mais on n'a pas le temps
	        		Message mes2 = (Message)mes;
	        		switch(mes2.instruction){
	        		case MessageKey.I_CHANGE_VIEW_TO_SALON:
	        			((MultiView)Game.getStateByID(Game.MULTI_VIEW_ID)).gotoSalonView();
	        			System.out.println("go to Salon client");
	        			break;
	        		case MessageKey.I_CHANGE_VIEW_TO_LOADING:
	        			((SalonView)Game.getStateByID(Game.SALON_VIEW_ID)).goToTransitionView();
	        			System.out.println("go to transition client");
	        			break;
	        		case MessageKey.I_CHANGE_VIEW_TO_GAME:
	        			((TransitionView)Game.getStateByID(Game.TRANSITION_VIEW_ID)).goToInGameMultiView();
	        			System.out.println("go to inGameView client");
	        			break;
	        		default:
	        			if(mes2.engine != EngineManager.NETWORK_ENGINE)
							engineManager.receiveMessage(mes2);	
	        			break;
	        		}
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

	/**
	 * 
	 * @param ip
	 * @param port
	 * @return true if connected to server
	 * @throws UnknownHostException
	 * @throws IOException
	 */
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

	/**
	 * Envoie un message au serveur que le client veut rejoindre la partie qui a cet ID
	 * @param id ID de la partie
	 * @return true if it has sent the message
	 */
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
	 * Envoie au serveur qu'il a fini de charger la partie (le level)
	 */
	public void envoyerLoadingIsFinished(){
		if(runSend != null){
			Message mes = new Message();
			mes.instruction = MessageKey.I_CLIENT_END_LOADING;
			
			runSend.ajoutMessage(mes);
		}else
			System.err.println("runSend is null - Send loading over");
	}
	
	/**
	 * 
	 */
	public void demanderUnRefreshListePartieServer(){
		if(runSend != null){
			Message mes = new Message();
			mes.instruction = MessageKey.I_REFRESH_LIST_PARTIE;
			
			runSend.ajoutMessage(mes);
		}//else
			//System.err.println("runSend is null - Send refresh - normal si non connecte a un serveur");
	}
	
	public void lancerPartie(){
		if(runSend != null){
			Message mes = new Message();
			mes.instruction = MessageKey.I_LAUNCH_GAME;
			
			runSend.ajoutMessage(mes);
		}else
			System.err.println("runSend is null - Launch partie");
	}
	
	/**
	 * Envoie un object au server
	 * Les ArrayList envoyes sont supposes contenir qu'un seul type de classe (a part l'heritage bien sur)
	 * @param ob
	 */
	public void sendObject(Object ob){
		if(runSend != null)
			runSend.ajoutMessage(ob);
		else
			System.err.println("runSend is null - Send Object");
	}
	
	public boolean disconnect()
	{
		//Appeler les fonctions pour desactiver
		runList.desactive();
		runSend.desactive();
		
		return true;
	}

}
