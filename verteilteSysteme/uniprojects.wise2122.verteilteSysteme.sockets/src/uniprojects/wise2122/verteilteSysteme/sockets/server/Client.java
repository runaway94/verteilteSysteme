package uniprojects.wise2122.verteilteSysteme.sockets.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String args[]) {

		System.out.println("Bitte geben Sie Ihre Nachricht ein:");
		Scanner sc = new Scanner(System.in);
		String userInput = sc.nextLine();
				
		try {
			Socket s = new Socket("localhost", 9998);
			
			InputStream in = s.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			OutputStream out = s.getOutputStream();
			PrintWriter writer = new PrintWriter(out);
			
			writer.println(userInput);
			writer.flush();
			String eingang = reader.readLine();
			
			System.out.println("Anwort vom Server: " + eingang);
			
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
	}
}