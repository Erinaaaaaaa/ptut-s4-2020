package twist.metier.ia;

import twist.metier.Coup;
import twist.metier.Pont;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Minimax implements IA
{
	private final int niveaux;

	public Minimax(int niveaux)
	{
		this.niveaux = niveaux;
	}

	public Coup next(Pont pont)
	{
		Noeud racine = new Noeud(null,
				pont.getScoreJoueur(pont.getJoueurActif()) - pont.getScoreJoueur(1 -pont.getJoueurActif()),
				null, true);

		int niveaux = Integer.min(3, pont.getJoueur(0).getNbLocks() + pont.getJoueur(1).getNbLocks());

		// Generation arbre
		racine.genererEnfants(pont, niveaux);
		racine.determinerMeilleurs();

		return racine.meilleurEnfant.coup;
	}
}

class Noeud
{

	public Noeud(Coup coup, int score, Noeud parent, boolean adversaire)
	{
		this.coup = coup;
		this.score = score;
		this.parent = parent;
		this.adversaire = adversaire;
	}

	Coup coup;
	int score;
	Noeud parent;
	ArrayList<Noeud> enfants;
	boolean adversaire;

	Noeud meilleurEnfant;

	/**
	 * Obtient le bénéfice de ce coup par rapport au coup précédent
	 * @return bénéfice.
	 */
	int getBenefice()
	{
		return 0;
	}

	void genererEnfants(Pont pont, int niveaux)
	{
		if (niveaux == 0) return;
		this.enfants = new ArrayList<>();
		boolean adversaire = !this.adversaire;

		for (int x = 0; x <= pont.getLargeur(); x++)
		for (int y = 0; y <= pont.getHauteur(); y++)
		{
			int lig, col, coin;

			if (x >= pont.getLargeur() && y >= pont.getHauteur())
			{
				col = x - 1;
				lig = y - 1;
				coin = 2;
			} else if (x >= pont.getLargeur())
			{
				col = x - 1;
				lig = y;
				coin = 1;
			} else if (y >= pont.getHauteur())
			{
				col = x;
				lig = y - 1;
				coin = 3;
			} else
			{
				col = x;
				lig = y;
				coin = 0;
			}

			if (pont.peutPlacerLock(col, lig, coin))
			{
				pont.simulerCoup(col, lig, coin, adversaire);

				Noeud enfant;

				enfants.add(
						enfant = new Noeud(
								new Coup(lig, col, coin),
								niveaux > 1 ? -2147483648 : pont.getScoreJoueur(pont.getJoueurActif()) - pont.getScoreJoueur(1 - pont.getJoueurActif()),
								this,
								adversaire
						)
				);

				enfant.genererEnfants(pont, niveaux - 1);

				pont.annulerCoup(col, lig, coin);
			}
		}
	}

	void determinerMeilleurs()
	{
		// Feuille de l'arbre. On connait déjà son score
		if (this.enfants == null) return;

		BiFunction<Noeud, Noeud, Noeud> comparaison;

		// Coup actuel de l'adversaire: coups enfants du joueur local -> MAXimiser le gain
		if (adversaire)
			comparaison = (best, current) ->
			{
				if (best.score < current.score)
					return current;
				else return best;
			};
		// Coup actuel du joueur local: coups enfants de l'adversaire -> MINImiser le gain
		else
			comparaison = (best, current) ->
			{
				if (best.score > current.score)
					return current;
				else return best;
			};


		for (Noeud enfant : enfants)
		{
			enfant.determinerMeilleurs();

			if (meilleurEnfant == null)
			{
				meilleurEnfant = enfant;
				continue;
			}

			meilleurEnfant = comparaison.apply(meilleurEnfant, enfant);
			score = meilleurEnfant.score;
		}
	}
}
