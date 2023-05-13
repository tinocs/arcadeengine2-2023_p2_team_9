package game;

import engine.Actor;
import javafx.scene.image.Image;

public class Brick extends Actor {
	
	private static final String IMG_PREFIX = "gameresources/";
	private static final Image STAND_LEFT_IMG = new Image(IMG_PREFIX +"brick.png");
	
	public Brick() {
		setImage(STAND_LEFT_IMG);
	}

	@Override
	public void act(long now) {
		
	}

}