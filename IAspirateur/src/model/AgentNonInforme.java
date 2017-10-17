package model;

import java.util.ArrayList;

public class AgentNonInforme extends Agent {

	public AgentNonInforme (Environnement environnement, int coordx, int coordy,int cpt) {
		super(environnement, coordx, coordy, cpt);
	}

	// Verify if the case is in the belief
	private boolean caseIsAGoal(int x, int y) {
		for(Case case_tested : cases_not_empty) {
			if (case_tested.getCoordX()==x && case_tested.getCoordY()==y) {
				return true;
			}
		}
		return false;
	}
	
	// Find the action sequence to do
	public void chooseAnAction(){

		if (!working) {

			int max_depth = 1;
			sequence_of_actions.clear();

			int x = coordx;
			int y = coordy;

			int case_marquees[][] = new int[10][10];


			while(!explore(environnement, x, y, 0, max_depth, case_marquees) && max_depth < 18) {
				max_depth ++;
				for(int i = 0; i < 10; i++){
					for(int j = 0; j < 10; j++){
						case_marquees[i][j] = 0;  
					}
				}
			}
			if (sequence_of_actions.isEmpty()) {
				sequence_of_actions.add(Action.IDLE);
			}
		}
	}


	// Exploration
	private boolean explore(Environnement environnement, int x, int y, int depth, int max_depth, int[][] case_marquees) {
		case_marquees[x][y] = 1;

		if ((environnement.hasJewel(x, y) || environnement.hasDust(x, y)) && caseIsAGoal(x,y)) {
			if (environnement.hasJewel(x, y)) {
				sequence_of_actions.add(Action.PICK);
			}
			if (environnement.hasDust(x, y)) {
				sequence_of_actions.add(Action.SUCK);
			}
			return true;
		}


		if (depth < max_depth) {

			//Explore LEFT
			if (y > 0 && case_marquees[x][y-1] == 0) {
				sequence_of_actions.add(Action.LEFT);
				if (explore(environnement, x, y - 1, depth + 1, max_depth, case_marquees)) {
					return true;
				}
				else {
					sequence_of_actions.remove(sequence_of_actions.size() - 1);
				}
			}

			//Explore RIGHT
			if (y < 9 && case_marquees[x][y+1] == 0) {
				sequence_of_actions.add(Action.RIGHT);
				if (explore(environnement, x, y + 1, depth + 1, max_depth, case_marquees)) {
					return true;
				}
				else {
					sequence_of_actions.remove(sequence_of_actions.size() - 1);
				}
			}		

			//Explore UP
			if (x > 0 && case_marquees[x-1][y] == 0) {
				sequence_of_actions.add(Action.UP);
				if (explore(environnement, x - 1, y, depth + 1, max_depth, case_marquees)) {
					return true;
				}
				else {
					sequence_of_actions.remove(sequence_of_actions.size() - 1);
				}
			}

			//Explore DOWN
			if (x < 9 && case_marquees[x+1][y] == 0) {
				sequence_of_actions.add(Action.DOWN);
				if (explore(environnement, x + 1, y, depth + 1, max_depth, case_marquees)) {
					return true;
				}
				else {
					sequence_of_actions.remove(sequence_of_actions.size() - 1);
				}
			}

		}

		return false;
	}

}
