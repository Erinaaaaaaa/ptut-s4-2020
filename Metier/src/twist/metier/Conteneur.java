package twist.metier;

import java.util.HashMap;
import java.util.Map;

public class Conteneur
{
    // Position des conteneurs voisins
    public static final int C_NO = 0;
    public static final int C_N  = 1;
    public static final int C_NE = 2;
    public static final int C_E  = 3;
    public static final int C_SE = 4;
    public static final int C_S  = 5;
    public static final int C_SO = 6;
    public static final int C_O  = 7;

    private Lock[] locks;

    private Conteneur[] voisins;

    private int valeur;

    public Conteneur()
    {
        this.valeur = (int) (Math.random() * 50 + 5);
        this.locks = new Lock[4];
        this.voisins = new Conteneur[8];
    }

    public Conteneur(int valeur)
    {
      this();
      this.valeur = valeur;
    }

    public void setValeur(int valeur) { this.valeur = valeur; }
    /*
     * Positions des locks:
     *        0 ----- 1
     *        |       |
     *        3 ----- 2
     */
    public boolean jouerLock(int coin, Lock lock)
    {
        // Valeur attendue: 0-3
        if (!putLockReference(coin, lock))
            return false;

        switch (coin)
        {
            case 0:
                {
                    if(voisins[C_O]  != null) voisins[C_O] .putLockReference(1, lock);
                    if(voisins[C_NO] != null) voisins[C_NO].putLockReference(2, lock);
                    if(voisins[C_N]  != null) voisins[C_N] .putLockReference(3, lock);
                }
                break;
            case 1:
                {
                    if(voisins[C_N]  != null) voisins[C_N] .putLockReference(2, lock);
                    if(voisins[C_NE] != null) voisins[C_NE].putLockReference(3, lock);
                    if(voisins[C_E]  != null) voisins[C_E] .putLockReference( 0, lock);
                }
                break;
            case 2:
                {
                    if(voisins[C_E]  != null) voisins[C_E] .putLockReference(3, lock);
                    if(voisins[C_SE] != null) voisins[C_SE].putLockReference(0, lock);
                    if(voisins[C_S]  != null) voisins[C_S] .putLockReference(1, lock);
                }
                break;
            case 3:
                {
                    if(voisins[C_S]  != null) voisins[C_S] .putLockReference(0, lock);
                    if(voisins[C_SO] != null) voisins[C_SO].putLockReference(1, lock);
                    if(voisins[C_O]  != null) voisins[C_O] .putLockReference( 2, lock);
                }
                break;
            default: return false;
        }

        return true;
    }

    private boolean putLockReference(int coin, Lock lock)
    {
        if (this.locks[coin] != null)
            return false;

        this.locks[coin] = lock;
        return true;
    }

    public Joueur joueurMajoritaire()
    {
        HashMap<Joueur, Integer> possessions = new HashMap<>();

        for (Lock l : locks)
        {
            if (l == null) continue;

            Joueur j = l.getJoueur();

            if (possessions.containsKey(j))
                possessions.replace(j, possessions.get(j)+1);
            else
                possessions.put(j, 1);
        }

        Joueur best = null;
        int nb = 0;

        for (Map.Entry<Joueur, Integer> pair : possessions.entrySet())
        {
            if (pair.getValue() > nb)
            {
                best = pair.getKey();
                nb = pair.getValue();
            }
            else if (pair.getValue() == nb)
            {
                best = null;
            }
        }

        return best;
    }

    public Lock getLock(int i) {return this.locks[i];}
    public Lock[] getLocks() {return locks;}

    /**
     * Définit le voisin à la position par i.
     * @param i Position du voisin
     * @param voisin Voisin
     */
    public void setVoisin(int i, Conteneur voisin)
    {
        this.voisins[i] = voisin;
    }

    public Conteneur getVoisin(int i) {return this.voisins[i];}

    public int getValeur() { return valeur; }
}
