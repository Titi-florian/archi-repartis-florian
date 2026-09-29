# Module RMI - Séance 2 & Séance 3

Ce module implémente une architecture répartie orientée objet en utilisant **Java RMI (Remote Method Invocation)**, enrichie par un système de notifications asynchrones (Callbacks) et une gestion robuste de la concurrence multi-clients.

##  Fonctionnalités (Séance 3)
* **Appels distants RMI** : Exposition de méthodes de calcul (`add`, `sub`, `mul`, `div`) et consultation d'historique.
* **Système de Callbacks** : Enregistrement dynamique de clients (`subscribe` / `unsubscribe`) pour recevoir des notifications en temps réel lors de chaque nouvelle opération effectuée.
* **Concurrence & Thread-Safety** : Utilisation d'une structure `CopyOnWriteArrayList` pour sécuriser les listes d'écouteurs partagées entre plusieurs threads sans verrouillage bloquant.
* **Test de charge** : Programme de validation (`TestConcurrence.java`) simulant des clients parallèles intensifs.

##  Compilation et Exécution (depuis la racine du projet)

1. **Compiler l'ensemble des fichiers du package rmi** :
   javac rmi/*.java
