package twist.metier;

public class Joueur
{
    private String nom;
    private int nbLocks;

    public Joueur(String nom, int nbLocks)
    {
        this.nom = nom;
        this.nbLocks = nbLocks;
    }

    public String getNom() { return nom; }
    public int getNbLocks() { return this.nbLocks; }
}
