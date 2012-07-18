package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * High score class
 * 
 * @author Niel Madlani
 * @date 09/12/11
 */

@SuppressWarnings("serial")
public class GameOver extends JPanel implements ActionListener {

	/**
	 * container window.
	 */
	private MainWindow window;

	/**
	 * Current running game
	 */
	Game game;

	/**
	 * Game over timer.
	 */
	javax.swing.Timer timer;

	/**
	 * Seconds left before returning to main
	 */
	int secs_left = 5;

	/**
	 * Constructor, sets game and main window.
	 * 
	 * @param window
	 * @param g
	 */
	public GameOver(final MainWindow window, Game g) {
		this.game = g;
		this.window = window;
		timer = new javax.swing.Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (--secs_left == 0) {
					window.switchToPanel(new MainMenu(window));
					timer.stop();
				}
				repaint();
			}
		});
		timer.start();
	}

	/**
	 * @param paint
	 *            graphic
	 */
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

		// Antialising start
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);
		// Antialising end

		// Increase the width of the lines drawn
		g2d.setStroke(new BasicStroke(2));

		// background
		g2d.setColor(new Color(141, 182, 205));
		g2d.fillRect(0, 0, Game.RES_X, Game.RES_Y);

		g2d.setFont(new Font("Tahoma", Font.BOLD, 24));
		g2d.setColor(Color.BLACK);
		g2d.drawString("And the winner is [drumroll] : "
				+ getWinner().getPlayerName(), Game.RES_X / 2 - 300,
				Game.RES_Y / 2);

		g2d.drawString(
				"Returning to menu in like " + secs_left + " Seconds...",
				Game.RES_X / 2 - 300, Game.RES_Y / 2 + 40);
	}

	/**
	 * 
	 * @return Highscore from the database where the name and highscore saved.
	 */
	private Player getWinner() {
		Player winner = null;
		for (int i = 0; i < game.getPlayers().size(); i++) {
			if (winner == null)
				winner = game.getPlayers().get(i);
			else if (winner.getScore() < game.getPlayers().get(i).getScore())
				winner = game.getPlayers().get(i);
		}
		return winner;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		window.switchToPanel(new MainMenu(window));

	}
}
