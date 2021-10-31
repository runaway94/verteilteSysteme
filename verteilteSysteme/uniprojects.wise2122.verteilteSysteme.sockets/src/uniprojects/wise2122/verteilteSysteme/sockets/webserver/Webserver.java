package uniprojects.wise2122.verteilteSysteme.sockets.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Webserver {
	
	private final int port = 8080;
	private final ExecutorService threadPool;
	
	public Webserver(int handlers) {
		this.threadPool = Executors.newFixedThreadPool(handlers);
	}
	
	
	public static void main(String args[]) {
		new Webserver(10).startConnection();;
	}
	
	private void startConnection() {
		try {
			ServerSocket server = new ServerSocket(port);
			System.out.println("Server gestartet...");
			while(true) {
				Socket clientRequest = server.accept();
				System.out.println("Neuer Request von " + clientRequest.getInetAddress().getHostAddress());
				
				Runnable requestHandler = new RequestHandler(clientRequest);
				this.threadPool.execute(requestHandler);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
