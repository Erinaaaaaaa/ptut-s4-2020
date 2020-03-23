package twist;

import twist.ihm.Fenetre;
import twist.metier.Pont;
import twist.ihm.launcher.Launcheur;

public class ControleurGui implements Controleur
{
    private Pont pont;

    public ControleurGui(){new Launcher(this);}

		public void NouveauJeu(String[] tabNomJoueur,int nbLock,int nbCol,int nbLigne){
			this.pont = new Pont(this, tabNomJoueur, nbLigne, nbCol, nbLock);
		}
    public static void main(String[] args){ new ControleurGui(); }
}
