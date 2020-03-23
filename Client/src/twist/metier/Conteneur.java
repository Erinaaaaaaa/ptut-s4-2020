package twist.metier;

public class Conteneur
{
    private Lock[] locks;

    private Conteneur[] voisins;

    private int valeur;

    public Conteneur()
    {
        this.valeur = (int) (Math.random() * 50 + 5);
    }

    public boolean placerLock(int coin)
    {
        return true;
    }

    public Lock getLock(int i) {return null;}
    public Lock[] getLocks() {return locks;}
    public int getValeur() { return valeur; }
}
