package mario;

import engine.Actor;
import engine.World;
import javafx.scene.Node;

public class MarioWorld extends World {
	MarioPlayer mario;
	int w = 870;
	int h = 500;
	public MarioWorld() {
		setPrefSize(w, h);
	}

	@Override
	public void act(long now) {
		if (mario.getX() > w - 150 && mario.isGoingRight()) {
			moveAll((int)-mario.getSpeed(), 0);
		} else if (mario.getX() < 150 && mario.isGoingLeft()) {
			moveAll((int)mario.getSpeed(), 0);
		}
	}
	
	@Override
	public void onDimensionsInitialized() {
		start();
		int x = 0;
		int y = (int) (getHeight()-new Brick().getHeight());
		makeBottomBricks(x, y);
		
		mario = new MarioPlayer();
		mario.setX(40);
		mario.setY(y - new Brick().getHeight()*4 - mario.getHeight());
		add(mario);
	}
	
	public void makeBottomBricks(int x, int y) {
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 100; i++) {
				Brick b = new Brick();
				b.setX(x);
				b.setY(y+2*j);
				add(b);
				x+= b.getWidth();
			}
			y -= new Brick().getHeight();
			x = 0;
		}
	}
	
	public void moveAll(int xDir, int yDir) {
		for (Node n : getChildren()) {
			if (n != mario) {
				((Actor) n).move(xDir, yDir);
			}
		}
	}

}
