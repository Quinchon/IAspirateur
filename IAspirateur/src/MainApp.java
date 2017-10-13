
import javafx.application.Application;
import javafx.stage.Stage;
import model.Environnement;
import view.EnvironnementView;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MainApp extends Application {

	private Environnement model ;
	private EnvironnementView board ;
	
	/**
	 * Start the graphical interface
	 */
	public void start(Stage primaryStage) {
		
		try {
			primaryStage.setTitle("IAspirateur") ;
			primaryStage.setResizable(false);
			Group root = new Group() ;
			Scene scene = new Scene(root,602,602) ;

			// Creation of the model
			this.model = new Environnement() ;
			
			// TEST
			this.model.getPlateau().get(5).get(5).setHasDust(true);
			this.model.getPlateau().get(5).get(6).setHasJewel(true);
			this.model.getPlateau().get(6).get(6).setHasJewel(true);
			this.model.getPlateau().get(6).get(6).setHasRobot(true);
			
			// Creation of the board
			this.board = new EnvironnementView(model) ;
			root.getChildren().add(this.board) ;

//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()) ;
			primaryStage.setScene(scene) ;
			primaryStage.show() ;
		}
		catch(Exception e) {
			e.printStackTrace() ;
		}
		essai();
	}

	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}



	
	public void essai() {
		
		try {
			Thread.currentThread().sleep(5000);
			
			this.model.getPlateau().get(6).get(6).setHasRobot(false);
			this.model.getPlateau().get(7).get(6).setHasRobot(true);
			
			this.board.update();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
