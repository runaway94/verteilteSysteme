package uniprojects.wise2122.verteilteSysteme.sockets.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	
	
	public static void main(String[] args) throws IOException {
				
		ServerSocket server = new ServerSocket(1212);
		while (true) {
			Socket client = null;
			try {
				System.out.println("Warten auf Verbindung...");
				client = server.accept();
				Sender s = new Sender(client);
				Reciever r = new Reciever(client);
				new Thread(s).start();
				new Thread(r).start();	
			}
			catch(IOException e) {
				
				
			}
		}
	}

}
