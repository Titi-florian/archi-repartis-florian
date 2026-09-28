package socket;

import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur en écoute sur le port " + port);
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
                    
                    System.out.println("Client connecté : " + clientSocket.getRemoteSocketAddress());
                    String line = reader.readLine();
                    if (line != null) {
                        System.out.println("Reçu : " + line);
                        writer.println(line); // Renvoie la ligne telle quelle (écho)
                    }
                } catch (IOException e) {
                    System.err.println("Erreur avec un client : " + e.getMessage());
                }
            }
        }
    }
}