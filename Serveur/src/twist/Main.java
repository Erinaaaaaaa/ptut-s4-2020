package twist;

import twist.net.ServeurJeu;
import twist.util.Logger;

/*
 * Classe Main.java
 * Classe permettant de lancer le Serveur
 */

public class Main
{
    public static void main(String[] args) throws Exception
    {
        /*
         * Le premier paramètre correspond au port du serveur
         * Le deuxième paramètre correspond au nombre de joueurs
         */
        new ServeurJeu(9876, 2).run();
    }
}
