package model;
import java.util.ArrayList;

public class Environnement {
	
	private ArrayList<ArrayList<Case>> plateau = new ArrayList<ArrayList<Case>>() ;
	
	public Environnement() {
		
		for (int i=0; i<10; i++) {
			ArrayList<Case> ligne = new ArrayList<Case>();
			
			for (int j=0; j<10; j++) {
				Case cellule = new Case(i,j);
				ligne.add(cellule);
			}
			
			plateau.add(ligne);	
		}	
	}
	
	// Getter
	
	public ArrayList<ArrayList<Case>> getPlateau(){
		return this.plateau;
	}
	
	

}
