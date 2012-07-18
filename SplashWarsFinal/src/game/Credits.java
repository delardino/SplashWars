package game;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class just draw the names of the creators of this game, with the
 * ordinary draw method
 * 
 * @author Emil
 * @version 1.0
 * @date 09/12/11
 */

@SuppressWarnings("serial")
public class Credits extends JPanel implements ActionListener {

	/**
	 * Main window holder of this panel.
	 */
	MainWindow window;

	/**
	 * back button
	 */
	JButton backButton;

	/**
	 * Constrcutor for initializing the screen.
	 * 
	 */
	public Credits(MainWindow window) {
		this.window = window;
		setLayout(new BorderLayout());
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		add(backButton, BorderLayout.SOUTH);
	}

	/**
	 * panting the panel.
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
		g2d.fillRect(0, 0, 800, 600);

		// Highscore
		g2d.setColor(Color.BLACK);
		g2d.drawString("Yeah! We made this game:", 220, 180);
		g2d.drawString("---------------------------------------------", 220,
				200);
		g2d.drawString("Emil Martensson", 220, 240);

		g2d.drawString("Taher Odeh", 220, 280);

		g2d.drawString("Aydan Halilov", 220, 320);

		g2d.drawString("Niel Madlani", 220, 360);

		g2d.drawString("Dong Yu", 220, 400);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		window.switchToPanel(new MainMenu(window));

	}
}
