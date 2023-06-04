package mario;

import javafx.scene.image.Image;

public class KoopaTroopa extends Enemy {
	//images
	private static final String IMG_PREFIX = "gameresources/";
	private static final Image Left1 = new Image(IMG_PREFIX+"KTLeft1.png");
	private static final Image Left2 = new Image(IMG_PREFIX+"KTLeft2.png");
	private static final Image Right1 = new Image(IMG_PREFIX+"KTRight1.png");
	private static final Image Right2 = new Image(IMG_PREFIX+"KTRight2.png");
	private static final Image squish = new Image(IMG_PREFIX+"box.png");
	private static final Image unSquish = new Image(IMG_PREFIX+"KTUnbox.png");
	
	private boolean isRight = false;
	private boolean isShell = false;
	private boolean zoomMode = false;
	
	private int shellCounter = 0;
	public KoopaTroopa(boolean dir) {
		isRight = dir;
		if (isRight)  {
			setImage(Right1);
			speed = Math.abs(speed);
		} else {
			setImage(Left1);
			speed = -Math.abs(speed);
		}
	}

	@Override
	public void act(long now) {
		if (getX() >= -90 && getX() <= 900) {
			gravity();
			
			if (!zoomMode && !isDead) {
				detectWalls();
				playerInteraction();
				if (!isShell) {
					
					if (speed > 0) {
						isRight = true;
					} else {
						isRight = false;
					}
					move(speed,0);
					if(frame % 20 == 0) {
						if (isRight) 
							setImage(Right1);
						else
							setImage(Left1);
					}else if(frame % 10 == 0){
						if (isRight) 
							setImage(Right2);
						else
							setImage(Left2);
					}
					frame++;
				} else {
					shellCounter++;
					if (shellCounter == 150) {
						setImage(unSquish);
					} else if (shellCounter == 200) {
						shellCounter = 0;
						isShell = false;
					}
					if ((playerOnLeft() || playerOnRight()) && shellCounter >= 50) {
						shellCounter = 0;
						zoomMode = true;
						if (playerOnRight()) {
							speed = -1;
						} else if (playerOnLeft()){
							speed = 1;
						}
						getPlayer().jump(-10);
						move(5*speed, 0);
					}
				}
			} else if (zoomMode) {
				move(3*speed, 0);
				shellWall();
				if (playerOnTop() && speed != 0) {
					speed = 0;
					getPlayer().jump(-5);
				} else if ((playerOnLeft() || playerOnRight()) && speed != 0) {
					getPlayer().setDead(true);
				} else if (speed == 0 && !getPlayer().getDead()) {
					if (playerOnRight()) {
						speed = -1;
					} else if (playerOnLeft()){
						speed = 1;
					}
				}
			} else if (isDead) {
				
			}
		}
	}
	private void shellWall() {
		if (getOneObjectAtOffset((int)-getWidth()/2, 0, Block.class) != null || getOneObjectAtOffset((int)getWidth()/2, 0, Block.class) != null) {
			if (getOneObjectAtOffset((int)-getWidth()/2, 0, Block.class) != null) {
				getOneObjectAtOffset((int)-getWidth()/2, 0, Block.class).killSwitch();
			} else {
				getOneObjectAtOffset((int)getWidth()/2, 0, Block.class).killSwitch();
			}
			speed = -speed;
			move(3*speed,0);
		}
	}
	@Override
	public void playerInteraction() {
		if (playerOnTop() && !getPlayer().getDead()) {
			MarioPlayer m = getOneIntersectingObject(MarioPlayer.class);
			setImage(squish);
			isShell = true;
			getPlayer().jump(-10);
			//setPlayerPos(0, -30);
		} else if (getPlayer() != null && !isShell){
			getPlayer().setDead(true);
		}
	}
	private boolean playerOnLeft() {
		return this.getOneObjectAtOffset(-(int)getWidth()/2, (int)-getHeight()/2, MarioPlayer.class) != null || this.getOneObjectAtOffset(-(int)getWidth()/2, (int)getHeight()/2, MarioPlayer.class) != null;
	}
	private boolean playerOnRight() {
		return this.getOneObjectAtOffset((int)getWidth()/2, (int)-getHeight()/2, MarioPlayer.class) != null || this.getOneObjectAtOffset((int)getWidth()/2, (int)getHeight()/2, MarioPlayer.class) != null;
	}
	private boolean playerOnTop() {
		return this.getOneObjectAtOffset((int)-getWidth()/4, (int)-getHeight()/2, MarioPlayer.class) != null || this.getOneObjectAtOffset((int)getWidth()/4, (int)-getHeight()/2, MarioPlayer.class) != null;
	}
	
	private void setPlayerPos(int dx, int dy) {
		getPlayer().setX(getPlayer().getX()+dx);
		getPlayer().setY(getPlayer().getY()+dy);
	}
}
