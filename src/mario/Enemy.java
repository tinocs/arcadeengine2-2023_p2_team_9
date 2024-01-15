package mario;

import engine.Actor;

public class Enemy extends Actor {
	public int speed = -1;
	public int frame = 1;
	public int vel = 0;
	public boolean isDead = false;
	public int deadCounter = 0;
	
	public Enemy() {
		super();
	}

	@Override
	public void act(long now) { }
	
	protected void gravity() {
		// TODO Auto-generated method stub
		vel+=1;
		move(0,vel);
		if(getOneIntersectingObject(Block.class) == null) {
		}else {
			vel = 0;
			Block touch = getOneIntersectingObject(Block.class);
			setY(touch.getY() - getHeight());
			vel = 0;
		}
	}
	
	protected void detectWalls() {
		if (getOneObjectAtOffset((int)-getWidth()/2, 0, Block.class) != null) {
			speed = -speed;
		} else if (getOneObjectAtOffset((int)getWidth()/2, 0, Block.class) != null) {
			speed = -speed;
		}
		if (getOneObjectAtOffset((int)-getWidth()/2, 0, Enemy.class) != null) {
			speed = -speed;
		} else if (getOneObjectAtOffset((int)getWidth()/2, 0, Enemy.class) != null) {
			speed = -speed;
		}
	}
	
	public void playerInteraction() { }
	
	protected MarioPlayer getPlayer() {
		return getOneIntersectingObject(MarioPlayer.class);
	}
}
