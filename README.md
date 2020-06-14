# Twist-Lock
Projet tutoré S4 à l'IUT du Havre (2019-2020)

## Principe du jeu

Ce jeu en tour par tour consiste à placer des locks (points de couleurs) au coins de conteneurs (rectangles). Le joueur avec le plus de locks autour d'un conteneur remporte le conteneur et gagne le nombre de points indiqués. Le joueur remportant le conteneur peut évoluer en cours de partie, et en cas d'égalité sur un conteneur (ex. 2 locks rouges vs 2 locks verts), personne ne remporte le conteneur, le laissant blanc.

## Compilation et exécution

Version minimale de Java requise: JDK 8

Il est recommandé d'utiliser IntelliJ pour exécuter le programme. En cas d'impossibilité, les instructions suivantes sont disponibles: 

### Serveur

1. Allez dans le dossier "Serveur"
2. Exécutez `run.bat` ou `run.sh` en fonction de votre système.

Utilisation:

```
run [port] [timeout]

port:    Port d'écoute du serveur. 
         Défaut: 9876
timeout: Temps de tour maximal en secondes accordé au joueur avant d'interrompre la partie.
         Défaut: 60 secondes
```

### Client joueur

1. Allez dans le dossier "Client"
2. Exécutez `run.bat` ou `run.sh` en fonction de votre système.

### Client IA

Ce client utilise une implémentation de l'algorithme Minimax.

1. Allez dans le dossier "Client"
2. Exécutez `run.bat` ou `run.sh` en fonction de votre système.
3. Suivez les instructions qui s'affichent dans votre terminal.
