package socket;

import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(hostname, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Entrez un texte à envoyer au serveur : ");
            String textToSend = keyboard.readLine();

            writer.println(textToSend);
            String response = reader.readLine();
            System.out.println("Réponse du serveur : " + response);

        } catch (UnknownHostException e) {
            System.err.println("Serveur inconnu : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur d'E/S : " + e.getMessage());
        }
    }
}