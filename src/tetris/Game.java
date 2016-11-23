/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Hier laufen die Fäden zusammen, die Game-Klasse ist dann ein aggregate-object
 * und hat als Fields z.B. die Spieler etc.
 */
public class Game extends JPanel {
	private static final long serialVersionUID = 1L; //default created
	private Shape shape;
	private TastaturAdapter tastaturAdapter;
	private Board board;
	private boolean gameOver;
	private boolean blinks;
	private AudioFile backgroundMusic;
	
	
	public Game() {
		this.setPreferredSize(new Dimension(Config.WINDOW_X, Config.WINDOW_Y)); //***x*** drawable
		this.setBackground(Config.BACKGROUND);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		tastaturAdapter = new TastaturAdapter();
		this.addKeyListener(tastaturAdapter);
	}
	
	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics2D graphics2D = (Graphics2D) graphics;
		
		//Meine Game-Objekte sollen nicht hier gezeichnet werden, sondern in den jeweiligen
		//Klassen. Bsp:
		//player.paint(graphics2D, this); //dieser Aufruf ist hier
		
		//die zugehörige Methode in der Klasse Player
		//public void paint(Graphics2D graphics, ImageObserver imageObserver) {...
		
		if (gameOver)
			paintGameOver(graphics2D);
		else {
			shape.paint(graphics2D);
			board.paint(graphics2D, blinks); //paint full rows in normal block-color (if there are any??)
		}
		
		graphics2D.dispose();
	}
	
	private void paintGameOver(Graphics2D graphics2D) {
		graphics2D.setColor(Color.WHITE);
		graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 40));
		graphics2D.drawString("Game Over", Config.BLOCKS_X * Config.BLOCK_SIZE / 2 - 96,
				Config.BLOCKS_Y * Config.BLOCK_SIZE / 2 - 20);
	}
	
	public void init() {
		board = new Board(); //muss VOR der Shape instantiierung sein... (Shape holt sich die board-Referenz hier)...
		shape = ServiceLocator.instance().getRandomShape();
		
		backgroundMusic = ServiceLocator.instance().getRandomAudioFile();
		backgroundMusic.start();
	}
	
	/**
	 * The main-loop. Manages fps itself via Thread.sleep(). Care: Ist NICHT die run-Methode
	 * eines Threads, sondern eine eigene Methode.
	 */
	public void run() {
		long startTime;
		long millisPerFrame = 1000 / Config.FPS;
		while (true) {
			startTime = System.currentTimeMillis();
			
			if (!gameOver)
				update();
				
			this.repaint();
			
			if (!backgroundMusic.isAlive()) {
				backgroundMusic = ServiceLocator.instance().getRandomAudioFile();
				backgroundMusic.start();
			}
			
			long timeout = millisPerFrame - (System.currentTimeMillis() - startTime);
			sleep(timeout);
		}
	}
	
	/**
	 * One game-step (one frame).
	 */
	private void update() {
		
		if (!shape.sink(tastaturAdapter.isVkDownPressed())) {
			//Shape collided in his sinking-attempt => new shape needed, old shape materializes into the board
			if (board.addShapeToBoard(shape)) {
				//gibt full rows
				letFullRowsBlink();
				
				board.deleteFullRows();
			}
			
			shape = ServiceLocator.instance().getRandomShape();
			
			if (board.checkShapeCollision(shape)) {
				//game over due to instant collision after creating the shape
				gameOver = true;
			}
		}
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public Board getBoard() {
		return board;
	}
	
	private void letFullRowsBlink() {
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < Config.FULL_ROW_BLINKING_TIME) {
			long blinkStart = System.currentTimeMillis();
			
			blinks = !blinks;
			repaint();
			
			long timeout = Config.FULL_ROW_BLINKING_INTERVALL - 
					(System.currentTimeMillis() - blinkStart);
			sleep (timeout);
		}
		
		blinks = false;
	}
	
	/**
	 * @param millis negative values allowed, method will deal with it (set to 0)
	 */
	private void sleep(long millis) {
		if (millis < 0)
			millis = 0;
		
		try {
			Thread.sleep(millis);
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
			System.err.println("Thread.sleep() failed.");
		}
	}
}
