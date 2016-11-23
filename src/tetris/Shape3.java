/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

/**
 * <pre>
 * xox
 * x
 * </pre>
 */
public class Shape3 extends Shape {
  private static final int[][] RELATIVE_BLOCKS_RELATIVE_POS = { { -1, 0 }, // Block
                                                                           // 1
      { 1, 0 }, // Block 2
      { -1, 1 } }; // Block 3

  public Shape3(Board boardReferenceCopy) {
    super(Config.SHAPE_3_COLOR, RELATIVE_BLOCKS_RELATIVE_POS, boardReferenceCopy);
  }
}
