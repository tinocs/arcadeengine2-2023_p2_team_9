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
	
	//images
	private static final String IMG_PREFIX = "gameresources/";
    	//right
	    private static final Image jump1 = new Image(IMG_PREFIX +"jump1.png");
	    private static final Image runR1 = new Image(IMG_PREFIX +"runR1.png");
	    private static final Image runR2 = new Image(IMG_PREFIX +"runR2.png");
	    private static final Image runR3 = new Image(IMG_PREFIX +"runR3.png");
	    private static final Image stand1 = new Image(IMG_PREFIX +"stand1.png");
    	//left
	    private static final Image jump2 = new Image(IMG_PREFIX +"jump2.png");
	    private static final Image runL1 = new Image(IMG_PREFIX +"runL1.png");
	    private static final Image runL2 = new Image(IMG_PREFIX +"runL2.png");
	    private static final Image runL3 = new Image(IMG_PREFIX +"runL3.png");
	    private static final Image stand2 = new Image(IMG_PREFIX +"stand2.png");
    
    
    public MarioPlayer() {
    	setImage(stand1);
    	getTimer().start();
    	speed = 4;
    }
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		gravity();
		controls();
	}
	
	private void controls() {
		if(getWorld().isKeyPressed(KeyCode.LEFT) || getWorld().isKeyPressed(KeyCode.A)) {
			int frame = 1;
			move(-speed,0);
			if(frame == 1) {
				setImage(runL1);
				frame++;
			}else if(frame == 2) {
				setImage(runL2);
				frame++;
			}else if(frame == 3) {
				setImage(runL3);
				frame=1;
			}
		}
		if(getWorld().isKeyPressed(KeyCode.RIGHT) || getWorld().isKeyPressed(KeyCode.D)) {
			int frame = 1;
			move(speed,0);
			if(frame == 1) {
				setImage(runR1);
				frame++;
			}else if(frame == 2) {
				setImage(runR2);
				frame++;
			}else if(frame == 3) {
				setImage(runR3);
				frame=1;
			}
			System.out.println("dan");
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
