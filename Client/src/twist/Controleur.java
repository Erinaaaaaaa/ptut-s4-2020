package twist;

import twist.ihm.Fenetre;
import twist.metier.Pont;
import twist.ihm.launcher.*;
public class Controleur {

	private Fenetre fenetre;

	public Controleur(){
		new Pont();
		//new Launcheur(this);

		this.fenetre = new Fenetre(this);
	}

public class Controleur
{
    private Pont pont;

    public Controleur()
    {
        this.pont = new Pont();
        new Launcheur(this);
    }

    public static void main(String[] args)
    {
        new Controleur();
    }
}
