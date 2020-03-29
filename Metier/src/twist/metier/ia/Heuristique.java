package twist.metier.ia;

import twist.metier.Coup;
import twist.metier.Joueur;
import twist.metier.Pont;

public class Heuristique implements IA
{
	@SuppressWarnings("DuplicatedCode")
	public Coup next(Pont pont)
	{
		int lig1, col1, coin1;
		do
		{
			lig1 = (int) (Math.random() * pont.getHauteur());
			col1 = (int) (Math.random() * pont.getLargeur());
			coin1 = (int) (Math.random() * 4);
		} while (!pont.peutPlacerLock(col1, lig1, coin1));

		int lig2, col2, coin2;
		do
		{
			lig2 = (int) (Math.random() * pont.getHauteur());
			col2 = (int) (Math.random() * pont.getLargeur());
			coin2 = (int) (Math.random() * 4);
		} while (!pont.peutPlacerLock(col2, lig2, coin2));

		int score1 = pont.evaluerCoup(col1, lig1, coin1, pont.getJoueurActif());
		int score2 = pont.evaluerCoup(col2, lig2, coin2, pont.getJoueurActif());

		if (score1 >= score2) return new Coup(lig1, col1, coin1);
		else return new Coup(lig2, col2, coin2);
	}
}
