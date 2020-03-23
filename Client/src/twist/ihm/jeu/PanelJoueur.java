package twist.ihm.jeu;

import javax.swing.*;
import java.awt.*;

import twist.ControleurGui;
import twist.ihm.Apparence;

public class PanelJoueur extends JPanel{
	private ControleurGui ctrl;
	private Color coulJoueur;
	private int numeroJoueur;
	private JLabel imageJoueur;

	public PanelJoueur(ControleurGui ctrl,int idJoeur,Boolean verticale){
		this.ctrl = ctrl;
		this.numeroJoueur = idJoeur;
		this.coulJoueur  = Apparence.getJoueurCouleur(idJoeur);
		this.imageJoueur = Apparence.getJoueurImage(idJoeur);
		if (verticale) {this.setLayout(new GridLayout(2,1));}
		else           {this.setLayout(new GridLayout(2,1));}

		this.add(this.imageJoueur);
		this.add(new JLabel("TEST ! "));
	}
}
