/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

/**
 * <pre>
 * xox
 *  x
 * </pre>
 */
public class Shape7 extends Shape {
  private static final int[][] RELATIVE_BLOCKS_RELATIVE_POS = { { -1, 0 }, // Block
                                                                           // 1
      { 1, 0 }, // Block 2
      { 0, 1 } }; // Block 3

  public Shape7(Board boardReferenceCopy) {
    super(Config.SHAPE_7_COLOR, RELATIVE_BLOCKS_RELATIVE_POS, boardReferenceCopy);
  }
}
