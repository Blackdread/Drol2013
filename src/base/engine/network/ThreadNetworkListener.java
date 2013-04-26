package base.engine.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import base.engine.Message;
import base.engine.NetworkEngine;

public class ThreadNetworkListener implements Runnable{
	private Socket sock;
	private ObjectInputStream ois;
	private NetworkEngine net;
	boolean active;
	
	
	public ThreadNetworkListener(Socket s, NetworkEngine e) throws UnknownHostException, IOException {
		sock = s;
		ois = new ObjectInputStream(sock.getInputStream());
		net = e;
	}

	@Override
	public void run() {
		active = true;
		while(active)
		{
			try {
				Object o = ois.readObject();
				
				//if(o instanceof Message)
					//net.receiveMessage(((Message)o));
				net.receiveMessage(o);
					
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void desactive()
	{
		active = false;
	}

}
