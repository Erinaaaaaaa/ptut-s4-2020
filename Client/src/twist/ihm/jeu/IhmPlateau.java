package twist.ihm.jeu;

import twist.ControleurGui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class IhmPlateau extends JFrame {
	private int joueur;
	private int relance;
	private int hauteur;
	private int largeur;
	private PanelJoueur[] tabJoueur;
	private SpringLayout slPlateau;
	private ControleurGui ctrl;

	public IhmPlateau(ControleurGui ctrl){
		this.setTitle( "Twist : Groupe 4" );
		this.ctrl = ctrl;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);

		int[] x = {largeur/2-largeur/3,largeur/2-largeur/8 ,
			       largeur/2-largeur/9  , largeur/2+largeur/9,
			       largeur/2+largeur/8,largeur/2+largeur/3,
			       largeur/2-largeur/9  , largeur/2+largeur/9};

		int[] y = {hauteur/2-hauteur/6 , hauteur/2+hauteur/10,
			       hauteur/50          , hauteur/2-hauteur/4,
			       hauteur/2-hauteur/6 , hauteur/2+hauteur/10,
			       hauteur/2+hauteur/7 , hauteur - hauteur/9 };

		slPlateau = new SpringLayout();
	this.tabJoueur = new PanelJoueur[this.ctrl.getNbJoueur()];
	this.setLayout(slPlateau);

	int dj = 0;
	if ( this.ctrl.getNbJoueur()==2) dj=2;
	for (int i = 0;i<this.tabJoueur.length ;i++ ) {
		tabJoueur[i] = new PanelJoueur(ctrl,i,dj!=2&&(i==1||i==3));
		slPlateau.putConstraint(SpringLayout.NORTH, tabJoueur[i], y[2*i+dj*i]   , SpringLayout.NORTH, this);
		slPlateau.putConstraint(SpringLayout.WEST,  tabJoueur[i], x[2*i+dj*i]   , SpringLayout.WEST, this);
		slPlateau.putConstraint(SpringLayout.SOUTH, tabJoueur[i], y[2*i+1+dj*i] , SpringLayout.NORTH, this);
		slPlateau.putConstraint(SpringLayout.EAST,  tabJoueur[i], x[2*i+1+dj*i] , SpringLayout.WEST, this);
		this.add(tabJoueur[i]);
	}

		this.setVisible(true);
	}


	public int getHauteur() {return hauteur;}
	public int getLargeur() {return largeur;}

}
