package twist.metier;

import java.util.*;

public class IAJoueur extends Joueur {
	private Pont pont;
	public IAJoueur(Pont pont,String nom,int nbLocks){
		super(nom, nbLocks);
		this.pont = pont;
	}

	public void jouer(){
		int x,y,coin;
		do {
		x = rand( 0, pont.getLargeur() );
		y = rand( 0, pont.getHauteur() );
		coin = (int) (Math.random()*4);
		}while( pont.getConteneurs()[x][y].getLocks()[coin] != null );
		pont.placerLock(x, y, coin);
	}

	public Coup[] getCoupsPossible() {

		ArrayList<Coup> lstSolutions = new ArrayList<Coup>();

		//Coups coin haut droit
		for(int lig=0; lig<pont.getHauteur(); lig++) {
			for(int col=0; col<pont.getLargeur(); col++) {
				if(pont.peutPlacerLock(lig,col,0)) {
					lstSolutions.add( new Coup(lig,col,0) );
				}
			}
		}

		for(int lig=0; lig<pont.getHauteur(); lig++) {
			if(pont.peutPlacerLock(lig, pont.getLargeur()-1 ,1)) {
				lstSolutions.add( new Coup(lig, pont.getLargeur()-1, 1));
			}
		}

		if(pont.peutPlacerLock(pont.getHauteur()-1, pont.getLargeur()-1, 2))
		{
			lstSolutions.add( new Coup(pont.getHauteur()-1, pont.getLargeur()-1, 2) );
		}

		Coup[] temp = new Coup[lstSolutions.size()];
		return lstSolutions.toArray(temp);
	}

	private int rand(int min, int max) {
		 return (int) (Math.random()*(max+min))+min;
	}
}
