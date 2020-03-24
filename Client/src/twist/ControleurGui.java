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
        this.pont = new Pont(this, tabNomJoueur, nbCol, nbLigne, nbLock);
        this.plateau = new IhmPlateau(this);
    }

    public static void main(String[] args)
    {
        new ControleurGui();
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
    public int getScoreJoueur(int i)
    {
        return this.pont.getScoreJoueur(i);
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
				System.out.println(x+" : "+y+" dans le coin "+coin);
				if (!this.partieTerminee()) {this.plateau.majIhm();}
				else {this.plateau.majIhm();this.plateau.fin();}
    }

		public int getJoueurActif(){return pont.getJoueurActif();}
}
