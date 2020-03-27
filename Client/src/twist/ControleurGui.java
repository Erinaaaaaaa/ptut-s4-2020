package twist;

import twist.metier.Pont;
import twist.ihm.jeu.IhmPlateau;
import twist.util.Logger;

/*
 * Classe ControleurGui.java
 * Classe se servant de Controleur.java comme référence et qui permet de lancer le jeu en mode GUI
 * Gère une partie locale
 */

public class ControleurGui extends Controleur
{
    private final IhmPlateau plateau;

    public ControleurGui(String[] tabNomJoueur,Boolean[] tabIA, int nbLock, int nbCol, int nbLigne)
    {
        this.pont = new Pont(this,tabNomJoueur, tabIA, nbCol, nbLigne, nbLock);
        this.plateau = new IhmPlateau(this);
				this.pont.faireJouerIA();
    }

    @Override
    public void jouer(int x, int y, int coin)
    {
        super.jouer(x, y, coin);
        Logger.information(x + " : " + y + " dans le coin " + coin);
        this.majIhm();
				pont.faireJouerIA();
        if (this.partieTerminee())
            this.plateau.fin();
    }

		public void majIhm(){this.plateau.majIhm();}
			public void finPartie(){this.plateau.fin();}
}
