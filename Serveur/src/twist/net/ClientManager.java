package twist.net;

import twist.metier.Joueur;

import java.net.InetSocketAddress;

/*
 * Classe ClientManager.java
 * Gère quelques informations de bases relatives au Client connecté
 */
public class ClientManager
{
    private static int NbJoueurs = 0;

    private InetSocketAddress adresse;
    private String nomJoueur;
    private int nbJoueur = NbJoueurs++;

    private Joueur joueur;

    public ClientManager(InetSocketAddress adresse, String nomJoueur)
    {
        this.adresse = adresse;
        this.nomJoueur = nomJoueur;
    }

    public String getNomJoueur()
    {
        return this.nomJoueur;
    }

    public int getNbJoueur() { return this.nbJoueur; }

    public void setJoueur(Joueur joueur)
    {
        this.joueur = joueur;
    }

    public InetSocketAddress getAdresse()
    {
        return this.adresse;
    }

    public Joueur getJoueur()
    {
        return this.joueur;
    }
}
