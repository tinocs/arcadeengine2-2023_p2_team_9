/**
 * Aryan Ajit
 * Period 2
 * May 23, 2023
 *
 * Is this code Working:
 */
package mario;

import engine.Actor;
import javafx.scene.image.Image;

public class Goomba extends Actor{
	private static final int speed = -1;
	int frame = 1;
	int vel = 0;
	boolean canJump = false;
	//images
	private static final String IMG_PREFIX = "gameresources/";
	private static final Image IMG = new Image(IMG_PREFIX+"tempPlayer.png");
	private static final Image goomba1 = new Image(IMG_PREFIX+"goomba1.png");
	private static final Image goomba2 = new Image(IMG_PREFIX+"goomba2.png");
	private static final Image goombaSquish = new Image(IMG_PREFIX+"goombaSquish.png");
	public Goomba() {
		setImage(goomba1);
		getTimer().start();
	}
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		gravity();
		move(speed,0);
		if(frame % 20 == 0) {
			setImage(goomba2);
		}else if(frame % 10 == 0){
			setImage(goomba1);
		}
		frame++;
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

}
