package uniprojects.wise2122.verteilteSysteme.sockets.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Reciever implements Runnable {

	private BufferedReader reader;
	private PrintWriter writer;
	private static final String PREFIX = ">";

	public Reciever(Socket s) {
		InputStream in;
		try {
			in = s.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			this.reader = reader;
			OutputStream out = s.getOutputStream();
			this.writer = new PrintWriter(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			String eingang;
			try {
				eingang = this.reader.readLine();
				writer.println(PREFIX + eingang);
				writer.flush();
				if (eingang.equals("bye")) {
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
