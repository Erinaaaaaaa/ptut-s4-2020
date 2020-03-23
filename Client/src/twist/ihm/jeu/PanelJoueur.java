package twist.ihm.jeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import twist.ControleurGui;
import twist.ihm.Apparence;

public class PanelJoueur extends JPanel{
	private ControleurGui ctrl;
	private Color coulJoueur;
	private int numeroJoueur;
	private JLabel imageJoueur;

	public PanelJoueur(ControleurGui ctrl,int idJoueur,Boolean verticale){
		this.ctrl = ctrl;
		this.numeroJoueur = idJoueur;
		this.coulJoueur   = Apparence.getJoueurCouleur(idJoueur);

		// Logo
		/*ImageIcon icon;
		try {
			icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("./twist/ihm/img/joueur" + idJoueur + ".png")));
		} catch (IOException e) {
		e.printStackTrace();
		return;
	}
		this.imageJoueur  = new JLabel(icon);*/
		if (verticale) {this.setLayout(new GridLayout(1,2));}
		else           {this.setLayout(new GridLayout(2,1));}

		//this.add(this.imageJoueur);
		this.add(setInfoJoueur());
	}

	private JPanel setInfoJoueur(){
		JPanel pan = new JPanel();
		String nbLocker = "<html>"+
				this.ctrl.getJoueur(numeroJoueur).getNom()+" "+
				this.ctrl.getScoreJoueur(numeroJoueur)    +"<br/>";
		for (int i =0; i<this.ctrl.getJoueur(numeroJoueur).getNbLocks(); i++) {
			if (i==this.ctrl.getJoueur(numeroJoueur).getNbLocks()/2) nbLocker += "<br/>";
			 nbLocker += "\u25CF";
		}
		nbLocker += "</html>";
		JLabel JlLocker = new JLabel(nbLocker);
		Apparence.setStyleLbl(JlLocker,coulJoueur);
		pan.add(JlLocker);
		return pan;
	}
}
