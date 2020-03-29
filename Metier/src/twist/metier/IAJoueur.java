package twist.metier;

import java.util.*;

public class IAJoueur extends Joueur
{
	public IAJoueur(Pont pont, String nom, int nbLocks)
	{
		super(pont, nom, nbLocks);
	}

	public void jouer()
	{
		/*int x,y,coin;
		do {
		x = rand( 0, pont.getLargeur() );
		y = rand( 0, pont.getHauteur() );
		coin = (int) (Math.random()*4);
	}while( pont.getConteneurs()[x][y].getLocks()[coin] != null );*/
		Pont fictice = this.pont;
		minMax(fictice, 2);
		//pont.placerLock(x, y, coin);
	}

	public Coup[] getCoupsPossible()
	{

		ArrayList<Coup> lstSolutions = new ArrayList<Coup>();

		//Coups coin haut droit
		for (int lig = 0; lig < pont.getHauteur(); lig++)
		{
			for (int col = 0; col < pont.getLargeur(); col++)
			{
				if (pont.peutPlacerLock(lig, col, 0))
				{
					lstSolutions.add(new Coup(lig, col, 0));
				}
			}
		}

		for (int lig = 0; lig < pont.getHauteur(); lig++)
		{
			if (pont.peutPlacerLock(lig, pont.getLargeur() - 1, 1))
			{
				lstSolutions.add(new Coup(lig, pont.getLargeur() - 1, 1));
			}
		}

		if (pont.peutPlacerLock(pont.getHauteur() - 1, pont.getLargeur() - 1, 2))
		{
			lstSolutions.add(new Coup(pont.getHauteur() - 1, pont.getLargeur() - 1, 2));
		}

		Coup[] temp = new Coup[lstSolutions.size()];
		return lstSolutions.toArray(temp);
	}

	private int rand(int min, int max)
	{
		return (int) (Math.random() * (max + min)) + min;
	}

//IA min max

	private void minMax(Pont pontfictice, int profondeur)
	{
		int max_val = -1000;
		int[] coup = {0, 0, 0};
		for (int i = 0; i < this.pont.getConteneurs().length; i++)
		{
			for (int j = 0; j < this.pont.getConteneurs()[0].length; j++)
			{
				for (int k = 0; k < 4; k++)
				{
					pontfictice.simulerCoup(i, j, k);
					int val = max(pontfictice, profondeur - 1);
					if (val > max_val)
					{
						max_val = val;
						coup[0] = i;
						coup[1] = j;
						coup[2] = k;
					}
					pontfictice.annulerCoup(i, j, k);
				}
			}
		}
		this.pont.jouer(coup[0], coup[1], coup[2]);
	}

	private int min(Pont pontfictice, int profondeur)
	{
		if (profondeur == 0 || pontfictice.partieTerminee())
		{
			return eval(pontfictice);
		}

		int min_val = 1000;
		for (int i = 0; i < this.pont.getConteneurs().length; i++)
		{
			for (int j = 0; j < this.pont.getConteneurs()[0].length; j++)
			{
				for (int k = 0; k < 4; k++)
				{
					pontfictice.simulerCoup(i, j, k);
					int val = max(pontfictice, profondeur - 1);
					if (val < min_val)
					{
						min_val = val;
					}
					pontfictice.annulerCoup(i, j, k);
				}
			}
		}
		return min_val;
	}

	private int max(Pont pontfictice, int profondeur)
	{
		if (profondeur == 0 || pontfictice.partieTerminee())
		{
			return eval(pontfictice);
		}

		int max_val = 1000;
		for (int i = 0; i < this.pont.getConteneurs().length; i++)
		{
			for (int j = 0; j < this.pont.getConteneurs()[0].length; j++)
			{
				for (int k = 0; k < 4; k++)
				{
					pontfictice.simulerCoup(i, j, k);
					int val = min(pontfictice, profondeur - 1);
					if (val > max_val)
					{
						max_val = val;
					}
					pontfictice.annulerCoup(i, j, k);
				}
			}
		}
		return max_val;
	}

	private int eval(Pont pontfictice)
	{
		int nb_de_pions = 0;
		int retour = 0;
		//On compte le nombre de pions présents sur le plateau
		for (int i = 0; i < pontfictice.getConteneurs().length; i++)
		{
			for (int j = 0; j < pontfictice.getConteneurs()[0].length; j++)
			{
				for (int k = 0; k < 4; k++)
				{
					if (pontfictice.getConteneurs()[i][j].getLock(k) != null)
					{
						nb_de_pions++;
					}
				}
			}

			int vainqueur = gagnant(pontfictice);
			if (vainqueur != 0)
			{
				if (vainqueur == 1)
				{
					retour = 1000 - nb_de_pions;
				} else if (vainqueur == 2)
				{
					retour = -1000 + nb_de_pions;
				} else
				{
					retour = 0;
				}
			}
		}
		int pointj1 = pontfictice.getScoreJoueur(0), pointj2 = pontfictice.getScoreJoueur(1);
		retour = pointj1 - pointj2;
		return retour;
	}

	private int gagnant(Pont pontfictice)
	{
		int gagnant = 0;
		if (pontfictice.partieTerminee() && this == pontfictice.getGagnant())
		{
			gagnant = 1;
		} else
		{
			gagnant = 2;
		}
		return gagnant;
	}
}
