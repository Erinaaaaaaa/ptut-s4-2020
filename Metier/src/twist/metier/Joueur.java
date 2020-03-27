package twist.metier;

/*
 * Classe Joueur.java
 * Classe gérant les joueurs, c'est-à-dire leur nom ainsi que leurs locks de départ et quand ils sont joués
 */

public class Joueur
{
    private final String nom;
    private int nbLocks;

    public Joueur(String nom, int nbLocks)
    {
        this.nom = nom;
        this.nbLocks = nbLocks;
    }

    public String getNom() { return nom; }
    public int getNbLocks() { return this.nbLocks; }

    public void utiliserLock()
    {
        this.nbLocks--;
    }
}
