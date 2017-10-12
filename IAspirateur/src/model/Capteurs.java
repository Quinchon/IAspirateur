package model;

import java.util.ArrayList;

public class Capteurs{
	
	public void scanEnvironnement(Environnement environnement, ArrayList<Case> cases_with_dust, ArrayList<Case> cases_with_jewel) {
		for(int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {
				if (environnement.hasDust(i,j)) {
					Case cellule = new Case(i,j);
					cases_with_dust.add(cellule);
				}
				if (environnement.hasJewel(i,j)) {
					Case cellule = new Case(i,j);
					cases_with_jewel.add(cellule);
				}
			}
		}
	}
	
	public int getScore(Environnement environnement) {
		return environnement.getScore();
	}
}
