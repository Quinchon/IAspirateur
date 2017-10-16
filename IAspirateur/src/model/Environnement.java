package model;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.concurrent.Task;
import util.Observable;

/**
 * The model of the application
 * @author Quentin Dechaux & Aymeri Dumartheray
 */
public class Environnement extends Observable{

	private ArrayList<ArrayList<Case>> plateau = new ArrayList<ArrayList<Case>>() ;
	private int pickUpJewel = 1;
	private int suckDust = 1;
	private int pickUpDust = 0;
	private int suckJewel = -2;
	private int uselessAction = 0;
	private int score = 0;
	private boolean gameIsRunning;


	/**
	 * Constructor of Environnement
	 */
	public Environnement() {

		for (int i=0; i<10; i++) {
			ArrayList<Case> ligne = new ArrayList<Case>();

			for (int j=0; j<10; j++) {
				Case cellule = new Case(i,j);
				ligne.add(cellule);
			}

			plateau.add(ligne);	
		}
		
		this.gameIsRunning=true;

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

	public boolean shouldThereBeANewDirtySpace() {
		return (Math.random()*100 < 20);
	}

	public boolean shouldThereBeANewLostJewel() {
		return (Math.random()*100 < 5);
	}
	
	private void GenerateDirt() {
		boolean dirtPut = false;
		int counter = 99;
		while(!dirtPut) {
			counter--;
			int x =(int)((Math.random())*10);
			int y =(int)((Math.random())*10);
			
			if(!this.getPlateau().get(x).get(y).getHasDust()) {
				this.getPlateau().get(x).get(y).setHasDust(true);
				dirtPut=true;
			}
			if(counter==0) {
				dirtPut=true;
			}
		}		
	}
	
	private void GenerateJewel() {
		boolean jewelPut = false;
		int counter = 99;
		while(!jewelPut) {
			counter--;
			int x =(int)((Math.random())*10);
			int y =(int)((Math.random())*10);
			if(!this.getPlateau().get(x).get(y).getHasJewel()) {
				this.getPlateau().get(x).get(y).setHasJewel(true);
				jewelPut=true;
			}
			if(counter==0) {
				jewelPut=true;
			}
		}
		
	}

	// Getter

	public ArrayList<ArrayList<Case>> getPlateau(){
		return this.plateau;
	}

	public Case getCase(int x, int y) {
		return this.plateau.get(x).get(y);
	}

	public int getScore() {
		return this.score;
	}
	
	private boolean gameIsRunning() {
		return this.gameIsRunning;
	}

	// Setter

	public void setScore(int score) {this.score = score;}



	//***BOUCLE DU MODEL***

	public void run() {
		//boolean h =false; // For the exemple
		while (gameIsRunning()){
			try {
				Thread.currentThread().sleep(500);
				if (shouldThereBeANewDirtySpace())
					GenerateDirt();
					if (shouldThereBeANewLostJewel())
						GenerateJewel();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// For the exemple
			//				if (!h) {
			//					this.getPlateau().get(4).get(4).setHasRobot(false);
			//					this.getPlateau().get(5).get(4).setHasRobot(true);
			//					h=true;
			//				}
			//				else {
			//					this.getPlateau().get(5).get(4).setHasRobot(false);
			//					this.getPlateau().get(4).get(4).setHasRobot(true);
			//					h=false;
			//				}
		}

	}

}