package engine;

import javafx.scene.image;

//TODO finish the shit that goes in the code my dudes

public abstract class Actor extends ImageView {

	public Actor() {
		
	}
	
	public abstract void act(long now);
	
	public void addedToWorld() {
		
	}
	public double getHeight() {
		return 0.0;
	}
	public double getWidth() {
		return 0.0;
	}
	public <A extends Actor> void getIntersectingObjects(Class cls) {
		
	}
	public <A extends Actor> void getOneIntersectingObject(Class cls) {
		
	}
	public World getWorld() {
		return null;
	}
	public void move(double dx, double dy) {
		
	}

}
