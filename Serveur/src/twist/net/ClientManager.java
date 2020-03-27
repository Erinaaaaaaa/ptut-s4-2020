package twist.net;

import twist.metier.Joueur;

import java.net.InetSocketAddress;

/*
 * Classe ClientManager.java
 * Gère quelques informations de bases relatives au Client connecté
 */
public class ClientManager
{

    private final InetSocketAddress adresse;
    private final String nomJoueur;

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
