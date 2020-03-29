package twist.net;

import twist.Controleur;
import twist.ihm.jeu.IhmPlateau;
import twist.metier.Conteneur;
import twist.metier.Pont;
import twist.util.Logger;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import java.util.*;

public class ControleurIA extends ControleurReseau
{

  public ControleurIA(String host, boolean estIA, int port, String nom) throws SocketException, UnknownHostException
  {
      super(host,estIA,port,nom);
  }

  @Override
  public void jouer(int col, int lig, int coin)
  {
      // pas notre tour
      if (this.pont.getJoueurActif() != this.indiceJoueurLocal) return;

      try
      {
          String message = new String(new char[]{(char)('1' + lig),(char)('A' + col),(char)('1' + coin)});
          client.envoyer(message);
          super.jouer(col, lig, coin);
          majIhm();
      } catch (IOException ex)
      {
          Logger.error("Impossible d'envoyer au serveur un message de jeu!");
      }

  }

  @Override
  public void jouer()
  {
    try{Thread.spleep(500);}catch(Exception e){}
    int x,y,coin;
		do {
		    x = rand( 0, pont.getLargeur()-1 );
        y = rand( 0, pont.getHauteur()-1 );
        coin = (int) (Math.random()*4);

	 }while(this.pont.getConteneurs()[x][y].getLocks()[coin] != null );

   jouer(x,y,coin);
  }

  private int rand(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

  public static void main(String[] args)
  {
    try {

      Scanner sc = new Scanner(System.in);
      String host = "";
      int port = 0;

      System.out.print("Host : ");
      host = sc.nextLine();
      System.out.print("Port : ");
      port = Integer.parseInt(sc.nextLine());

      new ControleurIA(host, true, port, "Mr. IA");

    } catch(Exception e){}
  }

}
