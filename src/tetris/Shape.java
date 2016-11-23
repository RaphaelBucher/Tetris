/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The Superclass of all seven possible Shapes.
 */
public abstract class Shape {
	private Block centerBlock;
	private RelativeBlock[] relativeBlocks;
	private Timer timer;
	private Block savedStateCenterBlock; //for rollbacks in case of collisions
	private RelativeBlock[] savedStateRelativeBlocks; //for rollbacks in case of collisions
	private Board boardReferenceCopy; //for the collision-call
	
	public Shape(Color blockColor, int[][] relBlocksRelPos, Board boardReferenceCopy) {
		assert blockColor != null;
		assert relBlocksRelPos != null;
		assert relBlocksRelPos.length == Config.SHAPEBLOCKS_AMOUNT - 1;
		
		centerBlock = new Block(blockColor, (Config.BLOCKS_X - 1) / 2, 0);
		relativeBlocks = new RelativeBlock[Config.SHAPEBLOCKS_AMOUNT - 1]; //-1 CenterBlock
		
		relativeBlocks[0] = new RelativeBlock(blockColor, relBlocksRelPos[0][0], relBlocksRelPos[0][1], centerBlock);
		relativeBlocks[1] = new RelativeBlock(blockColor, relBlocksRelPos[1][0], relBlocksRelPos[1][1], centerBlock);
		relativeBlocks[2] = new RelativeBlock(blockColor, relBlocksRelPos[2][0], relBlocksRelPos[2][1], centerBlock);
		
		savedStateRelativeBlocks = new RelativeBlock[relativeBlocks.length];
		this.boardReferenceCopy = boardReferenceCopy;
		
		timer = new Timer(Config.SHAPE_AUTOMATIC_SINK_RATE_MILLIS, false);
		
		assert invariant();
	}
	
	public void paint(Graphics2D graphics2D) {
		assert graphics2D != null;
		
		centerBlock.paint(graphics2D);
		
		for (int i = 0; i < relativeBlocks.length; i++) {
			relativeBlocks[i].paint(graphics2D);
		}
	}
	
	/**
	 * @return true if collided
	 */
	protected boolean checkCollision() {
		if (boardReferenceCopy.checkShapeCollision(this)) {
			rollbackToSavedShapeState();
			return true;
		}
		return false;
	}
	
	/**
	 * Shapes 3, 4 and 7 will use this default-implementation ("normal" rotation as expected).
	 * newX = -oldY, newY = oldX
	 * Shapes 1 and 2 use their overwritten version.
	 * @return returns true if the shape was rotated, else returns false.
	 */
	public boolean tryRotate() {
		saveShapeState();
		rotate();
		
		boolean rotated = !checkCollision();
		
		assert invariant();
		return rotated;
	}
	
	/**
	 * Should not be called outside of shape-classes.
	 */
	protected void rotate() {
		for (int i = 0; i < relativeBlocks.length; i++) {
			relativeBlocks[i].rotate();
		}
	}
	
	/**
	 * Should not be called outside of shape-classes.
	 */
	protected void move1Up() {
		centerBlock.move1Up();
		updateRelativeBlocks();
	}
	
	/**
	 * @return true if the Shape was moved, false otherwise
	 */
	public boolean tryMove1Down() {
		saveShapeState();
		move1Down();
		
		boolean rotated = !checkCollision();
		
		assert invariant();
		return rotated;
	}
	
	/**
	 * Should not be called outside of shape-classes.
	 */
	protected void move1Down() {
		centerBlock.move1Down();
		updateRelativeBlocks();
	}
	
	/**
	 * @return true if the Shape was moved, false otherwise
	 */
	public boolean tryMove1Left() {
		saveShapeState();
		move1Left();
		
		boolean rotated = !checkCollision();
		
		assert invariant();
		return rotated;
	}
	
	/**
	 * Should not be called outside of shape-classes.
	 */
	protected void move1Left() {
		centerBlock.move1Left();
		updateRelativeBlocks();
	}
	
	/**
	 * @return true if the Shape was moved, false otherwise
	 */
	public boolean tryMove1Right() {
		saveShapeState();
		move1Right();
		
		boolean rotated = !checkCollision();
		
		assert invariant();
		return rotated;
	}
	
	/**
	 * Should not be called outside of shape-classes.
	 */
	protected void move1Right() {
		centerBlock.move1Right();
		updateRelativeBlocks();
	}
	
	/**
	 * The automatic sinking of the shape. This method is called once every Game.update()
	 * = once every frame.
	 * @return returns false only if the shape wanted to move but could not due to a collision, true otherwise
	 * (shape could move or didn't have to move at all (not yet "movetime" by the timer).
	 */
	public boolean sink(boolean vkDownPressed) {
		if (!timer.getStartsInstant() && vkDownPressed)
			timer = new Timer(Config.SHAPE_PLAYER_SINK_RATE_MILLIS, true);
		
		if (timer.getStartsInstant() && !vkDownPressed)
			timer = new Timer(Config.SHAPE_AUTOMATIC_SINK_RATE_MILLIS, false);
		
		if (timer.intervallSurpassed()) {
			return tryMove1Down(); 
		}
		
		return true; //wasn't time to move the shape at all
	}
	
	private void updateRelativeBlocks() {
		for (int i = 0; i < relativeBlocks.length; i++) {
			relativeBlocks[i].updateAbsolutePositions();
		}
	}
	
	protected boolean invariant() {
		boolean invariantHolds = centerBlock.invariantOfBlock();
		
		for (int i = 0; i < relativeBlocks.length; i++) {
			invariantHolds = invariantHolds && relativeBlocks[i].invariantOfRelativeBlock();
		}
		
		return invariantHolds;
	}

	public Block getCenterBlock() {
		return centerBlock;
	}

	public RelativeBlock[] getRelativeBlocks() {
		return relativeBlocks;
	}
	
	/**
	 * Called before every move (including rotation).
	 */
	protected void saveShapeState() {
		savedStateCenterBlock = centerBlock.cloneOfBlock();
		
		for (int i = 0; i < savedStateRelativeBlocks.length; i++) {
			savedStateRelativeBlocks[i] = relativeBlocks[i].cloneOfRelativeBlock(savedStateCenterBlock);
		}
	}
	
	/**
	 * Called in case of occurance of a collision.
	 */
	protected void rollbackToSavedShapeState() {
		centerBlock = savedStateCenterBlock;
		
		for (int i = 0; i < relativeBlocks.length; i++) {
			relativeBlocks[i] = savedStateRelativeBlocks[i];
		}
	}
}
