package socket;

import java.io.*;
import java.net.*;

public class CalcClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(hostname, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Entrez l'opération (ex: ADD;4;7) : ");
            String request = keyboard.readLine();

            writer.println(request);
            String response = reader.readLine();
            System.out.println("Réponse du serveur -> " + response);

        } catch (IOException e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }
}