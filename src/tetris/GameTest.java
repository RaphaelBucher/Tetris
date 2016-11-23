/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

import static org.junit.Assert.*;

import java.awt.Dimension;

import org.junit.Before;
import org.junit.Test;

public class GameTest {
	private Game game;
	private Main main;

	@Before
	public void setUp() {
		game = new Game();
		main = new Main(game);
	}

	@Test
	public void dimension() {
		Dimension expectedDimension = new Dimension(Config.WINDOW_X, Config.WINDOW_Y);
		Dimension panelDimension = game.getSize();
		assertEquals(expectedDimension, panelDimension);
		
		expectedDimension = new Dimension(Config.WINDOW_X + main.getInsets().left +	main.getInsets().right,
				Config.WINDOW_Y + main.getInsets().top + main.getInsets().bottom);
		Dimension frameDimension = main.getSize(); //inkl. Fensterränder vom OS
		assertEquals(expectedDimension, frameDimension);
	}
}
