package mario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import engine.Actor;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameStart extends Application {
	MarioWorld world;
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Mario Game");
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		world = new MarioWorld();
		root.setCenter(world);
		stage.setResizable(false);
		
		root.setOnKeyPressed(new MyKeyEventHandler());
		
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	private class MyKeyEventHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent event) {
			if (event.getText() != null && event.getText().matches("r")) {
				char c = event.getText().charAt(0);
				if (c == 'r') {
					try {
						world.loadLevel();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
}
