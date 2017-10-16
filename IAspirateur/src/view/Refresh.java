package view;

import javafx.application.Platform;
import model.Environnement;

public class Refresh extends Thread{

	private Environnement environnement;
	private boolean running;

	public Refresh(Environnement env) {
		this.environnement =env;
		this.running=true;
	}


	// *** Boucle de rafraichissement de l'UI ***

	public void run() {

		while (running) {
			
			try {
				Thread.currentThread().sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			// Refresh the UI with JavaFx tools
			new Thread(new Runnable() {
				@Override public void run() {
					Platform.runLater(new Runnable() {
						@Override public void run() {
							environnement.notifyAllObserver();
						}
					});
				}
			}).start();
		}

	}

}
