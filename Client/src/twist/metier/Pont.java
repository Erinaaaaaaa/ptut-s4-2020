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

        this.conteneurs = new Conteneur[largeur][hauteur];

        for (int x = 0; x < largeur; x++)
        for (int y = 0; y < hauteur; y++)
            this.conteneurs[x][y] = new Conteneur();

        // TODO: définition des voisins
        for (int x = 0; x < largeur; x++)
        for (int y = 0; y < hauteur; y++)
        {
            Conteneur c = this.conteneurs[x][y];

            if (y > 0           && x > 0          ) c.setVoisin(0, this.conteneurs[x - 1][y - 1]);
            if (y > 0                             ) c.setVoisin(1, this.conteneurs[x    ][y - 1]);
            if (y > 0           && x < largeur - 1) c.setVoisin(2, this.conteneurs[x + 1][y - 1]);
            if (                   x < largeur - 1) c.setVoisin(3, this.conteneurs[x + 1][y    ]);
            if (y < hauteur - 1 && x < largeur - 1) c.setVoisin(4, this.conteneurs[x + 1][y + 1]);
            if (y < hauteur - 1                   ) c.setVoisin(5, this.conteneurs[x    ][y + 1]);
            if (y < hauteur - 1 && x > 0          ) c.setVoisin(6, this.conteneurs[x - 1][y + 1]);
            if (                   x > 0          ) c.setVoisin(7, this.conteneurs[x - 1][y    ]);
        }
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
        System.out.println("break");
    }
}
