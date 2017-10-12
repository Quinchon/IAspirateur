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
	
	private Capteurs capteurs;
	private Effecteurs effecteurs;
	
	public Agent(Environnement environnement, int coordx, int coordy) {
		this.environnement = environnement;
		this.coordx = coordx;
		this.coordy = coordy;
	}
	
	public void observeEnvironmentWithAllMySensors() {
		capteurs.scanEnvironnement(environnement, cases_with_dust, cases_with_jewel);
		
	}
	
	public void updateMyState() {
		
	}
	
	public void chooseAnAction(){
		
	}
	
	public void justDoIt() {
		
	}
	
}
