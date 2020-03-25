package twist.net;

import twist.metier.Joueur;

import java.net.InetSocketAddress;

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
}
