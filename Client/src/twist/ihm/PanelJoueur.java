package twist.ihm;

import twist.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanelJoueur extends JPanel
{
    private Controleur ctrl;
    private Color coul;

    public PanelJoueur(Controleur c, Color coul)
    {
        this.ctrl = c;
        this.coul = coul;


    }
}
