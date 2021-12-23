package uniprojects.wise2122.verteilteSysteme.sockets.chat.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main(String args[]) {
		try {
			//Socket s = new Socket("im-lamport.oth-regensburg.de", 1212);
			Socket s = new Socket("localhost", 1212);
			Sender sender = new Sender(s);
			Reciever empfänger = new Reciever(s);
			new Thread(sender).start();
			new Thread(empfänger).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
