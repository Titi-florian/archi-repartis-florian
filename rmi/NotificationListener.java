package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NotificationListener extends Remote {
    void onNewOperation(String entry) throws RemoteException;
}