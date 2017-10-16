package model;

import java.util.ArrayList;

/**
 * The sensors of the agent
 * @author Quentin Dechaux & Aymeri Dumartheray
 */
public class Capteurs{

	public ArrayList<Action> sequence_of_actions = new ArrayList<Action>();

	public ArrayList<Case> scanEnvironnement(Environnement environnement) {

		ArrayList<Case> cases_not_empty  = new ArrayList<Case>();

		for(int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {
				if (environnement.hasDust(i,j) || environnement.hasJewel(i, j)) {
					Case cellule = new Case(i,j);
					cases_not_empty.add(cellule);
				}
			}
		}

		return cases_not_empty;
	}


	public boolean hasDust(Environnement environnement, Case case_tested) {
		return environnement.hasDust(case_tested.getCoordX(), case_tested.getCoordY());
	}

	public boolean hasJewel(Environnement environnement, Case case_tested) {
		return environnement.hasJewel(case_tested.getCoordX(), case_tested.getCoordY());
	}

	public int getScore(Environnement environnement) {
		return environnement.getScore();
	}


}

