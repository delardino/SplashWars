package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JPanel;

/**
 * The Player Class
 * This class contains the player properties and functions, such as positon of player, shooting,
 * stamina and more properties.
 * 
 * @author:  Taher Odeh
 */
@SuppressWarnings("serial")
public class Player extends JPanel {

	/**
	 * minimum distance between 2 players.
	 */

	private static final int MIN_DISTANCE = 100;
	/**
	 * Player name
	 */

	private String playerName;
	/**
	 * the X cordination of the player.
	 */

	private int posX;
	/**
	 * the Y cordination of player.
	 */
	private int posY;

	/**
	 * Stamina value of the player.
	 */
	private int HP = 100;

	/**
	 * player score.
	 */
	private int score = 0;

	/**
	 * true if this player is a CPU.
	 */
	private boolean isCPU = false;

	/**
	 * The player character
	 */
	private PlayerCharacter character;

	/**
	 * 
	 * angle value chosen
	 */
	private int angleValue;

	/**
	 * power value chosen
	 */
	private int powerValue;

	/**
	 * last chosen angle value, for deleting the previous drawing.
	 */
	private int lastAngleValue = -1;

	/**
	 * Current running game
	 */
	private Game game;

	/**
	 * Stamina that the player has just lost (for drawing).
	 */
	private int hp_lost = 0;

	/**
	 * A timer for showing the lost stamina.
	 */
	private Date statusLastShown;

	/**
	 * Constructor used for loading the players from the menu. sets the name,
	 * cpu, character and current game instance.
	 * 
	 * @param playerName
	 *            player name
	 * @param is
	 *            CPU cpu or human
	 * @param character
	 *            player character
	 * @param game
	 *            game starts
	 */
	public Player(String playerName, boolean isCPU, int characterNumber,
			Game game) {
		setPlayerName(playerName);
		this.isCPU = isCPU;
		this.character = new PlayerCharacter(characterNumber);
		this.HP = 100;
		this.angleValue = 90;
		this.powerValue = 50;
		this.game = game;

		Point p = generatePlayerPoint(game);
		this.posX = p.x;
		this.posY = p.y;
		this.character = new PlayerCharacter(characterNumber);

	}

	/**
	 * this constructor is used for listing players in high score.
	 * 
	 * @param playerName
	 *            player name
	 * @param score
	 *            player score
	 */
	public Player(String playerName, int score) {
		setPlayerName(playerName);
		this.score = score;
	}

	/**
	 * Constructor for loading players from load game option. sets all player
	 * properties.
	 * 
	 * @param playerName
	 *            player name
	 * @param isCPU
	 *            cpu or no cpu
	 * @param characterNumber
	 *            player character
	 * @param score
	 *            player score
	 * @param hp
	 *            player hp
	 * @param angle
	 *            player angle
	 * @param power
	 *            player power
	 * @param x
	 *            x positon of the player
	 * @param y
	 *            y position of the player
	 */
	public Player(String playerName, boolean isCPU, int characterNumber,
			int score, int hp, int angle, int power, int x, int y) {

		setPlayerName(playerName);
		this.score = score;
		this.isCPU = isCPU;
		this.character = new PlayerCharacter(characterNumber);
		this.HP = hp;
		this.angleValue = angle;
		this.powerValue = power;

		this.posX = x;
		this.posY = y;
		this.character = new PlayerCharacter(characterNumber);

	}

	/**
	 * @param g
	 *            game instance
	 */
	public void setGame(Game g) {
		game = g;
	}

	/**
	 * @return health points of player.
	 */
	public int getHP() {
		return HP;
	}

	/**
	 * sets hp, and sets the hp lost variable. validates that lost hp is not
	 * negative.
	 * 
	 * @param hp
	 *            sets hp.
	 */
	public void setHP(int hp) {
		// making sure hp is not negative.
		hp_lost = (this.HP - hp) < 0 ? this.HP : (this.HP - hp);
		statusLastShown = new Date();
		hp = hp < 0 ? 0 : hp;
		HP = hp;
	}

