package engine;

import javafx.scene.layout.Pane;

// TODO finish the shit that goes in the code my dudes

public abstract class World extends Pane {

	public World() {
		
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
		return false;
		
	}
	public abstract void onDimensionsInitialized();
	
	public void remove(Actor actor) {
		
	}
	public void start() {
		
	}
	public void stop() { 
		
	}
}