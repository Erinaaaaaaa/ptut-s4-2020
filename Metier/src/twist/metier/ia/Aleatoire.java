package twist.metier.ia;

import twist.metier.Coup;
import twist.metier.Pont;

public class Aleatoire implements IA
{
	@SuppressWarnings("DuplicatedCode")
	public Coup next(Pont pont)
	{
		int lig, col, coin;

		do
		{
			lig = (int) (Math.random() * pont.getHauteur());
			col = (int) (Math.random() * pont.getLargeur());
			coin = (int) (Math.random() * 4);
		} while (!pont.peutPlacerLock(col, lig, coin));

		return new Coup(lig, col, coin);
	}
}
