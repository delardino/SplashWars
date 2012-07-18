package game;


import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main window class, holds the game frame and the container panel.
 * 
 * @author Niel Madlani
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	/**
	 * the container panel.
	 */
	private JPanel mainPanel;

	/**
	 * Constructor, initializes the title, sizes and properties of the window,
	 * and connects to receives DB connection.
	 */
	public MainWindow() {

		try {
			new DBConnection();
			// calling DBConnection constructor to retrieve a static connection.

		} catch (SQLException e) {
			// all catches are done in the DBConnection
			// class, so there is nothing to catch.
		}

		setTitle("SPLASH WARS");

		setSize(Game.RES_X, Game.RES_Y);
		setBackground(Game.BACKGROUND_CLR);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		mainPanel = (JPanel) getContentPane();
		mainPanel.setPreferredSize(new Dimension(Game.RES_X, Game.RES_Y));
		setResizable(false);

		mainPanel.add(new MainMenu(this));

		setVisible(true);

	}

	/**
	 * Switch to panel p, for changing screens.
	 * removes all panels and shows p.
	 * @param p panel to show.
	 */
	public void switchToPanel(JPanel p) {
		mainPanel.removeAll();
		repaint();
		mainPanel.add(p);
		mainPanel.revalidate();

	}
}
