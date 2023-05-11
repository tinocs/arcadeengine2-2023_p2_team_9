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
		List<Actor> obj = getWorld().getObjects(cls);
		List<A> intObj = new ArrayList<A>();
		for (Actor a : obj) {
			if (a.intersects(getBoundsInLocal()) && a.getClass().equals(cls) && !a.equals(this)) {
				intObj.add((A) a);
			}
		}
		return intObj;
	}
	public <A extends Actor> A getOneIntersectingObject(Class<A> cls) {
		
	}
	public World getWorld() {
		return (World) getParent();
	}
	public void move(double dx, double dy) {
		this.setX(this.getX()+dx);
		this.setY(this.getY()+dy);
	}
	public void jump(double height) {
		
	}
}