/**
 * Aryan Ajit
 * Period 2
 * May 22, 2023
 *
 * Is this code Working:
 */
package mario;

import engine.Actor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class MarioPlayer extends Actor {
	double speed;
	boolean canJump = false;
	double vel = 0;
	int jumpSpeed = 4;
	int frame = 1;
	boolean isRight = false;
	
	boolean isDead = false;
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
	    private static final Image death = new Image(IMG_PREFIX +"death.jpeg", 20, 30, false, false);
	    //private static final Image dot = new Image(IMG_PREFIX +"tempPlayer.png", 2, 2, false, false);
    
    public MarioPlayer() {
    	setImage(stand1);
    	speed = 4;
    	
    }
	@Override
	public void act(long now) {
		if (!isDead) {
			gravity();
			controls();
		}
	}
	public void setDead(boolean bool) {
		isDead = bool;
		if (isDead) {
			setImage(death);
			Label l = new Label("You lost! Press \"r\" to restart");
			l.setLayoutX(400);
			l.setLayoutY(250);
			getWorld().getChildren().add(l);
		}
	}
	public boolean getDead() {
		return isDead;
	}
	private void controls() {
		 if (getTopBlockIntersections() > 12) {
			 if (this.getOneObjectAtOffset((int)-getWidth()/2, (int)-getHeight()/2, Block.class) != null) {
				 this.getOneObjectAtOffset((int)-getWidth()/2, (int)-getHeight()/2, Block.class).killSwitch();
				 move(0, 5);
			 } else if (this.getOneObjectAtOffset((int)getWidth()/2, (int)-getHeight()/2, Block.class) != null) {
				 this.getOneObjectAtOffset((int)getWidth()/2, (int)-getHeight()/2, Block.class).killSwitch();
				 move(0, 5);
			 }
			 vel = 0;
		 }else if((getWorld().isKeyPressed(KeyCode.UP) || getWorld().isKeyPressed(KeyCode.W)) && blockOnBottom() && getTopBlockIntersections() <= 3) {
				jump(-11.5);
		 } else if ((getWorld().isKeyPressed(KeyCode.LEFT) || getWorld().isKeyPressed(KeyCode.A)) && getLeftBlockIntersections() <= 1) {
			if (getX() >= ((MarioWorld)getWorld()).playerLOffset) {
				move(-speed,0);
			}
			frame();
			isRight = false;
		} else if ((getWorld().isKeyPressed(KeyCode.RIGHT) || getWorld().isKeyPressed(KeyCode.D)) && getRightBlockIntersections() <= 1 ) {
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
	public int getLeftBlockIntersections() {
		int counter = 0;
		for (int i = -(int)getHeight()/2; i <= (int)getHeight()/2; i++) {
			if (blockAt(-(int)getWidth()/2, i)) {
				counter++;
			}
		}
		//System.out.println(counter);

		return counter;
	}
	public int getRightBlockIntersections() {
		int counter = 0;
		for (int i = -(int)getHeight()/2; i <= (int)getHeight()/2; i++) {
			if (blockAt((int)getWidth()/2, i)) {
				counter++;
			}
		}
		//System.out.println(counter);
		return counter;
	}
	public int getTopBlockIntersections() {
		int counter = 0;
		for (int i = -(int)getWidth()/2; i <= (int)getWidth()/2; i++) {
			if (blockAt(i, -(int)getHeight()/2)) {
				counter++;
			}
		}
		return counter;
	}
	public boolean blockAt(int dx, int dy) {
		return this.getOneObjectAtOffset(dx, dy, Block.class) != null;
	}
	public boolean blockOnTop() {
		return (this.getOneObjectAtOffset(-(int)getWidth()/2, (int)-getHeight()/2, Block.class) != null || this.getOneObjectAtOffset((int)getWidth()/2, (int)-getHeight()/2, Block.class) != null) && this.getOneObjectAtOffset(0, (int)-getHeight()/2, Block.class) != null;
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
		vel+=.5;
		move(0,vel);
		if(this.getOneObjectAtOffset(0, (int)getHeight()/2, Block.class) == null) {
			canJump = false;
		} else {
			vel = 0;
			Block touch = getOneObjectAtOffset(0, (int)getHeight()/2, Block.class);
			setY(touch.getY() - getHeight()); 
			canJump = true;
			vel = 0;
		}
		if(getY() > 1000) {
			setDead(true);
		}
		
		
	}
	public void jump(double vel) {
		// TODO Auto-generated method stub
		if(canJump) {
			this.vel = vel;
			setY(getY() - 1);
		}
	}
}
