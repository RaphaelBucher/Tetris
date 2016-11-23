/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

public class ServiceLocator {
	private static ServiceLocator serviceLocator = new ServiceLocator();
	
	private ServiceLocator() {
	}

	public static ServiceLocator instance() {
		return serviceLocator;
	}
	
	public Shape getRandomShape() {
		Shape shape = null;
		int version = (int)(Math.random() * Config.SHAPE_AMOUNT) + 1;
		assert version >= 1 && version <= Config.SHAPE_AMOUNT;
		
		switch (version) {
			case 1: shape = new Shape1(Main.getGame().getBoard());
				break;
			case 2: shape = new Shape2(Main.getGame().getBoard());
				break;
			case 3: shape = new Shape3(Main.getGame().getBoard());
				break;
			case 4: shape = new Shape4(Main.getGame().getBoard());
				break;
			case 5: shape = new Shape5(Main.getGame().getBoard());
				break;
			case 6: shape = new Shape6(Main.getGame().getBoard());
				break;
			case 7: shape = new Shape7(Main.getGame().getBoard());
				break;
		}
		
		assert shape != null;
		return shape;
	}
	
	public AudioFile getRandomAudioFile() {
		AudioFile audioFile = null;
		int theme = (int)(Math.random() * Config.AUDIO_THEME_FILENAMES.length);
		assert theme >= 0 && theme < Config.AUDIO_THEME_FILENAMES.length;
		
		audioFile = new AudioFile("AudioFile-Thread", Config.AUDIO_THEME_FILENAMES[theme]);
		
		assert audioFile != null;
		return audioFile;
	}
}
