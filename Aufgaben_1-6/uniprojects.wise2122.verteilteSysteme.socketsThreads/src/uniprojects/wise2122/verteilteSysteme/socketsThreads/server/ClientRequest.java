package uniprojects.wise2122.verteilteSysteme.socketsThreads.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientRequest implements Runnable {

	private final Socket s;
	private final MessageStore store;

	public static String TERMINATED = "Application terminated.";

	public ClientRequest(Socket s, MessageStore store) {
		this.s = s;
		this.store = store;
	}

	@Override
	public void run() {
		try {
			InputStream in = this.s.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			OutputStream out = this.s.getOutputStream();
			PrintWriter writer = new PrintWriter(out);

			String input = null;

			do {
				input = reader.readLine();
				String[] split = input.split("#");
				if (split.length == 0) {
					continue;
				}
				switch (split[0]) {
				case "REG":
					if (split.length != 2) {
						writer.println("Ungültige eingabe.");
					} else {

						this.store.register(split[1]);
						writer.println("OK, " + split[1]);
					}
					break;
				case "SND":
					if (split.length != 3) {
						writer.println("Ungültige eingabe.");
					} else {

						boolean sent = this.store.writeMessage(split[1], split[2]);
						if (sent) {
							writer.println("Message sent succesfully.");
						} else {
							writer.println(split[1] + " is not registered.");
						}
					}
					break;
				case "RCV":
					if (split.length != 2) {
						writer.println("Ungültige eingabe.");
					} else {
						List<String> messages = this.store.retrieve(split[1]);
						writer.println(split[1] + " has messages: " + messages.toString());
					}
					break;
				case "ESC":
					writer.println(TERMINATED);
					writer.flush();
					this.s.close();
					return;
				default:
					writer.println("Ungültige eingabe.");
				}
				writer.flush();

			} while (true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
