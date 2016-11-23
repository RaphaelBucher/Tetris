/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

import java.awt.Color;

public class CustomRotatableShape extends Shape {
	private int rotationState; //0 bis 3 beginnend bei 0
	private int savedStateRotationState; //for rollbacks (collisions)
	
	public CustomRotatableShape(Color blockColor, int[][] relBlocksRelPos, Board boardReferenceCopy) {
		super(blockColor, relBlocksRelPos, boardReferenceCopy);
		
		/*
		 * da die Corrections die gleichen sind wie bei Shape5 und Shape6, lediglich um eins verschoben
		 */
		if (this instanceof Shape2)
			rotationState = 3; 
	}
	
	/**
	 * Die Version für Shape 2, Shape5 und Shape6
	 */
	public boolean tryRotate() {
		saveShapeState();
		rotate();
		
		rotateCorrections();
		
		rotationState++;
		if (rotationState % 4 == 0)
			rotationState = 0;
		
		boolean rotated = !checkCollision();
		assert invariant();
		return rotated;
	}
	
	private void rotateCorrections() {
		assert rotationState >= 0 && rotationState <= 3;
		
		switch (rotationState) {
			case 0:
				move1Right();
				break;
			case 1: 
				move1Left();
				move1Down();
				break;
			case 2:
				move1Up();
				break;
			case 3: 
				break;
		}
	}
	
	protected void saveShapeState() {
		super.saveShapeState();
		savedStateRotationState = rotationState;
	}
	
	protected void rollbackToSavedShapeState() {
		super.rollbackToSavedShapeState();
		rotationState = savedStateRotationState;
	}
}
