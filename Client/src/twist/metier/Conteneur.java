package twist.metier;

public class Conteneur
{
    // Position des conteneurs voisins
    public static final int C_N  = 0;
    public static final int C_NE = 1;
    public static final int C_E  = 2;
    public static final int C_SE = 3;
    public static final int C_S  = 4;
    public static final int C_SO = 5;
    public static final int C_W  = 6;
    public static final int C_NO = 7;

    private Lock[] locks;

    private Conteneur[] voisins;

    private int valeur;

    public Conteneur()
    {
        this.valeur = (int) (Math.random() * 50 + 5);
        this.locks = new Lock[4];
    }

    public boolean placerLock(int coin)
    {
        return true;
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
