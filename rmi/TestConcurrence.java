package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TestConcurrence {
    public static void main(String[] args) {
        int nbClients = 5;
        int nbOperationsParClient = 20;

        System.out.println("Lancement du test de charge : " + nbClients + " clients parallèles, " + nbOperationsParClient + " opérations chacun.");

        for (int i = 1; i <= nbClients; i++) {
            final int clientId = i;
            new Thread(() -> {
                try {
                    Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                    Calculatrice calc = (Calculatrice) registry.lookup("CalculatriceService");

                    for (int j = 1; j <= nbOperationsParClient; j++) {
                        calc.add(clientId, j);
                    }
                    System.out.println("-> Client " + clientId + " a terminé ses requêtes avec succès.");
                } catch (Exception e) {
                    System.err.println("Erreur sur le client " + clientId + " : " + e.getMessage());
                }
            }).start();
        }
    }
}