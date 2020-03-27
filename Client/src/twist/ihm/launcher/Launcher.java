package twist.ihm.launcher;

import javax.swing.*;

import twist.ControleurGui;

public class Launcher extends JFrame
{
    private final DialogInfoGenerale panelDemandeJ;
    private final DialogNomReseau panelReseau;
    private int nbJoueurs;
    private int nbLock;
    private int nbCol;
    private int nbLigne;

    public Launcher()
    {
        this.setSize(516, 705);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanImage panelAccueil = new PanImage(this);
        this.panelDemandeJ = new DialogInfoGenerale(this);
        this.panelReseau = new DialogNomReseau(this);
        this.setContentPane(panelAccueil);

        this.setVisible(true);

        //initialisation nbJ
        this.nbJoueurs = 0;
    }

    public void addPanDemandeJ()
    {
        this.panelDemandeJ.setVisible(true);
    }

    public void addPanReseau()
    {
        this.panelReseau.setVisible(true);
    }

    public void setNbJoueur(int nbJ)
    {
        this.nbJoueurs = nbJ;
    }

    public void setNbLock(int nbL)
    {
        this.nbLock = nbL;
    }

    public void setNbCol(int nbC)
    {
        this.nbCol = nbC;
    }

    public void setNbLgn(int nbL)
    {
        this.nbLigne = nbL;
    }

    public void addDialogNomJoueur()
    {
        new DialogNomJoueur(this.nbJoueurs, this);
    }

    public void debuterPartie(String[] tabNomJoueur,Boolean[] tabIA)
    {
        new ControleurGui(tabNomJoueur,tabIA, this.nbLock, this.nbCol, this.nbLigne);
        this.dispose();
    }

}
