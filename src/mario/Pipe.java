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
	//images
		private static final String IMG_PREFIX = "gameresources/";
		private static final Image img  = new Image(IMG_PREFIX + "topPipe.png");
	
	public Pipe() {
		setImage(img);
		getTimer().start();
	}
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
	}

}
