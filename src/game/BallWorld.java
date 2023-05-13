package game;

import engine.World;

public class BallWorld extends World {

	public BallWorld() {
		setPrefSize(500, 500);
	}

	@Override
	public void act(long now) {
		
	}

	@Override
	public void onDimensionsInitialized() {
		Ball ball = new Ball();
		ball.setLayoutX(40);
		ball.setLayoutY(40);
		getChildren().add(ball);
	}

}
