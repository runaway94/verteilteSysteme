package uniprojects.wise2122.verteilteSysteme.sockets.chat.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Sender implements Runnable {

	private PrintWriter writer;
	Scanner sc = new Scanner(System.in);

	public Sender(Socket s) {
		try {
			OutputStream out = s.getOutputStream();
			this.writer = new PrintWriter(out);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
			String userInput = sc.nextLine();
			if(userInput.toLowerCase().equals("bye")) {
				return;
			}
			writer.println(userInput);
			writer.flush();
		}
	}

}
