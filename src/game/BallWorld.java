package game;



import engine.World;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
public class BallWorld extends World {
	Ball ball;
	Paddle pad;
	public BallWorld() {
		setPrefSize(500, 500);
	}

	@Override
	public void act(long now) {	
	}

	@Override
	public void onDimensionsInitialized() {
		ball = new Ball();
		ball.setX(250);
		ball.setY(250);
		add(ball);
		
		pad = new Paddle();
		pad.setX(460);
		pad.setY(460);
		add(pad);
		
		setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				pad.setX(event.getX()- pad.getWidth()/2);
			}
			
		});
	}

}
