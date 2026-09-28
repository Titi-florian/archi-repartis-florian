package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ClientRMI extends UnicastRemoteObject implements NotificationListener {

    public ClientRMI() throws RemoteException {
        super();
    }

    @Override
    public void onNewOperation(String entry) throws RemoteException {
        System.out.println("\n[NOTIFICATION REÇUE] -> " + entry);
        System.out.print("Entrez une opération (ex: add 10 5) ou 'exit' : ");
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculatrice calc = (Calculatrice) registry.lookup("CalculatriceService");

            // Instanciation du client en tant qu'objet distant pour le callback
            ClientRMI client = new ClientRMI();
            calc.subscribe(client);
            System.out.println("Abonnement aux notifications réussi !");

            Scanner scanner = new Scanner(System.in);
            System.out.println("\nCommandes disponibles : add a b | sub a b | mul a b | div a b | exit");

            while (true) {
                System.out.print("Entrez une opération : ");
                String line = scanner.nextLine().trim();
                
                if ("exit".equalsIgnoreCase(line)) {
                    calc.unsubscribe(client);
                    break;
                }

                String[] parts = line.split("\\s+");
                if (parts.length == 3) {
                    String op = parts[0].toLowerCase();
                    double a = Double.parseDouble(parts[1]);
                    double b = Double.parseDouble(parts[2]);

                    double result = 0;
                    switch (op) {
                        case "add": result = calc.add(a, b); break;
                        case "sub": result = calc.sub(a, b); break;
                        case "mul": result = calc.mul(a, b); break;
                        case "div": result = calc.div(a, b); break;
                        default:
                            System.out.println("Opération inconnue.");
                            continue;
                    }
                    System.out.println("Résultat local -> " + result);
                } else {
                    System.out.println("Format incorrect. Utilisez : operation a b");
                }
            }

            scanner.close();
            System.exit(0);

        } catch (Exception e) {
            System.err.println("Erreur du client RMI : " + e.getMessage());
            e.printStackTrace();
        }
    }
}