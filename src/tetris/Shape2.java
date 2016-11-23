/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

/**
 * <pre>
 * xoxx
 * </pre>
 */
public class Shape2 extends CustomRotatableShape {
  private static final int[][] RELATIVE_BLOCKS_RELATIVE_POS = { { -1, 0 }, // Block
                                                                           // 1
      { 1, 0 }, // Block 2
      { 2, 0 } }; // Block 3

  public Shape2(Board boardReferenceCopy) {
    super(Config.SHAPE_2_COLOR, RELATIVE_BLOCKS_RELATIVE_POS, boardReferenceCopy);
  }
}
