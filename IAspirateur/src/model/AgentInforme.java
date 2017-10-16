package model;

import static java.lang.Math.abs;

public class AgentInforme extends Agent {

	public AgentInforme (Environnement environnement, int coordx, int coordy) {
		super(environnement, coordx, coordy);
	}

	public void chooseAnAction(){

		//sequence_of_actions = capteurs.createSequence(environnement, coordx, coordy, 0);

		if (!working) {

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

			if (capteurs.hasJewel(environnement, goal)) {
				sequence_of_actions.add(Action.PICK);
				System.out.print("Ajout action RAMASSE\n");
			}
			if (capteurs.hasDust(environnement, goal)) {
				sequence_of_actions.add(Action.SUCK);
				System.out.print("Ajout action ASPIRE\n");
			}

		}

	}


}
