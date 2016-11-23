/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TastaturAdapter extends KeyAdapter {
	private boolean vkDownPressed;
	
	//CARE: Zeitliche Aufrufe sind wie im "OS-Style". => dummy-println hier rein zum Testen und Taste halten
	public void keyPressed(KeyEvent keyEvent) {
		int key = keyEvent.getKeyCode();
		
		if (key == KeyEvent.VK_UP) {
			Main.getGame().getShape().tryRotate();
		}
		
		if (key == KeyEvent.VK_LEFT) {
			Main.getGame().getShape().tryMove1Left();
		}
		
		if (key == KeyEvent.VK_RIGHT) {
			Main.getGame().getShape().tryMove1Right();
		}
		
		if (key == KeyEvent.VK_DOWN) {
			vkDownPressed = true;
		}
	}
	
	public void keyReleased(KeyEvent keyEvent) {
		int key = keyEvent.getKeyCode();
		
		if (key == KeyEvent.VK_DOWN) {
			vkDownPressed = false;
		}
	}
	
	public boolean isVkDownPressed() {
		return vkDownPressed;
	}
}


