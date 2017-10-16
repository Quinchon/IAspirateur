package model;

import java.util.ArrayList;

public class AgentNonInforme extends Agent {

	public AgentNonInforme (Environnement environnement, int coordx, int coordy) {
		super(environnement, coordx, coordy);
	}

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
			System.out.print("max depth :" + max_depth + "\n");
		}
		
		System.out.println("MAX DEPTH FINAL:" + max_depth);

		if (sequence_of_actions.isEmpty()) {
			sequence_of_actions.add(Action.IDLE);
		}
		
		}

	}

	private boolean explore(Environnement environnement, int x, int y, int depth, int max_depth, int[][] case_marquees) {
		case_marquees[x][y] = 1;

		System.out.println("Je suis dans explore, position:" + x + "/" + y);

		if (environnement.hasJewel(x, y) || environnement.hasDust(x, y)) {
			System.out.println("JE SUIS SUR UNE CASE SALE OMG");
			if (environnement.hasJewel(x, y)) {
				sequence_of_actions.add(Action.PICK);
			}
			if (environnement.hasDust(x, y)) {
				sequence_of_actions.add(Action.SUCK);
			}
			return true;
		}


		System.out.println("valeur de depth :" + depth + "valeur de max_depth" + max_depth +" test depth < max_depth:" + (depth < max_depth));
		if (depth < max_depth) {

			//Explore UP
			if (y > 0 && case_marquees[x][y-1] == 0) {
				System.out.println("Je tente le UP");
				sequence_of_actions.add(Action.UP);
				if (explore(environnement, x, y - 1, depth + 1, max_depth, case_marquees)) {
					return true;
				}
				else {
					sequence_of_actions.remove(sequence_of_actions.size() - 1);
				}
			}

			//Explore DOWN
			if (y < 9 && case_marquees[x][y+1] == 0) {
				System.out.println("Je tente le DOWN");
				sequence_of_actions.add(Action.DOWN);
				if (explore(environnement, x, y + 1, depth + 1, max_depth, case_marquees)) {
					return true;
				}
				else {
					sequence_of_actions.remove(sequence_of_actions.size() - 1);
				}
			}		

			//Explore LEFT
			if (x > 0 && case_marquees[x-1][y] == 0) {
				System.out.println("Je tente le LEFT");
				sequence_of_actions.add(Action.LEFT);
				if (explore(environnement, x - 1, y, depth + 1, max_depth, case_marquees)) {
					return true;
				}
				else {
					sequence_of_actions.remove(sequence_of_actions.size() - 1);
				}
			}

			//Explore RIGHT
			if (x < 9 && case_marquees[x+1][y] == 0) {
				System.out.println("Je tente le RIGHT");
				sequence_of_actions.add(Action.RIGHT);
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
