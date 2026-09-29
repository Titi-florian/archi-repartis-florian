# Analyse critique des limites du couplage fort (Séance 3)

Ce document analyse les limites du couplage fort observées concrètement lors de la mise en œuvre de notre service RMI et de son mécanisme de callback.

## 1. Couplage de langage et de plateforme
* Constat : Notre code repose entièrement sur le protocole Java RMI (`java.rmi.*` et la sérialisation binaire Java). L'interface `Calculatrice.java` impose l'utilisation de types Java stricts des deux côtés du réseau.
* Impact : Si nous voulons exposer ce service à un client écrit en Python ou en JavaScript (ex: application web moderne), c'est impossible sans réécrire une couche de passerelle complète, car le format de sérialisation et les stubs sont exclusifs à l'écosystème Java.

## 2. Disponibilité et fragilité des callbacks face aux pannes
* Constat : Les appels synchrones de RMI et les notifications par callback rendent le système sensible aux ruptures de connexion. Dans notre `CalculatriceImpl.java`, nous avons dû implémenter un mécanisme de désabonnement silencieux pour parer aux pannes :
  ```java
  for (NotificationListener listener : new ArrayList<>(listeners)) {
      try {
          listener.onNewOperation(entry);
      } catch (RemoteException e) {
          listeners.remove(listener); // Isole le client mort
      }
  }