package twist;

import twist.ihm.Fenetre;
import twist.metier.Pont;
import twist.ihm.launcher.*;

public class ControleurGui implements Controleur
{
    private Pont pont;
    private Fenetre fenetre;

    public ControleurGui()
    {
        this.pont = new Pont();
        new Launcheur(this);
        this.fenetre = new Fenetre(this);
    }

    public static void main(String[] args)
    {
        new ControleurGui();
    }
}
