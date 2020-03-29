package twist.metier.ia;

import twist.metier.Coup;
import twist.metier.Pont;

public class Goinfre implements IA
{
	public Coup next(Pont pont)
	{
		Coup coup = new Coup(0, 0, 0);
		int score = -214748368;

		for (int x = 0; x <= pont.getLargeur(); x++)
		for (int y = 0; y <= pont.getHauteur(); y++)
		{

			int lig, col, coin;

			if (x >= pont.getLargeur() && y >= pont.getHauteur())
			{
				col = x - 1; lig = y - 1; coin = 2;
			}
			else if (x >= pont.getLargeur())
			{
				col = x - 1; lig = y; coin = 1;
			}
			else if (y >= pont.getHauteur())
			{
				col = x; lig = y - 1; coin = 3;
			}
			else
			{
				col = x; lig = y; coin = 0;
			}

			if (pont.peutPlacerLock(col, lig, coin))
			{
				int eval = pont.evaluerCoup(col, lig, coin, pont.getJoueurActif());

				if (eval > score)
				{
					score = eval;
					coup = new Coup(lig, col, coin);
				}
			}
		}

		return coup;
	}
}
