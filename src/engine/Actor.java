package engine;

import java.util.List;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

//TODO finish the shit that goes in the code my dudes

public abstract class Actor extends ImageView {

	public Actor() {
		
	}
	
	public abstract void act(long now);
	
	public void addedToWorld() {
		
	}
	public double getHeight() {
		return getHeight();
	}
	public double getWidth() {
		return getWidth();
	}
	public <A extends Actor> List<A> getIntersectingObjects(Class cls) {
		return null;
	}
	public <A extends Actor> A getOneIntersectingObject(Class cls) {
		return null;
	}
	public World getWorld() {
		return (World) getParent();
	}
	public void move(double dx, double dy) {
		
	}

}
