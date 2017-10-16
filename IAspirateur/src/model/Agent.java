package model;

import java.util.ArrayList;

import javafx.application.Platform;

import static java.lang.Math.abs;


/**
 * The agent
 * @author Quentin Dechaux & Aymeri Dumartheray
 */
public class Agent extends Thread{
	
	private Environnement environnement;
	
	private int coordx;
	private int coordy;
	
	private boolean living;
	
//	private int score[] = new int[10];
//	
//	int tours = 0;
//	int max_tours = 20;
	
	ArrayList<Case> cases_not_empty = new ArrayList<Case>();
	
	Case goal;
	
	ArrayList<Action> sequence_of_actions = new ArrayList<Action>();
	
	private Capteurs capteurs = new Capteurs();
	private Effecteurs effecteurs = new Effecteurs();
	
	
	/**
	 * Constructor of the Agent
	 * @param environnement
	 * @param coordx
	 * @param coordy
	 */
	public Agent(Environnement environnement, int coordx, int coordy) {
		this.environnement = environnement;
		this.coordx = coordx;
		this.coordy = coordy;
		this.living=true;
		
		this.environnement.getPlateau().get(coordx).get(coordy).setHasRobot(true);
	}
	
	
	
//Gérer les exceptions à l'avenir !!
	public void takeAction(Action action) {
		this.environnement.getPlateau().get(coordx).get(coordy).setHasRobot(false);
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
		this.environnement.getPlateau().get(coordx).get(coordy).setHasRobot(true);
	}
	
	
	
	
	
	public void observeEnvironmentWithAllMySensors() {
		cases_not_empty = capteurs.scanEnvironnement(environnement);
		System.out.print("scan ok\n");
	}
	
	public void updateMyState() {
		
		if (!cases_not_empty.isEmpty()) {
		
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
		
		System.out.print("Case choisie :" + goal.getCoordX() + "/" + goal.getCoordY() + "\n");
		
		}
	}
	
	public void chooseAnAction(){
		
		//sequence_of_actions = capteurs.createSequence(environnement, coordx, coordy, 0);
		
		int x = coordx;
		int y = coordy;
		
		while(x != goal.getCoordX()) {
			if (x > goal.getCoordX()) {
				sequence_of_actions.add(Action.LEFT);
				System.out.print("Ajout action GAUCHE\n");
				x --;
			}
			if (x < goal.getCoordX()) {
				sequence_of_actions.add(Action.RIGHT);
				System.out.print("Ajout action DROITE\n");
				x ++;
			}
		}
		
		while(y != goal.getCoordY()) {
			if (y > goal.getCoordY()) {
				sequence_of_actions.add(Action.UP);
				System.out.print("Ajout action HAUT\n");
				y --;
			}
			if (y < goal.getCoordY()) {
				sequence_of_actions.add(Action.DOWN);
				System.out.print("Ajout action BAS\n");
				y ++;
			}
		}
		
		System.out.print("TEST = " + capteurs.hasDust(environnement, goal) + "\n");
		System.out.print("TEST2 = " + environnement.hasDust(goal.getCoordX(), goal.getCoordY()) + "\n");
		if (capteurs.hasJewel(environnement, goal)) {
			sequence_of_actions.add(Action.PICK);
			System.out.print("Ajout action RAMASSE\n");
		}
		if (capteurs.hasDust(environnement, goal)) {
			sequence_of_actions.add(Action.SUCK);
			System.out.print("Ajout action ASPIRE\n");
		}
		
	}
	
	public void justDoIt() {
		while (!sequence_of_actions.isEmpty()) {
		takeAction(sequence_of_actions.get(0));
		sequence_of_actions.remove(0);
		}
	}
	
	public boolean AmIAlive() {
		return this.living;
	}
	
	
	// *** BOUCLE DE L'AGENT ***
	public void run() {
		
		while(AmIAlive()) {
			try {
				Thread.currentThread().sleep(500);
				System.out.print("Boucle Start !!! Position de l'aspi :" + coordx + "/" + coordy + "\n");
				observeEnvironmentWithAllMySensors();
				updateMyState();
				chooseAnAction();
				justDoIt();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
