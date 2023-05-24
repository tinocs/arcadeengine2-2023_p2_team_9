package mario;

import engine.Actor;
import javafx.scene.image.Image;

public class Brick extends Actor {
	private static final String IMG_PREFIX = "gameresources/";
    private static final Image IMG = new Image(IMG_PREFIX +"brick.png", 30, 30, false, false);
	public Brick() {
		setImage(IMG);
	}

	@Override
	public void act(long now) {
		
	}

}
