package game;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * The graphical part of the game. This is where the landscape will be drawed
 * 
 * @author Niel Madlani
 * 
 *         Date 09/12/11 Version 1.0
 */
@SuppressWarnings("serial")
class LandScape extends JPanel {

	// Points of landscape
	private Point[] landScapePoints;

	/**
	 * Constructor for creating a random landscape.
	 */
	public LandScape() {
		createLandScapePoints();
		this.setPreferredSize(new Dimension(Game.RES_X, Game.RES_Y));
	}

	/**
	 * Constructor for creating a landscape from points passed.
	 * 
	 * @param points
	 *            points to creates lines for landscape.
	 */
	public LandScape(Point[] points) {
		landScapePoints = points;
		this.setPreferredSize(new Dimension(Game.RES_X, Game.RES_Y));
	}

	/**
	 * 
	 * @return the points that build the landscape.
	 */
	public Point[] getLandScapePoints() {
		return this.landScapePoints;
	}

	/**
	 * 
	 * @param points
	 *            the points that create the landscape.
	 */
	public void setLandScapePoints(Point[] points) {
		landScapePoints = points;
	}

	/**
	 * Creates random landscape points. with limitation of x and y cordinates.
	 * 
	 */
	private void createLandScapePoints() {
		if (landScapePoints == null) {
			ArrayList<Point> points = new ArrayList<Point>();

			points.add(new Point(0, Game.RES_Y));

			for (int i = 0; i <= Game.RES_X; i += Game.RES_X / 10) {
				// generating points between Y-200 to Y-100;
				points.add(new Point(i, GeneralFunctions.generateRandomInt(
						Game.RES_Y - 200, Game.RES_Y - 100)));
			}

			points.add(new Point(points.get(points.size() - 1).x, Game.RES_Y));

			/*
			 * Converting arraylist to array of points
			 */
			landScapePoints = new Point[points.size()];
			for (int j = 0; j < points.size(); j++) {
				landScapePoints[j] = points.get(j);
			}

		}
	}

	/**
	 * drawing the map on screen. by drawing lines between each landscape point.
	 * 
	 * @param [g] draws the graphic
	 */
	public void drawMap(Graphics g) {

		Polygon polygon = new Polygon();

		Graphics2D g2d = (Graphics2D) g;
		RenderingHints hints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		g2d.setRenderingHints(hints);

		for (int i = 0; i < landScapePoints.length; i++) {
			polygon.addPoint(landScapePoints[i].x, landScapePoints[i].y);
		}

		Color c = Color.GREEN;
		g2d.setColor(c);

		g2d.fillPolygon(polygon);

	}

	/**
	 * 
	 * @param x
	 *            x position for getting the landscape y of.
	 * @return y position on lanscape x.
	 */
	protected int getLandScapeY(int x) {
		for (int i = 0; i < landScapePoints.length - 1; i++) {
			// checking if ball hit the landscape

			if (x >= landScapePoints[i].x && x < landScapePoints[i + 1].x) {
				// checking if ball is in the x area of the 2 checked landscape
				// points

				double m = ((double) (landScapePoints[i].y - landScapePoints[i + 1].y) / (double) (landScapePoints[i].x - landScapePoints[i + 1].x));
				// calculating the slope point of the 2 landscape points

				return (int) (m * x - m * landScapePoints[i].x + landScapePoints[i].y);
			}
		}
		return -1; // this is never reached.

	}
}