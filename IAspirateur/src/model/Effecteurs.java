package model;

public class Effecteurs{

	private enum Direction{
		UP, DOWN, LEFT, RIGHT
	}
	
	
//G�rer les exceptions � l'avenir !!
	public void move(Direction direction, int coordx, int coordy) {
		switch (direction) {
		case UP:
			coordy ++;
			break;
		case DOWN:
			coordy --;
			break;
		case LEFT:
			coordx --;
			break;
		case RIGHT:
			coordx ++;
			break;
		}
	}
	
	public void suck() {
		
	}
	
	public void pickUp() {
		
	}
	
}
