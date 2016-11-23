/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class RelativeBlockTest {
  RelativeBlock relativeBlock;
  Block centerBlock;

  @Before
  public void setUp() {
    centerBlock = new Block(Color.ORANGE, 5, 0);
    relativeBlock = new RelativeBlock(Color.ORANGE, 2, 1, centerBlock);
  }

  @Test
  public void validInitialAbsolutePos() {
    this.setUp();
    assertEquals(relativeBlock.getPosX(), 7);
    assertEquals(relativeBlock.getPosY(), 1);
  }
}
