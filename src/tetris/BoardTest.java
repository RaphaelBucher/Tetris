/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	private Shape shape4;
	private Board board;
	
	@Before
	public void setUp() {
		board = new Board();
		shape4 = new Shape4(board);
	}
	
	@Test
	public void collision() {
		assertTrue(shape4.tryMove1Left());
		assertTrue(shape4.tryMove1Left());
		assertTrue(shape4.tryMove1Left());
		assertFalse(shape4.tryMove1Left()); //collision
	}
}
