package game;

/*
 * MainMenu.java
 *
 * Created on Dec 8, 2009, 7:53:46 PM
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This is the main menu class ,include some buttons like play game, load
 * game,high score,help,credits and exit.
 * 
 * @date 09/12/11
 * @version 1.0
 * @author Dong Yu
 */
@SuppressWarnings("serial")
public class MainMenu extends JPanel implements ActionListener {

	/**
	 * Main window which this panel is attached to
	 */
	private MainWindow window;

	/**
	 * background image.
	 */
	private Image backgroundImg;

	/**
	 * Constructor, sets the background image and initiates the buttons and
	 * layout.
	 * 
	 * @param window
	 */
	public MainMenu(MainWindow window) {
		this.window = window;
		URL url = MainMenu.class.getResource("/images/balloons.GIF");
		backgroundImg = Toolkit.getDefaultToolkit().getImage(url);

		initComponents(); // initiate menu items and add listeners.
	}

	/**
	 * initiates Components such as buttons and layouts, this method was created
	 * using the NetBeans visual designer.
	 */
	private void initComponents() {

		PlayGame = new javax.swing.JButton();
		LoadGame = new javax.swing.JButton();
		HighScore = new javax.swing.JButton();
		Help = new javax.swing.JButton();
		Credits = new javax.swing.JButton();
		Quit = new javax.swing.JButton();

		PlayGame.setText("Play Game");
		PlayGame.setPreferredSize(new java.awt.Dimension(100, 25));
		PlayGame.addActionListener(this);

		LoadGame.setText("Load Game");
		LoadGame.setPreferredSize(new java.awt.Dimension(100, 25));
		LoadGame.addActionListener(this);

		HighScore.setText("High Score");
		HighScore.setPreferredSize(new java.awt.Dimension(100, 25));
		HighScore.addActionListener(this);

		Help.setText("Help");
		Help.setPreferredSize(new java.awt.Dimension(100, 25));
		Help.addActionListener(this);

		Credits.setText("Credits");
		Credits.setPreferredSize(new java.awt.Dimension(100, 25));
		Credits.addActionListener(this);

		Quit.setText("Quit");
		Quit.setPreferredSize(new java.awt.Dimension(100, 25));
		Quit.addActionListener(this);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(350, 350, 350)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																PlayGame,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																Quit,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																Credits,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																Help,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																HighScore,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																LoadGame,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(142, Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(160, 160, 160)
										.addComponent(
												PlayGame,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												LoadGame,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												HighScore,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												Help,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												Credits,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												Quit,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(36, Short.MAX_VALUE)));

		this.setLayout(layout);
	}

	// Variables declaration
	private javax.swing.JButton Credits;
	private javax.swing.JButton Help;
	private javax.swing.JButton HighScore;
	private javax.swing.JButton LoadGame;
	private javax.swing.JButton PlayGame;
	private javax.swing.JButton Quit;

	// End of variables declaration

	/**
	 * An inner action listener for the buttons.
	 * 
	 * @see ActionListener
	 */
	public void actionPerformed(ActionEvent ev) {
		String text = ((JButton) ev.getSource()).getText();

		if (text.equals("Quit")) { // quit button clicked.
			int quit_ans = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to quit?", "Quit Game",
					JOptionPane.YES_NO_OPTION);
			if (quit_ans == JOptionPane.YES_OPTION) {
				System.exit(0);
			}

		} else if (text.equals("Credits")) { // Credits button clicked.
			window.switchToPanel(new Credits(window));
			// repaint();
		} else if (text.equals("High Score")) {
			window.switchToPanel(new HighScore(window));

		} else if (text.equals("Load Game")) { // LoaD GAME button clicked.
			Game g = DBConnection.loadGame();
			g.setWindow(window);
			if (g == null) {
				GeneralFunctions
						.showDialogBox("Game has never been saved yet.");
			} else
				window.switchToPanel(g);

		} else if (text.equals("Play Game")) { // play game button clicked.
			window.switchToPanel(new StartGameMenu(window));
		} else if (text.equals("Help")) {
			window.switchToPanel(new Help(window)); // help clicked.
		}

	}

	/**
	 * Paints the background image.
	 * 
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImg, 0, 0, 800, 600, null);
	}
}
