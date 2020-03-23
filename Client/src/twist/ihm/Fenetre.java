package twist.ihm;

import twist.Controleur;

import javax.swing.*;

public class Fenetre extends JFrame
{
    private Controleur ctrl;

    public Fenetre(Controleur c)
    {
        this.setTitle("Twist-Lock");
        this.setLocation(50, 50);
        this.setSize(900,600);
        this.ctrl = c;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        /*-------------------*/
        /* PANEL DES JOUEURS */
        /*-------------------*/

        

        
    }



        public void majIHM(){

    }

}
