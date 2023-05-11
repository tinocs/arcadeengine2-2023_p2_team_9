package engine;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

// TODO 

public abstract class World extends Pane {
	// private fields as listed
	private Timer timer;
	private boolean isOn = false;

	private boolean dimSet = false;
	private Set<KeyCode> keysPressed;
	// methods
	public World() {
		timer = new Timer();
		 
		keysPressed = new HashSet<KeyCode>();
		
		
		widthProperty().addListener(new WidthHandler());
		heightProperty().addListener(new HeightHandler());
		
		if(this != null) {
			this.sceneProperty().addListener(new SceneListener());
		}
		
		//ArrayListProperty<Object> arrayList = new ArrayListProperty<>(); 
		//arrayList.addListener(); 
	}
	
	public abstract void act(long now);
	
	public abstract void onDimensionsInitialized();
	
	public void add(Actor actor) {
		this.getChildren().add(actor);
		actor.addedToWorld();
	}
	public <A extends Actor> java.util.List<A> getObjects(Class cls) {
		ArrayList list = new ArrayList();
		for (Object obj : this.getChildren()) {
			if (obj.getClass() == cls) {
				list.add(obj);
			}
		}
		return list;
	}
	public <A extends Actor> java.util.List<A> getObjectsAt(double x, double y, java.lang.Class<A> cls) {
		ArrayList list = new ArrayList();
		for (Object obj : this.getChildren()) {
			if (obj.getClass() == cls) {
				list.add(obj);
			}
		}
		return list;
	}
	
	public boolean isKeyPressed(javafx.scene.input.KeyCode code) {
		return keysPressed.contains(code);
	}
	public boolean isStopped() {
		return isOn;
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
				list.get(i).act(now);
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
}