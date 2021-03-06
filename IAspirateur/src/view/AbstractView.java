package view;

import javafx.scene.Parent;
import model.Environnement;
import util.Observer;

/**
 * The abstract class of a view
 * @author Quentin Dechaux & Aymeri Dumartheray
 */
public abstract class AbstractView extends Parent implements Observer{
	
	protected Environnement model ;

	/**
	 * Constructor
	 * @param model
	 */
	public AbstractView(Environnement model) {
		this.model = model ;
	}

	public abstract void update() ;
}