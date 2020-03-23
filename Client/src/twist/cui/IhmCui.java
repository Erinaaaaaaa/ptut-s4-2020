package twist.cui;

import twist.Controleur;
import twist.ControleurCui;
import twist.metier.Conteneur;
import twist.metier.Joueur;
import twist.metier.Lock;

import java.util.Scanner;

public class IhmCui
{
    private Controleur ctrl;

    public IhmCui(Controleur ctrl)
    {
        this.ctrl = ctrl;

        while (!ctrl.partieTerminee())
        {
            afficherPlateau();
            input();
        }

    }

    private void input()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez les coordonnées (x, y, coin):");
        System.out.println("Coins: 1 - haut gauche ; 2 - haut droite ; 3 - bas droite ; 4 - bas gauche");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int coin = scanner.nextInt();

        ctrl.jouer(x, y, coin-1);
    }

    private void afficherPlateau()
    {
        int largeur = ctrl.getLargeurPont();
        int hauteur = ctrl.getHauteurPont();

        Conteneur[][] conteneurs = ctrl.getConteneurs();

        System.out.print("   ");

        for (int i = 0; i < largeur; i++)
            System.out.print(String.format("  %2d  ", i));


        for (int x = 0; x < hauteur; x++)
        {
            System.out.println();
            // Ligne haute
            for (int y = -1; y < largeur; y++)
            {
                if (y == -1) System.out.print("   ");
                else
                {
                    Lock[] locks = conteneurs[y][x].getLocks();
                    char lock0 = ' ';
                    char lock1 = ' ';

                    if (locks[0] != null)
                    {
                        lock0 = (char) ('A' + ctrl.getNumeroJoueur(locks[0].getJoueur()));
                    }

                    if (locks[1] != null)
                    {
                        lock1 = (char) ('A' + ctrl.getNumeroJoueur(locks[1].getJoueur()));
                    }

                    System.out.print(String.format("%c----%c",lock0, lock1));
                }
            }

            System.out.println();
            // Ligne centrale
            for (int y = -1; y < largeur; y++)
            {
                if (y == -1) System.out.print(String.format("%2d ", x));
                else
                {
                    int nom = conteneurs[y][x].getValeur();
                    System.out.print(String.format("| %2d |", nom));
                }
            }

            System.out.println();
            // Ligne basse
            for (int y = -1; y < largeur; y++)
            {
                if (y == -1) System.out.print("   ");
                else
                {
                    Lock[] locks = conteneurs[y][x].getLocks();
                    char lock2 = ' ';
                    char lock3 = ' ';

                    if (locks[2] != null)
                    {
                        lock2 = (char) ('A' + ctrl.getNumeroJoueur(locks[2].getJoueur()));
                    }

                    if (locks[3] != null)
                    {
                        lock3 = (char) ('A' + ctrl.getNumeroJoueur(locks[3].getJoueur()));
                    }
                    System.out.print(String.format("%c----%c",lock3, lock2));
                }
            }
        }
        System.out.println();

        for (int i = 0; i < ctrl.getNbJoueur(); i++)
        {
            Joueur joueur = ctrl.getJoueur(i);
            int score = ctrl.getScoreJoueur(i);

            System.out.println(String.format("%-20s (%2d locks) : %3d points", joueur.getNom(), joueur.getNbLocks(), score));
        }
    }
}
