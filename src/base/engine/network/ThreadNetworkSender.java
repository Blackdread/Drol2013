package base.engine.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import base.engine.NetworkEngine;

@SuppressWarnings("unused")
public class ThreadNetworkSender implements Runnable{
	private Socket sock;
	private ObjectOutputStream oos;
	private NetworkEngine net;
	//private Queue<Message> message_queue = new LinkedList<Message>();
	private Queue<Object> message_queue = new LinkedList<Object>();	// Je commence a donner la possibilite d'envoyer tout ce qu'on veut
	boolean active;
	

	public ThreadNetworkSender(Socket s, NetworkEngine e) throws IOException {
		s.setTcpNoDelay(true);
		sock = s;
		oos = new ObjectOutputStream(s.getOutputStream());
		net = e;
	}

	@Override
	public void run() {
		active = true;
		while(active)
		{
			Object m;
			if((m = retirerMessage()) != null)
			{
				try {
					oos.writeObject(m);
					oos.flush();
					oos.reset();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		try {
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	synchronized public void ajoutMessage(Object m)
	{
		if(m != null)
		{
			message_queue.add(m);
		}
	}
	
	synchronized public Object retirerMessage()
	{
		return message_queue.poll();
	}
	
	public void desactive()
	{
		active = false;
	}

}
