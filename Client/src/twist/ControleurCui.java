package twist;

import twist.cui.IhmCui;
import twist.metier.Conteneur;
import twist.metier.Joueur;
import twist.metier.Pont;

public class ControleurCui implements Controleur
{
    private Pont pont;
    private IhmCui ihmCui;

    public ControleurCui()
    {
        this.pont = new Pont(this, new String[]{"Joueur 1", "Joueur 2"}, 5,5,5);
        this.ihmCui = new IhmCui(this);
    }

    public Conteneur[][] getConteneurs()
    {
        return pont.getPlateau();
    }

    @Override
    public int getLargeurPont()
    {
        return this.pont.getLargeur();
    }

    @Override
    public int getHauteurPont()
    {
        return this.pont.getHauteur();
    }

    @Override
    public int getNbJoueur()
    {
        return this.pont.getNbJoueur();
    }

    @Override
    public Joueur getJoueur(int i)
    {
        return this.pont.getJoueur(i);
    }

    @Override
    public Joueur getGagnant()
    {
        return pont.getGagnant();
    }

    @Override
    public int getScoreJoueur(int i)
    {
        return this.pont.getScoreJoueur(i);
    }

    @Override
    public int getScoreJoueur(Joueur j)
    {
        return pont.getScoreJoueur(j);
    }

    @Override
    public int getNumeroJoueur(Joueur joueur)
    {
        return this.pont.getNumeroJoueur(joueur);
    }

    @Override
    public boolean partieTerminee()
    {
        return pont.partieTerminee();
    }

    @Override
    public void jouer(int x, int y, int coin)
    {
        pont.placerLock(x, y, coin);
    }

    public static void main(String[] args)
    {
        new ControleurCui();
    }
}
