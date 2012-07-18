package game;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URL;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This is the help menu in this the player can read how the game works.
 * 
 * @author Niel Madlani Date 09/12/11
 */
@SuppressWarnings("serial")
public class Help extends JPanel implements ActionListener {

	Image img;
	JButton backButton;
	MainWindow window;

	/**
	 * @param get
	 *            the image
	 */
	public Help(MainWindow window) {
		this.window = window;

		URL url = Help.class.getResource("/images/help.gif");
		img = Toolkit.getDefaultToolkit().getImage(url);
		
		setLayout(new BorderLayout());
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		add(backButton, BorderLayout.SOUTH);

	}

	/**
	 * @param paint
	 *            the graphic so the image is visible.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(img, 0, 0, null);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		window.switchToPanel(new MainMenu(window));

	}
}
