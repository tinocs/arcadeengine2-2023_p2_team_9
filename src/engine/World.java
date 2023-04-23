package engine;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

// TODO finish the shit that goes in the code my dudes

public abstract class World extends Pane {
	// private fields as listed
	private Timer timer;
	private boolean isOn = false;
	
	private boolean widthSet = false;
	private boolean heightSet = false;
	private double width = 0.0;
	private double height = 0.0;
	private ArrayList<Key> keysPressed;
	// methods
	public World() {
		timer = new Timer();
		
		keysPressed = new ArrayList<Key>();
		
	}
	
	public abstract void act(long now);
	
	public abstract void onDimensionsInitialized();
	
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