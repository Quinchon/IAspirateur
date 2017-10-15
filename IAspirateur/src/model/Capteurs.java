package model;

import java.util.ArrayList;


/**
 * The sensors of the agent
 * @author Quentin Dechaux & Aymeri Dumartheray
 */
public class Capteurs{
	
	public void scanEnvironnement(Environnement environnement, ArrayList<Case> cases_not_empty) {
		for(int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {
				if (environnement.hasDust(i,j) || environnement.hasJewel(i, j)) {
					Case cellule = new Case(i,j);
					cases_not_empty.add(cellule);
				}
			}
		}
	}
	
	public boolean hasDust(Case case_tested) {
		return case_tested.getHasDust();
	}
	
	public boolean hasJewel(Case case_tested) {
		return case_tested.getHasJewel();
	}
	
	public int getScore(Environnement environnement) {
		return environnement.getScore();
	}
}
