package breakout;



import engine.World;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
public class BallWorld extends World {
	Ball ball;
	Paddle pad;
	Score scoreBoard;
	public BallWorld() {
		setPrefSize(500, 500);
		this.setMaxHeight(500);
		this.setMaxWidth(500);
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
		pad.setY(getHeight()-pad.getHeight());
		add(pad);
		
		//brick 
		Brick b = new Brick();
		b.setX(40);
		b.setY(40);
		add(b);
		
		//scoreboard
		scoreBoard = new Score();
		scoreBoard.setX(100);
		scoreBoard.setY(40);
		getChildren().add(scoreBoard); 
		
		setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (event.getX() >= pad.getWidth()/2 && event.getX() <= getWidth()-pad.getWidth()/2)
					pad.setX(event.getX()- pad.getWidth()/2);
			}
			
		});
	}

	public Score getScoreObject() {
		return scoreBoard;
	}
}
