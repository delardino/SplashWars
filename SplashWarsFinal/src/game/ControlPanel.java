package game;

import java.awt.*;
import java.math.BigDecimal;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The control panel class on top, deals with showing player, wind and timer
 * status as well as it inclues the angle and power controls.
 * 
 * @author Emil
 * @date 09/12/11
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ControlPanel extends JPanel {

	/**
	 * Player status panel
	 * 
	 * @see PlayerStatus
	 */
	private PlayerStatus playerStatus;

	/**
	 * Throwing control panel
	 * 
	 * @see ThrowingControls
	 */
	private ThrowingControls throwingControls;

	/**
	 * ViewWind panel
	 * 
	 * @see ViewWind
	 */
	private ViewWind WindViewer;

	/**
	 * Clock panel
	 * 
	 * @see Clock
	 */
	private Clock clock;

	/**
	 * Health bar panel
	 * 
	 * @see HealthBar
	 */
	private HealthBar healthBar;

	/**
	 * Player instance of the current player playing to show his status.
	 */
	private Player player;

	/**
	 * Default constructor creates all the inner panels
	 */
	public ControlPanel() {

		throwingControls = new ThrowingControls();
		WindViewer = new ViewWind();
		clock = new Clock();
		healthBar = new HealthBar();
		playerStatus = new PlayerStatus();

	}

	/**
	 * Draws the control panel with all its inner panels
	 * 
	 * @param g
	 *            Graphics
	 */
	protected void draw(Graphics g) {
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
		g2d.fillRect(20, 20, Game.RES_X - 50, 80);

		clock.draw(g, 700, 30); // drawing clock

		WindViewer.draw(g, 600, 43); // drawing wind status and arrow

		healthBar.draw(g, 35, 85); // drawing health bar

		playerStatus.draw(g, 35, 65); // drawing player name and score.

		throwingControls.draw(g, 270, 45); // drwaing the throwing controls.

	}

	/**
	 * 
	 * @return clock panel
	 */
	public Clock getClock() {
		return clock;
	}

	/**
	 * @return the throwingControls panel.
	 */
	public ThrowingControls getThrowingControl() {
		return throwingControls;
	}

	/**
	 * 
	 * @return the wind viewer panel
	 */
	public ViewWind getWindViewer() {
		return WindViewer;
	}

	/**
	 * Sets the player instance to use for name score and hp.
	 * 
	 * @param player
	 *            current playing player.
	 */
	public void setPlayer(Player player) {
		this.player = player;
		updateControlPanel();
	}

	/**
	 * Updates the control panel by setting the current player's score, hp and
	 * name, updating angle and power values, then finally repaints.
	 */
	public void updateControlPanel() {
		playerStatus.setPlayerName(player.getPlayerName());
		playerStatus.setScore(player.getScore());
		healthBar.setHP(player.getHP());

		if (player != null) {
			throwingControls.setAngleValue(player.getAngleValue());
			throwingControls.setPowerValue(player.getPowerValue());
		}
	}
}

/**
 * The Clock class, responsible for turn timing, placed on top of the screen.
 * 
 * @author Emil
 * @date 09/12/11
 * @version 1.0
 */
@SuppressWarnings("serial")
class Clock extends JPanel {

	/**
	 * the clock arc angle2, for showing how much time has passed.
	 */
	private int angle2;

	/**
	 * seconds left for clock to be fille of for turn to be finished
	 */
	private int sec_left = timeLimit;

	/**
	 * The time limit of each turn.
	 */
	protected final static int timeLimit = 30;

	/**
	 * Clock constrcutor, initiates the angles
	 */
	public Clock() {
		this.angle2 = 0;
	}

	/**
	 * Restarts the clock and sets the arc angle to 0.
	 */
	public void restart() {
		sec_left = timeLimit;
		angle2 = 0;
	}

	/**
	 * Moves the clock 1 step.
	 */
	public void moveClock() {
		angle2 -= 360 / timeLimit; // indicates 1 step.
		sec_left--;
	}

