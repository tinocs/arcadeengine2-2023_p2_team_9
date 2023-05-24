package mario;

import engine.Actor;
import javafx.scene.image.Image;

public class UnbreakaBlock extends Actor{
	private static final String IMG_PREFIX = "gameresources/";
    private static final Image IMG = new Image(IMG_PREFIX +"unbreakablock.png", 28, 27, false, false);
	public UnbreakaBlock() {
		setImage(IMG);
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

}