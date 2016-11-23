/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

import javax.swing.JFrame;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L; //default created
	private static Game game;

	public Main(Game game) {
		this.add(game);
		this.setTitle("Yet another Tetris-clone by Raphael Bucher");
		this.pack(); //Wenn die Komponenten (mein ***x***-Panel) grösser sind als die
		//Frame-Size (glaub noch 0 atm) vergrössert sich das JFrame (Main) entsprechend
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close Window => System.exit
		
		//die Reihenfolge dieser (und evtl. der vorherigen) Aufrufe ist wichtig
		//für die Positionierung mittels setLocationRelativeTo(...)
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public static void main(String[] args) throws InterruptedException {
		game = new Game();
		game.init();
		
		/*
		 * Darf erst nach game.init() kommen da das Frame via setVisible(true) dann automatisch paint
		 * aufruft, das gäbe dann NullPointerExceptions da z.B. shape noch nicht instanziiert wurde 
		 * (shape.paint() knallt z.B. da shape = null)
		 */
		new Main(game);
		
		game.run();
	}
	
	public static Game getGame() {
		return game;
	}
}

