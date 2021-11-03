package uniprojects.wise2122.verteilteSysteme.socketsThreads.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessagingService {

	private static final int PORT = 1212;
	private final MessageStore store;
	private final ExecutorService threadPool;

	public MessagingService(int handlers) {
		this.threadPool = Executors.newFixedThreadPool(handlers);
		this.store = new MessageStore();
		startConnection();
	}

	private void startConnection() {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			while (true) {
				Socket client = serverSocket.accept();
				this.threadPool.execute(new ClientRequest(client, store));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MessagingService(10);
	}

}
