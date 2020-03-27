package twist.ihm.launcher;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import twist.ihm.Apparence;

public class DialogNomJoueur extends JDialog implements ActionListener
{
    private final JTextField[] listJtf;
    private final JButton buttonOk;
    private final JButton buttonCancel;
    private final Launcher ihm;

    public DialogNomJoueur(int taille, Launcher ihm)
    {
        super(ihm, true);
        this.ihm = ihm;
        this.setSize(300, taille * 50 + 20);
        this.setUndecorated(true);
        this.setLocationRelativeTo(this.ihm);
        this.setModal(true);
        this.listJtf = new JTextField[taille];
        for (int i = 0; i < this.listJtf.length; i++)
            this.listJtf[i] = new JTextField("Joueur " + (i + 1));

        JPanel panCentre = new JPanel();
        JPanel panBas = new JPanel();

        panCentre.setLayout(new GridLayout(taille, 2));
        panCentre.setBackground(new Color(223, 224, 226));
        for (int j = 0; j < this.listJtf.length; j++)
        {
            JLabel lbl = new JLabel("Joueur " + (j + 1) + " : ", JLabel.RIGHT);
            panCentre.add(lbl);
            panCentre.add(this.listJtf[j]);
        }

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

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.buttonCancel) this.dispose();
        if (e.getSource() == this.buttonOk)
        {
            StringBuilder message = new StringBuilder("Veuillez remplir correctement le champ : ");
            boolean correcte = true;
            for (int i = 0; i < listJtf.length; i++)
            {
                if (this.listJtf[i].getText().equals(""))
                {
                    correcte = false;
                    message.append("\n	- Joueur ").append(i + 1);
                }
            }
            if (correcte)
            {
                String[] tabNom = new String[listJtf.length];
                for (int i = 0; i < listJtf.length; i++)
                {
                    tabNom[i] = listJtf[i].getText();
                }
                this.dispose();
                this.ihm.debuterPartie(tabNom);
            } else
            {
                JOptionPane.showMessageDialog(null, message.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }


        }
    }
}
