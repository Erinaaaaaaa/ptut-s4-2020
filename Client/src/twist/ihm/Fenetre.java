package twist.ihm;

import twist.Controleur;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame
{
    private Controleur ctrl;
    private PanelJoueur[] pJ;

    public Fenetre(Controleur c)
    {
        this.setTitle("Twist-Lock");
        this.setLocation(50, 50);
        this.setSize(900, 600);
        this.ctrl = c;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*-------------------*/
        /* PANEL DES JOUEURS */
        /*-------------------*/

        //TODO: faire en fonction du nombre de joueur

        this.pJ[0] = new PanelJoueur(this.ctrl, "joueurR");
        this.pJ[1] = new PanelJoueur(this.ctrl, "joueurV");
        this.pJ[2] = new PanelJoueur(this.ctrl, "joueurB");
        this.pJ[3] = new PanelJoueur(this.ctrl, "joueurJ");

        this.add(this.pJ[0], BorderLayout.WEST);
        this.add(this.pJ[1], BorderLayout.EAST);
        this.add(this.pJ[2], BorderLayout.NORTH);
        this.add(this.pJ[3], BorderLayout.SOUTH);

        this.setVisible(true);

    }


    public void majIHM()
    {

    }

}
