package twist.ihm.jeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import twist.Controleur;
import twist.ControleurGui;
import twist.ihm.Apparence;

public class PanelJoueur extends JPanel
{
    private Controleur ctrl;
    private Color coulJoueur;
    private int numeroJoueur;
    private JLabel imageJoueur;
    private JLabel infoJoueur;

    public PanelJoueur(Controleur ctrl, int idJoueur, Boolean verticale)
    {
        this.ctrl = ctrl;
        this.numeroJoueur = idJoueur;
        this.coulJoueur = Apparence.getJoueurCouleur(idJoueur);
        Apparence.setBackgroundPanelJoueur(this, this.ctrl.getJoueurActif());

        // Logo
        ImageIcon icon;
        try
        {
            icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/twist/ihm/img/joueur" + (idJoueur + 1) + ".png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
        icon = new ImageIcon(icon.getImage().getScaledInstance(65, 158, Image.SCALE_SMOOTH));
        this.imageJoueur = new JLabel(icon);
        if (verticale)
        {
            this.setLayout(new GridLayout(1, 2));
        } else
        {
            this.setLayout(new GridLayout(2, 1));
        }

        this.add(this.imageJoueur);
        this.infoJoueur = new JLabel(setInfoJoueur());
        Apparence.setStyleLbl(this.infoJoueur, coulJoueur);
        this.add(this.infoJoueur);
    }

    private String setInfoJoueur()
    {
        String nbLocker = "<html>" +
                this.ctrl.getJoueur(numeroJoueur).getNom() + " " +
                this.ctrl.getScoreJoueur(numeroJoueur) + "<br/>";
        for (int i = 0; i < this.ctrl.getJoueur(numeroJoueur).getNbLocks(); i++)
        {
            if (i == this.ctrl.getJoueur(numeroJoueur).getNbLocks() / 2) nbLocker += "<br/>";
            nbLocker += "\u25CF";
        }
        nbLocker += "</html>";
        return nbLocker;
    }

    public int getNbJoueur()
    {
        return this.numeroJoueur;
    }

    public void miseAJour()
    {
        this.infoJoueur.setText(setInfoJoueur());
        Apparence.setBackgroundPanelJoueur(this, this.ctrl.getJoueurActif());
        this.repaint();
    }
}
