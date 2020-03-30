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
			new ControleurIA(host, port, "Big brain LV.3", new Minimax(3));


		}
		catch (Exception e)
		{
		}
	}

}
