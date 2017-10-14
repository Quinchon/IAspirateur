package view;

import model.Environnement;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


/**
 * The class for a unit of the UI
 * @author Quentin Dechaux & Aymeri Dumartheray
 */
public class CaseView extends AbstractView{

	private int i ;
	private int j ;

	private Rectangle ground;
	private BorderPane layout ;

	private Label jewelText;
	private Label robot;


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
		this.layout = new BorderPane();		
		draw();
	}

	private void draw() {

		this.getChildren().remove(ground) ;
		this.getChildren().remove(layout) ;
		
		jewelText = new Label("") ;
		robot = new Label("");
		// The color
		Color color = Color.BEIGE ;
		if (super.model.getPlateau().get(i).get(j).getHasDust()) {
			color = Color.DARKGRAY ;
		}
		if (super.model.getPlateau().get(i).get(j).getHasJewel()) {
			jewelText.setText("jewel");
			jewelText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
		}
		if (super.model.getPlateau().get(i).get(j).getHasRobot()) {
			robot.setText("IA");
			robot.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));

		}

		// The ground
		ground = new Rectangle(58,58,color) ;
		layout.setTop(jewelText);
		layout.setCenter(robot);

		this.getChildren().add(ground) ;
		this.getChildren().add(layout) ;
	}

	@Override
	public void update() {
		draw();
	}
}
