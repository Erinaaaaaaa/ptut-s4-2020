package twist.metier;

/*
 * Classe Lock.java
 * CLasse permettant d'attribuer un joueur au Lock
 */

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
