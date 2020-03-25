package twist;

import twist.metier.Conteneur;
import twist.metier.Joueur;

public interface Controleur
{
    Conteneur[][] getConteneurs();

    int getLargeurPont();
    int getHauteurPont();

    int getNbJoueur();
    Joueur getJoueur(int i);
    Joueur getGagnant();
    int getScoreJoueur(int i);
    int getScoreJoueur(Joueur j);
    int getNumeroJoueur(Joueur joueur);

    boolean partieTerminee();

    void jouer(int x, int y, int coin);

}
