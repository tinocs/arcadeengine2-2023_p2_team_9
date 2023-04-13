package engine;

import javafx.scene.layout.Pane;

public abstract class World extends Pane {

	public World() {}
	
	public abstract void act(long now);
	
	public void addActor(Actor actor) {
		
	}
	public <A extends Actor> void getObjects(java.lang.Class<A> cls) {
		
	}
}
