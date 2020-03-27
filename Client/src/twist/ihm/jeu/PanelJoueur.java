package twist.ihm.jeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import twist.Controleur;
import twist.ihm.Apparence;

public class PanelJoueur extends JPanel
{
    private final Controleur ctrl;
    private final int numeroJoueur;
    private JLabel infoJoueur;
		private ImageIcon icon;

    public PanelJoueur(Controleur ctrl, int idJoueur, Boolean verticale)
    {
        this.ctrl = ctrl;
        this.numeroJoueur = idJoueur;
        Color coulJoueur = Apparence.getJoueurCouleur(idJoueur);
        Apparence.setBackgroundPanelJoueur(this, this.ctrl.getJoueurActif());

        // Logo
        try{
						if (idJoueur == 0) {
            	icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/twist/ihm/img/joueur" + (idJoueur + 1) + "1.png")));
						}else {
							icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/twist/ihm/img/joueur" + (idJoueur + 1) + "0.png")));
						}
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
        icon = new ImageIcon(icon.getImage().getScaledInstance(100, 158, Image.SCALE_SMOOTH));
        JLabel imageJoueur = new JLabel(icon);
        if (verticale)
        {
            this.setLayout(new GridLayout(1, 2));
        } else
        {
            this.setLayout(new GridLayout(2, 1));
        }

        this.add(imageJoueur);
        this.infoJoueur = new JLabel(setInfoJoueur());
        Apparence.setStyleLbl(this.infoJoueur, coulJoueur);
        this.add(this.infoJoueur);
    }

    private String setInfoJoueur()
    {
        StringBuilder nbLocker = new StringBuilder("<html>" +
                this.ctrl.getJoueur(numeroJoueur).getNom() + " " +
                this.ctrl.getScoreJoueur(numeroJoueur) + "<br/>");
        for (int i = 0; i < this.ctrl.getJoueur(numeroJoueur).getNbLocks(); i++)
        {
            if (i == this.ctrl.getJoueur(numeroJoueur).getNbLocks() / 2) nbLocker.append("<br/>");
            nbLocker.append("\u25CF");
        }
        nbLocker.append("</html>");
        return nbLocker.toString();
    }

    public int getNbJoueur()
    {
        return this.numeroJoueur;
    }

    public void miseAJour()
    {
				try{
						icon = null;
						if (numeroJoueur == this.ctrl.getJoueurActif()) {
            	icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/twist/ihm/img/joueur" + (numeroJoueur + 1) + "1.png")));
						}else {
							icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/twist/ihm/img/joueur" + (numeroJoueur + 1) + "0.png")));
						}
        	}
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
				icon = new ImageIcon(icon.getImage().getScaledInstance(100, 158, Image.SCALE_SMOOTH));
				this.removeAll();
				this.infoJoueur.setText(setInfoJoueur());
				this.add(new JLabel(icon));
				this.add(this.infoJoueur);
				Apparence.setBackgroundPanelJoueur(this, this.ctrl.getJoueurActif());
				this.revalidate();
				this.repaint();
    }
}
