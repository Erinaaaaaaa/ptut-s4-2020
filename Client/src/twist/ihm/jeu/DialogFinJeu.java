package twist.ihm.jeu;

import twist.Controleur;
import twist.ihm.Apparence;
import twist.ihm.launcher.Launcher;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class DialogFinJeu extends JDialog implements ActionListener
{
    private final JButton bLancerPartie;
    private final JButton bQuitter;

    public DialogFinJeu(Controleur ctrl)
    {
        this( ctrl, null);
    }

    public DialogFinJeu(Controleur ctrl, String s)
    {
        this.setSize(450, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(4, 1, 20, 20));
        JLabel lblVictoir = new JLabel(s == null ? "Victoire !!" : "Fin de partie", JLabel.CENTER);
        Apparence.setStyleTitle(lblVictoir);
        this.add(lblVictoir);
        JLabel lblGagnaint = new JLabel(s == null ? ("Bien joué à " + ctrl.getGagnant().getNom()) : "<html>"+s.replace("\n", "<br>")+"</html>", JLabel.CENTER);
        Apparence.setStyleLbl(lblGagnaint, new Color(7, 16, 19));
        this.add(lblGagnaint);

        this.bLancerPartie = new JButton("Rejouer");
        this.bLancerPartie.addActionListener(this);
        Apparence.setStyleBtnPrincipale(this.bLancerPartie);
        this.add(this.bLancerPartie);

        this.bQuitter = new JButton("Quitter");
        this.bQuitter.addActionListener(this);
        Apparence.setStyleBtnPrincipale(this.bQuitter);
        this.add(this.bQuitter);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.bLancerPartie)
        {
            this.dispose();
            new Launcher();
        }
        if (e.getSource() == this.bQuitter) System.exit(1);
    }
}
