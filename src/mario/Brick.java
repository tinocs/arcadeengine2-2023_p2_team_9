package mario;

import engine.Actor;
import javafx.scene.image.Image;
import tests.Player;

public class Brick extends Actor {
	private static final String IMG_PREFIX = "gameresources/";
    private static final Image IMG = new Image(IMG_PREFIX +"brick.png", 30, 30, false, false);
    private boolean unbreakable;
	public Brick(boolean unb) {
		setImage(IMG);
		System.out.println("unb = " + unb);
		unbreakable = unb;
		System.out.println("unbreakable = " + unbreakable);
	}

	@Override
	public void act(long now) {
		
	}
	public void killSwitch() {
		System.out.println("killed: " + unbreakable);
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
