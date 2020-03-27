package twist.ihm.jeu;

import twist.Controleur;

import javax.swing.*;
import java.awt.*;

public class IhmPlateau extends JFrame
{

    public static final String[] PLACES_MAIN = new String[]{"West", "North", "East", "South"};
    private final Plateau plateau;
    private final PanelJoueur[] tabJoueur;
    private final Controleur ctrl;

    public IhmPlateau(Controleur ctrl)
    {
        this.setTitle("Jeu des locks");
        this.ctrl = ctrl;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setLocationRelativeTo(null);

        this.plateau = new Plateau(this.ctrl);
        this.add(this.plateau, BorderLayout.CENTER);

        this.tabJoueur = new PanelJoueur[this.ctrl.getNbJoueur()];

        int dj = 0;
        if (this.ctrl.getNbJoueur() == 2) dj = 2;
        for (int i = 0; i < this.tabJoueur.length; i++)
        {
            tabJoueur[i] = new PanelJoueur(ctrl, i, dj != 2 && (i == 1 || i == 3));
            if (dj == 2)
            {
                this.add(tabJoueur[i], PLACES_MAIN[i * dj]);
            } else
            {
                this.add(tabJoueur[i], PLACES_MAIN[i]);
            }
        }
        this.pack();
        this.setVisible(true);
    }

    public void majIhm()
    {
        this.plateau.miseAJour();
        for (PanelJoueur panelJoueur : this.tabJoueur)
        {
            panelJoueur.miseAJour();
        }
        this.repaint();
    }

    public void fin()
    {
        new DialogFinJeu(this, this.ctrl);
    }

    public void fin(String s)
    {
        new DialogFinJeu(this, this.ctrl, s);
    }
}
