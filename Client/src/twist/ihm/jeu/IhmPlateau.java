package twist.ihm.jeu;

import twist.ControleurGui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class IhmPlateau extends JFrame {
	private int joueur;
	private int relance;
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
		this.setResizable(false);
		this.setVisible(true);
	}
}
