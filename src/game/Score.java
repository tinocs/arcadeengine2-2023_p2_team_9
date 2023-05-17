package game;

import javafx.scene.text.Font;
import javafx.scene.text.Text; 

public class Score extends Text {
	private int score;
	
	public Score() {
		score = 0;
		setFont(new Font(20));
		updateDisplay();
	}
	public void updateDisplay() {
		setText(score+"");
	}
	
	public int getScoreValue() {
		return score;
	}
	
	public void setScoreValue(int newscore) {
		score = newscore;
		updateDisplay();
	}
}
