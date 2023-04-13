package engine;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

// TODO finish the shit that goes in the code my dudes

public abstract class World extends Pane {
	// private fields as listed
	private Timer timer;
	private boolean isOn = false;
	
	private double width = 0.0;
	private double height = 0.0;
	// methods
	public World(Double w, double h) {
		timer = new Timer();
		
		// lol i used this shitty way to make it just to get some errors out
		width = width + w;
		height = height + h;
	}
	
	public abstract void act(long now);
	
	public void addActor(Actor actor) {
		
	}
	public <A extends Actor> void getObjects(java.lang.Class<A> cls) {
		
	}
	public <A extends Actor> void getObjectsAt(double x, double y, java.lang.Class<A> cls) {
		
	}
	public boolean isKeyPressed(javafx.scene.input.KeyCode code) {
		return false;
		
	}
	public boolean isStopped() {
		return isOn;
		
	}
	public abstract void onDimensionsInitialized();
	
	public void remove(Actor actor) {
		
	}
	public void start() {
		timer.start();
		isOn = true;
	}
	public void stop() { 
		timer.stop();
		isOn = false;
	}
	
	private class Timer extends AnimationTimer {

		@Override
		public void handle(long now) {
			act(now);
		}
		
	}
}