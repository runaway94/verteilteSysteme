package solutionFour.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import solutionFour.util.InputStreamListener;
import solutionFour.util.KeyboardListener;

public class Server {

    public static final int PORT = 1212;
    public static final String END_STRING = "Ende";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket client = serverSocket.accept();

                new Thread(new InputStreamListener(client)).start();
                new Thread(new KeyboardListener(client)).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
