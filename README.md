# Projet - Architecture Logicielle et Objets Répartis

Dépôt contenant les différentes implémentations pratiques du semestre (Sockets, RMI, REST, Événementiel, gRPC).

## Structure du projet
* **`socket/`** : Implémentations des services TCP bas niveau (Sockets et ServerSockets).
  * Service Echo (Partie B.1)
  * Service Calculateur TCP (Partie B.2)
* **`rmi/`** : Implémentations de services distribués via Java RMI (Remote Method Invocation).
  * Service de Calculateur distant et Historique (Séance 2)
  * Système de notifications par Callbacks et gestion de la concurrence thread-safe (Séance 3)
* **`ANALYSE.md`** : Analyse critique des limites du couplage fort en architecture répartie.

# Module /socket - Séance 1

## Instructions d'exécution

### Service de Calculatrice Distante
1. Lancer le serveur :
   ```bash
   java socket.CalcServer

   - `/rmi` : Séance 2 - Java RMI (Invocation de méthodes distantes et annuaire Registry).
   
   ## Exécution rapide
Consultez les fichiers `README.md` et `NOTES.md` présents dans chaque sous-dossier (`socket/`, `rmi/`) pour les instructions détaillées de compilation et d'exécution de chaque séance.