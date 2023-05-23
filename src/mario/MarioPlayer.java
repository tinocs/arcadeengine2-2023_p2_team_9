/**
 * Aryan Ajit
 * Period 2
 * May 22, 2023
 *
 * Is this code Working:
 */
package mario;

import engine.Actor;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class MarioPlayer extends Actor{
	double speed;
	boolean canJump = false;
	int vel;
	int jumpSpeed = 4;
	private static final String IMG_PREFIX = "gameresources/";
    private static final Image IMG = new Image(IMG_PREFIX +"tempPlayer.png");
    
    public MarioPlayer() {
    	setImage(IMG);
    	getTimer().start();
    	speed = 4;
    }
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		gravity();
		if(getWorld().isKeyPressed(KeyCode.LEFT) || getWorld().isKeyPressed(KeyCode.A)) {
			move(-speed,0);
		}
		if(getWorld().isKeyPressed(KeyCode.RIGHT) || getWorld().isKeyPressed(KeyCode.D)) {
			move(speed,0);
		}
		if(getWorld().isKeyPressed(KeyCode.UP) || getWorld().isKeyPressed(KeyCode.W)) {
			jump();
		}
	}
	private void gravity() {
		// TODO Auto-generated method stub
		vel+=1;
		move(0,vel);
		if(getOneIntersectingObject(Brick.class) == null) {
			canJump = false;
		}else {
			vel = 0;
			Brick touch = getOneIntersectingObject(Brick.class);
			setY(touch.getY() - getHeight());
			canJump = true;
			vel = 0;
		}
	}
	private void jump() {
		// TODO Auto-generated method stub
		if(canJump) {
			vel = -25;
			setY(getY() - .5);
		}
	}
}
