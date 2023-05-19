package mario;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tests.TestWorld;

public class GameStart extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Arcade Engine Test App");
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		root.setCenter(new MarioWorld());
		stage.setResizable(false);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
