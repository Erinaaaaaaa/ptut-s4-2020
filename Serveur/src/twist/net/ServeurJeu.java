package twist.net;

import twist.metier.Pont;
import twist.util.Logger;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Classe ServeurJeu.java
 * Classe gérant le jeu côté serveur avec la récupération des noms des joueurs
 * Elle génère également la map qu'elle enverra aux joueurs
 */

public class ServeurJeu
{
    private ServeurUdp serveur;

    private final int nbJoueurs = 2;

    private final int timeout;

    private HashMap<InetSocketAddress, ClientManager> hmClients;
    private ArrayList<ClientManager> alClients;

    private String[] couleurs = { "ROUGE", "VERT", "BLEU", "JAUNE" };

    private Pont pont;

    public ServeurJeu(int port, int timeout) throws SocketException
    {
        serveur = new ServeurUdp(port);
        this.timeout = 1000 * timeout;
    }

    public void run() throws IOException
    {
        Logger.information("Ecoute sur " + serveur.getPort());

        hmClients = new HashMap<>();
        alClients = new ArrayList<>();

        // Etape 1: récupération du nom des joueurs
        while (this.hmClients.size() < this.nbJoueurs)
        {
            try
            {
                Message m = serveur.lireMessage();

                if (!hmClients.containsKey(m.getAdresse()))
                {
                    String nomJoueur = m.getMessage();

                    hmClients.put(m.getAdresse(), new ClientManager(m.getAdresse(), nomJoueur));
                    alClients.add(hmClients.get(m.getAdresse()));

                    int numJoueur = hmClients.size();

                    String messageSortie = numJoueur + "-Bonjour " + nomJoueur + "; vous êtes le Joueur " + numJoueur + "(" + couleurs[numJoueur-1] + "), attente suite...";

                    serveur.envoyerMessage(messageSortie, m.getAdresse());

                    Logger.information(nomJoueur + " est connecté!");
                }
                else
                {
                    serveur.envoyerMessage("91-Vous êtes déjà inscrit!", m.getAdresse());
                    Logger.warning(hmClients.get(m.getAdresse()).getNomJoueur() + " est déjà inscrit");
                }
            }
            catch (IOException e)
            {
                Logger.warning("Erreur lors de la lecture d'un message");
            }
        }

        // Etape 2: préparation de la partie
        {
            String[] noms = new String[hmClients.size()];
            int i = 0;
            for (ClientManager client : hmClients.values())
            {
                noms[i] = client.getNomJoueur();
                i++;
            }

            pont = new Pont(noms, (int)(Math.random() * 5 + 5), (int)(Math.random() * 5 + 5), 20);

            for (int j = 0; j < alClients.size(); j++)
                alClients.get(j).setJoueur(pont.getJoueur(j));

            StringBuilder sb = new StringBuilder("01-La partie va commencer\nMAP=");

            for (int y = 0; y < pont.getHauteur(); y++)
            {
                for (int x = 0; x < pont.getLargeur(); x++)
                {
                    sb.append(pont.getConteneurs()[x][y].getValeur());

                    if (x != pont.getLargeur() - 1)
                        sb.append(':');
                }

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

        // Etape 3: jeu
        boolean inviteEnvoyee = false;
        Timer t = new Timer(timeout, e -> {
            try
            {
                // On sait qu'il n'y a que deux joueurs.
                broadcast("88-Timeout de " + alClients.get(this.pont.getJoueurActif()).getNomJoueur() + " - " + alClients.get(1 - this.pont.getJoueurActif()).getNomJoueur() + " gagne par forfait");
                System.exit(0);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        });


        {
            while (!pont.partieTerminee())
            {
                try
                {
                    if (!inviteEnvoyee)
                    {
                        t.restart();
                        serveur.envoyerMessage("10-A vous de jouer (" + couleurs[pont.getJoueurActif()] + ")",
                                alClients.get(pont.getJoueurActif()).getAdresse());
                        inviteEnvoyee = true;
                    }

                    Message m = serveur.lireMessage();

                    boolean resultat = false;

                    if (!hmClients.containsKey(m.getAdresse()))
                    {
                        Logger.warning("Une personne invalide a essayé de jouer");
                        // Aucune réponse
                    }
                    else if (!m.getAdresse().equals(alClients.get(pont.getJoueurActif()).getAdresse()))
                    {
                        Logger.warning(hmClients.get(m.getAdresse()).getNomJoueur() + " n'a pas joué au bon moment");
                        serveur.envoyerMessage("91-Ce n'est pas votre tour!", m.getAdresse());
                    }
                    else
                    {
                        // Le bon joueur a envoyé son message
                        t.stop();
                        inviteEnvoyee = false;
                        String message = m.getMessage();
                        if (!message.matches("[0-9][A-Z][1-4]"))
                        {
                            pont.placerLock(-1, -1, -1);
                        }
                        else
                        {
                            int lig = message.charAt(0) - '1';
                            int col = message.charAt(1) - 'A';
                            int coin = message.charAt(2) - '1';
                            Logger.verbose("pont.placerLock("+lig+", "+col+", "+coin+")");
                            resultat = pont.placerLock(col, lig, coin);
                        }

                        if (!resultat)
                        {
                            Logger.warning(hmClients.get(m.getAdresse()).getNomJoueur() + " a entré un coup illégal");
                            envoyerAPresqueToutLeMonde("22-Coup adversaire illégal", m.getAdresse());
                            serveur.envoyerMessage("21-Coup joué illégal", m.getAdresse());
                        }
                        else
                        {
                            Logger.information(hmClients.get(m.getAdresse()).getNomJoueur() + " a joué " + m.getMessage());
                            envoyerAPresqueToutLeMonde("20-Coup adversaire:" + m.getMessage(), m.getAdresse());
                        }

                        if (hmClients.get(m.getAdresse()).getJoueur().getNbLocks() <= 0)
                        {
                            Logger.information(hmClients.get(m.getAdresse()).getNomJoueur() + " est tombé à court de locks!");
                            serveur.envoyerMessage("50-Vous êtes tombé à court de locks!", m.getAdresse());
                        }
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            StringBuilder sb = new StringBuilder("88-Partie terminée, scores finaux: ");

            for (int i = 0, tabClientsLength = alClients.size(); i < tabClientsLength; i++)
            {
                ClientManager clientManager = alClients.get(i);

                sb.append(String.format("\n%-20s (%5s): %3d points", clientManager.getNomJoueur(), couleurs[i], pont.getScoreJoueur(i)));
            }

            sb.append("\nGagnant: ").append(pont.getGagnant().getNom());

            broadcast(sb.toString());
        }

        Logger.information("Exécution terminée");
    }

    private void broadcast(String message) throws IOException
    {
        for (ClientManager clientManager : alClients)
            serveur.envoyerMessage(message, clientManager.getAdresse());
    }

    private void envoyerAPresqueToutLeMonde(String message, InetSocketAddress exception) throws IOException
    {
        for (ClientManager clientManager : alClients)
            if (!clientManager.getAdresse().equals(exception))
                serveur.envoyerMessage(message, clientManager.getAdresse());
    }
}
