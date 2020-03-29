package twist;

import twist.metier.Conteneur;
import twist.metier.Joueur;
import twist.metier.Pont;

/*
 * Classe abstraite Controleur.java
 * Classe servant de base aux autres Controleur, GUI ou Réseau
 */

public abstract class Controleur
{
    protected Pont pont;

    public Conteneur[][] getConteneurs() { return this.pont.getConteneurs(); }

    public int getLargeurPont(){ return this.pont.getLargeur(); }
    public int getHauteurPont(){ return this.pont.getHauteur(); }

    public int getNbJoueur(){ return this.pont.getNbJoueur(); }
    public Joueur getJoueur(int i){ return this.pont.getJoueur(i); }
    public Joueur getGagnant(){ return this.pont.getGagnant(); }
    public int getScoreJoueur(int i){ return this.pont.getScoreJoueur(i); }

    public int getNumeroJoueur(Joueur j){ return this.pont.getNumeroJoueur(j); }
    public int getJoueurActif() { return this.pont.getJoueurActif(); }


    public boolean partieTerminee(){ return this.pont.partieTerminee(); }

    public void jouer(int x, int y, int coin) { this.pont.jouer(x, y, coin); }

		public void majIhm(){}
			public void finPartie(){}

}
