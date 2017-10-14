
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Environnement;
import view.EnvironnementView;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * The main class of the application
 * @author Quentin Dechaux & Aymeri Dumartheray
 */
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

			// Creation of the board
			this.board = new EnvironnementView(model) ;
			root.getChildren().add(this.board) ;

			// Initiate the game
			this.model.getPlateau().get(5).get(5).setHasDust(true);
			this.model.getPlateau().get(1).get(2).setHasDust(true);
			this.model.getPlateau().get(8).get(6).setHasDust(true);
			this.model.getPlateau().get(2).get(4).setHasDust(true);
			
			this.model.getPlateau().get(5).get(6).setHasJewel(true);
			this.model.getPlateau().get(1).get(9).setHasJewel(true);
			
			this.model.getPlateau().get(8).get(5).setHasRobot(true);

			// Print the UI. Launch a thread.
			primaryStage.setScene(scene) ;
			primaryStage.show() ;


			// Enable to kill all the threads when closing the application
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent e) {
					Platform.exit();
					System.exit(0);
				}
			});

			// Begin the "boucle de jeu". Launch a thread.
			this.model.start();
		}
		catch(Exception e) {
			e.printStackTrace() ;
		}


	}


	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}


}
