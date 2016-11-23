/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Shape2Test {
	private Shape shape2;
	private Block centerBlock;
	private RelativeBlock[] relativeBlocks;
	private int startPosX;
	private Board board;

	@Before
	public void setUp() {
		board = new Board();
		shape2 = new Shape2(board);
		centerBlock = shape2.getCenterBlock();
		relativeBlocks = shape2.getRelativeBlocks();
	}

	@Test
	public void scenario() {
		// Constructor-Test
		startPosX = centerBlock.getPosX();
		assertEquals(startPosX, (Config.BLOCKS_X - 1) / 2);
		assertEquals(centerBlock.getPosY(), 0);
		assertEquals(relativeBlocks.length, 3);

		part1();
		assertEquals(centerBlock.getPosX(), startPosX + 2);
		assertEquals(centerBlock.getPosY(), 2);
		assertEquals(relativeBlocks[0].getPosX(), startPosX + 2);
		assertEquals(relativeBlocks[0].getPosY(), 1);
		assertEquals(relativeBlocks[2].getPosY(), 4);

		part2();
		assertEquals(centerBlock.getPosX(), startPosX + 3);
		assertEquals(centerBlock.getPosY(), 2);
		assertEquals(relativeBlocks[0].getPosX(), startPosX + 4);
		assertEquals(relativeBlocks[0].getPosY(), 2);
		assertEquals(relativeBlocks[1].getPosX(), startPosX + 2);
		assertEquals(relativeBlocks[1].getPosY(), 2);

		part3();
		assertEquals(centerBlock.getPosX(), startPosX + 2);
		assertEquals(centerBlock.getPosY(), 4);
		assertEquals(relativeBlocks[0].getPosX(), startPosX + 2);
		assertEquals(relativeBlocks[0].getPosY(), 5);
		assertEquals(relativeBlocks[2].getPosX(), startPosX + 2);
		assertEquals(relativeBlocks[2].getPosY(), 2);
	}

	/**
	 * 2x down, 1x rotate, 2x right
	 */
	private void part1() {
		shape2.tryMove1Down();
		shape2.tryMove1Down();
		shape2.tryRotate();
		shape2.tryMove1Right();
		shape2.tryMove1Right();
	}

	/**
	 * 1x rotate
	 */
	private void part2() {
		shape2.tryRotate();
	}

	/**
	 * 1x rotate, 1x moveDown
	 */
	private void part3() {
		shape2.tryRotate();
		shape2.tryMove1Down();
	}
}
