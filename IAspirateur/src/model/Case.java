package model;

/**
 * The model unit
 * @author Quentin Dechaux & Aymeri Dumartheray
 */
public class Case {

	// information about the case
	private boolean hasDust;
	private boolean hasJewel;
	private boolean hasRobot;
	private int coordx;
	private int coordy;
	
	/**
	 * Constructor of the Case class
	 * @param x : x coordinate
	 * @param y : y coordinate
	 */
	public Case(int x, int y) {
		this.coordx=x;
		this.coordy=y;
		this.hasDust=false;
		this.hasJewel=false;
		this.hasRobot=false;
	}
	
	// Getter
	public boolean getHasDust() {return this.hasDust;}
	
	public boolean getHasJewel() {return this.hasJewel;}
	
	public boolean getHasRobot() {return this.hasRobot;}
	
	public int getCoordX() {return this.coordx;}
	
	public int getCoordY() {return this.coordy;}
	
	
	// Setter
	public void setHasDust(boolean dust) {this.hasDust=dust;}
	
	public void setHasJewel(boolean jewel) {this.hasJewel=jewel;}

	public void setHasRobot(boolean robot) {this.hasRobot=robot;}

	public void setCoordX(int x) {this.coordx=x;}
	
	public void setCoordY(int y) {this.coordy=y;}
}
