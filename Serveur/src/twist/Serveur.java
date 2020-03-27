package twist;

import twist.net.ServeurJeu;
import twist.util.Logger;

/*
 * Classe Serveur.java
 * Classe permettant de lancer le Serveur
 */

public class Serveur
{
    public static void main(String[] args) throws Exception
    {
        int port = 9876;
        int timeout = 60;

        if (args.length >= 1)
        {
            port = Integer.parseInt(args[0]);
        }

        if (args.length >= 2)
        {
            timeout = Integer.parseInt(args[1]);
        }

        new ServeurJeu(port, timeout).run();
    }
}
