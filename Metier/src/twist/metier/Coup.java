package twist.metier;

public class Coup
{
  private int ligne;
  private int colonne;
  private int coin;

  public Coup(int ligne, int colonne, int coin) {
    this.ligne   = ligne;
    this.colonne = colonne;
    this.coin    = coin;
  }

  public int getLigne()  {return this.ligne;}
  public int getColonne(){return this.colonne;}
  public int getCoin()   {return this.coin;}

  public String toString()
  {
    String rep = "";

    rep += ( ligne + 1 );
    rep += ( 'A'   + colonne );
    rep += ( coin  + 1);

    return rep;
  }
}
