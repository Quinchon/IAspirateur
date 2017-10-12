package model;

import java.util.ArrayList;

enum Action {
	UP, DOWN, LEFT, RIGHT, SUCK, PICK, IDLE
}


public class Agent {
	
	private Environnement environnement;
	
	private int coordx;
	private int coordy;
	
	private int score;
	
	ArrayList<Case> cases_with_dust = new ArrayList<Case>();
	ArrayList<Case> cases_with_jewel = new ArrayList<Case>();
	
	ArrayList<Action> sequence_of_actions = new ArrayList<Action>();
	
	private Capteurs capteurs = new Capteurs();
	private Effecteurs effecteurs = new Effecteurs();
	
	public Agent(Environnement environnement, int coordx, int coordy) {
		this.environnement = environnement;
		this.coordx = coordx;
		this.coordy = coordy;
	}
	
//Gérer les exceptions à l'avenir !!
	public void takeAction(Action action) {
		switch (action) {
		case UP:
			if (coordy < 9)
				coordy ++;
			break;
		case DOWN:
			if (coordy > 0)
				coordy --;
			break;
		case LEFT:
			if (coordx > 0)
				coordx --;
			break;
		case RIGHT:
			if (coordx < 9)
				coordx ++;
			break;
		case SUCK:
			effecteurs.suck(coordx, coordy, environnement);
			break;
		case PICK:
			effecteurs.pickUp(coordx, coordy, environnement);
			break;
		case IDLE:
			break;
		}
	}
	
	
	
	public void observeEnvironmentWithAllMySensors() {
		capteurs.scanEnvironnement(environnement, cases_with_dust, cases_with_jewel);
		score = capteurs.getScore(environnement);
	}
	
//	public void updateMyState() {
//		
//	}
	
	public void chooseAnAction(){
		
	}
	
	public void justDoIt() {
		takeAction(sequence_of_actions.get(0));
		sequence_of_actions.remove(0);
	}
	
}
