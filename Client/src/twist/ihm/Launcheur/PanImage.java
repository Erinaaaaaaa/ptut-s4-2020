package twist.ihm.Launcheur;
import Ihm.*;

import java.awt.*;
import java.awt.font.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

import javax.swing.*;

public class PanImage extends JPanel implements ActionListener{

	private JButton bLancerPartie;
	private IHMGUI ctrl;

	public PanImage(IHMGUI ctrl){
		this.ctrl = ctrl;

		SpringLayout sl_panel = new SpringLayout();
		this.setLayout(sl_panel);

		JLabel lblCreation = new JLabel("");
		sl_panel.putConstraint(SpringLayout.WEST, lblCreation, -5, SpringLayout.WEST, this);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblCreation, -300, SpringLayout.SOUTH, this);
		Font font = new Font("Helvetica",Font.BOLD,40);
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		this.add(lblCreation);

		this.bLancerPartie = new JButton("Jouer");
		this.bLancerPartie.addActionListener(this);
		Apparence.setStyleBtnPrincipale(this.bLancerPartie);
		sl_panel.putConstraint(SpringLayout.NORTH, this.bLancerPartie, 0, SpringLayout.SOUTH, lblCreation);
		sl_panel.putConstraint(SpringLayout.WEST, this.bLancerPartie, 66, SpringLayout.WEST, this);
		sl_panel.putConstraint(SpringLayout.SOUTH, this.bLancerPartie, -250, SpringLayout.SOUTH, this);
		sl_panel.putConstraint(SpringLayout.EAST, this.bLancerPartie, -72, SpringLayout.EAST, this);
		this.add(this.bLancerPartie);

		Apparence.setFond(this);
	}

	public void actionPerformed(ActionEvent e){
		if (e.getSource()==this.bLancerPartie){
			this.ctrl.addPanDemandeJ();
		}

	}
}
