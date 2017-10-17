package model;

import static java.lang.Math.abs;

public class AgentInforme extends Agent {

	public AgentInforme (Environnement environnement, int coordx, int coordy, int cpt) {
		super(environnement, coordx, coordy, cpt);
	}

	// Find the action sequence to do
	public void chooseAnAction(){

		//sequence_of_actions = capteurs.createSequence(environnement, coordx, coordy, 0);

		if (!working) {

			// Find the goal
			if (!cases_not_empty.isEmpty()) {
				goal = cases_not_empty.get(0);
				int min_dist = abs(coordx - goal.getCoordX()) + abs(coordy - goal.getCoordY());
				int case_dist;
				int caseIndex=0;
				for (int k=0; k<cases_not_empty.size();k++) {
					case_dist = abs(coordx - cases_not_empty.get(k).getCoordX()) + abs(coordy - cases_not_empty.get(k).getCoordY());
					if (case_dist < min_dist) {
						min_dist = case_dist;
						goal = cases_not_empty.get(k);
						caseIndex=k;
					}
				}
				cases_not_empty.remove(caseIndex);
				System.out.print("Case choisie :" + goal.getCoordX() + "/" + goal.getCoordY() + "\n");
				int x = coordx;
				int y = coordy;
				System.out.println("x = "+x+" || goal.getCoordX() = "+goal.getCoordX());
				// Find the actions sequence
				while(x != goal.getCoordX()) {
					if (x > goal.getCoordX()) {
						sequence_of_actions.add(Action.UP);
						x --;
					}
					if (x < goal.getCoordX()) {
						sequence_of_actions.add(Action.DOWN);
						x ++;
					}
				}
				System.out.println("y = "+y+" || goal.getCoordY() = "+goal.getCoordY());
				while(y != goal.getCoordY()) {
					if (y > goal.getCoordY()) {
						sequence_of_actions.add(Action.LEFT);
						y --;
					}
					if (y < goal.getCoordY()) {
						sequence_of_actions.add(Action.RIGHT);
						y ++;
					}
				}

				if (capteurs.hasJewel(environnement, goal)) {
					sequence_of_actions.add(Action.PICK);
				}
				if (capteurs.hasDust(environnement, goal)) {
					sequence_of_actions.add(Action.SUCK);
				}
			}
			else {

				sequence_of_actions.add(Action.IDLE);
			}
		}
	}
}
