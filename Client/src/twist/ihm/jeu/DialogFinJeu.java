package twist.ihm.jeu;

import twist.Controleur;
import twist.ControleurGui;
import twist.ihm.Apparence;
import twist.ihm.launcher.Launcher;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class DialogFinJeu extends JDialog implements ActionListener
{
    private IhmPlateau ihm;
    private Controleur ctrl;
    private JButton bLancerPartie;
    private JButton bQuitter;

    public DialogFinJeu(IhmPlateau ihm, Controleur ctrl)
    {
        this(ihm, ctrl, null);
    }

    public DialogFinJeu(IhmPlateau ihm, Controleur ctrl, String s)
    {
        super(ihm, true);
        this.ihm = ihm;
        this.ctrl = ctrl;
        this.setSize(450, 600);
        this.setLocationRelativeTo(this.ihm);
        this.setLayout(new GridLayout(4, 1, 20, 20));
        JLabel lblVictoir = new JLabel(s == null ? "Victoire !!" : "Fin de partie", JLabel.CENTER);
        Apparence.setStyleTitle(lblVictoir);
        this.add(lblVictoir);
				String reponce ="";
				if (s != null) {
					reponce = "<html>Bien joué<br>"+s.replace("\n", "<br>")+"</html>";
				}
        JLabel lblGagnaint = new JLabel(reponce, JLabel.CENTER);
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
            this.ihm.dispose();
            this.dispose();
            new Launcher();
        }
        if (e.getSource() == this.bQuitter) System.exit(1);
    }
}
