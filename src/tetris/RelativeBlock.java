/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

import java.awt.Color;

/**
 * A Shape has 3 relative Blocks which rotate around the Center-Block when the
 * user attempts to rotate the shape.
 */
public class RelativeBlock extends Block {
  private int relativePosX, relativePosY; // in relation to the Center-Block of
                                          // the shape
  private Block centerBlock; // relative to this block

  /**
   * Will also compute the actual absolutePositions.
   */
  public RelativeBlock(Color color, int relativePosX, int relativePosY, Block centerBlock) {
    super(color, 0, 0); // 0, 0 are just tmp dummy values

    assert centerBlock != null;
    assert validRelativePos(relativePosX, relativePosY);

    this.relativePosX = relativePosX;
    this.relativePosY = relativePosY;
    this.centerBlock = centerBlock;

    this.updateAbsolutePositions();

    assert invariantOfRelativeBlock();
  }

  /**
   * Needs to be called when the Center-Block moves.
   */
  public void updateAbsolutePositions() {
    this.setPosX(centerBlock.getPosX() + relativePosX);
    this.setPosY(centerBlock.getPosY() + relativePosY);
  }

  public void rotate() {
    int tmpRelativePosX = relativePosX;
    relativePosX = -relativePosY;
    relativePosY = tmpRelativePosX;

    updateAbsolutePositions();
  }

  private boolean validRelativePos(int relativePosX, int relativePosY) {
    return relativePosX >= -2 && relativePosX <= 2 && relativePosY >= -2 && relativePosY <= 2;
  }

  protected boolean invariantOfRelativeBlock() {
    return validRelativePos(relativePosX, relativePosY) && invariantOfBlock() && centerBlock != null;
  }

  /**
   * Own implementation, returns a new RelativeBlock-object and not a reference
   * on the existing one.
   */
  protected RelativeBlock cloneOfRelativeBlock(Block newCenterBlock) {
    return new RelativeBlock(this.getColor(), relativePosX, relativePosY, newCenterBlock);
  }
}
