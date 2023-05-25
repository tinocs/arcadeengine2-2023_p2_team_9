package mario;

import engine.Actor;
import javafx.scene.image.Image;
import tests.Player;

public class Brick extends Actor {
	private static final String IMG_PREFIX = "gameresources/";
    private static final Image IMG = new Image(IMG_PREFIX +"brick.png", 30, 30, false, false);
    private static boolean unbreakable;
	public Brick(boolean unb) {
		setImage(IMG);
		unbreakable = unb;
	}

	@Override
	public void act(long now) {
		
	}
	public void killSwitch() {
		if (unbreakable) {
			System.out.println("added");
			UnbreakaBlock unb = new UnbreakaBlock();
			unb.setX(getX());
			unb.setY(getY());
			getWorld().add(unb);
		}
		getWorld().remove(this);
	}
}
