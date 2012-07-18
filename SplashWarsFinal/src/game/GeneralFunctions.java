package game;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * GENERAL FUNCTIONS CLASS this class contains all the general main functions
 * needed.
 * 
 * @author Dong Yu
 * @date 09/12/11
 * @version 1.0
 */

public class GeneralFunctions {

	/**
	 * generate a random integer between min and max integers.
	 * 
	 * @param min
	 *            minumum number to be randomally generated
	 * @param max
	 *            maximum number to be randomally generated
	 * @return a random number between min and max.
	 */
	public static int generateRandomInt(int min, int max) {
		return (int) ((max - min) * Math.random() + min + 1);
	}

	/**
	 * generate a random float between min and max floats.
	 * 
	 * @param min
	 *            minumum number to be randomally generated
	 * @param max
	 *            maximum number to be randomally generated
	 * @return a random number between min and max.
	 */
	public static float generateRandomInt(float min, float max) {

		return (float) ((max - min) * Math.random() + min + 1);
	}

	/**
	 * calculates distance between two points. p1 and p2.
	 * 
	 * @param p1
	 *            first point.
	 * @param p2
	 *            sencond point.
	 * @return the 2D distance between points p1 and p2.
	 */
	public static double distanceBetweenTwoPoints(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2)); // Pythagoras
																				// formula.
	}

	/**
	 * shows a dialog box with a message defined.
	 * 
	 * @param msg
	 *            a message to be shown on the dialog.
	 */
	public static void showDialogBox(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	/**
	 * Takes an array list of players, sorts them by score and returns 
	 * 
	 * @param players array list of players.
	 * @return sorted array list of players.
	 */

	public static ArrayList<Player> sortPlayersByScore(ArrayList<Player> players) {
		ArrayList<Player> temp = new ArrayList<Player>();
		Player topPlayer = null;
		for (int i = 0; i < players.size(); i++) {

			for (int j = i; j < players.size(); j++) {
				if (topPlayer == null
						|| players.get(j).getScore() > topPlayer.getScore())
					topPlayer = players.get(j);
			}
			temp.add(topPlayer);
		}

		return temp;

	}

}
