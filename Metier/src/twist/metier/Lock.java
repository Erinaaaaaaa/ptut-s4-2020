package twist.metier;

public class Lock
{
    private Joueur joueur;

    public Lock(Joueur joueur)
    {
        this.joueur = joueur;
    }

    public Joueur getJoueur()
    {
        return this.joueur;
    }
}