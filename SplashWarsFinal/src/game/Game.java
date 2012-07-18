package game;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Game Class
 * 
 * 
 * This is the main game class, manages the game-play, such as player actions,
 * timing, rounds, high score storing; Game class extends LandScape class.
 * 
 * @version 0.9
 * @date 09/12/06.
 * @author Taher Odeh & Aydan Halilov
 */
@SuppressWarnings("serial")
public class Game extends LandScape {

	/**
	 * Holds the main window instance for switching screens.
	 */
	MainWindow window;

	/**
	 * Resolution X of game window.
	 */
	public static final int RES_X = 800;

	/**
	 * Resolution Y of game window.
	 */
	public static final int RES_Y = 600;

	/**
	 * Background color
	 */
	public static final Color BACKGROUND_CLR = new Color(164, 211, 238);

	/**
	 * Easy difficulty level
	 */
	public static final int DIFF_EASY = 1;

	/**
	 * Hard difficulty level
	 */
	public static final int DIFF_HARD = 2;

	/**
	 * Maximum number of players allowed.
	 */
	public static final int MAX_PLAYERS = 4;

	/**
	 * The maximum distance the player can be affected by the balloon.
	 */
	private static final int FAREST_HIT_RANGE = 50;

	/**
	 * Player turn timer.
	 */
	private javax.swing.Timer timer;

	/**
	 * The wind parameter value affecting the player throw.
	 */
	private int wind;

	/**
	 * Holds the current player index.
	 */
	private int currentPlayerIndex = -1;

	/**
	 * Holds the current player instance.
	 */
	private Player currentPlayer;

	/**
	 * Holds the players participating in the current running game.
	 */
	private ArrayList<Player> players = new ArrayList<Player>();

	/**
	 * The top control pane instance.
	 */
	private ControlPanel controlPanel;

	/**
	 * Counts the number of turns played.
	 */
	private int turnNumber = 1;

	/**
	 * Throw ball timer animation timer.
	 */
	private javax.swing.Timer throwTimer;

	/**
	 * Ball position x.
	 */
	private int ballPosX = 0;

	/**
	 * Ball position y.
	 */
	private int ballPosY = 0;

	/**
	 * Last ball x position, saved in order to know where to sweep the previous
	 * ball.
	 */
	private int lastBallPosX = 0;

	/**
	 * Last ball y position, saved in order to know where to sweep the previous
	 * ball.
	 */
	private int lastBallPosY = 0;

	/**
	 * Game constructor; reads menu, creates players, restarts timers, starts
	 * and manages the game.
	 */

	/**
	 * indicates if game is running.
	 */
	public boolean gameRunning;

	/**
	 * indicates the landing point of the ball, used for ball splash.
	 */
	@SuppressWarnings("unused")
	private Point ballLandPoint = null;

	/**
	 * Splash effect timer for splash animation.
	 */
	private Timer splashTimer;

	/**
	 * A kickoff timer before the game starts.
	 */
	private Timer startTimer;

	/**
	 * Seconds left before the game starts
	 */
	private int kickOffSecsLeft = 3;

	/**
	 * Difficulty level of the game. in easy level the computer has more chances
	 * to hit the player.
	 */
	private int difficultyLevel = DIFF_HARD;

	/**
	 * Constructor, loads all properties for loading the game from a saved
	 * instance.
	 * 
	 * @param players
	 *            players in the game
	 * @param landscapePoints
	 *            landscape points to create landscape
	 * @param wind
	 *            wind value
	 * @param currentPlayerIndex
	 *            current player
	 * @param difficultyLvl
	 *            difficulty level of game loaded.
	 */
	public Game(ArrayList<Player> players, Point[] landscapePoints, int wind,
			int currentPlayerIndex, int difficultyLvl) {
		super(landscapePoints);

		this.difficultyLevel = difficultyLvl;

		for (int i = 0; i < players.size(); i++)
			players.get(i).setGame(this);

		this.players = players;

		this.currentPlayerIndex = currentPlayerIndex;

		init();

		controlPanel.getWindViewer().setWindValue(wind);

		kickOff();// startGame();

	} // END OF CONST 1

