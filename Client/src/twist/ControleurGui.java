package twist;

import twist.ihm.Fenetre;
import twist.metier.Conteneur;
import twist.metier.Joueur;
import twist.metier.Pont;
import twist.ihm.launcher.Launcher;
import twist.ihm.jeu.IhmPlateau;

public class ControleurGui implements Controleur
{
    private Pont pont;
    private IhmPlateau plateau;

    public ControleurGui()
    {
        new Launcher(this);
    }

    public void NouveauJeu(String[] tabNomJoueur, int nbLock, int nbCol, int nbLigne)
    {
        this.pont = new Pont(this, tabNomJoueur, nbLigne, nbCol, nbLock);
        this.plateau = new IhmPlateau(this);
    }

    public static void main(String[] args)
    {
        new ControleurGui();
    }

    @Override
    public Conteneur[][] getConteneurs()
    {
        // TODO
        return null;
    }

    @Override
    public int getLargeurPont()
    {
        // TODO
        return 0;
    }

    @Override
    public int getHauteurPont()
    {
        // TODO
        return 0;
    }

    @Override
    public int getNbJoueur()
    {
        // TODO
        return 0;
    }

    @Override
    public Joueur getJoueur(int i)
    {
        // TODO
        return null;
    }

    @Override
    public int getScoreJoueur(int i)
    {
        // TODO
        return 0;
    }

    @Override
    public int getNumeroJoueur(Joueur joueur)
    {
        // TODO
        return 0;
    }

    @Override
    public boolean partieTerminee()
    {
        // TODO
        return false;
    }

    @Override
    public void jouer(int x, int y, int coin)
    {

    }
}
