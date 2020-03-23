package twist.metier;

public class Pont
{
    private static final int MIN_LIGNES   = 5;
    private static final int MIN_COLONNES = 5;

    private static final int MAX_LIGNES   = 20;
    private static final int MAX_COLONNES = 20;

    private int largeur;
    private int hauteur;

    private Conteneur[][] conteneurs;

    private Joueur[] joueurs;

    public Pont()
    {
        this.largeur = (int) (Math.random() * MAX_LIGNES   - MIN_LIGNES)   + MIN_LIGNES;
        this.hauteur = (int) (Math.random() * MAX_COLONNES - MIN_COLONNES) + MIN_COLONNES;

        // La création d'un tableau a deux dimensions nécessite d'inverser les tailles
        this.conteneurs = new Conteneur[largeur][hauteur];

        for (int i = 0; i < largeur; i++)
            for (int j = 0; j < hauteur; j++)
                this.conteneurs[i][j] = new Conteneur();

        // TODO: définition des voisins

    }

    public Conteneur[][] getPlateau()
    {
        return conteneurs;
    }

    public boolean placerLock(int x, int y, int coin)
    {
        // Hors plateau
        if (x < 0 || x >= largeur || y < 0 || y >= hauteur)
            return false;

        return this.conteneurs[x][y].placerLock(coin);
    }

    public Joueur getJoueur(int i)
    {
        return null;
    }

    public static void main(String[] args)
    {
        Pont pont = new Pont();
    }
}
