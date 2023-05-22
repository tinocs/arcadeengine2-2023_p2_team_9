package mario;

import engine.World;

public class MarioWorld extends World {

	public MarioWorld() {
		setPrefSize(870, 500);
	}

	@Override
	public void act(long now) {
		
	}
	
	@Override
	public void onDimensionsInitialized() {
		start();
		int x = 0;
		int y = (int) (getHeight()-new Brick().getHeight());
		makeBottomBricks(x, y);
		
		MarioPlayer mario = new MarioPlayer();
		mario.setX(40);
		mario.setY(40);
		add(mario);
	}
	
	public void makeBottomBricks(int x, int y) {
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 32; i++) {
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

}
