package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import uniprojects.wise2122.verteilteSysteme.socketsThreads.server.ClientRequest;

public class Client {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try {
			while (true) {
				Socket s = new Socket("localhost", 1212);
				InputStream in = s.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				OutputStream out = s.getOutputStream();
				PrintWriter writer = new PrintWriter(out);

				String userInput = sc.nextLine();
				writer.println(userInput);
				writer.flush();
	
				String eingang = reader.readLine();
				System.out.println(eingang);
				if(eingang.equals(ClientRequest.TERMINATED)) {
					System.out.println("terminating...");
					s.close();
					sc.close();
					return;
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}
