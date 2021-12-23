package uniprojects.wise2122.verteilteSysteme.sockets.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static String PRE_POST_FIX = "*";
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(9998); // Bind Application to port 9999
		while (true) {
			Socket client = null;
			try {
				System.out.println("Warten auf Verbindung...");
				client = server.accept(); // Wait for client connection
				
				InputStream in = client.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				
				OutputStream out = client.getOutputStream();
				PrintWriter writer = new PrintWriter(out);
				
				String antwort = reader.readLine();
				System.out.println(antwort);
				antwort = PRE_POST_FIX + antwort + PRE_POST_FIX;
				
				writer.println(antwort);
				writer.flush();
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (client != null)
					try {
						client.close();
					} catch (IOException e) {
					}
			}
		}
	}
}
