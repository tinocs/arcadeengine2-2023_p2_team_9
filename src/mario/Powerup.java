/**
 * Aryan Ajit
 * Period 2
 * Jun 6, 2023
 *
 * Is this code Working:
 */
package mario;

import javafx.scene.image.Image;

public class Powerup extends Enemy{
	private static final String IMG_PREFIX = "gameresources/";
	private static final Image img = new Image(IMG_PREFIX + "shroom.png");
	
	public Powerup() {
		setImage(img);
		getTimer().start();
	}
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		move(-7,0);
	}

}
