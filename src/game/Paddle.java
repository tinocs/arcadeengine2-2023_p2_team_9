package game;

import engine.Actor;
import javafx.scene.image.Image;

public class Paddle extends Actor {
	
	private static final String IMG_PREFIX = "testresources/";
	private static final Image STAND_LEFT_IMG = new Image(IMG_PREFIX +"CharacterLeft_Standing.png");
	
	public Paddle() {
		setImage(STAND_LEFT_IMG);
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
}
