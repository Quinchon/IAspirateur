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

	protected int cpt; 
	protected int compteurAction;


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
	public Agent(Environnement environnement, int coordx, int coordy, int cptAction) {
		this.environnement = environnement;
		this.coordx = coordx;
		this.coordy = coordy;
		this.living=true;
		this.working = false;
		this.compteurAction = cptAction;
		this.cpt=this.compteurAction;

		this.environnement.getPlateau().get(coordx).get(coordy).setHasRobot(true);

		observeEnvironmentWithAllMySensors();
	}


	// Execute the action
	//Gérer les exceptions à l'avenir !!
	public void takeAction(Action action) {
		this.environnement.getPlateau().get(coordx).get(coordy).setHasRobot(false);
		switch (action) {
		case LEFT:
			if (coordy > 0)
				coordy --;
			break;
		case RIGHT:
			if (coordy < 9)
				coordy ++;
			break;
		case UP:
			if (coordx > 0)
				coordx --;
			break;
		case DOWN :
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


	// Decide when to observe the environnement
	public void observeEnvironmentWithAllMySensors() {
		if(this.cpt == 0) {
			sequence_of_actions.clear();
			this.cpt=this.compteurAction;
			cases_not_empty = capteurs.scanEnvironnement(environnement);
			System.out.print("scan ok\n");
		}		
	}

	// Update the state of the agent
	public void updateMyState() {
		if(sequence_of_actions.isEmpty()) {
			working = false;
		}
		else working = true;
	}

	// Find the actions to do
	abstract void chooseAnAction();

	// Execute the first action from the action sequence
	public void justDoIt() {
		if (!sequence_of_actions.isEmpty()) {
			System.out.println("action = "+sequence_of_actions.get(0));
			takeAction(sequence_of_actions.get(0));
			sequence_of_actions.remove(0);
		}		
	}

	// Determine if the agent should be running or not
	public boolean AmIAlive() {
		return this.living;
	}


	// *** BOUCLE DE L'AGENT ***
	public void run() {

		while(AmIAlive()) {
			try {
				Thread.currentThread().sleep(500);
				observeEnvironmentWithAllMySensors();
				updateMyState();
				chooseAnAction();
				justDoIt();
				this.cpt --;
				System.out.println("cpt = "+this.cpt);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
