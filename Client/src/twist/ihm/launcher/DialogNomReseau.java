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
    private final JTextField txtHost;
    private final JSpinner spinnerPort;

    private final JButton buttonOk;
    private final JButton buttonCancel;
    private final Launcher ihm;

    public DialogNomReseau(Launcher ihm)
    {
        super(ihm, true);

        int taille = 3;

        this.ihm = ihm;
        this.setSize(300, taille * 50 + 20);
        this.setUndecorated(true);
        this.setLocationRelativeTo(this.ihm);
        this.setModal(true);

        this.txtNom = new JTextField();
        this.txtHost = new JTextField("localhost");
        SpinnerModel spinnPort = new SpinnerNumberModel(2000, 1024, 65535, 1);

        JPanel panCentre = new JPanel();
        JPanel panBas = new JPanel();

        panCentre.setLayout(new GridLayout(taille, 2));
        panCentre.setBackground(new Color(223, 224, 226));

        panCentre.add(new JLabel("Nom : "));
        panCentre.add(this.txtNom);
        panCentre.add(new JLabel("Host : "));
        panCentre.add(this.txtHost);
        panCentre.add(new JLabel("Port : "));
        panCentre.add(this.spinnerPort = new JSpinner(spinnPort));

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
                    new ControleurReseau(this.txtHost.getText(), (int)this.spinnerPort.getValue(), this.txtNom.getText());
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
