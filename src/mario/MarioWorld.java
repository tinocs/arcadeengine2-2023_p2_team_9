package mario;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.Scanner;

import engine.Actor;
import engine.World;
import javafx.scene.Node;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MarioWorld extends World {
	private final static int MARIO_POS = 60;
	MarioPlayer mario;
	int w = 870;
	int h = 510;
	public int playerLOffset = 300;
	public int playerROffset = 500;
	
	MediaView mediaView;
	
	int levelOn = 1;
	
	UnbreakaBlock end = new UnbreakaBlock();
	File f = new File("Level1.txt");
	public MarioWorld() {
		playMusic("tgtf.mp3");
		end.setX(5730);
		end.setY(0);
		add(end);
		setPrefSize(w, h);
	}

	@Override
	public void act(long now) {
		if (!mario.getDead()) {
			if (mario.getX() > w - playerROffset && mario.isGoingRight()&& mario.getRightBlockIntersections() <= 1 && mario.getTopBlockIntersections() <= 1) {
				moveAll((int)-mario.getSpeed(), 0, false);
			} else if (mario.getX() < playerLOffset && mario.isGoingLeft() &&mario.getLeftBlockIntersections() <= 1 && mario.getTopBlockIntersections() <= 1) {
				moveAll((int)mario.getSpeed(), 0, false);
			}
		}
		if (end.getX() <= 870 && end.getX() % 30 == 0.0) {
			System.out.println(end.getX());
		}
	}
	
	@Override
	public void onDimensionsInitialized() {
		
		
		try {
			loadWorld(f);
		} catch (Exception e) { 
			System.out.println("File not found."); 
			e.printStackTrace();
		}
		
		
		start();
	}
	
	public void loadWorld(File f) throws Exception {
		Scanner scan = new Scanner(f);
		
		this.getChildren().removeAll(getChildren());
		
		while (scan.hasNext()) {
			String str = scan.nextLine();
			double x = Double.parseDouble(str.substring(0, str.indexOf(',')));
			double y = Double.parseDouble(str.substring(str.indexOf(',')+1, str.indexOf(':')));
			String actorClass = str.substring(str.indexOf(':')+1) ;
			//System.out.println("adding at " + x + ", " + y + " class is " + actorClass);
			Actor a = null;
			Random r = new Random();
			int rand = r.nextInt(1);
			if (actorClass.equals("Brick")) {
				if (rand == 0) {
					a = new Brick(false);
				} else {
					a = new Brick(true);
				}
			} else if (actorClass.equals("QuestionBlock")) {
				a = new QuestionBlock();
			} else if (actorClass.equals("UnbreakaBlock")) {
				a = new UnbreakaBlock();
			} else if (actorClass.equals("Pipe")) {
				a = new Pipe(1);
			} else if (actorClass .equals("ExtendPipe")) {
				a = new ExtendPipe(1);
			}else if (actorClass.equals("KoopaTroopa")) {
				if (rand == 0) {
					a = new KoopaTroopa(false);
				} else {
					a = new KoopaTroopa(true);
				}
			} else if (actorClass.equals("MarioPlayer")) {
				mario = new MarioPlayer();
				mario.setX(x);
				mario.setY(y);
				add(mario);
			} else {
				a = new Goomba();
			}
			if (a != null) {
				a.setX(x);
				a.setY(y);
				add(a);
				if (a.getClass() == Brick.class || a.getClass() == Pipe.class || a.getClass() == ExtendPipe.class
						|| a.getClass() == QuestionBlock.class|| a.getClass() == UnbreakaBlock.class) {
					a.getTimer().stop();
				}
			}
			
		}
		scan.close();
		//System.out.println("file found!");
	}
	
	public void testWorld() {
		int x = 0;
		int y = (int) (getHeight() - 30);
		
		mario = new MarioPlayer();
		mario.setX(MARIO_POS);
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
		
		koopaTroopa(350, 360);
		Powerup p = new Powerup();
		p.setX(300);
		p.setY(100);
		add(p);
		
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
		makePipe(4350, 360,1,3);
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
	public void makePipe(int x, int y, int h, int dir) {
		
		if(dir == 1) {
			for (int i = 0; i < h; i++) {
				ExtendPipe e = new ExtendPipe(dir);
				e.setX(x);
				e.setY(y);
				add(e);
				if(i != h-1) {
					y-=e.getHeight();
				}
				
			}
			
			Pipe p = new Pipe( dir);
			y-=p.getHeight();
			p.setX(x);
			p.setY(y);;
			add(p);
		}else if(dir == 2) {
			for (int i = 0; i < h; i++) {
				ExtendPipe e = new ExtendPipe(dir);
				e.setX(x);
				e.setY(y);
				add(e);
				if(i != h-1) {
					x+=e.getHeight();
				}
				
			}
			Pipe p = new Pipe(dir);
			x+=p.getHeight();
			p.setX(x);
			p.setY(y);;
			add(p);
		}else if(dir == 3) { 
			for (int i = 0; i < h; i++) {
				ExtendPipe e = new ExtendPipe(dir);
				e.setX(x);
				e.setY(y);
				add(e);
				if(i != h-1) {
					x-=e.getHeight();
				}
				
			}
			Pipe p = new Pipe(dir);
			x-=p.getHeight();
			p.setX(x);
			p.setY(y);;
			add(p);
		}
//		
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
	public void moveAll(int xDir, int yDir, boolean moveMario) {
		for (Node n : getChildren()) {
			if (n != mario && !moveMario) {
				((Actor) n).move(xDir, yDir);
			}
		}
	}
	public void playMusic(String url) {
		try {
			mediaView = new MediaView();
			String filename = getClass().getResource(url).toURI().toString();
			Media media = new Media(filename);  
			MediaPlayer player = new MediaPlayer(media);
			
			mediaView.setMediaPlayer(player);
			mediaView.getMediaPlayer().play();
		} catch (URISyntaxException e) {
			System.out.println("syntax exception");
		}
	}
}