	/**
	 * @return Seconds left for turn to be finished.
	 */
	public int getSecondsLeft() {
		return sec_left;
	}

	/**
	 * mutator for decreasing the time left.
	 */
	public void decreaceSecsLeft() {
		this.sec_left--;
	}

	/**
	 * Drawing the clock.
	 * 
	 * @param g
	 *            Graphics engine.
	 * @param x
	 *            position x of clock
	 * @param y
	 *            position y of clock.
	 */
	protected void draw(Graphics g, int x, int y) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawArc(x, y, 60, 60, 0, 360);
		g2d.setColor(Color.RED);
		g2d.fillArc(x, y, 60, 60, 90, angle2);
	}

}

/**
 * Wind viewer class
 * 
 * @author Emil
 * @date 09/12/11
 * @version 1.0
 */
@SuppressWarnings("serial")
class ViewWind extends JPanel {

	/**
	 * The current wind value.
	 */
	private float windValue;

	/**
	 * Maximum wind value.
	 */
	private final static float MAX_WIND_VALUE = 2;

	/**
	 * Minimum wind value.
	 */
	private final static float MIN_WIND_VALUE = -2;

	/**
	 * Constructor, setting the wind value to a randomally generated number.
	 */
	public ViewWind() {
		windValue = GeneralFunctions.generateRandomInt(MIN_WIND_VALUE,
				MAX_WIND_VALUE);
	}

	/**
	 * @return the current wind value.
	 */
	public float getWindValue() {
		return windValue;
	}

	/**
	 * @param wind
	 *            Wind value
	 */
	public void setWindValue(int wind) {
		windValue = wind;
	}

	/**
	 * Generates a random wind between the miniumum and maximum vaules.
	 */
	public void setWindValue() {
		windValue = GeneralFunctions.generateRandomInt(MIN_WIND_VALUE,
				MAX_WIND_VALUE);
	}

	/**
	 * Draws the wind status, arrow and value. positioned in x,y
	 * 
	 * @param g
	 *            Graphics engine.
	 * @param x
	 *            x position of the wind viewer
	 * @param y
	 *            y position of the wind viewier
	 */
	public void draw(Graphics g, int x, int y) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(Color.BLACK);
		g2d.drawString("Wind: "
				+ new BigDecimal(Math.abs(this.windValue) * 10).setScale(1,
						BigDecimal.ROUND_HALF_UP).doubleValue() + " m/s", x, y); // shows 2 digits after the dot.
		y += 20;
		x += 10;
		g2d.drawLine(x, y, x + 40, y);
		Polygon p = new Polygon();
		if (this.windValue > 0) {
			// drawing an arrow poniting to the right.
			p.addPoint(x - 5, y);
			p.addPoint(x + 10, y + 10);
			p.addPoint(x + 10, y - 10);
		} else {
			// drawing an arrow pointing to the left.
			p.addPoint(x + 40 + 5, y);
			p.addPoint(x + 40 - 10, y + 10);
			p.addPoint(x + 40 - 10, y - 10);
		}
		g2d.fillPolygon(p);
	}

}

/**
 * Throwing control panel
 * 
 * @author Emil
 * @date 09/12/11
 * @version 1.0
 */

@SuppressWarnings("serial")
class ThrowingControls extends JPanel {

	/**
	 * Maximum angle value allowed
	 */
	private final static int MAX_ANGLE = 180;

	/**
	 * Minimum angle allwoed
	 */
	private final static int MIN_ANGLE = 0;

	/**
	 * Maximum power value allowed.
	 */
	private final static int MAX_POWER = 100;

	/**
	 * minimum power value allowed.
	 */
	private final static int MIN_POWER = 0;

	/**
	 * Power value to show.
	 */
	private int powerValue;

	/**
	 * Angle value to show.
	 */
	private int angleValue;

	/**
	 * are Buttons disabled
	 */
	private boolean buttonsDisabled = false;

