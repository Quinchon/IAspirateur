package view;

import model.Environnement;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


/**
 * The abstract class of view case
 * @author Quentin Dechaux & Aymeri Dumartheray
 */
public class CaseView extends AbstractView{
	
	private int i ;
	private int j ;
	private Text jewelText ;
	private Text robot;
	private Rectangle ground;

	/**
	 * Constructor
	 * @param model
	 * @param i : the line of the box
	 * @param j : the column of the box
	 */
	public CaseView(Environnement model, int i, int j) {
		super(model) ;
		this.i = i ;
		this.j = j ;
		//super.model.attach(this) ;
		
		// The color
		Color color = Color.BEIGE ;
		if (super.model.getPlateau().get(i).get(j).getHasDust()) {
			color = Color.DARKGRAY ;
		}
		if (super.model.getPlateau().get(i).get(j).getHasJewel()) {
			jewelText = new Text("jewel");
			jewelText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
		}

		// The ground
		ground = new Rectangle(58,58,color) ;
		this.getChildren().add(ground) ;

	}

	@Override
	public void update() {
	}
}