	/**
	 * Constructor, loads difficulty level, used for settings a new game from
	 * the menu.
	 * 
	 * @param difficultyLvl
	 *            difficulty level of the cpu player [if any].
	 */
	public Game(int difficultyLvl) {
		super();
		this.difficultyLevel = difficultyLvl;

	} // END OF Constructor 2

	/**
	 * draws all components like lanscape and playrs, balls and splashes...
	 * 
	 * @param g
	 *            Graphis engine.
	 */
	protected void paintComponent(Graphics g) {

		super.drawMap(g); // drawing the landscape

		Graphics2D g2d = (Graphics2D) g; // casting Graphics object into
		// Graphics2D.

		g2d.setStroke(new BasicStroke(5)); // Making line strokes thicker

		RenderingHints hints = new RenderingHints(
				// making lines softer using the rendering hints class.
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON); // use anti aliasing.
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_OFF); // use text anti
		// aliasing.

		g2d.setRenderingHints(hints); // apply rendering hints to g2d

		/*
		 * Creating save and exit icons on top of the landscape.
		 */

		URL saveUrl = Game.class.getResource("/images/save.GIF");
		URL quitUrl = Game.class.getResource("/images/exit.GIF");

		Image saveImg = Toolkit.getDefaultToolkit().getImage(saveUrl);
		Image quitImg = Toolkit.getDefaultToolkit().getImage(quitUrl);
		g2d.drawImage(quitImg, 30, 1, 20, 20, null);
		g2d.drawImage(saveImg, 55, 1, 20, 20, null);

		for (int i = 0; i < players.size(); i++) {
			// drawing players
			players.get(i).drawPlayer(g); // draw player

		}

		// draw a false line, in order to redraw.
		getGraphics().drawLine(0, 0, 0, 0);

		controlPanel.draw(g2d); // drawing the control panel

