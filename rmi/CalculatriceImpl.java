package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatriceImpl extends UnicastRemoteObject implements Calculatrice {

    public CalculatriceImpl() throws RemoteException {
        super(); // Exporte l'objet sur un port anonyme
    }

    @Override
    public double add(double a, double b) throws RemoteException {
        System.out.println("Appel distant : add(" + a + ", " + b + ")");
        return a + b;
    }

    @Override
    public double sub(double a, double b) throws RemoteException {
        System.out.println("Appel distant : sub(" + a + ", " + b + ")");
        return a - b;
    }

    @Override
    public double mul(double a, double b) throws RemoteException {
        System.out.println("Appel distant : mul(" + a + ", " + b + ")");
        return a * b;
    }

    @Override
    public double div(double a, double b) throws RemoteException {
        System.out.println("Appel distant : div(" + a + ", " + b + ")");
        if (b == 0) {
            throw new RemoteException("Division par zéro impossible !");
        }
        return a / b;
    }
}