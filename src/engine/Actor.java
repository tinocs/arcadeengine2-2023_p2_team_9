package engine;

import java.util.ArrayList;
import java.util.List;	

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

//TODO 

public abstract class Actor extends ImageView {

	public Actor() {
		
	}
	
	public abstract void act(long now);
	
	public void addedToWorld() {
		
	}
	public double getHeight() {
		return this.getBoundsInParent().getHeight();
	}
	public double getWidth() {
		return this.getBoundsInParent().getWidth();
	}
	public <A extends Actor> List<A> getIntersectingObjects(Class cls) {
		ArrayList<A> obj = getWorld().getObjects(cls)
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
