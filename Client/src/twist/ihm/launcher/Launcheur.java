package twist.ihm.launcher;

import javax.swing.*;

import twist.ControleurGui;

public class Launcheur extends JFrame
{
	private PanImage panelAccueil;
	private DialogInfoGenerale panelDemandeJ;
	private DialogNomJoueur panNomsJ;
	private ControleurGui ctrl;
	private int nbJoueurs;
	private int nbLock;
	private int nbCol;
	private int nbLigne;

	public Launcheur(ControleurGui ctrl){
		this.ctrl = ctrl;
		this.setSize(516,705);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.panelAccueil  = new PanImage(this);
		this.panelDemandeJ = new DialogInfoGenerale(this);
		this.setContentPane(this.panelAccueil);

		this.setVisible(true);

		//initialisation nbJ
		this.nbJoueurs = 0;
	}

	public void addPanDemandeJ()
	{
		this.panelDemandeJ.setVisible(true);
	}

	public void setNbJoueur(int nbJ){ this.nbJoueurs=nbJ; }
	public void setNbLock  (int nbL){ this.nbLock=nbL;    }
	public void setNbCol   (int nbC){ this.nbCol=nbC;     }
	public void setNbLgn   (int nbL){ this.nbLigne=nbL;   }

	public void addDialogNomJoueur(){this.panNomsJ = new DialogNomJoueur(this.nbJoueurs,this);}

	public void debuterPartie(String[] tabNomJoueur){
		this.ctrl.NouveauJeu(tabNomJoueur,this.nbLock,this.nbCol,this.nbLigne);
		this.dispose();
	}

}
