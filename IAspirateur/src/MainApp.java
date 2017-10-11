
import javafx.application.Application;
import javafx.stage.Stage;
import model.Environnement;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		essai();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void essai() {
		System.out.println("Hello World");
		Environnement environnement = new Environnement();
		for (int i=0; i<10; i++) {			
			for (int j=0; j<10; j++) {
				System.out.println("Case : x coordinate = "+environnement.getPlateau().get(i).get(j).getCoordX()+" |"
						+ " y coordinate = "+environnement.getPlateau().get(i).get(j).getCoordY());
			}
		}
	}
	
}
