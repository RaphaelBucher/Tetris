/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

import java.awt.Color;

public interface Config {
	int BLOCKS_X = 10; //Spielfeld hat so viele Blöcke in horizontaler Richtung
	int BLOCKS_Y = 18; //Amount of blocks inside the playing field in the vertical direction
	int BLOCK_SIZE = 40; //quadratic blocks
	
	int WINDOW_X = BLOCKS_X * BLOCK_SIZE; //drawable Pixels of the GamePanel
	int WINDOW_Y = BLOCKS_Y * BLOCK_SIZE;
	
	int FPS = 30;
	
	Color BACKGROUND = Color.BLACK;
	int BLOCK_DRAWING_INSETS = 3;
	
	int SHAPE_AMOUNT = 7;
	int SHAPEBLOCKS_AMOUNT = 4; //kann NICHT ohne weiteres (ohne massive? Anpassungengen) verändert werden 
	
	Color SHAPE_1_COLOR =  Color.RED;
	Color SHAPE_2_COLOR =  Color.ORANGE;
	Color SHAPE_3_COLOR =  new Color(30, 60, 255); //Blue, not too dark
	Color SHAPE_4_COLOR =  new Color(255, 0, 255); //Purple
	Color SHAPE_5_COLOR =  new Color(120, 220, 255); //Light-blue
	Color SHAPE_6_COLOR =  Color.GREEN;
	Color SHAPE_7_COLOR =  Color.YELLOW;
	
	int SHAPE_AUTOMATIC_SINK_RATE_MILLIS = 900; //The Shape sinks automatically every ____ Millis.
	int SHAPE_PLAYER_SINK_RATE_MILLIS = 60; //In case the player presses the arrow-down key to let
											 //the shape sink faster
	
	int FULL_ROW_BLINKING_INTERVALL = 60;
	int FULL_ROW_BLINKING_TIME = 900;
	Color FULL_ROW_BLINK_COLOR = Color.WHITE;
	
	String[] AUDIO_THEME_FILENAMES = {"./audio/theme1.wav", "./audio/theme2.wav", "./audio/theme3.wav",
			"./audio/theme4.wav", "./audio/theme5.wav"};
}
