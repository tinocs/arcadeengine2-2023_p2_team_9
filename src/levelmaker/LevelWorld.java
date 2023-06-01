package levelmaker;

import engine.Actor;
import engine.World;
import javafx.scene.Node;

public class LevelWorld extends World {
	int w = 870;
	int h = 510;
	
	public LevelWorld() {
		setPrefSize(w, h);
	}

	@Override
	public void act(long now) {
		
	}

	@Override
	public void onDimensionsInitialized() {
		
	}
	
	public void moveAll(int xDir, int yDir) {
		for (Node n : getChildren()) {
			((Actor) n).move(xDir, yDir);
		}
	}
}
