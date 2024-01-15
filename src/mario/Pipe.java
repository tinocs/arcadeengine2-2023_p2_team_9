/**
 * Aryan Ajit
 * Period 2
 * May 25, 2023
 *
 * Is this code Working:
 */
package mario;

import javafx.scene.image.Image;

public class Pipe extends Block{
	int height;
	int dir;
	//images
		private static final String IMG_PREFIX = "gameresources/";
		private static final Image top  = new Image(IMG_PREFIX + "topPipe.png", 30, 30, false, false);
		private static final Image left  = new Image(IMG_PREFIX + "leftpipe.png", 30, 30, false, false);
		private static final Image right  = new Image(IMG_PREFIX + "rightPipe.png", 30, 30, false, false);
	
	public Pipe(int dir) {
		this.dir = dir;
		
		if(dir == 1) {
			setImage(top);
		}else if(dir == 2) {
			setImage(right);
		}else {
			setImage(left);
		}
		getTimer().start();
	}
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

}


