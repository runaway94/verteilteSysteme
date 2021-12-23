package ChatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.MessageFormat;

public class OnlineUser implements Runnable {

	private final Socket s;
	private String name = "user";
	private final ChatAdministration chat;
	private BufferedReader reader;
	private PrintWriter writer;

	public OnlineUser(Socket client, ChatAdministration chat) {
		this.s = client;
		this.chat = chat;

		try {
			InputStream in = this.s.getInputStream();
			this.reader = new BufferedReader(new InputStreamReader(in));
			OutputStream out = this.s.getOutputStream();
			this.writer = new PrintWriter(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return this.name;
	}

	@Override
	public void run() {
		String input = null;
		while (true) {
			try {
				input = reader.readLine();
				if (handleInput(input)) {
					this.s.close();
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean handleInput(String input) {
		String[] split = input.split("#");
		if (split.length == 0) {
			return true;
		}
		switch (split[0]) {
		case "OPEN":
			if (split.length != 2) {
				return true;
			}
			this.name = split[1];
			this.chat.userEntered(this);
			return true;
		case "EXIT":
			this.chat.userLeft(this);
			return false;
		case "PUBL":
			if (split.length != 2) {
				return true;
			}
			this.chat.sendMsg(this, split[1]);
			return true;
		}
		return true;
	}

	public void sendMessage(String user, String message) {
		if (writer == null) {
			return;
		}
		String query = String.format("SHOW#%s#%s", user, message);
		writer.println(query);
		writer.flush();
	}

	public void sendAdminMessage(String message) {
		if (writer == null) {
			return;
		}
		String query = String.format("ADMN#%s", message);
		writer.println(query);
		writer.flush();
	}

}
