package twist.ihm.launcher;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import twist.ihm.Apparence;
public class DialogNomReseau extends JDialog implements ActionListener
{
  private JTextField   txtNom;
  private JTextField   txtHost;
  private SpinnerModel spinnPort;

	private JPanel panCentre,panBas;
	private JButton buttonOk, buttonCancel;
	private Launcher ihm;

	public DialogNomReseau(Launcher ihm){
    super(ihm,true);

    int taille = 3;

		this.ihm=ihm;
		this.setSize(300,taille*50+20);
		this.setUndecorated(true);
		this.setLocationRelativeTo(this.ihm);
		this.setModal(true);

		this.txtNom  = new JTextField();
    this.txtHost = new JTextField();
    this.spinnPort = new SpinnerNumberModel(2000,1024,65535,1);

		this.panCentre=new JPanel();
		this.panBas=new JPanel();

		this.panCentre.setLayout(new GridLayout(taille,2));
		this.panCentre.setBackground(new Color(223,224,226));

    this.panCentre.add(new JLabel("Nom : "));
    this.panCentre.add(this.txtNom);
    this.panCentre.add(new JLabel("Host : "));
    this.panCentre.add(this.txtHost);
    this.panCentre.add(new JLabel("Port : "));
    this.panCentre.add(new JSpinner(this.spinnPort));

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
		this.add(this.panBas, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==this.buttonCancel) this.dispose();
		if (e.getSource()==this.buttonOk)
		{
  			String message = "Veuillez remplir correctement les champs : \n";
  			boolean correct = true;

        if(txtNom.getText().equals(""))  { correct = false; message += "\t-Nom\n";}
        if(txtHost.getText().equals("")) { correct = false; message += "\t-Host";}

  		if (correct) {
  				this.dispose();
  				//this.ihm.debuterPartie(tabNom);
          JOptionPane.showMessageDialog(null, "Boomer alert", "Boomer alert", JOptionPane.ERROR_MESSAGE);
  		}else{
  				JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
  		}


		}
	}
}
