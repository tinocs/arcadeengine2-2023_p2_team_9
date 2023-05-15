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
		ball.setX(40);
		ball.setY(60);
		add(ball);
	}

}