	/**
	 * Power incremental arrow.
	 */
	private clickablePolygon powerIncArrow;

	/**
	 * Power decremental arrow.
	 */
	private clickablePolygon powerDecArrow;

	/**
	 * Angle incremental arrow.
	 */
	private clickablePolygon angleIncArrow;

	/**
	 * Angle decremental arrow.
	 */
	private clickablePolygon angleDecArrow;

	/**
	 * Default constrcutor, assigns default values for power and angle.
	 */
	public ThrowingControls() {
		powerValue = 50;
		angleValue = 90;
	}

	/**
	 * Assigns values for angle and power.
	 * 
	 * @param power
	 *            power value
	 * @param angle
	 *            angle value
	 */
	public ThrowingControls(int power, int angle) {
		this.powerValue = power;
		this.angleValue = angle;
	}

	/**
	 * @param power
	 *            power value
	 */
	public void setPowerValue(int power) {
		if (power <= MAX_POWER && power >= MIN_POWER)
			powerValue = power;
		else powerValue = 80;
	}

	/**
	 * @param angle
	 *            Angle value.
	 */
	public void setAngleValue(int angle) {
		if (angle <= MAX_ANGLE && angle >= MIN_ANGLE)
			angleValue = angle;
		else angleValue = 60;
	}

	/**
	 * @return power value.
	 */
	public int getPowerValue() {
		return powerValue;
	}

	/**
	 * @return angle value.
	 */
	public int getAngleValue() {
		return angleValue;
	}

	/**
	 * disables the throwing buttons.
	 */
	public void disableButtons() {
		buttonsDisabled = true;
	}

	/**
	 * Enables the buttons
	 */
	public void enableButtons() {
		buttonsDisabled = false;
	}

	/**
	 * Draws the throwing controls at x,y
	 * 
	 * @param g
	 *            Graphics engine
	 * @param x
	 *            x position of throwing controls
	 * @param y
	 *            y position of throwing controls.
	 */
	public void draw(Graphics g, int x, int y) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(5));

		g2d.setColor(Color.BLACK);
		g2d.drawString("Power: ", x, y);
		g2d.drawString(powerValue + "", x + 85, y);

		// sets button color to grey if they're disabled, otherwise, paint them
		// in blue like.
		g2d.setColor(buttonsDisabled ? Color.GRAY : new Color(54, 100, 139));

		// painting the power decrimental arrow.
		powerDecArrow = new clickablePolygon();
		powerDecArrow.addPoint(x + 55, y - 5);
		powerDecArrow.addPoint(x + 70, y + 5);
		powerDecArrow.addPoint(x + 70, y - 15);
		powerDecArrow.draw(g2d);

		// painting the angle incrimental arrow.
		angleIncArrow = new clickablePolygon();
		angleIncArrow.addPoint(x + 135, y + 30);
		angleIncArrow.addPoint(x + 120, y + 40);
		angleIncArrow.addPoint(x + 120, y + 20);
		angleIncArrow.draw(g2d);

		// painting the angle decrimental arrow.
		angleDecArrow = new clickablePolygon();
		angleDecArrow.addPoint(x + 55, y + 30);
		angleDecArrow.addPoint(x + 70, y + 40);
		angleDecArrow.addPoint(x + 70, y + 20);
		angleDecArrow.draw(g2d);

		// painting the power incrimental arrow.
		powerIncArrow = new clickablePolygon();
		powerIncArrow.addPoint(x + 135, y - 5);
		powerIncArrow.addPoint(x + 120, y + 5);
		powerIncArrow.addPoint(x + 120, y - 15);
		powerIncArrow.draw(g2d);

		// Throw button drawing.
		g2d.fillRect(x + 185, 30, 100, 60);
		g2d.setColor(Color.BLACK);
		g2d.drawLine(x + 185, y - 15, x + 285, y - 15);
		g2d.drawLine(x + 285, y - 15, x + 285, y + 45);
		g2d.drawLine(x + 285, y + 45, x + 185, y + 45);
		g2d.drawLine(x + 185, y + 45, x + 185, y - 15);
		g2d.drawString("THROW!", x + 210, y + 17);

		// Drawing angle value.
		g2d.setColor(Color.BLACK);
		g2d.drawString("Angle: ", x, y + 35);
		g2d.drawString(angleValue + "", x + 85, y + 35);
	}
}

