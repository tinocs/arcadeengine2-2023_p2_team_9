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
	//World world = getWorld();
	double height = 500;
	double width = 500;
	private double dx = 2;
	private double dy = 2;
	private static final String IMG_PREFIX = "gameresources/";
	private static final Image BALL_IMAGE = new Image(IMG_PREFIX +"Red-Ball-PNG.png");
	/**
	 * @param args
	 */

	public Ball() {
		setImage(BALL_IMAGE);
	}
	@Override
	public void addedToWorld() {
		getTimer().start();
	}
	@Override
	public void act(long now) {
		move(dx, dy);
		borders();
	}
	public void borders() {
		if(getX() < 0 || getX() > width-getWidth()) {
			dx = -dx;
		}
		if(getY() < 0 || getY() > height-getHeight()) {
			dy = -dy;
		}
		//paddle
		if(getOneIntersectingObject(Paddle.class) != null) {
			dy = -dy;
		}
	}

}