	/**
	 * Get score
	 * 
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            .
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return true if player is wet
	 */
	public boolean isWet() {
		return HP > 0 ? false : true;
	}

	/**
	 * 
	 * @return the player name.
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * 
	 * @param name
	 *            player name
	 */
	public void setPlayerName(String name) {
		playerName = name.length() > 10 ? name.substring(0, 10) : name;
	}

	/**
	 * Draws the player on the map.
	 * 
	 * @param g
	 *            draw graphic
	 */
	public void drawPlayer(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Image image = this.character.getCurrentImage();

		int linestart_pos_x, linestart_pos_y;
		int angleLineLength = 20;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);

		linestart_pos_x = posX + imageWidth / 2 - 5;
		linestart_pos_y = posY - imageHeight + 10;

		if (isWet()) {
			g2d.setColor(Color.BLUE);
			g2d.drawString("WET!", posX - 3, posY - 30);

		}

		g2d.setStroke(new BasicStroke(1));
		g2d.setFont(new Font("Arial", Font.BOLD, 12));
		g2d.setColor(Color.BLACK);
		g2d.drawString(playerName, posX, posY + 12);
		g2d.setFont(new Font("Arial", Font.BOLD, 10));
		g2d.drawString(isCPU() ? "[CPU]" : "", posX - 3, posY + 22);

		// g2d.setFont(new Font("Arial", Font.BOLD, 12));

		if (hp_lost > 0
				&& (new Date()).getTime() - statusLastShown.getTime() <= 1500) {
			g2d.setColor(Color.RED);
			g2d.drawString("-" + hp_lost + " HP", posX, posY - 50);

		} else {
			g2d.setColor(Game.BACKGROUND_CLR);
			g2d.drawString("-" + hp_lost + " HP", posX, posY - 50);
			hp_lost = 0;
			repaint();
		}

		drawCharacter(g2d);

