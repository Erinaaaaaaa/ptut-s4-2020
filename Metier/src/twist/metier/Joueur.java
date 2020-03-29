package twist.metier;

/*
 * Classe Joueur.java
 * Classe gérant les joueurs, c'est-à-dire leur nom ainsi que leurs locks de départ et quand ils sont joués
 */

import twist.metier.ia.IA;

public class Joueur
{
    private final String nom;
    protected final Pont pont;
    private int nbLocks;

    private final IA intelligence;

    public Joueur(Pont pont, String nom, int nbLocks)
    {
        this(pont, nom, nbLocks, null);
    }

    public Joueur(Pont pont, String nom, int nbLocks, IA intelligence)
    {
        this.pont = pont;
        this.nom = nom;
        this.nbLocks = nbLocks;
        this.intelligence = intelligence;
    }

    public String getNom() { return nom; }
    public int getNbLocks() { return this.nbLocks; }

    public void utiliserLock()
    {
        this.nbLocks--;
    }

    public boolean estIA() { return this.intelligence != null; }

    public Coup getCoup() { return this.intelligence.next(pont); }
}
