package model;

import java.util.ArrayList;

import static java.lang.Math.abs;


/**
 * The agent
 * @author Quentin Dechaux & Aymeri Dumartheray
 */
public class Agent {
	
	private Environnement environnement;
	
	private int coordx;
	private int coordy;
	
	private int score;
	
	ArrayList<Case> cases_not_empty = new ArrayList<Case>();
	
	Case goal;
	
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
				coordy --;
			break;
		case DOWN:
			if (coordy > 0)
				coordy ++;
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
		cases_not_empty = capteurs.scanEnvironnement(environnement);
		score = capteurs.getScore(environnement);
	}
	
	public void updateMyState() {
		goal = cases_not_empty.get(0);
		int min_dist = abs(coordx - goal.getCoordX()) + abs(coordy - goal.getCoordY());
		int case_dist;
		
		for (Case case_tested : cases_not_empty) {
			case_dist = abs(coordx - case_tested.getCoordX()) + abs(coordy - case_tested.getCoordY());
			if (case_dist < min_dist) {
				min_dist = case_dist;
				goal = case_tested;
			}
		}
	}
	
	public void chooseAnAction(){
		
		//sequence_of_actions = capteurs.createSequence(environnement, coordx, coordy, 0);
		
		int x = coordx;
		int y = coordy;
		
		while(x != goal.getCoordX()) {
			if (x > goal.getCoordX()) {
				sequence_of_actions.add(Action.LEFT);
				x --;
			}
			if (x < goal.getCoordX()) {
				sequence_of_actions.add(Action.RIGHT);
				x ++;
			}
		}
		
		while(y != goal.getCoordY()) {
			if (y > goal.getCoordY()) {
				sequence_of_actions.add(Action.UP);
				y --;
			}
			if (y < goal.getCoordY()) {
				sequence_of_actions.add(Action.DOWN);
				y ++;
			}
		}
		
		if (capteurs.hasJewel(goal)) {
			sequence_of_actions.add(Action.PICK);
		}
		if (capteurs.hasDust(goal)) {
			sequence_of_actions.add(Action.SUCK);
		}
		
	}
	
	public void justDoIt() {
		while (!sequence_of_actions.isEmpty()) {
		takeAction(sequence_of_actions.get(0));
		sequence_of_actions.remove(0);
		}
	}
	
}
