package twist.ihm.launcher;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import twist.ihm.Apparence;

public class DialogInfoGenerale extends JDialog implements ActionListener
{
    private final Launcher ihm;
    private final JSpinner spinnerJoueur;
    private final JSpinner spinnerLock;
    private final JSpinner spinnerLigne;
    private final JSpinner spinnerColone;
    private final JButton buttonOk;
    private final JButton buttonCancel;

    public DialogInfoGenerale(Launcher ihm)
    {
        super(ihm, true);
        this.setSize(320, 140);
        this.ihm = ihm;
        this.setLocationRelativeTo(this.ihm);
        SpinnerModel spinNbJoueur = new SpinnerNumberModel(2, 2, 4, 1);
        SpinnerModel spinNbLock = new SpinnerNumberModel(20, 1, 30, 1);
        SpinnerModel spinNbLigne = new SpinnerNumberModel(10, 5, 20, 1);
        SpinnerModel spinNbColone = new SpinnerNumberModel(10, 5, 20, 1);
        this.setUndecorated(true);
        this.spinnerJoueur = new JSpinner(spinNbJoueur);
        this.spinnerLock = new JSpinner(spinNbLock);
        this.spinnerLigne = new JSpinner(spinNbLigne);
        this.spinnerColone = new JSpinner(spinNbColone);
        this.spinnerJoueur.setBounds(100, 100, 50, 30);
        this.spinnerLock.setBounds(100, 100, 50, 30);
        this.spinnerLigne.setBounds(100, 100, 50, 30);
        this.spinnerColone.setBounds(100, 100, 50, 30);

        JPanel panHaut = new JPanel();
        JPanel panBas = new JPanel();
        this.buttonOk = new JButton("Valider");
        Apparence.setStyleBtnAction(this.buttonOk);
        this.buttonOk.addActionListener(this);
        this.buttonCancel = new JButton("Quitter");
        Apparence.setStyleBtnAction(this.buttonCancel);
        this.buttonCancel.addActionListener(this);

        panHaut.setLayout(new GridLayout(4, 2, 10, 10));
        panHaut.setBackground(new Color(223, 224, 226));
        panBas.setLayout(new GridLayout(1, 2));

        panHaut.add(new JLabel("Nombre de Joueurs  : "));
        panHaut.add(this.spinnerJoueur);
        panHaut.add(new JLabel("Nombre de Locks    : "));
        panHaut.add(this.spinnerLock);
        panHaut.add(new JLabel("Nombre de Lignes   : "));
        panHaut.add(this.spinnerLigne);
        panHaut.add(new JLabel("Nombre de Colonnes : "));
        panHaut.add(this.spinnerColone);

        panBas.add(this.buttonOk);
        panBas.add(this.buttonCancel);


        this.add(panHaut, BorderLayout.NORTH);
        this.add(panBas, BorderLayout.SOUTH);

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.buttonCancel) this.dispose();

        if (e.getSource() == this.buttonOk)
        {
            this.dispose();
            this.ihm.setNbJoueur(Integer.parseInt(this.spinnerJoueur.getValue().toString()));
            this.ihm.setNbLock(Integer.parseInt(this.spinnerLock.getValue().toString()));
            this.ihm.setNbCol(Integer.parseInt(this.spinnerLigne.getValue().toString()));
            this.ihm.setNbLgn(Integer.parseInt(this.spinnerColone.getValue().toString()));
            this.ihm.addDialogNomJoueur();
        }
    }
}
