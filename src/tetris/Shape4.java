/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

/**
 * <pre>
 * xox
 *   x
 * </pre>
 */
public class Shape4 extends Shape {
	private static final int[][] RELATIVE_BLOCKS_RELATIVE_POS = {{-1, 0},  //Block 1
																 {1, 0},  //Block 2
																 {1, 1}}; //Block 3

	public Shape4(Board boardReferenceCopy) {
		super(Config.SHAPE_4_COLOR, RELATIVE_BLOCKS_RELATIVE_POS, boardReferenceCopy);
	}
}