		// drawing a line indicating the angle chosen.
		if (game.getCurrentPlayer() != null
				&& game.getCurrentPlayer().equals(this)) {
			g2d.setColor(Color.BLACK);

			int x = linestart_pos_x
					+ (angleValue == 90 ? 0 : (int) (angleLineLength * Math
							.cos(Math.toRadians(angleValue))));
			int y = linestart_pos_y
					- (int) (angleLineLength * Math.sin(Math
							.toRadians(angleValue)));

			if (lastAngleValue != -1 && lastAngleValue != angleValue) {
				/*
				 * deleting previews drawn throwing direction mark.
				 */
				g2d.setColor(Game.BACKGROUND_CLR);
				int last_x = linestart_pos_x
						+ (lastAngleValue == 90 ? 0
								: (int) (angleLineLength * Math.cos(Math
										.toRadians(lastAngleValue))));
				int last_y = linestart_pos_y
						- (int) (angleLineLength * Math.sin(Math
								.toRadians(lastAngleValue)));
				g2d.drawString("X", last_x, last_y);
			}

			g2d.setColor(Color.BLACK);

			g2d.drawString("X", x, y);

			// saving last chosen angle value for deleting the mark drawn
			// indicating the direction of the throw.
			lastAngleValue = angleValue;

		}

	}

	/**
	 * Generates the player position on the map. constrains: must not be placed
	 * close to other players.
	 * 
	 * @param game
	 *            : the running game instance
	 */
	private static Point generatePlayerPoint(Game game) {

		Point[] landScapePoints = game.getLandScapePoints();
		Player closestPlayer;
		int new_x;
		Point p1, p2;
		double m;
		int pos;

		do {
			pos = GeneralFunctions.generateRandomInt(1,
					landScapePoints.length - 4);
			p1 = new Point(landScapePoints[pos]);
			p2 = new Point(landScapePoints[pos + 1]);
			m = ((double) (p2.y - p1.y) / (double) (p2.x - p1.x));

			new_x = GeneralFunctions.generateRandomInt(p2.x, p1.x);

			closestPlayer = getClosestPlayer(new Point(new_x, (int) (m * new_x
					- m * p1.x + p1.y)), game.getPlayers());

		} while (closestPlayer != null
				&& Math.abs(closestPlayer.getPlayerPosition().x - new_x) < MIN_DISTANCE);

		return new Point(new_x, (int) (m * new_x - m * p1.x + p1.y));

	}

	/**
	 * takes a point p and an array list of players, gets the player who is
	 * positioned closest to the point p.
	 * 
	 * @param p
	 *            : a point on the map
	 * @param players
	 *            : an array list of players on the map.
	 */
	public static Player getClosestPlayer(Point p, ArrayList<Player> players) {
		int diff_x, i = 1;
		Player ret_player = null;

		if (!players.isEmpty()) {
			diff_x = Math.abs(players.get(0).getPlayerPosition().x - p.x);
			ret_player = players.get(0);

			while (i < players.size()) {

				if (Math.abs(players.get(i).getPlayerPosition().x - p.x) < diff_x) {
					ret_player = players.get(i);
					diff_x = Math.abs(players.get(i).getPlayerPosition().x
							- p.x);

				}
				i++;
			}
		}

		return ret_player;

	}

	/**
	 * takes an array list of players, gets the player who is positioned closest
	 * to this player.
	 * 
	 * @param players
	 *            : an arrayl ist of players on the map.
	 */
	public Player getClosestPlayer(ArrayList<Player> players) {
		int diff_x, i = 1;
		Player ret_player = null;
		ArrayList<Player> other_players = new ArrayList<Player>();

		for (int j = 0; j < players.size(); j++) {
			if (!this.equals(players.get(j)) && !players.get(j).isWet())
				other_players.add(players.get(j));
		}

		if (!other_players.isEmpty()) {
			diff_x = Math.abs(other_players.get(0).getPlayerPosition().x
					- this.posX);
			ret_player = other_players.get(0);

			while (i < other_players.size()) {

				if (Math.abs(other_players.get(i).getPlayerPosition().x
						- this.posX) < diff_x) {
					ret_player = other_players.get(i);
					diff_x = Math
							.abs(other_players.get(i).getPlayerPosition().x
									- this.posX);

				}
				i++;
			}
		}

		return ret_player;

	}

	/**
	 * Draws the character player on the map.
	 * 
	 * @param g
	 *            Graphics engine.
	 */
	public void drawCharacter(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		character.loadImage(angleValue > 90 ? "l" : "r");
		Image img = character.getCurrentImage();
		g2d.drawImage(img, posX, posY - img.getHeight(null) + 1, null);

	}

	/**
	 * Returns the position of the player.
	 * 
	 * @return the Point position of the player
	 * 
	 * @see Point
	 */
	public Point getPlayerPosition() {
		return new Point(this.posX, this.posY);
	}

	/**
	 * Get character width
	 */
	public int getWidth() {
		return character.getCurrentImage().getWidth(null);
	}

	/**
	 * Get character height
	 */
	public int getHeight() {
		return character.getCurrentImage().getWidth(null);
	}

	/**
	 * 
	 * @return power value
	 */
	public int getPowerValue() {
		return powerValue;
	}

	/**
	 * 
	 * @return angle value
	 */
	public int getAngleValue() {
		return angleValue;
	}

	/**
	 * 
	 * @param power
	 *            set value
	 */
	public void setPowerValue(int power) {
		if (game.getControlPanel() != null) {
			game.getControlPanel().getThrowingControl().setPowerValue(power);
		}
		powerValue = power;
	}

	/**
	 * 
	 * @param angle
	 *            set value
	 */
	public void setAngleValue(int angle) {
		if (game.getControlPanel() != null) {
			game.getControlPanel().getThrowingControl().setAngleValue(angle);
		}
		angleValue = angle;
	}

	/**
	 * 
	 * @return cpu or not
	 */
	public boolean isCPU() {
		return isCPU;
	}

	/**
	 * 
	 * @return character
	 */
	public PlayerCharacter getPlayerCharacter() {
		return character;
	}
}
