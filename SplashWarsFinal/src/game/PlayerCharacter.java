package game;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

/**
 * Holds the character image of the player.
 * 
 * @author Emil
 */
public class PlayerCharacter {

	/**
	 * Green player.
	 */
	public static final int GREEN_STONEGUY = 1;
	/**
	 * Red player.
	 */
	public static final int RED_STONEGUY = 2;
	/**
	 * Blue player.
	 */
	public static final int BLUE_STONEGUY = 3;
	/**
	 * Naked player.
	 */
	public static final int NAKED_STONEGUY = 4;

	/**
	 * The character number
	 */
	private int characterNumber;

	/**
	 * The image handler for holding the player character.
	 */
	private Image currentImage;

	/**
	 * Constructor, takes a characterr number and loads the image.
	 * 
	 * @param character
	 *            character number.
	 */
	public PlayerCharacter(int character) {

		if (character < 1 || character > 4) { // validate character number.

		}

		characterNumber = character;

		loadImage("r");

	}

	/**
	 * The name of the images.
	 * 
	 * @param direction
	 *            The direction where the picture is taken from
	 */
	public void loadImage(String direction) {

		URL url = PlayerCharacter.class.getResource("/images/character"
				+ characterNumber + "_" + direction + ".gif");
		currentImage = Toolkit.getDefaultToolkit().getImage(url);

		/**
		 * If a image is missing a error will occure and say cannot load image
		 */

	}

	/**
	 * 
	 * @return character number
	 */
	public int getCharacterNumber() {
		return characterNumber;
	}

	/**
	 * return image.
	 * 
	 * @return
	 */
	public Image getCurrentImage() {
		return currentImage;
	}

}
