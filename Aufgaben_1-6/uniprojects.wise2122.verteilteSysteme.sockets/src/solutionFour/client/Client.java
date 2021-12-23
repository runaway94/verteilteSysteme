package solutionFour.client;

import solutionFour.util.InputStreamListener;
import solutionFour.util.KeyboardListener;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("im-lamport.oth-regensburg.de", 1213);

            new Thread(new InputStreamListener(socket)).start();
            new Thread(new KeyboardListener(socket)).start();

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

}
