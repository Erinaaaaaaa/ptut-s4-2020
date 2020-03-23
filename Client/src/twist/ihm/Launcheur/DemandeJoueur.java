package twist.ihm.Launcheur;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Toolkit;

public class DemandeJoueur extends JDialog implements ActionListener
{
	private IHMGUI ihm;
	private SpinnerModel spinNbJ;
	private JSpinner spinner;
	private JPanel panHaut, panBas;
	private JButton bOk, bCancel;

	public DemandeJoueur(IHMGUI ihm)
	{
		this.setSize(320,50);
		this.ihm = ihm;
		this.setLocationRelativeTo(this.ihm);
		this.spinNbJ = new SpinnerNumberModel(2,2,4,1);
		this.setUndecorated(true);
		this.spinner = new JSpinner(this.spinNbJ);
		this.spinner.setBounds(100,100,50,30);

		this.panHaut = new JPanel();
		this.panBas  = new JPanel();
		this.bOk=new JButton("Valider");
		this.bOk.addActionListener(this);
		this.bCancel=new JButton("Quitter");
		this.bCancel.addActionListener(this);

		this.panHaut.setLayout(new GridLayout(1,2));
		this.panHaut.setBackground(new Color(197, 175, 146));
		this.panBas.setLayout(new GridLayout(1,2));

		JLabel lbl = new JLabel("Nombre de joueurs : ");
		Apparence.setStyleLbl(lbl);
		this.panHaut.add(lbl);
		this.panHaut.add(this.spinner);

		this.panBas.add(this.bOk);
		this.panBas.add(this.bCancel);
		Apparence.setStyleBtnAction(this.bOk);
		Apparence.setStyleBtnAction(this.bCancel);

		this.add(this.panHaut,BorderLayout.NORTH);
		this.add(this.panBas,BorderLayout.SOUTH);

	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==this.bCancel)
			this.dispose();
		if (e.getSource()==this.bOk)
		{
			this.dispose();
			this.ihm.setNbJ(Integer.valueOf(this.spinner.getValue().toString()));
		}
	}
}
