package engine;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

//TODO 

public abstract class Actor extends ImageView {
	public MyTimer timer = new MyTimer();
	public Actor() {
		super();
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
			if (a.intersects(getBoundsInParent()) && a != this) {
				intObj.add((A) a);
			}
		}
		return intObj;
	}
	public <A extends Actor> A getOneIntersectingObject(Class<A> cls) {
		List<A> temp  = getIntersectingObjects(cls);
		if(temp.size() <= 0) {
			return null;
		}
		return temp.get(0);
	}
	public World getWorld() {
		return (World) getParent();
	}
	public void move(double dx, double dy) {
		this.setX(this.getX()+dx);
		this.setY(this.getY()+dy);
	}
	public MyTimer getTimer() {
		return timer;
	}
	public class MyTimer extends AnimationTimer {
		long oldTime = 0;
		
		@Override
		public void handle(long now) {
			if (now - oldTime >= 0.01 * 1e9) {
				act(now);
				oldTime = now;
			}
			
		}
		
	}
}