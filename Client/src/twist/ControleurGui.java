package twist;

import twist.ihm.Fenetre;
import twist.metier.Pont;
import twist.ihm.launcher.Launcher;
import twist.ihm.jeu.IhmPlateau;

public class ControleurGui implements Controleur
{
    private Pont pont;
		private IhmPlateau plateau;
    public ControleurGui(){new Launcher(this);}

		public void NouveauJeu(String[] tabNomJoueur,int nbLock,int nbCol,int nbLigne){
			this.pont    = new Pont(this, tabNomJoueur, nbLigne, nbCol, nbLock);
			this.plateau = new IhmPlateau(this);
		}

    public static void main(String[] args){ new ControleurGui(); }
}
