package uniprojects.wise2122.verteilteSysteme.sockets.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class RequestHandler implements Runnable {

	private final Socket clientRequest;

	private static final String HEADER = "HTTP/1.1 OK \n" + "Content-Type: text/html \n" + "Content-Lenght: %s \n"
			+ "\n";

	private static final String NOT_FOUND_404 = "HTTP/1.1 Not found \n" + "Content-Type: text/html \n"
			+ "Content-Lenght: 34 \n\n" + "Fehler 404: Datei nicht gefunden. \n" + "\n";

	public RequestHandler(Socket s) {
		this.clientRequest = s;
	}

	@Override
	public void run() {
		PrintWriter writer = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientRequest.getInputStream()));
			writer = new PrintWriter(clientRequest.getOutputStream());

			String request = reader.readLine();
			String[] requestParts = request.split(" ");
			String fileName = requestParts[1];

			System.out.println("Angeforderte Datei: " + fileName);

			System.out.println("akt. Pfad: " + Paths.get(".").toAbsolutePath().toString());
			List<String> linesInFile = Files.readAllLines(Paths.get("./resources" + fileName));

			int length = linesInFile.stream().mapToInt(line -> line.length() + 1).sum();
			String header = String.format(HEADER, length);
			writer.println(header);
			for (String zeile : linesInFile) {
				writer.println(zeile);
			}
			writer.flush();
			clientRequest.close();
		} catch (NoSuchFileException nf) {
			writer.println(NOT_FOUND_404);
			writer.flush();
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			clientRequest.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
