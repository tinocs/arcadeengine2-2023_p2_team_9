package levelmaker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

import engine.Actor;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mario.Block;
import mario.Brick;
import mario.UnbreakaBlock;
import mario.QuestionBlock;
import mario.Pipe;
import mario.Enemy;
import mario.ExtendPipe;
import mario.Goomba;
import mario.KoopaTroopa;
import mario.MarioPlayer;
import mario.MarioWorld;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class driver extends Application {
	static int save = 1;
	
	int displacement;
	private static final String NAME = "Level1";
	
	MediaView mediaView;
	BorderPane root;
	LevelWorld w = new LevelWorld();
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Level Maker");
		root = new BorderPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		root.setCenter(w);
		root.getChildren().add(b);
		stage.setResizable(false);
		
		//playMusic("tui.mp3");
		
		createWall(-240);
		createWall(5400);
		initializeBoundary();
		stage.show();
		
		root.setOnMousePressed(new MyMouseEventHandler());
		root.setOnMouseDragged(new MyMouseEventHandler());
		root.setOnKeyPressed(new MyKeyEventHandler());
	}

	public static void main(String[] args) {
		
		launch(args);
	}
	UnbreakaBlock leftPass = new UnbreakaBlock();
	UnbreakaBlock rightPass = new UnbreakaBlock();
	boolean toRemove = false;
	boolean isBlock = true;
	boolean isEnemy = false;
	boolean isPlayer = false;
	boolean playerOver = false;
	Block b = new Brick(false);
	Enemy e = new Goomba();
	MarioPlayer m = new MarioPlayer();
	MarioPlayer toAdd = new MarioPlayer();
	public void createWall(int startX) {
		for (int x = startX; x <= startX + 300; x+=30) {
			for (int y = 0; y <= 480; y+=30) {
				UnbreakaBlock block = new UnbreakaBlock();
				block.setX(x);
				block.setY(y);
				w.add(block);
			}
		}
	}
	public void initializeBoundary() {
		leftPass.setX(-240);
		leftPass.setY(0);
		w.add(leftPass);
		rightPass.setX(5700);
		rightPass.setY(0);
		w.add(rightPass);
	}
	/*
	 * b: brick
	 * u: unbreakabrick
	 * q: question block
	 * p: pipe
	 * 
	 * g: goomba
	 * k: koopa troopa
	 * 
	 * m: mario
	 * 
	 * r: remove
	 * left arrow: scroll view left
	 * right arrow: scroll view right
	 * 
	 * s: save
	 */
	private class MyKeyEventHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent event) {
			if (event.getCode() == KeyCode.RIGHT && rightPass.getX() >= 870) {
				w.moveAll(-30, 0);
			} else if (event.getCode() == KeyCode.LEFT && leftPass.getX() <= -60) {
				//System.out.println(leftPass.isVisible());
				w.moveAll(30, 0);
			} else if (event.getCode() != KeyCode.LEFT && event.getCode() != KeyCode.RIGHT){
				char c = event.getText().charAt(0);
				
				if (c == 's') {
					File f = new File(NAME + ".txt");
					displacement = 180 - (int)toAdd.getX();
					
					try {
						FileWriter write = new FileWriter(f);
						for (Node a : w.getChildren()) {
							Actor actor = (Actor) a;
							String toWrite = (displacement + actor.getX()) + "," + actor.getY() + ":" + actor.getClass().toString().substring(12);
							write.write(toWrite + "\n");
						}
						write.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (c == 'b') {
					isBlock = true;
					isEnemy = false;
					isPlayer = false;
					toRemove = false;
					b = new Brick(false);
					root.getChildren().remove(1);
					root.getChildren().add(b);
				} else if (c == 'u') {
					isBlock = true;
					isEnemy = false;
					isPlayer = false;
					toRemove = false;
					b = new UnbreakaBlock();
					root.getChildren().remove(1);
					root.getChildren().add(b);
				} else if (c == 'q') {
					isBlock = true;
					isEnemy = false;
					isPlayer = false;
					toRemove = false;
					b = new QuestionBlock();
					root.getChildren().remove(1);
					root.getChildren().add(b);
				} else if (c == 'p') {
					isBlock = true;
					isEnemy = false;
					isPlayer = false;
					toRemove = false;
					b = new Pipe(1);
					root.getChildren().remove(1);
					root.getChildren().add(b);
				} else if (c == 'g') {
					isBlock = false;
					isEnemy = true;
					isPlayer = false;
					toRemove = false;
					e = new Goomba();
					
					root.getChildren().remove(1);
					root.getChildren().add(e);
				} else if (c == 'k') {
					isBlock = false;
					isEnemy = true;
					isPlayer = false;
					toRemove = false;
					e = new KoopaTroopa(false);
					root.getChildren().remove(1);
					root.getChildren().add(e);
				} else if (c == 'm' && !playerOver) {
					isBlock = false;
					isEnemy = false;
					isPlayer = true;
					toRemove = false;
					root.getChildren().remove(1);
					root.getChildren().add(m);
				} else if (c == 'r') {
					isBlock = false;
					isEnemy = false;
					isPlayer = false;
					toRemove = true;
					root.getChildren().get(1).resize(1, 1);
				}
			}
		}
	}
	
	
	private class MyMouseEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			int x = (int) event.getX() - (int) event.getX()%30;
			int y = (int) event.getY() - (int) event.getY()%30;
			//System.out.print("From" + x + ", " + y + " ---> ");
			removeObjectAt(x, y);
			//System.out.println(x + ", " + y);
			if (isBlock) {
				if (b.getClass() == Brick.class) {
					Block block = new Brick(false);
					block.setX(x);
					block.setY(y);
					w.add(block);
				} else if (b.getClass() == Pipe.class) {
					Block block;
					if (getObjectAt(x, y - 30) != null && (getObjectAt(x, y - 30).getClass() == Pipe.class || getObjectAt(x, y - 30).getClass() == ExtendPipe.class)) {
						block = new ExtendPipe(1);
					} else {
						block = new Pipe(1);
					}
					
					if (getObjectAt(x, y + 30) != null && getObjectAt(x, y + 30).getClass() == Pipe.class) {
						removeObjectAt(x, y + 30);
						ExtendPipe p = new ExtendPipe(1);
						p.setX(x);
						p.setY(y+30);
						w.add(p);
					}
					block.setX(x);
					block.setY(y);
					w.add(block);
				} else if (b.getClass() == QuestionBlock.class) {
					Block block = new QuestionBlock();
					block.setX(x);
					block.setY(y);
					w.add(block);
				} else if (b.getClass() == UnbreakaBlock.class) {
					Block block = new UnbreakaBlock();
					block.setX(x);
					block.setY(y);
					w.add(block);
				}
			} else if (isEnemy) {
				if (e.getClass() == Goomba.class) {
					Enemy block = new Goomba();
					block.setX(x);
					block.setY(y);
					w.add(block);
					block.getTimer().stop();
				} else if (e.getClass() == KoopaTroopa.class) {
					Enemy block = new KoopaTroopa(false);
					block.setX(x);
					block.setY(y);
					w.add(block);
					block.getTimer().stop();
				}
			} else if (isPlayer && !playerOver) {
				toAdd.setX(x);
				toAdd.setY(y);
				w.add(toAdd);
				System.out.println(x);
				toAdd.getTimer().stop();
				playerOver = true;
			} else if (toRemove) {
				removeObjectAt(x, y);
			}
			
		}
		
	}
	
	public Actor getObjectAt(int x, int y) {
		for (Node a : w.getChildren()) {
			if (((Actor)a).getX() == x && ((Actor)a).getY() == y) {
				return (Actor) a;
			}
		}
		return null;
	}
	// need to get actual actor coords (getX() getY() in Actor.java -> mario.package?)
	public void removeObjectAt(int x, int y) {
		for (Node a : w.getChildren()) {
			if (((Actor)a).getX() == x && ((Actor)a).getY() == y) {
				if (a.getClass() == MarioPlayer.class) {
					playerOver = false;
				}
				w.getChildren().remove(a);
				break;
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
