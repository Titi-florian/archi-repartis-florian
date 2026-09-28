# Notes de réflexion - Séance 1 (Sockets TCP)

## 1. Questions de réflexion sur la robustesse et les pannes
- **Client inactif sans rien envoyer** : Le serveur reste bloqué indéfiniment sur `reader.readLine()`, en attente d'une fin de ligne.
- **Déconnexion brutale du client (Ctrl+C)** :
  - Message d'exception exact observé côté serveur : `java.net.SocketException: Connection reset`.
  - Comportement : Le thread gère l'exception dans le bloc `catch`, ferme la ressource proprement, et le serveur reste actif pour les autres clients.