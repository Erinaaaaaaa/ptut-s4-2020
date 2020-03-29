package twist.net;

import twist.Controleur;
import twist.ihm.jeu.IhmPlateau;
import twist.metier.Conteneur;
import twist.metier.Coup;
import twist.metier.Pont;
import twist.metier.ia.*;
import twist.util.Logger;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import java.util.*;

public class ControleurIA extends ControleurReseau
{
	private IA intelligence;

	public ControleurIA(String host, int port, String nom, IA intelligence) throws SocketException, UnknownHostException
	{
		super(host, port, nom);
		this.intelligence = intelligence;
	}

	public void jouer()
	{
		try
		{
			Thread.sleep(500);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Coup next = intelligence.next(this.pont);

		this.jouer(next.getColonne(), next.getLigne(), next.getCoin());
	}

	@Override
	public boolean estIA()
	{
		return true;
	}

	public static void main(String[] args)
	{
		try
		{

			Scanner sc = new Scanner(System.in);
			String host;
			int port;

			System.out.print("Host : ");
			host = sc.nextLine();
			System.out.print("Port : ");
			port = sc.nextInt();
			System.out.println("Choix de l'IA:");
			System.out.println("1. Aléatoire");
			System.out.println("2. Heuristique (deux coups aléatoires, le meilleur des deux)");
			System.out.println("3. Goinfre (le meilleur coup disponible immédiatement)");
			System.out.println("4. Minimax (Un peu plus intelligent mais le plus lourd)");
			System.out.print("Votre choix: ");
			int ia = sc.nextInt();

			switch (ia)
			{
				case 1:
					new ControleurIA(host, port, "Mr. Aléa", new Aleatoire());
					break;
				case 2:
					new ControleurIA(host, port, "Le ristique", new Heuristique());
					break;
				case 3:
					new ControleurIA(host, port, "Goinfre de service", new Goinfre());
					break;
				case 4:
					System.out.print("Niveaux Minimax (2-20, recommandé: 3):");
					int niveaux = sc.nextInt();
					if (niveaux > 20) niveaux = 20;
					if (niveaux < 2 ) niveaux = 2 ;
					new ControleurIA(host, port, "Big brain lv." + niveaux, new Minimax(niveaux));
					break;
			}


		}
		catch (Exception e)
		{
		}
	}

}
