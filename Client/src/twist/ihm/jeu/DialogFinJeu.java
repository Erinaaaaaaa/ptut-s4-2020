package twist.ihm.jeu;

import twist.ControleurGui;
import twist.ihm.Apparence;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class DialogFinJeu extends JDialog implements ActionListener {
	private IhmPlateau ihm;
	private ControleurGui ctrl;
	public DialogFinJeu(IhmPlateau ihm,ControleurGui ctrl){
		super(ihm,true);
		this.ihm = ihm;
		this.ctrl = ctrl;
		this.setSize(450,600);
		this.setLocationRelativeTo(this.ihm);
		SpringLayout sl_panel = new SpringLayout();
		this.setLayout(sl_panel);

		JLabel lblCreation = new JLabel("Victoire !!");
		Apparence.setStyleTitle(lblCreation);
		sl_panel.putConstraint(SpringLayout.WEST, lblCreation, 66, SpringLayout.WEST, this);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblCreation, -200, SpringLayout.SOUTH, this);
		this.add(lblCreation);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
	}
}
