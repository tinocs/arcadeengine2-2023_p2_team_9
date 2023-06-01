/**
 * Aryan Ajit
 * Period 2
 * Jun 1, 2023
 *
 * Is this code Working:
 */
package mario;

import javafx.scene.image.Image;

public class ExtendPipe extends Block{
	private static final String IMG_PREFIX = "gameresources/";
	private static final Image img = new Image(IMG_PREFIX + "extendPipe.png");
	private static final Image right = new Image(IMG_PREFIX + "R_extendPipe.png");
	private static final Image left = new Image(IMG_PREFIX + "L_extendPipe.png");

	public ExtendPipe(int dir) {
		if(dir == 2) {
			setImage(right);
		}else if(dir == 3) { 
			setImage(left);
		}
		setImage(img);
		getTimer().start();
	}
}
