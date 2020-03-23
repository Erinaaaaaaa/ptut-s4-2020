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

	public PanelJoueur(ControleurGui ctrl,int idJoueur,Boolean verticale){
		this.ctrl = ctrl;
		this.numeroJoueur = idJoueur;
		this.coulJoueur  = Apparence.getJoueurCouleur(idJoueur);
		this.imageJoueur = Apparence.getJoueurImage(idJoueur);
		if (verticale) {this.setLayout(new GridLayout(2,1));}
		else           {this.setLayout(new GridLayout(1,2));}

		System.out.println(this.imageJoueur);
		this.add(this.imageJoueur);
		this.add(new JLabel(this.ctrl.getJoueur(idJoueur).getNom()));
	}
}
