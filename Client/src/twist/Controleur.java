package twist;

import twist.ihm.Fenetre;
import twist.metier.Pont;
import twist.ihm.launcher.*;

public class Controleur
{
    private Pont pont;
    private Fenetre fenetre;

    public Controleur()
    {
        this.pont = new Pont();
        new Launcheur(this);
        this.fenetre = new Fenetre(this);

    }

    public static void main(String[] args)
    {
        new Controleur();
    }
}
