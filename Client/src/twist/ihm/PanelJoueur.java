package twist.ihm;

import twist.Controleur;
import twist.util.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;

public class PanelJoueur extends JPanel
{
    private Controleur ctrl;
    private String coul;

    public PanelJoueur(Controleur c, String coul)
    {
        this.ctrl = c;
        this.coul = coul;

        Logger.verbose(this.coul);

        /*switch(this.coul)
        {
            case Rouge :break;
            case Vert  :break;
            case Bleu  :break;
            case Jaune :break;
        }*/


    }

    public void paintComponent(Graphics g)
    {
        //Appel de la méthode paintComponent de la classe mère
        super.paintComponent(g);

        BufferedImage img = null;

        try
        {
            img = ImageIO.read(getClass().getResourceAsStream("/twist/ihm/img/" + this.coul + ".png"));

            //img = ImageIO.read(getClass().getResourceAsStream("/twist/ihm/img/"+this.coul+".png"));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        g.drawImage(img, 0, 0, 251, 425, null);


    }
}
