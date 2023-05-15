/**
 * Aryan Ajit
 * Period 2
 * May 12, 2023
 *
 * Is this code Working:
 */
package game;

import engine.Actor;
import engine.World;
import javafx.scene.image.Image;

public class Ball extends Actor {
	World world = getWorld();
	double height = getHeight();
	double width = getWidth();
	
	private static final String IMG_PREFIX = "gameresources/";
	private static final Image BALL_IMAGE = new Image(IMG_PREFIX +"Red-Ball-PNG.png");
	/**
	 * @param args
	 */

	public Ball() {
		setImage(BALL_IMAGE);
	}
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		move(getX(), getY());
		borders();
	}
	public void borders() {
		if(getX() < 0 || getX() > width) {
			move(-getX(),getY());
		}
		if(getY() < 0 || getY() > height) {
			move(getX(),-getY());
		}
		//paddle
		if(getOneIntersectingObject(Paddle.class) != null) {
			move(getX(), -getY());
		}
	}

}
