package model;

import java.util.ArrayList;

public class Environnement {
	
	private ArrayList<ArrayList<Case>> plateau = new ArrayList<ArrayList<Case>>() ;
	private int pickUpJewel = 1;
	private int suckDust = 1;
	private int pickUpDust = 0;
	private int suckJewel = -2;
	private int uselessAction = 0;
	private int score = 0;
	
	public Environnement() {
		
		for (int i=0; i<10; i++) {
			ArrayList<Case> ligne = new ArrayList<Case>();
			
			for (int j=0; j<10; j++) {
				Case cellule = new Case(i,j);
				ligne.add(cellule);
			}
			
			plateau.add(ligne);	
		}
		
	}
	
	public boolean hasDust(int x, int y) {
		return plateau.get(x).get(y).getHasDust();
	}
	
	public boolean hasJewel(int x, int y) {
		return plateau.get(x).get(y).getHasJewel();
	}
	
	public void pickUp(int x, int y) {
		if (plateau.get(x).get(y).getHasJewel()){
			plateau.get(x).get(y).setHasJewel(false);
			changeScore(pickUpJewel);
		}
		if (plateau.get(x).get(y).getHasDust()){
			changeScore(pickUpDust);
		}
		else
			changeScore(uselessAction);
	}
	
	public void suck(int x, int y) {
		if (plateau.get(x).get(y).getHasJewel()){
			plateau.get(x).get(y).setHasJewel(false);
			changeScore(suckJewel);
		}
		if (plateau.get(x).get(y).getHasDust()){
			plateau.get(x).get(y).setHasDust(false);
			changeScore(suckDust);
		}
		else
			changeScore(uselessAction);
	}

	
	public void changeScore(int input) {
		setScore(this.score + input);
	}
	
	// Getter
	
	public ArrayList<ArrayList<Case>> getPlateau(){
		return this.plateau;
	}
	
	public int getScore() {
		return this.score;
	}
	
	// Setter
	
	public void setScore(int score) {this.score = score;}
	

}