/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

/**
 * <pre>
 * ox
 * xx
 * </pre>
 */
public class Shape1 extends Shape { // x, y
  private static final int[][] RELATIVE_BLOCKS_RELATIVE_POS = { { 1, 0 }, // Block
                                                                          // 1
      { 0, 1 }, // Block 2
      { 1, 1 } }; // Block 3

  public Shape1(Board boardReferenceCopy) {
    super(Config.SHAPE_1_COLOR, RELATIVE_BLOCKS_RELATIVE_POS, boardReferenceCopy);
  }

  /**
   * Empty method since this shape can't really be rotated.
   * 
   * @return returns true if the shape was rotated, false otherwise.
   */
  public boolean tryRotate() {
    return true;
  }
}
