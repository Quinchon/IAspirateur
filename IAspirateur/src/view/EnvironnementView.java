package view;

import java.util.ArrayList;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Case;
import model.Environnement;

/**
 * The abstract class the view
 * @author Quentin Dechaux & Aymeri Dumartheray
 */
public class EnvironnementView extends AbstractView{

	private ArrayList<ArrayList<CaseView>> plateauView = new ArrayList<ArrayList<CaseView>>() ;

	private GridPane boxes ;
	
	/**
	 * Constructor
	 * @param model
	 */
	public EnvironnementView(Environnement model) {
		super(model);
		
		draw();
		
	}
	
	private void draw() {
		// The font
				Rectangle font = new Rectangle(602,602,Color.WHITE) ;
				this.getChildren().add(font) ;

				// The panel of boxes
				this.boxes = new GridPane() ;
				this.boxes.setTranslateX(2) ;
				this.boxes.setTranslateY(2) ;
				this.boxes.setHgap(2) ;
				this.boxes.setVgap(2) ;
				this.getChildren().add(this.boxes) ;
				this.setTranslateX(0) ;
				this.setTranslateY(0) ;

				// The boxes
				for (int i=0 ; i<10 ; i++) {
					ArrayList<CaseView> ligne = new ArrayList<CaseView>();
					for (int j=0 ; j<10 ; j++) {
						CaseView box = new CaseView(model,i,j) ;
						ligne.add(box);
						this.boxes.add(box, i, j) ;
					}
					plateauView.add(ligne);	
				}
	}

	public void update() {
		for (int i=0 ; i<10 ; i++) {
			for (int j=0 ; j<10 ; j++) {
				plateauView.get(i).get(j).update();
			}
		}
	}

}
