package breakout;

import engine.Actor;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Paddle extends Actor {
	int speed;
	private static final String IMG_PREFIX = "gameresources/";
	private static final Image STAND_LEFT_IMG = new Image(IMG_PREFIX +"paddle.png");
	
	public Paddle() {
		setImage(STAND_LEFT_IMG);
		speed = 4;
		getTimer().start();
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		if(getWorld().isKeyPressed(KeyCode.LEFT)){
			move(-speed,0);
		}
		if(getWorld().isKeyPressed(KeyCode.RIGHT)){
			move(speed,0);
		}
		
		//borders
		if(getX() < 0) {
			setX(0.0);
		}
		if(getX() > getWorld().getWidth()- getWidth()) {
			setX(getWorld().getWidth()- getWidth());
		}
	}
}
