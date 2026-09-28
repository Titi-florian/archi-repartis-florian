package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI {
    public static void main(String[] args) {
        try {
            // Connexion à l'annuaire RMI distant (ou local)
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            
            // Récupération de l'objet distant par son nom
            Calculatrice calc = (Calculatrice) registry.lookup("CalculatriceService");
            
            // Test des méthodes distantes
            System.out.println("10 + 5 = " + calc.add(10, 5));
            System.out.println("20 - 8 = " + calc.sub(20, 8));
            System.out.println("6 * 7 = " + calc.mul(6, 7));
            System.out.println("100 / 4 = " + calc.div(100, 4));

        } catch (Exception e) {
            System.err.println("Erreur du client RMI : " + e.getMessage());
            e.printStackTrace();
        }
    }
}