package game;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * High score class
 * 
 * @author Niel Madlani
 * @date 09/12/11
 */

@SuppressWarnings("serial")
public class HighScore extends JPanel implements ActionListener {

	/**
	 * vertical changable y.
	 */
	private int y = 240;

	/**
	 * container window.
	 */
	private MainWindow window;

	/**
	 * Backbutton
	 */
	private JButton backButton;

	public HighScore(MainWindow window) {
		this.window = window;
		setLayout(new BorderLayout());
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		add(backButton, BorderLayout.SOUTH);
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

		// Controlpanel background
		g2d.setColor(new Color(141, 182, 205));
		g2d.fillRect(0, 0, Game.RES_X, Game.RES_Y);

		// Highscore
		g2d.setColor(Color.BLACK);
		g2d.drawString("HIGHSCORE", 220, 180);
		g2d.drawString(
				"---------------------------------------------------------",
				220, 200);

		ArrayList<Player> players = getHighScores();

		for (int i = 0; i < players.size(); i++) {
			g2d.drawString(i + 1 + "", 220, y);
			g2d.drawString(players.get(i).getPlayerName(), 360, y);
			g2d.drawString(players.get(i).getScore() + "", 500, y);
			y += 40;
		}

	}

	/**
	 * 
	 * @return Highscore from the database where the name and highscore saved.
	 */
	private ArrayList<Player> getHighScores() {

		ArrayList<Player> players = new ArrayList<Player>();

		try {
			if (DBConnection.connection == null)
				new DBConnection();

			Statement stat = DBConnection.connection.createStatement();
			ResultSet highScoresRS = stat
					.executeQuery("SELECT name, score FROM HighScores ORDER BY score DESC LIMIT 0,5");

			while (highScoresRS.next()) {
				Player p = new Player(highScoresRS.getString("name"),
						highScoresRS.getInt("score"));
				players.add(p);
			}
		} catch (SQLException e) {
		}

		return players;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		window.switchToPanel(new MainMenu(window));

	}
}
