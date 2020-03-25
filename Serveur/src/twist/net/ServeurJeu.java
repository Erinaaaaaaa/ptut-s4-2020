package twist.net;

import twist.metier.Pont;
import twist.util.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServeurJeu
{
    private ServeurUdp serveur;

    private int nbJoueurs;

    private HashMap<InetSocketAddress, ClientManager> clients;
    private String[] couleurs = { "ROUGE", "VERT", "BLEU", "JAUNE" };

    private Pont pont;

    public ServeurJeu(int port, int nbJoueurs) throws SocketException
    {
        serveur = new ServeurUdp(port);
        this.nbJoueurs = nbJoueurs;
    }

    public void run()
    {
        Logger.information("Ecoute sur " + serveur.getPort());

        clients = new HashMap<>();

        // Etape 1: récupération du nom des joueurs
        while (this.clients.size() < this.nbJoueurs)
        {
            try
            {
                Message m = serveur.lireMessage();

                if (!clients.containsKey(m.getAdresse()))
                {
                    String nomJoueur = m.getMessage();

                    clients.put(m.getAdresse(), new ClientManager(m.getAdresse(), nomJoueur));

                    int numJoueur = clients.size();

                    String messageSortie = numJoueur + "-Bonjour " + nomJoueur + "; vous êtes le Joueur " + numJoueur + "(" + couleurs[numJoueur-1] + "), attente suite...";

                    serveur.envoyerMessage(messageSortie, m.getAdresse());

                    Logger.information(nomJoueur + " est connecté!");
                }
                else
                {
                    serveur.envoyerMessage("91-Vous êtes déjà inscrit!", m.getAdresse());
                    Logger.warning(clients.get(m.getAdresse()).getNomJoueur() + " est déjà inscrit");
                }
            }
            catch (IOException e)
            {
                Logger.warning("Erreur lors de la lecture d'un message");
            }
        }

        // Etape 2: préparation de la partie
        {
            String[] noms = new String[clients.size()];
            int i = 0;
            for (Map.Entry<InetSocketAddress, ClientManager> entry : clients.entrySet())
            {
                noms[i] = entry.getValue().getNomJoueur();
                i++;
            }

            pont = new Pont(noms);

            StringBuilder sb = new StringBuilder("01-La partie va commencer\nMAP=");

            for (int x = 0; x < pont.getHauteur(); x++)
            {
                for (int y = 0; y < pont.getLargeur(); y++)
                {
                    sb.append(pont.getPlateau()[y][x].getValeur());

                    if (y != pont.getLargeur() - 1)
                        sb.append(':');
                }

                if (x != pont.getHauteur() - 1)
                    sb.append('|');
            }

            Logger.information("Envoi de la map au clients");
            try
            {
                broadcast(sb.toString());
            }
            catch (IOException e)
            {
                Logger.fatal("Erreur lors de l'envoi de la map");
            }
        }

        

        Logger.information("Exécution terminée");
    }

    private void broadcast(String message) throws IOException
    {
        for (InetSocketAddress addr : clients.keySet())
            serveur.envoyerMessage(message, addr);
    }
}
