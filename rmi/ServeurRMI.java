package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServeurRMI {
    public static void main(String[] args) {
        try {
            // Création de l'annuaire RMI sur le port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Instanciation de l'objet distant
            Calculatrice calc = new CalculatriceImpl();
            
            // Enregistrement de l'objet avec un nom
            registry.rebind("CalculatriceService", calc);
            
            System.out.println("Serveur RMI prêt et en attente de requêtes...");
        } catch (Exception e) {
            System.err.println("Erreur du serveur RMI : " + e.getMessage());
            e.printStackTrace();
        }
    }
}