		// kick off timer
		if (kickOffSecsLeft >= 0) {
			g2d.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 36));

			g2d.setColor(BACKGROUND_CLR);
			g2d.fillRect(RES_X / 2, RES_Y / 2 - 30, 30, 30);

			// do not draw 0.
			if (kickOffSecsLeft > 0) {
				g2d.setColor(Color.BLACK);
				g2d.drawString(kickOffSecsLeft + "", RES_X / 2, RES_Y / 2);
				repaint();
			}

		}

		if (isBallFlying()) { // check if it's in the air.
			// drawing the water ball if it's in the air

			g2d.setColor(Color.BLUE); // coloring blue.
			g2d.fillArc(ballPosX, ballPosY, 5, 5, 0, 360); // draw ball.
			if (lastBallPosX != 0) { // check if its not a first draw.
			}

			// deleting last ball position by drawing the ball in white.
			g2d.setColor(BACKGROUND_CLR);
			g2d.fillArc(lastBallPosX - 1, lastBallPosY - 1, 7, 7, 0, 360);

			lastBallPosX = ballPosX; // update last ball x position.
			lastBallPosY = ballPosY; // update last ball y position.

		}

	} // END OF PAINTCOMPONENT

	/**
	 * manually initialize panel and players, used after loading the game from
	 * menu class when players are already added.
	 */
	public void manualInit() {
		if (players.size() > 0) {
			currentPlayerIndex = GeneralFunctions.generateRandomInt(0, players
					.size() - 1); // picking a random player index to play first

			init();

			controlPanel.getWindViewer().setWindValue();

			kickOff();
		}

	}

	/**
	 * intitializes control panel and stats the game.
	 */
	private void init() {

		gameRunning = true; // game is paused at start.

		controlPanel = new ControlPanel(); // The control panel instance

		currentPlayer = players.get(currentPlayerIndex); // settings the current
		// player object.

		controlPanel.setPlayer(currentPlayer); // setting the player to the
		// control panel.

		controlPanel.updateControlPanel(); // updating control panel

		addMouseListener(new ListenCarefullyMouse());

		controlPanel.getThrowingControl().disableButtons();

	}

	/**
	 * kick off timer before game starts
	 */
	private void kickOff() {
		startTimer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (--kickOffSecsLeft == 0) {
					startTimer.stop();
					startGame();
				}
			}
		});
		startTimer.start();
	}

	/**
	 * return true if game already started.
	 */
	public boolean gameStarted() {
		return kickOffSecsLeft > 0 ? false : true;
	}

	/**
	 * Starts the game.
	 */
	private void startGame() {

		// restarting turn timer
		restartTimer();

		// re enabling control buttons.
		controlPanel.getThrowingControl().enableButtons();

		if (currentPlayer.isCPU()) {
			// checking if the current player is a cpu, calculate angle and
			// power, then throw.

			CPUThrow();
		}

	}

	/**
	 * restarts the turn timer. assigning timer to a swing timer object if its
	 * not assigned yet.
	 */
	private void restartTimer() {
		if (timer == null) {
			timer = new javax.swing.Timer(1000, new ActionListener() { // a
						// second
						// delay
						/**
						 * An inner action listener class.
						 */
						@Override
						public void actionPerformed(ActionEvent e) {
							controlPanel.getClock().moveClock(); // updating
							// clock status.

							repaint();

							if (controlPanel.getClock().getSecondsLeft() <= 0) {
								// if time limit reached, next player plays.

								timer.stop(); // stopping the timer.
								nextTurn(); // next player turn.
							}
						}
					});
		}

		controlPanel.getClock().restart(); // Timer has to be restarted in
		// control panel as well

		timer.stop();
		timer.start();
		// restaring timer
	}

	/**
	 * Paints the landscape, players and ball if thrown.
	 * 
	 * @param g
	 *            graphics library.
	 * 
	 */
	public void CPUThrow() {
		Player closestPlayer = currentPlayer.getClosestPlayer(players);
		@SuppressWarnings("unused")
		Point p1 = new Point(), p2 = new Point();
		int x = closestPlayer.getPlayerPosition().x;
		int y = closestPlayer.getPlayerPosition().y;
		switch (difficultyLevel) {
		case DIFF_EASY:

			p1 = new Point(x - 60, getLandScapeY(x - 60));
			p2 = new Point(x + 60, getLandScapeY(x + 60));
			break;
		case DIFF_HARD:
			p1 = new Point(x - 40, getLandScapeY(x - 40));
			p2 = new Point(x + 40, getLandScapeY(x + 40));
			break;
		}

		// determining power depends on the distance between the 2 playrs.
		int dist = (int) GeneralFunctions.distanceBetweenTwoPoints(new Point(x,
				y), new Point(currentPlayer.getPlayerPosition().x,
				currentPlayer.getPlayerPosition().y));
		int power = 60;
		if (dist > 100 && dist < 300)
			power = GeneralFunctions.generateRandomInt(50, 70);
		else if (dist > 300 && dist < 500)
			power = GeneralFunctions.generateRandomInt(70, 80);
		else if (dist > 500 && dist < 800)
			power = GeneralFunctions.generateRandomInt(90, 100);
		else
			power = GeneralFunctions.generateRandomInt(10, 50);

		int angle = 90;

		int hill_y = currentPlayer.getPlayerPosition().y
				- currentPlayer.getHeight();

		if (x > currentPlayer.getPlayerPosition().x) {
			for (int i = currentPlayer.getPlayerPosition().x; i < x; i++) {
				// finding a hill on the way between the thrower and the victim.
				if (getLandScapeY(i) < hill_y)
					hill_y = getLandScapeY(i);
			}

			if (hill_y == currentPlayer.getPlayerPosition().y)
				angle = 10;
			else {
				/*
				 * victim is on top of the cpu or there is a hill on the way.
				 * calc the angle by using the atan of the division of y and x
				 * diffrence
				 */
				angle = (int) Math
						.toDegrees((double) Math
								.atan(((double) (currentPlayer
										.getPlayerPosition().y - hill_y) / (double) (x - currentPlayer
										.getPlayerPosition().x))));
				angle = GeneralFunctions.generateRandomInt(angle + 5,
						angle + 30);
				power = power + 10 > 100 ? 100 : power + 10;
			}
		} else {
			for (int i = x; i < currentPlayer.getPlayerPosition().x; i++)
				// finding a hill on the way between the thrower and the victim.
				if (getLandScapeY(i) < hill_y)
					hill_y = getLandScapeY(i);

			if (hill_y == currentPlayer.getPlayerPosition().y)
				angle = GeneralFunctions.generateRandomInt(160, 180);
			else { // victim is on top of the cpu or there is a hill on the way.
				angle = 180 - (int) Math
						.toDegrees((double) Math.atan((double) (currentPlayer
								.getPlayerPosition().y - hill_y)
								/ (double) (-x + currentPlayer
										.getPlayerPosition().x)));
				angle = GeneralFunctions.generateRandomInt(angle - 5,
						angle - 30);
				power = power + 10 > 100 ? 100 : power + 10;
			}
		}

		// DEBUG
		System.out.println("distance : " + dist);

		// if in easy mode, dont be that specific regarding the angle, just
		// choose a random angle in the right direction.

		if (difficultyLevel == DIFF_EASY)
			angle = x > currentPlayer.getPlayerPosition().x ? GeneralFunctions
					.generateRandomInt(10, 85) : GeneralFunctions
					.generateRandomInt(95, 175);

		currentPlayer.setAngleValue(angle);
		currentPlayer.setPowerValue(power);

		throwBall(currentPlayer.getPlayerPosition().x, currentPlayer
				.getPlayerPosition().y);

	}

	/**
	 * 
	 * @return currentPlayerIndex the array index of the current player
	 */
	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}

	/**
	 * 
	 * @param i
	 *            index of player in the players array list
	 */
	public void setCurrentPlayerIndex(int i) {
		if (i < players.size())
			currentPlayerIndex = i;
	}

	/**
	 * @param x
	 *            X position of ball to set
	 */
	public void setBallPosX(int x) {
		ballPosX = x;
	}

	/**
	 * Accessor for difficulty level.
	 * 
	 * @return difficulty level chosen.
	 */
	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	/**
	 * @param y
	 *            Y position ball to set
	 */
	public void setBallPosY(int y) {
		ballPosY = y;
	}

	/**
	 * 
	 * @return players ArrayList of the current players in the game.
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * 
	 * @return currentPlayer the current player instance.
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * 
	 * @return controlPanel the control panel instance.
	 */
	public ControlPanel getControlPanel() {
		return controlPanel;
	}

	/**
	 * 
	 * @return the casted LandScape of this class.
	 */
	public LandScape getLandScape() {
		return (LandScape) this;
	}

	/**
	 * Adds a new player to the players ArraList.
	 * 
	 * @param playerName
	 *            the name of the player to add
	 * @param CharacterCode
	 *            The character code
	 * @param isCPU
	 *            is the player added a cpu or a human player?
	 */
	public void addPlayer(String playerName, int CharacterCode, boolean isCPU) {
		if (players.size() < MAX_PLAYERS) { // limit the number of players
			// allowed.
			players.add(new Player(playerName, isCPU, CharacterCode, this));
		}
	}

	/**
	 * Adds a new player to the players ArraList.
	 * 
	 * @param p
	 *            a player instance
	 */
	public void addPlayer(Player p) {
		if (players.size() < MAX_PLAYERS) { // limit the number of players
			// allowed.
			players.add(p);
		}
	}

	/**
	 * Choosing the next player to take turn, restarts the timer, changes wind
	 * values depending on round number and upating control panel statuses.
	 */
	public void nextTurn() {

		do {
			if (++currentPlayerIndex >= players.size())
				// choosing next player from the array, if reached end of array,
				// choose the first player
				currentPlayerIndex = 0;

			currentPlayer = players.get(currentPlayerIndex); // assigning
			// current
			// player
		} while (currentPlayer.isWet());
		// checking is player is wet (dead), if so, next player's turn

		restartTimer(); // restarting clock timer

		controlPanel.setPlayer(currentPlayer);
		// updating the player instance in control panel to update info

		turnNumber++; // counting turns.

		/* Holds the players alive */
		int dry_players = 0;
		for (int i = 0; i < players.size(); i++)
			if (!players.get(i).isWet())
				dry_players++;

		// change wind every turn
		getControlPanel().getWindViewer().setWindValue();

		if (currentPlayer.isCPU()) {
			// checking if player is cpu, if so, get the cpu to throw.
			CPUThrow();

		}
	}

	/**
	 * Throw the ball from (x,y), until it reaches an end (player, gound, screen
	 * end).
	 * 
	 * @param x
	 *            X position of where the ball is thrown.
	 * @param y
	 *            Y position of where the ball is thrown.
	 */
	public void throwBall(int x, int y) {

		ballPosX = x; // setting x ball position
		ballPosY = y; // setting Y ball position

		timer.stop(); // stopping the turn timer clock

		getControlPanel().getThrowingControl().disableButtons();
		// disabling control panel buttons

		/**
		 * Assigning the throwing timer to a new swing Timer object, delay is
		 * set to 20 milliseconds.
		 */
		throwTimer = new Timer(20, new ActionListener() {

			// Temporary ball x position
			double ball_x = currentPlayer.getPlayerPosition().x;

			// Temporary ball x position
			double ball_y = currentPlayer.getPlayerPosition().y;

			// throwing power value chosen
			int power = getControlPanel().getThrowingControl().getPowerValue();
			/** throwing angle value chosen */
			int angle = getControlPanel().getThrowingControl().getAngleValue();

			// x velocity of ball
			double vx = ((power / 7.0) * (float) Math
					.cos((Math.PI * 2 * (double) angle) / 360.0));
			// y velocity of ball
			double vy = -((power / 7.0) * (float) Math
					.sin((Math.PI * 2 * (double) angle) / 360.0));

			/**
			 * the action performed in the throwing timer.
			 * 
			 * @param e
			 *            action event.
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {

				ballPosX = (int) ball_x; // setting ball position x to the
				// new position
				// pixels to the right.

				ballPosY = (int) ball_y - 30; // setting ball position x to the
				// new position, moving it 30
				// pixels top.

				ball_x += vx;
				// decreasing ball position x by the velocity calculated
				ball_y += vy;
				// decreasing ball position y by the velocity calculated

				vy += 0.3; // Adding gravity factor to y velocity

				/** Holds calculated effect of wind */
				double w = getControlPanel().getWindViewer().getWindValue()
						- vx;

				if (wind > 0) {
					vx += (w * w) / 700.0; // wind affecting left side.
				} else {
					vx -= (w * w) / 700.0; // wind affecting left side.
				}

				if (isBallTouching()) {
					// Checking if ball is touching anything.
					ballLand(ballPosX, ballPosY); // stop the ball.
					lastBallPosX = ballPosX;
					lastBallPosY = ballPosY;
				}

				repaint(); // repainting ...

			}
		});

		throwTimer.start(); // begin the throwing animation

	}

	/**
	 * Method indicating whether the ball already hit something or if it's still
	 * in the air.
	 * 
	 * @return true if ball is still in tha air
	 */
	public boolean isBallFlying() {
		return throwTimer != null && throwTimer.isRunning();
	}

	/**
	 * Stop ball at given x and y.
	 * 
	 * @param x
	 *            X position of where the ball stopped.
	 * @param y
	 *            Y position of where the ball stopped.
	 */
	public void ballLand(int x, int y) {
		throwTimer.stop(); // stopping animation timer

		timer.start(); // starting turn timer clock again.

		for (int i = 0; i < players.size(); i++) {
			// checking if any player is close (check FAREST_HIT_RANGE) to the
			// water balloon.

			Player p = players.get(i);

			if (p.isWet())
				continue; // check if player is already dead.

			if (p.equals(currentPlayer)) // own hit is not supported.
				continue;

			/** The distance between player p and ball end point */
			double dist = GeneralFunctions.distanceBetweenTwoPoints(new Point(p
					.getPlayerPosition().x, p.getPlayerPosition().y),
					new Point(x, y));

			if (dist < FAREST_HIT_RANGE) {
				// checking if player p is close enough to be wet and lose hp

				p.setHP(p.getHP() - (FAREST_HIT_RANGE - 1) * 2 + (int) dist);
				// calculating HP loss, by the following formula: hp - (farest
				// hit range - 1) * 2 * distance between ball and player.

				currentPlayer.setScore(currentPlayer.getScore() + (int) dist
						* 2); // updating thrower score
			}

			ballLandPoint = new Point(x, y);

		}

		makeBallSplash(getGraphics(), x, y);

		if (isGameFinished())
			finishGame();
		else {
			nextTurn(); // next player's turn.
			getControlPanel().getThrowingControl().enableButtons(); // re-enable
			// buttons.
		}

		// TODO add animation.
		// makeBallSplash(getGraphics(), x, y);
	}

	/**
	 * make a ball splash effect at x,y
	 * 
	 * @param g
	 *            graphics engine
	 * @param x
	 *            x position of splash
	 * @param y
	 *            y position of splash
	 */
	private void makeBallSplash(Graphics g, int x, int y) {
		final Graphics g2d = (Graphics2D) g;
		final int x1 = x;
		final int y1 = y;
		try {
			splashTimer = new Timer(20, new ActionListener() {
				int i = 0;

				@Override
				public void actionPerformed(ActionEvent e) {

					i += 10;
					if (i < FAREST_HIT_RANGE) {
						// FIXME a null pointer exception is thrown here, have
						// to check that.
						if (g2d != null) {
							g2d.setColor(new Color(54, 100, 139));
							g2d.fillOval(x1 - i / 2, y1 - i / 2, i, i);
						}

					} else
						splashTimer.stop();
				}
			});
			splashTimer.start();
		} catch (NullPointerException ex) {
		}

	}

	/**
	 * returns true if game is finished, otherwise false.
	 * 
	 * @return
	 */
	public boolean isGameFinished() {
		int wet_players = 0;

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isWet())
				wet_players++;
		}
		if (wet_players == (players.size() - 1))
			return true;

		return false;
	}

	/**
	 * Finishes the game, stops timer, saves scores and shows a game over
	 * window.
	 */
	public void finishGame() {
		timer.stop();
		DBConnection.saveScores(this);
		gameRunning = false;
		if (window != null) {
			window.switchToPanel(new GameOver(window, this));

		}

	}

	/**
	 * removes all players.
	 */
	public void emptyPlayers() {
		players = new ArrayList<Player>();
	}

	/**
	 * Indicates if ball hit something.
	 * 
	 * @return true if ball hit an object, landscape or the end of the screen,
	 *         otherwise, return false.
	 */
	public boolean isBallTouching() {

		/** Temporary x position of the ball */
		int x = ballPosX;

		/** Temporary y position of the ball */
		int y = ballPosY;

		/** Landscape generated points */
		Point[] landScapePts = getLandScapePoints();

		/** Holds the slope value of two points in the landscape */
		double m;

		for (int i = 0; i < players.size(); i++) {
			// checking if ball directly hits a player, by checking each
			// player's area and whether the ball enters this area.

			Player p = players.get(i);
			if (p.equals(currentPlayer))
				continue;

			if (p.isWet())
				continue;

			if (x > p.getPlayerPosition().x
					&& x < p.getWidth() + p.getPlayerPosition().x // x area of
					// player.
					&& y < p.getPlayerPosition().y
					&& y > (p.getPlayerPosition().y - p.getHeight() - 8)) {
				// The ball entered the player's area, so it is a direct hit

				return true; // ball has reached to an end
			}

		}

		for (int i = 0; i < landScapePts.length - 1; i++) {
			// checking if ball hit the landscape

			if (x > landScapePts[i].x && x < landScapePts[i + 1].x) {
				// checking if ball is in the x area of the 2 checked landscape
				// points

				m = ((double) (landScapePts[i].y - landScapePts[i + 1].y) / (double) (landScapePts[i].x - landScapePts[i + 1].x));
				// calculating the slope point of the 2 landscape points

				if (y >= (int) (m * x - m * landScapePts[i].x + landScapePts[i].y)) {
					// calculating the linear function between the checked 2
					// landscape points and checking if the ball's position is
					// in this line

					return true;
					// ball's position exists on the line between the 2 given
					// landscape points.
				}
			}
		}

		if (x >= RES_X || x < 0) // checking if ball hits the window's end.
			return true;

		return false;
	}

	/**
	 * @param window Container window.
	 */
	public void setWindow(MainWindow window) {
		this.window = window;
	}

}
