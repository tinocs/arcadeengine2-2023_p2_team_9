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
		vel+=1;
		move(0,vel);
		
		// TODO Auto-generated method stub
		if(getOneIntersectingObject(Brick.class) == null) {
			canJump = false;
		}else {
			Brick touch = getOneIntersectingObject(Brick.class);
			setY(touch.getY() - getHeight());
			canJump = true;
		}
	}
	private void jump() {
		// TODO Auto-generated method stub
		if(canJump) {
			System.out.println("jumps");
			vel = -25;
		}
	}
}
