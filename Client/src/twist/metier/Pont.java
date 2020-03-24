package twist.metier;

import twist.Controleur;

public class Pont
{
    private static final int MIN_LIGNES   = 5;
    private static final int MIN_COLONNES = 5;

    private static final int MAX_LIGNES   = 10;
    private static final int MAX_COLONNES = 10;

    private static final int NB_LOCKS_DEFAULT = 20;

    private Controleur ctrl;

    private int largeur;
    private int hauteur;

    private Conteneur[][] conteneurs;

    private Joueur[] joueurs;
    private int joueurCourant;

    public Pont(Controleur ctrl, String[] nomsJoueurs)
    {
        this(ctrl, nomsJoueurs,
                (int) (Math.random() * (MAX_LIGNES   - MIN_LIGNES))   + MIN_LIGNES,
                (int) (Math.random() * (MAX_COLONNES - MIN_COLONNES)) + MIN_COLONNES,
                NB_LOCKS_DEFAULT);
    }

    public Pont(Controleur ctrl, String[] nomsJoueurs, int largeur, int hauteur, int nbLocks)
    {
        this.ctrl = ctrl;

        this.largeur = largeur;
        this.hauteur = hauteur;

        this.conteneurs = new Conteneur[largeur][hauteur];

        for (int x = 0; x < largeur; x++)
        for (int y = 0; y < hauteur; y++)
            this.conteneurs[x][y] = new Conteneur();

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

        this.joueurs = new Joueur[nomsJoueurs.length];

        for (int i = 0; i < nomsJoueurs.length; i++)
            this.joueurs[i] = new Joueur(nomsJoueurs[i], nbLocks);

        this.joueurCourant = 0;
    }

    public int getLargeur()
    {
        return largeur;
    }

    public int getHauteur()
    {
        return hauteur;
    }

    public Conteneur[][] getPlateau()
    {
        return conteneurs;
    }

    public boolean placerLock(int x, int y, int coin)
    {
        boolean result = true;
        Joueur j = this.joueurs[this.joueurCourant];

        // Hors plateau
        if (x < 0 || x >= largeur || y < 0 || y >= hauteur || coin > 3 || coin < 0)
            result = false;
        else
            result = this.conteneurs[x][y].jouerLock(coin, new Lock(j));

        j.utiliserLock();
        // Perdre un lock en cas de jeu incorrect
        if (!result)
            j.utiliserLock();

        this.joueurCourant = (joueurCourant + 1) % this.joueurs.length;

        while (this.joueurs[this.joueurCourant].getNbLocks() == 0 && !partieTerminee())
            this.joueurCourant = (joueurCourant + 1) % this.joueurs.length;

        return result;
    }

    public boolean partieTerminee()
    {
        for (Joueur joueur : this.joueurs)
            if (joueur.getNbLocks() != 0)
                return false;

        for (Conteneur[] c1 : conteneurs)
        for (Conteneur c : c1)
        for (Lock l : c.getLocks())
            if (l == null) return false;

        return true;
    }

    public Joueur getJoueur(int i)
    {
        if (i < 0 || i >= getNbJoueur()) return null;

        return this.joueurs[i];
    }

    public int getScoreJoueur(int i)
    {
        int score = 0;
        Joueur j = this.joueurs[i];

        for (int x = 0; x < largeur; x++)
        for (int y = 0; y < hauteur; y++)
        {
            Conteneur c = this.conteneurs[x][y];

            if (c.joueurMajoritaire() == j)
                score += c.getValeur();
        }

        return score;
    }

    public int getNumeroJoueur(Joueur j)
    {
        for (int i = 0; i < joueurs.length; i++)
            if (j == joueurs[i])
                return i;

        return -1;
    }

    public int getNbJoueur()
    {
        return this.joueurs.length;
    }

    public int getJoueurActif(){return this.joueurCourant;}
}
