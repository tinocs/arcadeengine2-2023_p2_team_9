package mario;

import javafx.scene.image.Image;

public class Brick extends Block {
	private static final String IMG_PREFIX = "gameresources/";
    private static final Image IMG = new Image(IMG_PREFIX +"brick.png", 30, 30, false, false);
    private boolean unbreakable;
	public Brick(boolean unb) {
		setImage(IMG);
		unbreakable = unb;
	}

	@Override
	public void act(long now) {
		
	}
	@Override
	public void killSwitch() {
		if (unbreakable) {
			UnbreakaBlock unb = new UnbreakaBlock();
			unb.setX(getX());
			unb.setY(getY());
			getWorld().add(unb);
		}
		getWorld().remove(this);
	}
}