/**
 * Player status viewer class. shows the player name, score and health bar.
 * 
 * @author Emil - Group M
 * 
 */
@SuppressWarnings("serial")
class PlayerStatus extends JPanel {

	/**
	 * The player name to show
	 */
	private String playerName;

	/**
	 * The score to show.
	 */
	private int score;

	/**
	 * Constructor initiates the player anme and score.
	 */
	public PlayerStatus() {

		this.playerName = "";
		this.score = 0;
	}

	/**
	 * @param name
	 *            player name.
	 */
	public void setPlayerName(String name) {
		// limits the name to 10 characters by cutting it.
		playerName = (name.length() > 10 ? name.substring(0, 10) + "..." : name);
	}

	/**
	 * @param score
	 *            score to set.
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Draws the player status on the control panel.
	 * 
	 * @param g
	 *            Graphics engine.
	 * @param x
	 *            X position of the player status panel
	 * @param y
	 *            Y position of the player status panel.
	 */
	public void draw(Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setStroke(new BasicStroke(2));
		g2d.setFont(new Font("Tahoma", Font.BOLD, 12));

		// Playername and score
		g2d.setColor(Color.BLACK);
		g2d.drawString("Player: " + playerName, 35, 45);
		g2d.drawString("Score: " + score, 175, 45);
	}

}

/**
 * Player health bar
 * 
 * @author Emil
 */
@SuppressWarnings("serial")
class HealthBar extends JPanel {

	/**
	 * Health points
	 */
	private int hp;

	/**
	 * constructor initiates hp.
	 */
	public HealthBar() {
		this.hp = 0;
	}

	/**
	 * @param hp
	 *            hp to set
	 */
	public void setHP(int hp) {
		this.hp = hp;
	}

	/**
	 * Draws the health bar of the player, depending on the HP value.
	 * 
	 * @param g
	 *            Graphics engine.
	 * @param x
	 *            X position of the health bar.
	 * @param y
	 *            Y position of the Health bar.
	 */
	protected void draw(Graphics g, int x, int y) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(5F));
		g2d.setFont(new Font("Tahoma", Font.BOLD, 12));

		g2d.drawString("HP: ", x, y);
		g2d.setColor(Color.RED);
		g2d.fillRect(x + 37, y - 13, 168 * hp / 100, 17); // draw a red
															// rectriangle
															// indicating the HP
															// left for the
															// player

		g2d.setColor(Color.BLACK);

		g2d.drawLine(x + 35, y - 15, x + 205, y - 15);
		g2d.drawLine(x + 205, y - 15, x + 205, y + 5);
		g2d.drawLine(x + 205, y + 5, x + 35, y + 5);
		g2d.drawLine(x + 35, y + 5, x + 35, y - 15);

	}
}

/**
 * A polygon used for drawing the power and angle value control.
 * 
 * @author Tahe Odeh
 * @date 09/12/11
 * @version 1.0
 */
@SuppressWarnings("serial")
class clickablePolygon extends JButton {
	/**
	 * the actual polygon.
	 */
	private Polygon p;

	/**
	 * Constructor initiates polygon.
	 */
	public clickablePolygon() {
		this.p = new Polygon();
	}

	/**
	 * Draws the polygon.
	 * @param g Graphics engine.
	 */
	public void draw(Graphics g) {
		if (p != null)
			g.fillPolygon(p);

	}
	
	/**
	 * @see Polygon.addPoint
	 */
	public void addPoint(int x, int y) {
		p.addPoint(x, y);
	}

}
