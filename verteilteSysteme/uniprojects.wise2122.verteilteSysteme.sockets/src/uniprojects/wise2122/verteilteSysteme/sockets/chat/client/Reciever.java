package uniprojects.wise2122.verteilteSysteme.sockets.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Reciever implements Runnable {

	private BufferedReader reader;

	public Reciever(Socket s) {
		try {
			InputStream in = s.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			this.reader = reader;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			String eingang;
			try {
				eingang = this.reader.readLine();
				if(eingang == null) {
					return;
				}
				System.out.println(eingang);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
