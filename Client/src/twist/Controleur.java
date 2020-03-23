package twist;

import twist.metier.Pont;
import twist.ihm.launcher.*;
public class Controleur {
	public Controleur(){
		new Pont();
		new Launcheur(this);
	}

public static void main(String[] args) {
		new Controleur();
	}
}
