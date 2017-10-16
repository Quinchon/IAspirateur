package model;

import java.util.ArrayList;


/**
 * The agent
 * @author Quentin Dechaux & Aymeri Dumartheray
 */
public abstract class Agent extends Thread{

	protected Environnement environnement;

	protected int coordx;
	protected int coordy;

	protected boolean living;
	protected boolean working;


	ArrayList<Case> cases_not_empty = new ArrayList<Case>();

	Case goal;

	ArrayList<Action> sequence_of_actions = new ArrayList<Action>();

	protected Capteurs capteurs = new Capteurs();
	protected Effecteurs effecteurs = new Effecteurs();


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
		this.working = false;

		this.environnement.getPlateau().get(coordx).get(coordy).setHasRobot(true);
	}



	//Gérer les exceptions à l'avenir !!
	public void takeAction(Action action) {
		this.environnement.getPlateau().get(coordx).get(coordy).setHasRobot(false);
		switch (action) {
		case UP:
			if (coordy > 0)
				coordy --;
			break;
		case DOWN:
			if (coordy < 9)
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
		if(sequence_of_actions.isEmpty()) {
			working = false;
		}
		else working = true;
	}

	abstract void chooseAnAction();

	public void justDoIt() {
		if (!sequence_of_actions.isEmpty()) {
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
				System.out.print("Position de l'aspi :" + coordx + "/" + coordy + "\n");
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
