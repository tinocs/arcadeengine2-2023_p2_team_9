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

public class Goomba extends Enemy {
	
	//images
	private static final String IMG_PREFIX = "gameresources/";
	private static final Image IMG = new Image(IMG_PREFIX+"tempPlayer.png");
	private static final Image goomba1 = new Image(IMG_PREFIX+"goomba1.png");
	private static final Image goomba2 = new Image(IMG_PREFIX+"goomba2.png");
	private static final Image goombaSquish = new Image(IMG_PREFIX+"goombaSquish.png");
	
	public Goomba() {
		setImage(goomba1);
	}
	
	@Override
	public void act(long now) {
		if (getOneIntersectingObject(KoopaTroopa.class) != null) {
			setImage(goombaSquish);
			isDead = true;
		}
		if (getX() >= -90 && getX() <= 900) {
			gravity();
			playerInteraction();
			detectWalls();
			
			if (!isDead) {
				
				move(speed,0);
				if(frame % 20 == 0) {
					setImage(goomba2);
				}else if(frame % 10 == 0){
					setImage(goomba1);
				}
				frame++;
			} else {
				deadCounter++;
				if (deadCounter == 50) {
					getWorld().remove(this);
				}
			}
		}
		
	}
	@Override
	public void playerInteraction() {
		if (this.getOneIntersectingObject(MarioPlayer.class) != null) {
			MarioPlayer m = getOneIntersectingObject(MarioPlayer.class);
			if (m.getY() < getY() - m.getHeight()/2) {
				setImage(goombaSquish);
				isDead = true;
				getPlayer().jump(-5);
			} else if (!isDead) {
				getPlayer().setDead(true);
			}
		}
	}
	
//	public void goombaInteraction() {
//		if()
//	}
}
