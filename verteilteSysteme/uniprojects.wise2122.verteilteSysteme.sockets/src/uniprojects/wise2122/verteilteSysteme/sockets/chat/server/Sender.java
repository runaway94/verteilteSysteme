package uniprojects.wise2122.verteilteSysteme.sockets.chat.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Sender implements Runnable {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd yyyy, h:mm:ss a");
	private PrintWriter writer;

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
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String ping = sdf.format(timestamp);
			writer.println("It is now: " + ping);
			writer.flush();
		}
	}

}
