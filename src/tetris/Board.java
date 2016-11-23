/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Board {
  private Block[][] blocks;
  private ArrayList<Integer> fullRows;

  public Board() {
    blocks = new Block[Config.BLOCKS_Y][Config.BLOCKS_X];
    fullRows = new ArrayList<Integer>();
  }

  /**
   * @param fullRowsBlinkColor
   *          pass null for normal block painting, pass a Color if you want to
   *          paint full rows in a special color
   */
  public void paint(Graphics2D graphics2D, boolean blinks) {
    for (int i = 0; i < blocks.length; i++) {
      for (int j = 0; j < blocks[0].length; j++) {
        if (blocks[i][j] != null) {
          if (blinks && fullRows.contains(i))
            blocks[i][j].paint(graphics2D, Config.FULL_ROW_BLINK_COLOR);
          else
            blocks[i][j].paint(graphics2D);
        }
      }
    }
  }

  /**
   * Is called if the block could not be moved down due to a collision.
   * 
   * @return true if there are full rows after adding the shape to the board.
   */
  public boolean addShapeToBoard(Shape shape) {
    addBlockToBoard(shape.getCenterBlock());

    for (int i = 0; i < shape.getRelativeBlocks().length; i++) {
      addBlockToBoard(shape.getRelativeBlocks()[i]);
    }

    return computeFullRows();
  }

  private void addBlockToBoard(Block block) {
    if (isBlockWithinBoardArray(block))
      blocks[block.getPosY()][block.getPosX()] = block.cloneOfBlock();
  }

  private boolean isBlockWithinBoardArray(Block block) {
    return block.getPosY() >= 0 && block.getPosY() < Config.BLOCKS_Y && block.getPosX() >= 0
        && block.getPosX() < Config.BLOCKS_X;
  }

  /**
   * Checks for all kinds of collisions (borders as well as the occupied
   * Board-Blocks).
   * 
   * @return true if a collision occured.
   */
  public boolean checkShapeCollision(Shape shape) {
    boolean collides = collidesWithBorders(shape) || collidesWithExistingBlocks(shape);

    return collides;
  }

  /**
   * @return true if collision detected
   */
  private boolean collidesWithBorders(Shape shape) {
    boolean collides = collidesWithBorders(shape.getCenterBlock());

    for (int i = 0; i < shape.getRelativeBlocks().length; i++) {
      collides = collides || collidesWithBorders(shape.getRelativeBlocks()[i]);
    }

    return collides;
  }

  /**
   * @return true if collision detected
   */
  private boolean collidesWithBorders(Block block) {
    return block.getPosX() < 0 || block.getPosX() >= Config.BLOCKS_X || block.getPosY() >= Config.BLOCKS_Y;
  }

  /**
   * @return true if collision detected
   */
  private boolean collidesWithExistingBlocks(Shape shape) {
    boolean collides = collidesWithExistingBlock(shape.getCenterBlock());

    for (int i = 0; i < shape.getRelativeBlocks().length; i++) {
      collides = collides || collidesWithExistingBlock(shape.getRelativeBlocks()[i]);
    }

    return collides;
  }

  /**
   * @return true if collision detected
   */
  private boolean collidesWithExistingBlock(Block block) {
    if (isBlockWithinBoardArray(block)) {
      if (blocks[block.getPosY()][block.getPosX()] != null)
        return true;
    }

    return false;
  }

  /**
   * Creates the private ArrayList of fullRows.
   * 
   * @return true if there are one or more full rows, false otherwise.
   */
  private boolean computeFullRows() {
    for (int i = Config.BLOCKS_Y - 1; i >= 0; i--) { // iterate over all rows
      if (isRowFull(i)) {
        fullRows.add(new Integer(i));
      }
    }

    return fullRows.size() >= 1;
  }

  private boolean isRowFull(int row) {
    for (int j = 0; j < Config.BLOCKS_X; j++) { // iterate over all collumns of
                                                // a certain row
      if (blocks[row][j] == null)
        return false;
    }

    return true;
  }

  public void deleteFullRows() {
    int grabBlocksOffset = 0;
    // von unten nach oben Zeilen iterieren
    for (int i = Config.BLOCKS_Y - 1; i >= 0 + fullRows.size(); i--) {
      while (grabBlocksOffset < fullRows.size() && fullRows.get(grabBlocksOffset) == i - grabBlocksOffset) {
        grabBlocksOffset++;
      }
      copyRow(i, grabBlocksOffset);
    }
    makeNullRows();
  }

  private void copyRow(int copyIntoRow, int grabBlocksOffset) {
    for (int j = 0; j < Config.BLOCKS_X; j++) {
      if (blocks[copyIntoRow - grabBlocksOffset][j] == null)
        blocks[copyIntoRow][j] = null;
      else
        blocks[copyIntoRow][j] = new Block(blocks[copyIntoRow - grabBlocksOffset][j].getColor(), j, copyIntoRow);
    }
  }

  /**
   * Generates nullRows at the top of the board. Amount = fullRows.size()
   */
  private void makeNullRows() {
    for (int i = 0; i < fullRows.size(); i++) {
      for (int j = 0; j < Config.BLOCKS_X; j++) {
        blocks[i][j] = null;
      }
    }

    fullRows.clear();
  }
}
