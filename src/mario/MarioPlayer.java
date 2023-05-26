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
	int vel = 0;
	int jumpSpeed = 4;
	int frame = 1;
	boolean isRight = false;
	//images
	private static final String IMG_PREFIX = "gameresources/";
    	//right
	    private static final Image jump1 = new Image(IMG_PREFIX +"jump1.png", 20, 30, false, false);
	    private static final Image runR1 = new Image(IMG_PREFIX +"runR1.png", 20, 30, false, false);
	    private static final Image runR2 = new Image(IMG_PREFIX +"runR2.png", 20, 30, false, false);
	    private static final Image runR3 = new Image(IMG_PREFIX +"runR3.png", 20, 30, false, false);
	    private static final Image stand1 = new Image(IMG_PREFIX +"stand1.png", 20, 30, false, false);
    	//left
	    private static final Image jump2 = new Image(IMG_PREFIX +"jump2.png", 20, 30, false, false);
	    private static final Image runL1 = new Image(IMG_PREFIX +"runL1.png", 20, 30, false, false);
	    private static final Image runL2 = new Image(IMG_PREFIX +"runL2.png", 20, 30, false, false);
	    private static final Image runL3 = new Image(IMG_PREFIX +"runL3.png", 20, 30, false, false);
	    private static final Image stand2 = new Image(IMG_PREFIX +"stand2.png", 20, 30, false, false);
	    //private static final Image dot = new Image(IMG_PREFIX +"tempPlayer.png", 2, 2, false, false);
    
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
		 if (blockOnTop() && !blockOnBottom()) {
			 if (this.getOneObjectAtOffset((int)-getWidth()/2, (int)-getHeight()/2, Block.class) != null) {
				 this.getOneObjectAtOffset((int)-getWidth()/2, (int)-getHeight()/2, Block.class).killSwitch();
			 } else {
				 this.getOneObjectAtOffset((int)getWidth()/2, (int)-getHeight()/2, Block.class).killSwitch();
			 }
			 vel = 0;
			 move(0, 5);
		 }else if((getWorld().isKeyPressed(KeyCode.UP) || getWorld().isKeyPressed(KeyCode.W)) && blockOnBottom()) {
				jump(-15);
		 } else if ((getWorld().isKeyPressed(KeyCode.LEFT) || getWorld().isKeyPressed(KeyCode.A)) && !blockOnLeft()) {
			if (getX() >= ((MarioWorld)getWorld()).playerLOffset) {
				move(-speed,0);
			}
			
			frame();
			isRight = false;
		} else if ((getWorld().isKeyPressed(KeyCode.RIGHT) || getWorld().isKeyPressed(KeyCode.D)) && !blockOnRight()) {
			if (getX() <= getWorld().getWidth() - ((MarioWorld)getWorld()).playerROffset) {
				move(speed,0);
			}
			frame();
			isRight = true;
		} else {
			if (isRight) {
				setImage(stand1);
			} else {
				setImage(stand2);
			}
			//frame = 1;
		}
		
	}
	public boolean blockOnTop() {
		return (this.getOneObjectAtOffset(-(int)getWidth()/2, (int)-getHeight()/2, Block.class) != null || this.getOneObjectAtOffset((int)getWidth()/2, (int)-getHeight()/2, Block.class) != null);
	}
	public boolean blockOnBottom() {
		return this.getOneObjectAtOffset(-(int)getWidth()/2, (int)getHeight()/2, Block.class) != null || this.getOneObjectAtOffset((int)getWidth()/2, (int)getHeight()/2, Block.class) != null;
	}
	public boolean blockOnLeft() {
		return (this.getOneObjectAtOffset(-(int)getWidth()/2, (int)getHeight()/2, Block.class) != null || this.getOneObjectAtOffset(-(int)getWidth()/2, -(int)getHeight()/2, Block.class) != null)  && this.getOneObjectAtOffset(-(int)getWidth()/2, 0, Block.class) != null;
	}
	public boolean blockOnRight() {
		return (this.getOneObjectAtOffset((int)getWidth()/2, (int)getHeight()/2, Block.class) != null || this.getOneObjectAtOffset((int)getWidth()/2, -(int)getHeight()/2, Block.class) != null) && this.getOneObjectAtOffset((int)getWidth()/2, 0, Block.class) != null;
	}
	public boolean isGoingRight() {
		return (getWorld().isKeyPressed(KeyCode.RIGHT) || getWorld().isKeyPressed(KeyCode.D));
	}
	public boolean isGoingLeft() {
		return (getWorld().isKeyPressed(KeyCode.LEFT) || getWorld().isKeyPressed(KeyCode.A));
	}
	public double getSpeed() {
		return speed;
	}
	private void frame() {
		if (isRight) {
			if(frame % 6 == 0) {
				setImage(runR1);
			}else if(frame % 4 == 0) {
				setImage(runR2);
			}else if (frame % 2 == 0){
				setImage(runR3);
			}
		} else {
			if(frame % 6 == 0) {
				setImage(runL1);
			}else if(frame % 4 == 0) {
				setImage(runL2);
			}else if (frame % 2 == 0){
				setImage(runL3);
			}
		}
		frame++;
	}
	private void gravity() {
		// TODO Auto-generated method stub
		vel+=1;
		move(0,vel);
		if(this.getOneObjectAtOffset(0, (int)getHeight()/2, Block.class) == null) {
			canJump = false;
		}else {
			vel = 0;
			Block touch = getOneObjectAtOffset(0, (int)getHeight()/2, Block.class);
			setY(touch.getY() - getHeight());
			canJump = true;
			vel = 0;
		}
	}
	public void jump(int vel) {
		// TODO Auto-generated method stub
		if(canJump) {
			this.vel = vel;
			setY(getY() - 1);
		}
	}
}
