package model;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The sensors of the agent
 * @author Quentin Dechaux & Aymeri Dumartheray
 */
public class Capteurs{
	
	public ArrayList<Action> sequence_of_actions = new ArrayList<Action>();
	
	public ArrayList<Case> scanEnvironnement(Environnement environnement) {
		
		ArrayList<Case> cases_not_empty  = new ArrayList<Case>();
		
		for(int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {
				if (environnement.hasDust(i,j) || environnement.hasJewel(i, j)) {
					Case cellule = new Case(i,j);
					cases_not_empty.add(cellule);
				}
			}
		}
		
		return cases_not_empty;
	}
	
	
	public boolean hasDust(Case case_tested) {
		return case_tested.getHasDust();
	}
	
	public boolean hasJewel(Case case_tested) {
		return case_tested.getHasJewel();
	}
	
	public int getScore(Environnement environnement) {
		return environnement.getScore();
	}
	
}
	
//	public ArrayList<Action> createSequence(Environnement environnement, int x, int y, int depth){
//	
//		sequence_of_actions.clear();
//		
//		if (!explore(environnement, sequence_of_actions, x, y, 0)) {
//			sequence_of_actions.add(Action.IDLE);
//		}
//		
//		return sequence_of_actions;
//	}
//	
//	private boolean explore(Environnement environnement, ArrayList<Action> sequence_of_actions, int x, int y, int depth) {
//		
//		int case_marquees[][] = new int[10][10];
//		for(int i = 0; i < 10; i++){
//			   for(int j = 0; j < 10; j++){
//			     case_marquees[i][j] = 0;  
//			   }
//			}
//		
//		
//		Queue<Case> fileCase = new LinkedList<Case>();
//		fileCase.add(environnement.getCase(x, y));
//		case_marquees[x][y] = 1;
//		
//		
//		if (environnement.hasJewel(x, y) || environnement.hasDust(x, y)) {
//			if (environnement.hasJewel(x, y)) {
//				sequence_of_actions.add(Action.PICK);
//			}
//			if (environnement.hasDust(x, y)) {
//				sequence_of_actions.add(Action.SUCK);
//			}
//			return true;
//		}
//		
//		
//
//		return false;
//	}
//	


//if (depth < 9) {
//	
//	//Explore UP
//	if (y > 0) {
//		sequence_of_actions.add(Action.UP);
//		if (explore(environnement, sequence_of_actions, x, y - 1, depth + 1)) {
//			return true;
//		}
//		else {
//			sequence_of_actions.remove(sequence_of_actions.size() - 1);
//		}
//	}
//	//Explore DOWN
//	if (y < 9) {
//		sequence_of_actions.add(Action.DOWN);
//		if (explore(environnement, sequence_of_actions, x, y + 1, depth + 1)) {
//			return true;
//		}
//		else {
//			sequence_of_actions.remove(sequence_of_actions.size() - 1);
//		}
//	}		
//	//Explore LEFT
//	if (x > 0) {
//		sequence_of_actions.add(Action.LEFT);
//		if (explore(environnement, sequence_of_actions, x - 1, y, depth + 1)) {
//			return true;
//		}
//		else {
//			sequence_of_actions.remove(sequence_of_actions.size() - 1);
//		}
//	}
//	//Explore RIGHT
//	if (x < 9) {
//		sequence_of_actions.add(Action.RIGHT);
//		if (explore(environnement, sequence_of_actions, x + 1, y, depth + 1)) {
//			return true;
//		}
//		else {
//			sequence_of_actions.remove(sequence_of_actions.size() - 1);
//		}
//	}
//
//}
//
//return false;