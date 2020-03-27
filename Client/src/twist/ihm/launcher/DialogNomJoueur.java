package twist.ihm.launcher;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import twist.ihm.Apparence;
public class DialogNomJoueur extends JDialog implements ActionListener
{
	private JTextField[] listJtf;
	private JCheckBox[]  intelligents;
	private JPanel panCentre,panBas,panDroite;
	private JButton buttonOk, buttonCancel;
	private Launcher ihm;
	public DialogNomJoueur(int taille, Launcher ihm){
		super(ihm,true);
		this.ihm=ihm;
		this.setSize(320,taille*50+20);
		this.setUndecorated(true);
		this.setLocationRelativeTo(this.ihm);
		this.setModal(true);
		this.listJtf = new JTextField[taille];
		this.intelligents = new JCheckBox[taille];
		for (int i=0; i<taille; i++){
			this.listJtf[i] = new JTextField("Joueur "+(i+1));
			this.intelligents[i] = new JCheckBox( "" , true );
		}
		this.panCentre=new JPanel();
		this.panDroite=new JPanel();
		this.panBas=new JPanel();

		this.panCentre.setLayout(new GridLayout(taille+1,3,10,10));
		this.panDroite.setLayout(new GridLayout(taille+1,1,10,10));
		this.panCentre.setBackground(new Color(223,224,226));
		this.panDroite.setBackground(new Color(223,224,226));
		this.panCentre.add(new JLabel(""));
		this.panCentre.add(new JLabel(""));
		this.panDroite.add(new JLabel("IA",JLabel.CENTER));
		for (int j=0; j<this.listJtf.length; j++)
		{
			JLabel lbl = new JLabel("Joueur "+(j+1)+" : ",JLabel.RIGHT);
			this.panCentre.add(lbl);
			this.panCentre.add(this.listJtf[j]);
			this.intelligents[j].setOpaque(false);
			this.panDroite.add(this.intelligents[j]);
		}

		this.buttonOk=new JButton("Valider");
		Apparence.setStyleBtnAction(this.buttonOk);
		this.buttonOk.addActionListener(this);
		this.buttonCancel=new JButton("Quitter");
		Apparence.setStyleBtnAction(this.buttonCancel);
		this.buttonCancel.addActionListener(this);

		this.panBas.setLayout(new GridLayout(1,2));
		this.panBas.add(this.buttonOk);
		this.panBas.add(this.buttonCancel);

		this.add(this.panCentre, BorderLayout.CENTER);
		this.add(this.panDroite,BorderLayout.EAST);
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
				Boolean[] tabIA = new Boolean[intelligents.length];
				for (int i = 0;i<listJtf.length ;i++ ) {
					tabNom[i]=listJtf[i].getText();
					tabIA[i]=intelligents[i].isSelected();
				}
				this.dispose();
				this.ihm.debuterPartie(tabNom,tabIA);
			}else{
				JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
			}


		}
	}
}
