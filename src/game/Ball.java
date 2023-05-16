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
	World world;
	double height;
	double width;
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
		world = getWorld();
		height = world.getHeight();
		width = world.getWidth();
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
			//dy = -dy;
			Paddle paddle = getOneIntersectingObject(Paddle.class);
			/*
			if (getX()+this.getWidth() <= paddle.getX() || getX() >= paddle.getWidth() + paddle.getX()) {
				dx = -dx;
			} else {
				dy = -dy;
			}
			*/
			if (getX()+this.getWidth() <= paddle.getX() || getX() >= paddle.getWidth() + paddle.getX()) {
				if (getY() <= paddle.getY()+paddle.getHeight() && getY() >= paddle.getY()-paddle.getHeight()) {
					dx = -dx;
				}
			} else {
				dy = -dy;
				move(0, -5);
			}
		}
	}

}
