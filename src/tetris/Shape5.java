/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

/**
 * <pre>
 *  ox
 * xx
 * </pre>
 */
public class Shape5 extends CustomRotatableShape {
  private static final int[][] RELATIVE_BLOCKS_RELATIVE_POS = { { 1, 0 }, // Block
                                                                          // 1
      { -1, 1 }, // Block 2
      { 0, 1 } }; // Block 3

  public Shape5(Board boardReferenceCopy) {
    super(Config.SHAPE_5_COLOR, RELATIVE_BLOCKS_RELATIVE_POS, boardReferenceCopy);

  }
}
