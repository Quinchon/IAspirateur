package model;

public class Effecteurs{
	
	public void suck(int coordx, int coordy, Environnement environnement) {
		environnement.suck(coordx, coordy);
	}
	
	public void pickUp(int coordx, int coordy, Environnement environnement) {
		environnement.pickUp(coordx, coordy);
	}
	
}
