package ChatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
	
	private static final int PORT = 1212;
	private final ChatAdministration chat;
	private final ExecutorService threadPool;
	
	public ChatServer(int handlers) {
		this.threadPool = Executors.newFixedThreadPool(handlers);
		this.chat = new ChatAdministration();
		startConnection();
	}
	
	private void startConnection() {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			while (true) {
				Socket client = serverSocket.accept();
				this.threadPool.execute(new OnlineUser(client, chat));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		new ChatServer(10);
	}
	

}
