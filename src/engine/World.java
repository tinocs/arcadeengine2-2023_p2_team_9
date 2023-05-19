package engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

// TODO 

public abstract class World extends Pane {
	// private fields as listed
	private MyTimer timer;
	private boolean isOn = false;
	private boolean widthSet = false;
	private boolean heightSet = false;

	private boolean dimSet = false;
	private Set<KeyCode> keysPressed;
	// methods
	public World() {
		super();
		timer = new MyTimer();
		
		keysPressed = new HashSet<KeyCode>();
		
		
		widthProperty().addListener(new WidthHandler());
		heightProperty().addListener(new HeightHandler());
		
		if(this != null) {
			this.sceneProperty().addListener(new SceneListener());
		}
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				keysPressed.add(event.getCode());
			}
			
		});
		
		setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				keysPressed.remove(event.getCode());
			}
			
		});
	}
	
	public abstract void act(long now);
	
	public abstract void onDimensionsInitialized();
	
	public void add(Actor actor) {
		this.getChildren().add(actor);
		actor.addedToWorld();
	}
	public <A extends Actor> java.util.List<A> getObjects(Class<A> cls) {
		ArrayList<A> list = new ArrayList<A>();
		for (Node obj : this.getChildren()) {
			if (cls.isInstance(obj)) {
				list.add(cls.cast(obj));
			}
		}
		return list;
	}
	public <A extends Actor> java.util.List<A> getObjectsAt(double x, double y, Class<A> cls) {
		ArrayList<A> list = new ArrayList<A>();
		for (A obj : getObjects(cls)) {
			Bounds bound = obj.getBoundsInParent();
			if (bound.contains(x, y)) {
				list.add(obj);
			}
		}
		return list;
	}
	
	public boolean isKeyPressed(javafx.scene.input.KeyCode code) {
		return keysPressed.contains(code);
	}
	public boolean isStopped() {
		return !isOn;
	}
	
	public void remove(Actor actor) {
		this.getChildren().remove(actor);
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
			List<Actor> list = getObjects(Actor.class);
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getWorld() != null) {
					list.get(i).act(now);
				}
			}
		}
		
	}
	
	private class WidthHandler implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			if (!widthSet) widthSet = true;
			if (widthSet && heightSet && !dimSet) { onDimensionsInitialized(); dimSet = true; }
		}
    }
	private class HeightHandler implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			if (!heightSet) heightSet = true;
			if (widthSet && heightSet && !dimSet) { onDimensionsInitialized(); dimSet = true; }
		}
    }
	private class SceneListener implements ChangeListener<Scene> {

		@Override
		public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
			if(newValue != null) {
				requestFocus();
			}
		}
		
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