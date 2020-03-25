package twist.ihm.launcher;

import java.awt.*;
import java.awt.font.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import twist.ihm.Apparence;

public class PanImage extends JPanel implements ActionListener{

	private JButton bLancerPartie;
	private JButton bLancerPartieReseau;
	private JButton bQuitter;
	private Launcher ctrl;

	public PanImage(Launcher ctrl){
		this.ctrl = ctrl;

		SpringLayout sl_panel = new SpringLayout();
		this.setLayout(sl_panel);

		JLabel lblCreation = new JLabel("Twist-Lock");
		Apparence.setStyleTitle(lblCreation);
		sl_panel.putConstraint(SpringLayout.WEST, lblCreation, 66, SpringLayout.WEST, this);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblCreation, -450, SpringLayout.SOUTH, this);
		this.add(lblCreation);

		this.bLancerPartie = new JButton("Jouer local");
		this.bLancerPartie.addActionListener(this);
		Apparence.setStyleBtnPrincipale(this.bLancerPartie);
		sl_panel.putConstraint(SpringLayout.NORTH, this.bLancerPartie, 10, SpringLayout.SOUTH, lblCreation);
		sl_panel.putConstraint(SpringLayout.WEST, this.bLancerPartie, 66, SpringLayout.WEST, this);
		sl_panel.putConstraint(SpringLayout.SOUTH, this.bLancerPartie, 75, SpringLayout.SOUTH, lblCreation);
		sl_panel.putConstraint(SpringLayout.EAST, this.bLancerPartie, -72, SpringLayout.EAST, this);
		this.add(this.bLancerPartie);

		this.bLancerPartieReseau = new JButton("Jouer réseau");
		this.bLancerPartieReseau.addActionListener(this);
		Apparence.setStyleBtnPrincipale(this.bLancerPartieReseau);
		sl_panel.putConstraint(SpringLayout.NORTH, this.bLancerPartieReseau, 10, SpringLayout.SOUTH, this.bLancerPartie);
		sl_panel.putConstraint(SpringLayout.WEST, this.bLancerPartieReseau, 66, SpringLayout.WEST, this);
		sl_panel.putConstraint(SpringLayout.SOUTH, this.bLancerPartieReseau, 75, SpringLayout.SOUTH, this.bLancerPartie);
		sl_panel.putConstraint(SpringLayout.EAST, this.bLancerPartieReseau, -72, SpringLayout.EAST, this);
		this.add(this.bLancerPartieReseau);

		this.bQuitter = new JButton("Quitter");
		this.bQuitter.addActionListener(this);
		Apparence.setStyleBtnPrincipale(this.bQuitter);
		sl_panel.putConstraint(SpringLayout.NORTH, this.bQuitter, 10, SpringLayout.SOUTH, this.bLancerPartieReseau);
		sl_panel.putConstraint(SpringLayout.WEST, this.bQuitter, 66, SpringLayout.WEST, this);
		sl_panel.putConstraint(SpringLayout.SOUTH, this.bQuitter, 75, SpringLayout.SOUTH, this.bLancerPartieReseau);
		sl_panel.putConstraint(SpringLayout.EAST, this.bQuitter, -72, SpringLayout.EAST, this);
		this.add(this.bQuitter);
	}

	public void actionPerformed(ActionEvent e){
		if (e.getSource()==this.bLancerPartie){
			this.ctrl.addPanDemandeJ();
		}

		if(e.getSource()==this.bLancerPartieReseau){
			this.ctrl.addPanReseau();
		}
		if (e.getSource()==this.bQuitter) System.exit(1);

	}
}
