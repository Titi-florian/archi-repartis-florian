package socket;

import java.io.*;
import java.net.*;

public class CalcServer {
    public static void main(String[] args) throws IOException {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Calculatrice Serveur en écoute sur le port " + port);
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
                    
                    String request = reader.readLine();
                    if (request != null) {
                        System.out.println("Requête reçue : " + request);
                        String response = compute(request);
                        writer.println(response);
                    }
                } catch (IOException e) {
                    System.err.println("Erreur de connexion client : " + e.getMessage());
                }
            }
        }
    }

    private static String compute(String request) {
        String[] parts = request.split(";");
        if (parts.length != 3) {
            return "ERREUR: Format invalide (attendu OPERATION;A;B)";
        }

        String op = parts[0].toUpperCase();
        double a, b;
        try {
            a = Double.parseDouble(parts[1]);
            b = Double.parseDouble(parts[2]);
        } catch (NumberFormatException e) {
            return "ERREUR: Paramètres numériques invalides";
        }

        switch (op) {
            case "ADD":
                return "RESULTAT: " + (a + b);
            case "SUB":
                return "RESULTAT: " + (a - b);
            case "MUL":
                return "RESULTAT: " + (a * b);
            case "DIV":
                if (b == 0) {
                    return "ERREUR: Division par zéro impossible";
                }
                return "RESULTAT: " + (a / b);
            default:
                return "ERREUR: Opération inconnue (" + op + ")";
        }
    }
}