package mario;

import engine.Actor;
import javafx.scene.image.Image;

public class QuestionBlock extends Block {
	private static final String IMG_PREFIX = "gameresources/";
    private static final Image IMG = new Image(IMG_PREFIX +"question.png", 30, 30, false, false);
	public QuestionBlock() {
		setImage(IMG);
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void killSwitch() {
		
		UnbreakaBlock unb = new UnbreakaBlock();
		unb.setX(getX());
		unb.setY(getY());
		getWorld().add(unb);
		
		/*
		Powerup pow = new Powerup();
		pow.setX(getX());
		pow.setY(getY() - getHeight()/2);
		getWorld().add(pow);
		*/
		
		getWorld().remove(this);
	}

}
