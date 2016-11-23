/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

/**
 * <pre>
 * xo
 *  xx
 * </pre>
 */
public class Shape6 extends CustomRotatableShape {
  private static final int[][] RELATIVE_BLOCKS_RELATIVE_POS = { { -1, 0 }, // Block
                                                                           // 1
      { 0, 1 }, // Block 2
      { 1, 1 } }; // Block 3

  public Shape6(Board boardReferenceCopy) {
    super(Config.SHAPE_6_COLOR, RELATIVE_BLOCKS_RELATIVE_POS, boardReferenceCopy);
  }
}
