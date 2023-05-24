package mario;

import engine.Actor;
import engine.World;
import javafx.scene.Node;

public class MarioWorld extends World {
	MarioPlayer mario;
	Goomba goomba;
	int w = 870;
	int h = 500;
	public int playerLOffset = 300;
	public int playerROffset = 500;
	public MarioWorld() {
		setPrefSize(w, h);
		System.out.println(new Brick().getWidth() + ", " + new Brick().getHeight());
	}

	@Override
	public void act(long now) {
		if (mario.getX() > w - playerROffset && mario.isGoingRight()) {
			moveAll((int)-mario.getSpeed(), 0);
		} else if (mario.getX() < playerLOffset && mario.isGoingLeft()) {
			moveAll((int)mario.getSpeed(), 0);
		}
	}
	
	@Override
	public void onDimensionsInitialized() {
		start();
		int x = 0;
		int y = (int) (getHeight()-new Brick().getHeight());
		makeBricks(x, y, 4, 100);
		makeBricks(100, 350, 1, 10);
		mario = new MarioPlayer();
		mario.setX(40);
		mario.setY(y - new Brick().getHeight()*4 - mario.getHeight());
		add(mario);
		
		goomba = new Goomba();
		goomba.setX(800);
		goomba.setY(300);
		add(goomba);
		
		
	}
	
	public void makeBricks(int x, int y, int height, int amt) {
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < amt; i++) {
				Brick b = new Brick();
				b.setX(x);
				b.setY(y);
				add(b);
				x+= b.getWidth();
			}
			y -= new Brick().getHeight();
			x = 0;
		}
		System.out.println(y);
	}
	
	public void moveAll(int xDir, int yDir) {
		for (Node n : getChildren()) {
			if (n != mario) {
				((Actor) n).move(xDir, yDir);
			}
		}
	}

}
