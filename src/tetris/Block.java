/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Superclass of CenterBlock and RelativeBlock.
 */
public class Block {
  private Color color;
  private int posX, posY; // starting at 0, 1, ..., the absolute position inside
                          // the Board-Matrix

  public Block(Color color, int posX, int posY) {
    assert color != null;
    assert validPosX(posX);
    assert validPosY(posY);

    this.posX = posX;
    this.posY = posY;
    this.color = color;

    assert invariantOfBlock();
  }

  public void paint(Graphics2D graphics2D) {
    graphics2D.setColor(color);
    paintBlock(graphics2D);
  }

  /**
   * @param tmpPaintColor
   *          paints the blocks temporarily in this color and not in their
   *          blocks-color
   */
  public void paint(Graphics2D graphics2D, Color tmpPaintColor) {
    graphics2D.setColor(tmpPaintColor);
    paintBlock(graphics2D);
  }

  private void paintBlock(Graphics2D graphics2D) {
    graphics2D.fillRect(posX * Config.BLOCK_SIZE + Config.BLOCK_DRAWING_INSETS,
        posY * Config.BLOCK_SIZE + Config.BLOCK_DRAWING_INSETS, Config.BLOCK_SIZE - (2 * Config.BLOCK_DRAWING_INSETS),
        Config.BLOCK_SIZE - (2 * Config.BLOCK_DRAWING_INSETS));
  }

  // needed to even out rotations as they are in the original Tetris
  public void move1Up() {
    posY--;
  }

  public void move1Down() {
    posY++;
  }

  public void move1Left() {
    posX--;
  }

  public void move1Right() {
    posX++;
  }

  Color getColor() {
    return color;
  }

  public int getPosX() {
    return posX;
  }

  public void setPosX(int posX) {
    this.posX = posX;
    assert invariantOfBlock();
  }

  public int getPosY() {
    return posY;
  }

  public void setPosY(int posY) {
    this.posY = posY;
    assert invariantOfBlock();
  }

  private boolean validPosX(int posX) {
    return posX >= -2 && posX < Config.BLOCKS_X + 2;
  }

  private boolean validPosY(int posY) {
    return posY >= -2 && posY < Config.BLOCKS_Y + 2; // -2 ist erlaubt (instant
                                                     // rota bei neuem shape)
  }

  protected boolean invariantOfBlock() {
    return validPosX(this.posX) && validPosY(this.posY);
  }

  /**
   * Own implementation, returns a new Block-object and not a reference on the
   * existing one.
   */
  protected Block cloneOfBlock() {
    return new Block(color, posX, posY);
  }
}
