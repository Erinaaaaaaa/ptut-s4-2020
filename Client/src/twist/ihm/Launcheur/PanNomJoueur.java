package twist.ihm.Launcheur;

import java.awt.*;
import java.awt.font.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class PanNomJoueur extends JDialog implements ActionListener
{
	private JTextField[] listJtf;
	private JPanel panCentre,panBas;
	private JButton bOk, bCancel;
	private IHMGUI ihm;
	public PanNomJoueur(int taille, IHMGUI ihm){
		this.ihm=ihm;
		this.setSize(300,taille*50+20);
		this.setUndecorated(true);
		this.setLocationRelativeTo(this.ihm);
		this.setModal(true);
		this.listJtf = new JTextField[taille];
		for (int i=0; i<this.listJtf.length; i++)
			this.listJtf[i]=new JTextField();

		this.panCentre=new JPanel();
		this.panBas=new JPanel();

		this.panCentre.setLayout(new GridLayout(taille,2));
		this.panCentre.setBackground(new Color(197, 175, 146));
		for (int j=0; j<this.listJtf.length; j++)
		{
			JLabel lbl = new JLabel("Joueur "+(j+1)+" : ",JLabel.RIGHT);
			Apparence.setStyleLbl(lbl);
			this.panCentre.add(lbl);
			this.panCentre.add(this.listJtf[j]);
		}

		this.bOk=new JButton("Valider");
		this.bOk.addActionListener(this);
		this.bCancel=new JButton("Quitter");
		this.bCancel.addActionListener(this);

		this.panBas.setLayout(new GridLayout(1,2));
		this.panBas.add(this.bOk);
		this.panBas.add(this.bCancel);
		Apparence.setStyleBtnAction(this.bOk);
		Apparence.setStyleBtnAction(this.bCancel);

		this.add(this.panCentre, BorderLayout.CENTER);
		this.add(this.panBas, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==this.bCancel) this.dispose();
		if (e.getSource()==this.bOk)
		{
			String message = "Veuillez remplir correctement le champ : ";
			Boolean correcte = true;
			for (int i = 0;i<listJtf.length ;i++ ) {
				if (this.listJtf[i].getText().equals("")) {
					correcte=false;
					message+= "\n	- Joueur "+(i+1);
				}
			}
			if (correcte) {
				String[] tabNom = new String[listJtf.length];
				for (int i = 0;i<listJtf.length ;i++ ) {
					tabNom[i]=listJtf[i].getText();
				}
				this.dispose();
				this.ihm.debuterPartie(tabNom);
			}else{
				JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
			}


		}
	}
}
