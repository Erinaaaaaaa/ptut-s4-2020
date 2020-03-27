package twist.metier;
public class IAJoueur extends Joueur {
	private Pont pont;
	public IAJoueur(Pont pont,String nom,int nbLocks){
		super(nom, nbLocks);
		this.pont = pont;
	}

	public void jouer(){
		int x,y,coin;
		do{
		x =(int) (Math.random()*pont.getLargeur());
		y =(int)( Math.random()*pont.getHauteur());
		coin = (int) (Math.random()*4);
		}while( pont.getConteneurs()[x][y].getLocks()[coin] != null );
		pont.placerLock(x, y, coin);
	}
}
