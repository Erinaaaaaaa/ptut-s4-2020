package twist.ihm.launcher;

import java.awt.*;
import java.awt.event.*;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.*;

import twist.net.ControleurReseau;
import twist.ihm.Apparence;

public class DialogNomReseau extends JDialog implements ActionListener
{
    private final JTextField txtNom;
		private JCheckBox  intelligents;
    private final JTextField txtHost;
    private final JSpinner spinnerPort;

    private final JButton buttonOk;
    private final JButton buttonCancel;
    private final Launcher ihm;

    public DialogNomReseau(Launcher ihm)
    {
        super(ihm, true);

        this.ihm = ihm;
        this.setSize(300, 4 * 50 + 20);
        this.setUndecorated(true);
        this.setLocationRelativeTo(this.ihm);
        this.setModal(true);

        this.txtNom = new JTextField("Joueur XXX");
        this.txtHost = new JTextField("localhost");
        SpinnerModel spinnPort = new SpinnerNumberModel(9876, 1024, 65535, 1);

        JPanel panCentre = new JPanel();
				JPanel panDroite=new JPanel();
        JPanel panBas = new JPanel();

        panCentre.setLayout(new GridLayout(4, 2));
				panDroite.setLayout(new GridLayout(4,1,10,10));
        panCentre.setBackground(new Color(223, 224, 226));
				panDroite.setBackground(new Color(223, 224, 226));
				panCentre.add(new JLabel(""));
				panCentre.add(new JLabel(""));
        panCentre.add(new JLabel("Nom : "));
        panCentre.add(this.txtNom);
				panCentre.add(new JLabel("Host : "));
        panCentre.add(this.txtHost);
        panCentre.add(new JLabel("Port : "));
        panCentre.add(this.spinnerPort = new JSpinner(spinnPort));

				this.intelligents = new JCheckBox( "" , true );
				this.intelligents.setOpaque(false);
				panDroite.add(new JLabel("IA",JLabel.CENTER));
				panDroite.add(this.intelligents);
				panDroite.add(new JLabel(""));
				panDroite.add(new JLabel(""));

        this.buttonOk = new JButton("Valider");
        Apparence.setStyleBtnAction(this.buttonOk);
        this.buttonOk.addActionListener(this);
        this.buttonCancel = new JButton("Quitter");
        Apparence.setStyleBtnAction(this.buttonCancel);
        this.buttonCancel.addActionListener(this);

        panBas.setLayout(new GridLayout(1, 2));
        panBas.add(this.buttonOk);
        panBas.add(this.buttonCancel);

        this.add(panCentre, BorderLayout.CENTER);
				this.add(panDroite,BorderLayout.EAST);
        this.add(panBas, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.buttonCancel) this.dispose();
        if (e.getSource() == this.buttonOk)
        {
            String message = "Veuillez remplir correctement les champs : \n";
            boolean correct = true;

            if (txtNom.getText().equals(""))
            {
                correct = false;
                message += "\t-Nom\n";
            }
            if (txtHost.getText().equals(""))
            {
                correct = false;
                message += "\t-Host";
            }

            if (correct)
            {
                this.dispose();
                try
                {
                    this.ihm.dispose();
                    new ControleurReseau(this.txtHost.getText(), (int)this.spinnerPort.getValue(), this.txtNom.getText(), true);
                }
                catch (SocketException | UnknownHostException ex)
                {
                    ex.printStackTrace();
                }
            } else
            {
                JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
            }


        }
    }
}
