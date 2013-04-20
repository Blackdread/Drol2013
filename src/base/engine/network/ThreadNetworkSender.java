package base.engine.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import base.engine.Message;
import base.engine.NetworkEngine;

public class ThreadNetworkSender implements Runnable{
	private Socket sock;
	private ObjectOutputStream oos;
	private NetworkEngine net;
	private Queue<Message> message_queue = new LinkedList<Message>();
	boolean active;
	

	public ThreadNetworkSender(Socket s, NetworkEngine e) throws IOException {
		sock = s;
		oos = new ObjectOutputStream(s.getOutputStream());
		net = e;
	}

	@Override
	public void run() {
		active = true;
		while(active)
		{
			Message m;
			if((m = retirerMessage()) != null)
			{
				try {
					oos.writeObject(m);
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
	
	synchronized public void ajoutMessage(Message m)
	{
		if(m != null)
		{
			message_queue.add(m);
		}
	}
	
	synchronized public Message retirerMessage()
	{
		return message_queue.poll();
	}
	
	public void desactive()
	{
		active = false;
	}

}