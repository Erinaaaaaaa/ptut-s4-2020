package twist.ihm.launcher;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class DialogNomJoueur extends JDialog implements ActionListener
{
	private JTextField[] listJtf;
	private JPanel panCentre,panBas;
	private JButton buttonOk, buttonCancel;
	private Launcher ihm;
	public DialogNomJoueur(int taille, Launcher ihm){
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
			this.panCentre.add(lbl);
			this.panCentre.add(this.listJtf[j]);
		}

		this.buttonOk=new JButton("Valider");
		this.buttonOk.addActionListener(this);
		this.buttonCancel=new JButton("Quitter");
		this.buttonCancel.addActionListener(this);

		this.panBas.setLayout(new GridLayout(1,2));
		this.panBas.add(this.buttonOk);
		this.panBas.add(this.buttonCancel);

		this.add(this.panCentre, BorderLayout.CENTER);
		this.add(this.panBas, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==this.buttonCancel) this.dispose();
		if (e.getSource()==this.buttonOk)
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
