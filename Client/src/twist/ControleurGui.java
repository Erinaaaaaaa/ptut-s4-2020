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

    public ControleurGui(String[] tabNomJoueur, int nbLock, int nbCol, int nbLigne)
    {
        this.pont = new Pont(tabNomJoueur, nbCol, nbLigne, nbLock);
        this.plateau = new IhmPlateau(this);
    }

    @Override
    public void jouer(int x, int y, int coin)
    {
        super.jouer(x, y, coin);
        Logger.information(x + " : " + y + " dans le coin " + coin);
        this.plateau.majIhm();
        if (this.partieTerminee())
            this.plateau.fin();
    }
}
