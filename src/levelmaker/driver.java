package levelmaker;

import javafx.application.Application;
import javafx.event.EventHandler;
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
import mario.Goomba;
import mario.KoopaTroopa;
import mario.MarioPlayer;
import mario.MarioWorld;

public class driver extends Application {
	BorderPane root;
	LevelWorld w = new LevelWorld();
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Arcade Engine Test App");
		root = new BorderPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		root.setCenter(w);
		root.getChildren().add(b);
		stage.setResizable(false);
		stage.show();
		
		root.setOnMousePressed(new MyMouseEventHandler());
		root.setOnMouseDragged(new MyMouseEventHandler());
		root.setOnKeyPressed(new MyKeyEventHandler());
	}

	public static void main(String[] args) {
		launch(args);
	}
	boolean isBlock = true;
	boolean isEnemy = false;
	boolean isPlayer = false;
	Block b = new Brick(false);
	Enemy e = new Goomba();
	MarioPlayer m = new MarioPlayer();
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
	 * left arrow: scroll view left
	 * right arrow: scroll view right
	 */
	private class MyKeyEventHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent event) {
			if (event.getCode() == KeyCode.RIGHT) {
				w.moveAll(-30, 0);
			} else if (event.getCode() == KeyCode.LEFT) {
				w.moveAll(30, 0);
			} else {
				System.out.println(event.getText());
				char c = event.getText().charAt(0);
				if (c == 'b') {
					isBlock = true;
					isEnemy = false;
					isPlayer = false;
					b = new Brick(false);
					root.getChildren().remove(1);
					root.getChildren().add(b);
				} else if (c == 'u') {
					isBlock = true;
					isEnemy = false;
					isPlayer = false;
					b = new UnbreakaBlock();
					root.getChildren().remove(1);
					root.getChildren().add(b);
				} else if (c == 'q') {
					isBlock = true;
					isEnemy = false;
					isPlayer = false;
					b = new QuestionBlock();
					root.getChildren().remove(1);
					root.getChildren().add(b);
				} else if (c == 'p') {
					isBlock = true;
					isEnemy = false;
					isPlayer = false;
					b = new Pipe(1, 1);
					root.getChildren().remove(1);
					root.getChildren().add(b);
				} else if (c == 'g') {
					isBlock = false;
					isEnemy = true;
					isPlayer = false;
					e = new Goomba();
					
					root.getChildren().remove(1);
					root.getChildren().add(e);
				} else if (c == 'k') {
					isBlock = false;
					isEnemy = true;
					isPlayer = false;
					e = new KoopaTroopa(false);
					root.getChildren().remove(1);
					root.getChildren().add(e);
				} else if (c == 'm') {
					isBlock = false;
					isEnemy = false;
					isPlayer = true;
					root.getChildren().remove(1);
					root.getChildren().add(m);
				}
			}
		}
	}
	
	private class MyMouseEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			int x = (int) event.getX() - (int) event.getX()%30;
			int y = (int) event.getY() - (int) event.getY()%30;
			//System.out.println(x + ", " + y);
			if (isBlock) {
				if (b.getClass() == Brick.class) {
					Block block = new Brick(false);
					block.setX(x);
					block.setY(y);
					w.add(block);
				} else if (b.getClass() == Pipe.class) {
					Block block = new Pipe(1, 1);
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
					block.getTimer().stop();
					w.add(block);
				}
			} else if (isPlayer) {
				MarioPlayer block = new MarioPlayer();
				block.setX(x);
				block.setY(y);
				w.add(block);
			}
			
		}
		
	}
}
