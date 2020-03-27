package twist.net;

import twist.Controleur;
import twist.ihm.jeu.IhmPlateau;
import twist.metier.Conteneur;
import twist.metier.Pont;
import twist.util.Logger;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

/*
 * Classe ControleurReseau.java
 * Classe se servant de Controleur comme référence
 * Cette classe permet de lancer un Client
 * Gère une partie réseau
 */

public class ControleurReseau extends Controleur
{
    private ClientUdp client;
    private IhmPlateau ihm;

    private String nomJoueur;
    private int indiceJoueurLocal;

    public ControleurReseau(String host, int port, String nom) throws SocketException, UnknownHostException
    {
        Logger.information("Connection à " + host +":"+ port + " en tant que " + nom);
        this.nomJoueur = nom;
        client = new ClientUdp(host, port);
        preparer();
    }

    private void preparer()
    {
        // Etape 0: préparer Thread de lecture
        new Thread(new LecteurThread(this)).start();

        // Etape 1: Envoyer le nom du joueur
        try
        {
            client.envoyer(this.nomJoueur);
        }
        catch (IOException e)
        {
            Logger.fatal("Impossible d'envoyer le nom du joueur! " + e.getMessage());
        }
    }

    public Conteneur[][] interpreterMap(String map)
    {
        map = map.substring(0, map.length() - 1);

        int nbLignes   = map.split("\\|").length;
        int nbColonnes = map.split("\\|")[0].split(":").length;

        Conteneur[][] tabConteneurs = new Conteneur[nbColonnes][nbLignes];

        String ligneActuelle = "";
        for(int i = 0; i < nbLignes; i++)
        {
            ligneActuelle = map.split("\\|")[i];
            for(int j = 0; j < nbColonnes; j++)
            {
                int valeur = Integer.parseInt(ligneActuelle.split(":")[j]);
                Conteneur cont = new Conteneur(valeur);
                tabConteneurs[j][i] = cont;
            }
        }

        return tabConteneurs;
    }

    public String lireMessage() throws IOException
    {

        return client.lireMessage();
    }

    public void envoyer(String texte) throws IOException
    {
        client.envoyer(texte);
    }

    public void setJoueurLocal(int i)
    {
        this.indiceJoueurLocal = i;
    }

    public void creerPont(Conteneur[][] conteneurs)
    {
        String[] noms = {"Joueur 1", "Joueur 2"};
        noms[indiceJoueurLocal] = nomJoueur;

        this.pont = new Pont(this, noms, conteneurs);
        this.ihm = new IhmPlateau(this);
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

    public void jouerLocal(int col, int lig, int coin)
    {
        super.jouer(col, lig, coin);
        majIhm();
    }

    public void majIhm()
    {
        this.ihm.majIhm();
    }

    public void finPartie()
    {
        this.ihm.fin();
    }

    public void finPartie(String s)
    {
        this.ihm.fin(s);
    }

    public void setJoueurLocalActif()
    {
        this.pont.setJoueurActif(this.indiceJoueurLocal);
    }
}
