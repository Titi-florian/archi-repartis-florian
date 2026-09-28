package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CalculatriceImpl extends UnicastRemoteObject implements Calculatrice {

    // Liste thread-safe pour stocker les écouteurs abonnés (séance 3)
    private final List<NotificationListener> listeners = new CopyOnWriteArrayList<>();
    private final List<String> history = new CopyOnWriteArrayList<>();

    public CalculatriceImpl() throws RemoteException {
        super();
    }

    private void notifyListeners(String operation) {
        // Version robuste : un client mort ne doit pas bloquer les autres (désabonnement silencieux)[cite: 1]
        for (NotificationListener listener : new ArrayList<>(listeners)) {
            try {
                listener.onNewOperation(operation);
            } catch (RemoteException e) {
                System.err.println("Client injoignable, suppression du listener déconnecté.");
                listeners.remove(listener);
            }
        }
    }

    @Override
    public double add(double a, double b) throws RemoteException {
        double res = a + b;
        String entry = "ADD(" + a + ", " + b + ") = " + res;
        history.add(entry);
        notifyListeners(entry);
        return res;
    }

    @Override
    public double sub(double a, double b) throws RemoteException {
        double res = a - b;
        String entry = "SUB(" + a + ", " + b + ") = " + res;
        history.add(entry);
        notifyListeners(entry);
        return res;
    }

    @Override
    public double mul(double a, double b) throws RemoteException {
        double res = a * b;
        String entry = "MUL(" + a + ", " + b + ") = " + res;
        history.add(entry);
        notifyListeners(entry);
        return res;
    }

    @Override
    public double div(double a, double b) throws RemoteException {
        if (b == 0) {
            throw new RemoteException("Division par zéro impossible !");
        }
        double res = a / b;
        String entry = "DIV(" + a + ", " + b + ") = " + res;
        history.add(entry);
        notifyListeners(entry);
        return res;
    }

    @Override
    public void subscribe(NotificationListener listener) throws RemoteException {
        listeners.add(listener);
        System.out.println("Nouveau client abonné aux notifications. Total abonnés : " + listeners.size());
    }

    @Override
    public void unsubscribe(NotificationListener listener) throws RemoteException {
        listeners.remove(listener);
        System.out.println("Client désabonné. Total abonnés : " + listeners.size());
    }
}