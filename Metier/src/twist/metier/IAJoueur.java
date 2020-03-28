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

	//IA min max

	private void minMax(Pont pontfictice, int profondeur){
		int max_val = -1000;
		int[] coup = {0,0,0};
		for (int i=0;i<this.pont.getConteneurs().length ;i++ ) {
			for (int j=0;j<this.pont.getConteneurs()[0].length ;j++ ) {
				for (int k=0;k<4 ;k++ ) {
					pontfictice.placerLock(i,j,k);
					int val = max(pontfictice,profondeur-1);
					if (val>max_val) {
						max_val = val;
						coup[0]=i;coup[1]=j;coup[2]=k;
					}
					pontfictice.anullerCoup(i, j, k);
				}
			}
		}
		this.pont.placerLock(coup[0], coup[1], coup[2]);
	}

	private int min(Pont pontfictice, int profondeur){
		if (profondeur == 0 || pontfictice.partieTerminee()) {
			return eval(pontfictice);
		}

		int min_val = 1000;
		for (int i=0;i<this.pont.getConteneurs().length ;i++ ) {
			for (int j=0;j<this.pont.getConteneurs()[0].length ;j++ ) {
				for (int k=0;k<4 ;k++ ) {
					pontfictice.placerLock(i,j,k);
					int val = max(pontfictice,profondeur-1);
					if (val<min_val) {
						min_val = val;
					}
					pontfictice.anullerCoup(i, j, k);
				}
			}
		}
		return min_val;
	}

	private int max(Pont pontfictice, int profondeur){
		if (profondeur == 0 || pontfictice.partieTerminee()) {
			return eval(pontfictice);
		}

		int max_val = 1000;
		for (int i=0;i<this.pont.getConteneurs().length ;i++ ) {
			for (int j=0;j<this.pont.getConteneurs()[0].length ;j++ ) {
				for (int k=0;k<4 ;k++ ) {
					pontfictice.placerLock(i,j,k);
					int val = min(pontfictice,profondeur-1);
					if (val>max_val) {
						max_val = val;
					}
					pontfictice.anullerCoup(i, j, k);
				}
			}
		}
		return max_val;
	}

	private int eval(Pont pontfictice){
		return (int)Math.random()*1000;
	}
}
