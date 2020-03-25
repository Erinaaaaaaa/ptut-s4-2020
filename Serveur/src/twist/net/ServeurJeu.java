package twist.net;

import twist.util.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.HashMap;

public class ServeurJeu
{
    private ServeurUdp serveur;

    private int nbJoueurs;

    private HashMap<InetSocketAddress, ClientManager> clients;
    private String[] couleurs = { "ROUGE", "VERT", "BLEU", "JAUNE" };

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

                    String messageSortie = numJoueur + "-Bonjour " + nomJoueur + "; vous êtes le Joueur " + numJoueur + "(" + couleurs[numJoueur] + "), attente suite...";

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

    }
}
