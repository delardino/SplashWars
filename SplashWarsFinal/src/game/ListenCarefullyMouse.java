package game;

import java.awt.event.*;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * In game general mouse listener class.
 * 
 * this class takes care of mouse click fires, check the area clicked and figure
 * out what button it is.
 * 
 * @version 1.0
 * @date 09/12/02
 * @author Aydan Halilov
 */
class ListenCarefullyMouse implements MouseListener {

	/** game instance */
	Game game;

	/** Click timer, used for mouse key hold. */
	Timer clickTimer;

	/**
	 * Indicates the time a button has been clicked.
	 */
	private Date last_clicked = new Date(); // make sure cannot click a lot on

	// save

	public ListenCarefullyMouse() {
		// and quit.
		last_clicked = new Date();
	}

	/**
	 * Apply a mouse click, for key hold value increasement use.
	 */
	private void apply(MouseEvent e) {

		int cur_value = 0; // temporary current value for area or power.

		game = (Game) e.getComponent();
		// getting the game instance from the event.

		if (!game.isBallFlying() && !game.isGameFinished()
				&& game.gameStarted()) {
			// checking if ball is in the air still.

			if (e.getX() >= 326 && e.getY() >= 30 && e.getX() <= 340
					&& e.getY() <= 46) { // Power down arrow is clicked

				cur_value = game.getControlPanel().getThrowingControl()
						.getPowerValue(); // getting current value.
				game.getControlPanel().getThrowingControl().setPowerValue(
						--cur_value); // updating power value in control panel.
				game.getCurrentPlayer().setPowerValue(cur_value); // updateing
				// power
				// value in
				// player
			}

			if (e.getX() >= 390 && e.getY() >= 30 && e.getX() <= 403
					&& e.getY() <= 47) { // Power up arrow is clicked
				cur_value = game.getControlPanel().getThrowingControl()
						.getPowerValue();
				game.getControlPanel().getThrowingControl().setPowerValue(
						++cur_value);
				game.getCurrentPlayer().setPowerValue(cur_value);
			}

			if (e.getX() >= 328 && e.getY() >= 66 && e.getX() <= 338
					&& e.getY() <= 82) { // Angle left arrow is clicked
				cur_value = game.getControlPanel().getThrowingControl()
						.getAngleValue();
				game.getControlPanel().getThrowingControl().setAngleValue(
						++cur_value);
				game.getCurrentPlayer().setAngleValue(cur_value);
			}

			if (e.getX() >= 390 && e.getY() >= 65 && e.getX() <= 400
					&& e.getY() <= 81) { // Angle right arrow is clicked

				cur_value = game.getControlPanel().getThrowingControl()
						.getAngleValue();
				game.getControlPanel().getThrowingControl().setAngleValue(
						--cur_value);
				game.getCurrentPlayer().setAngleValue(cur_value);
			}

			if (e.getX() >= 460 && e.getY() >= 33 && e.getX() <= 552
					&& e.getY() <= 85) { // Throw button is clicked
				game.throwBall(game.getCurrentPlayer().getAngleValue(), game
						.getCurrentPlayer().getPowerValue());
			}
			game.repaint(); // repainting
		}

	}

	/**
	 * Mouse click event action, searches for save or quit clicks, otherwise
	 * looks for the throwing control buttons.
	 * 
	 * @param e
	 *            Mouse event.
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getX() >= 30 && e.getY() >= 3 && e.getX() <= 50 && e.getY() <= 23) { // Quit
			// button
			// is
			// clicked
			if ((new Date()).getTime() - last_clicked.getTime() >= 500) {
				if (JOptionPane.showConfirmDialog(null,
						"Are you sure you want to finish the game?") == JOptionPane.OK_OPTION) {
					game.finishGame();
				}
				last_clicked = new Date();

			}
		}

		else if (e.getX() >= 55 && e.getY() >= 3 && e.getX() <= 75
				&& e.getY() <= 23) { // Save button is clicked
			if ((new Date()).getTime() - last_clicked.getTime() >= 1000) {
				DBConnection.saveGame(game);
				last_clicked = new Date();
			}
		} else
			apply(e);
	}

	/**
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * For usability, a timer is used when mouse is pressed for speeding up/down
	 * the values of angle and power.
	 */
	@Override
	public void mousePressed(MouseEvent e) {

		/**
		 * the mouse event, has to be final in order to use it in inline
		 * classes.
		 */
		final MouseEvent ev = e;

		/**
		 * An inline Action Listener class.
		 */
		clickTimer = new Timer(40, new ActionListener() {
			// delaying every 40 milliseconds.
			@Override
			public void actionPerformed(ActionEvent ae) {
				apply(ev);
			}
		});
		clickTimer.start(); // starting the timer.
	}

	/**
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		clickTimer.stop(); // stopping timer
	}
}
