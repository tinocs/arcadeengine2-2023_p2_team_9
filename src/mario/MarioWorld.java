package mario;

import engine.Actor;
import engine.World;
import javafx.scene.Node;

public class MarioWorld extends World {
	MarioPlayer mario;
	int w = 870;
	int h = 510;
	public int playerLOffset = 300;
	public int playerROffset = 500;
	public MarioWorld() {
		setPrefSize(w, h);
	}

	@Override
	public void act(long now) {
		if (mario.getX() > w - playerROffset && mario.isGoingRight() && !mario.blockOnRight()) {
			moveAll((int)-mario.getSpeed(), 0);
		} else if (mario.getX() < playerLOffset && mario.isGoingLeft() && !mario.blockOnLeft()) {
			moveAll((int)mario.getSpeed(), 0);
		}
	}
	
	@Override
	public void onDimensionsInitialized() {
		start();
		
		testWorld();
		
//		int x = 0;
//		int y = (int) (getHeight()-new Brick(false).getHeight());
//		makeBricks(x, y, 4, 100, false);
//		makeBricks(200, 370, 1, 2, true);
//		makeBricks(500, 370, 1, 2, false);
//		//makeBricks(100, 250, 1, 10, true);
//		mario = new MarioPlayer();
//		mario.setX(40);
//		mario.setY(y - new Brick(false).getHeight()*4 - mario.getHeight());
//		add(mario);
//		
//		
//		Goomba goomba = new Goomba();
//		goomba.setX(800);
//		goomba.setY(300);
//		add(goomba);
//		
//		
//		KoopaTroopa kt = new KoopaTroopa(true);
//		kt.setX(350);
//		kt.setY(300);
//		add(kt);
//		
//		Pipe p = new Pipe(1, 2);
//		p.setX(600);
//		p.setY(360);
//		add(p);
		
	}
	
	public void testWorld() {
		int x = 0;
		int y = (int) (getHeight() - 30);
		
		mario = new MarioPlayer();
		mario.setX(40);
		mario.setY(y - new Brick(false).getHeight()*4 - mario.getHeight());
		add(mario);
		
		
		makeBricks(x, y, 4, 40, false);
		x+=(int)new Brick(false).getWidth()*42;
		makeBricks(x, y, 4, 10, false);
		x+=(int)new Brick(false).getWidth()*12;
		makeBricks(x, y, 4, 13, false);
		x+=(int)new Brick(false).getWidth()*15;
		makeBricks(x, y, 4, 66, false);
		x+=(int)new Brick(false).getWidth()*68;
		makeBricks(x, y, 4, 66, false);
		System.out.println(x);
		
		koopaTroopa(350, 360);
		newQBlock(210, 270);
		makeBricks(300, 270, 1, 5, true);
		newQBlock(360, 180);
		
		// pipe
		makeBricks(510, 360, 2, 1, false);
		goomba(600, 360);
		// pipe
		makeBricks(720, 360, 3, 1, false);
		goomba(810, 360);
		koopaTroopa(870, 360);
		// pipe
		makeBricks(930, 360, 3, 1, false);
		makeBricks(1770, 270, 1, 3, true);
		newQBlock(1800, 270);
		makeBricks(1830, 180, 1, 6, false);
		makeBricks(2100, 180, 1, 4, false);
		newQBlock(2220, 180);
		makeBricks(2220, 270, 1, 1, true);
		makeBricks(2520, 270, 1, 2, true);
		newQBlock(2700, 270);
		newQBlock(2790, 270);
		newQBlock(2790, 180);
		newQBlock(2880, 270);
		makeBricks(3000, 270, 1, 1, true);
		makeBricks(3120, 180, 1, 3, true);
		makeBricks(3360, 180, 1, 4, true);
		newQBlock(3390, 180);
		newQBlock(3420, 180);
		makeBricks(3390, 270, 1, 2, true);
		
		makeUBricks(3510, 360, 1, 4);
		makeUBricks(3540, 330, 1, 3);
		makeUBricks(3570, 300, 1, 2);
		makeUBricks(3600, 270, 1, 1);
		
		makeUBricks(3660, 360, 1, 4);
		makeUBricks(3660, 330, 1, 3);
		makeUBricks(3660, 300, 1, 2);
		makeUBricks(3660, 270, 1, 1);
		
		makeUBricks(3900, 360, 1, 5);
		makeUBricks(3930, 330, 1, 4);
		makeUBricks(3960, 300, 1, 3);
		makeUBricks(3990, 270, 1, 2);
		
		makeUBricks(4110, 360, 1, 4);
		makeUBricks(4110, 330, 1, 3);
		makeUBricks(4110, 300, 1, 2);
		makeUBricks(4110, 270, 1, 1);
		
		// pipe
		makeBricks(4350, 360, 2, 2, false);
		makePipe(4350, 360);
		makeBricks(4470, 270, 1, 4, false);
		newQBlock(4530, 270);
		
		makeUBricks(4800, 360, 1, 9);
		makeUBricks(4830, 330, 1, 8);
		makeUBricks(4860, 300, 1, 7);
		makeUBricks(4890, 270, 1, 6);
		makeUBricks(4920, 240, 1, 5);
		makeUBricks(4950, 210, 1, 4);
		makeUBricks(4980, 180, 1, 3);
		makeUBricks(5010, 150, 1, 2);
		makeBricks(5220, 360, 10, 1, true);
	}
	public void makePipe(int x, int y) {
		Pipe p = new Pipe();
		p.setX(x);
		p.setY(y);;
		add(p);
	}
	public void makeBricks(int x, int y, int height, int amt, boolean unb) {
		int inX = x;
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < amt; i++) {
				Brick b = new Brick(unb);
				b.setX(x);
				b.setY(y);
				add(b);
				x+= b.getWidth();
			}
			y -= 30;
			x = inX;
		}
	}
	public void makeUBricks(int x, int y, int height, int amt) {
		int inX = x;
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < amt; i++) {
				UnbreakaBlock b = new UnbreakaBlock();
				b.setX(x);
				b.setY(y);
				add(b);
				x+= b.getWidth();
			}
			y -= new UnbreakaBlock().getHeight();
			x = inX;
		}
	}
	public void newQBlock(int x, int y) {
		QuestionBlock b = new QuestionBlock();
		b.setX(x);
		b.setY(y);
		add(b);
	}
	
	public void goomba(int x, int y) {
		Goomba goomba = new Goomba();
		goomba.setX(x);
		goomba.setY(y);
		add(goomba);
	}
	public void koopaTroopa(int x, int y) {
		KoopaTroopa kt = new KoopaTroopa(true);
		kt.setX(x);
		kt.setY(y);
		add(kt);
	}
	public void moveAll(int xDir, int yDir) {
		for (Node n : getChildren()) {
			if (n != mario) {
				((Actor) n).move(xDir, yDir);
			}
		}
	}

}
