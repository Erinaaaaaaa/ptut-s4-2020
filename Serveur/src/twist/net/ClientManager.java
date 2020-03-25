package twist.net;

import java.net.InetSocketAddress;

public class ClientManager
{
    private InetSocketAddress adresse;
    private String nomJoueur;

    public ClientManager(InetSocketAddress adresse, String nomJoueur)
    {
        this.adresse = adresse;
        this.nomJoueur = nomJoueur;
    }

    public String getNomJoueur()
    {
        return this.nomJoueur;
    }
}
