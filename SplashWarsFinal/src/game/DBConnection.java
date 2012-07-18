package game;

import java.awt.Point;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Database connection class Connects to an SQLite database, responsible for
 * saving and loading game, as well as saving high scores.
 * 
 * @author Taher Odeh
 * @date 09/12/01
 */
public class DBConnection {

	/**
	 * A static connection instance to the database.
	 */
	protected static Connection connection;

	/**
	 * Default constructor, connects to data.db, creates it if not found, then
	 * creates the needed tables for the game.
	 * 
	 * @throws SQLException
	 */
	public DBConnection() throws SQLException {

		try {

			// loading JDBC for sqlite.
			Class.forName("org.sqlite.JDBC");

			// connecting to the schema.
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");

			// creating the tables.
			initDBTables();

		} catch (SQLException e) {
			// SQL error occured
			GeneralFunctions.showDialogBox(e.getMessage() + "\n"
					+ e.getSQLState());

			// org.sqlite.JDBC not found.
		} catch (ClassNotFoundException e) {
			GeneralFunctions
					.showDialogBox("Error loading SQLite JDBC. please download the required library.");

		}
	}

	/**
	 * Create database tables if they do not exist in the database.
	 */
	private static void initDBTables() {

		/*
		 * query for creating the player table in the database.
		 */
		String createPlayerSc = "CREATE TABLE IF NOT EXISTS Player  ("
				+ "player_id INTEGER,"
				// +
				// "game_id INT(3) REFERENCES Game ON DELETE CASCADE ON UPDATE CASCADE,"
				+ "name VARCHAR(200)," + "hp INT(3)," + "pos_x INT(3),"
				+ "pos_y INT(3)," + "score INT(3)," + "angle INT(3),"
				+ "power INT(3)," + "cpu BOOLEAN," + "character_number INT(3),"
				+ "PRIMARY KEY (player_id)" + ");";

		/*
		 * query for creating the Game table in the database.
		 */
		String createGameSc = "CREATE TABLE IF NOT EXISTS Game("
				+ "game_id INT(3) PRIMARY KEY," + "wind FLOAT,"
				+ "player_turn INT(3)," + "remaining_time INT(3),"
				+ "difficultyLevel INT(3),"
				+ "creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + ");";

		/*
		 * query for creating the player Landscape points in the database.
		 */
		String createLandScapeSc = "CREATE TABLE IF NOT EXISTS Landscape_Points("
				+ "x INT(3)," + "y INT(3),"
				// +
				// "game_id INT(3) REFERENCES Game ON DELETE CASCADE ON UPDATE CASCADE,"
				+ "PRIMARY KEY (x,y)" + ");";

		/*
		 * query for creating the high score table in the database.
		 */
		String createHighScoresSc = "CREATE TABLE IF NOT EXISTS HighScores("
				+ "id INTEGER PRIMARY KEY, " + "name VARCHAR(200),"
				+ "score INT(3),"
				+ "creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";

		try {
			if (connection == null) {
				new DBConnection();
			}
			Statement stat = connection.createStatement();
			stat.executeUpdate(createGameSc);
			stat.executeUpdate(createLandScapeSc);
			stat.executeUpdate(createPlayerSc);
			stat.executeUpdate(createHighScoresSc);
		} catch (SQLException e) {
			GeneralFunctions.showDialogBox(e.getMessage() + "\n"
					+ e.getSQLState());
			
		}
	}

	/**
	 * saves the passed game instance into the database.
	 * 
	 * @param g Game instance to be saved
	 */
	public static void saveGame(Game g) {

		try {

			Statement statement = connection.createStatement();

			// checking a prev saved game exists.
			ResultSet rs1 = statement.executeQuery("SELECT * FROM Game");

			if (!rs1.next()
					|| JOptionPane.showConfirmDialog(null,
							"Overwrite last saved game ?") == JOptionPane.YES_OPTION) {

				statement.executeUpdate("DELETE FROM Game");

				/*
				 * Saving the currect game.
				 */
				PreparedStatement prep = connection
						.prepareStatement("INSERT INTO Game ( wind, player_turn, remaining_time, difficultyLevel) VALUES (?, ?, ?, ?)");

				prep.setFloat(1, g.getControlPanel().getWindViewer()
						.getWindValue());
				prep.setInt(2, g.getCurrentPlayerIndex());
				prep.setInt(3, g.getControlPanel().getClock().getSecondsLeft());
				prep.setInt(4, g.getDifficultyLevel());

				prep.executeUpdate();

				prep.close();
				
				// saving landscape points.
				statement.executeUpdate("DELETE FROM Landscape_Points");

				PreparedStatement prepLS = connection
						.prepareStatement("INSERT INTO Landscape_Points (x, y) VALUES (?, ?)");
				for (int i = 0; i < g.getLandScapePoints().length; i++) {
					prepLS.setInt(1, g.getLandScapePoints()[i].x);
					prepLS.setInt(2, g.getLandScapePoints()[i].y);
					prepLS.addBatch();
				}

				prepLS.executeBatch();
				connection.setAutoCommit(true);

				statement.executeUpdate("DELETE FROM Player");

				/*
				 * saving players.
				 */
				prep = connection
						.prepareStatement("INSERT INTO Player (name, hp, pos_x, pos_y, score, angle, power, cpu, character_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

				// saving each player to the db
				for (int j = 0; j < g.getPlayers().size(); j++) {
					Player p = g.getPlayers().get(j);
					prep.setString(1, p.getPlayerName());
					prep.setInt(2, p.getHP());
					prep.setInt(3, p.getPlayerPosition().x);
					prep.setInt(4, p.getPlayerPosition().y);
					prep.setInt(5, p.getScore());
					prep.setInt(6, p.getAngleValue());
					prep.setInt(7, p.getPowerValue());
					prep.setBoolean(8, p.isCPU());
					prep.setInt(9, p.getPlayerCharacter().getCharacterNumber());
					prep.addBatch();
				}
				prep.executeBatch();

				// Game saved confirmation window.
				JOptionPane.showMessageDialog(null, "Game Saved!");

				rs1.close();

			}

		} catch (SQLException e) {
			GeneralFunctions.showDialogBox(e.getMessage());
			System.exit(0);
		}
	}

	/**
	 * Loads the game 
	 * @return an instance of the loaded game.
	 */
	public static Game loadGame() {

		Game returnedGame;
		
		int currentPlayerIndex;
		int windValue;
		int difficultyLevel;
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Point> landscapePoints = new ArrayList<Point>();

		if (connection != null) {
			try {
				Statement stat = connection.createStatement();

				ResultSet isGameLoadedRS = stat
						.executeQuery("SELECT COUNT(*) as c FROM Game");

				isGameLoadedRS.next();

				if (isGameLoadedRS.getInt("c") > 0) {

					ResultSet gameRS = stat
							.executeQuery("SELECT player_turn, remaining_time, creation_time, wind, difficultyLevel  FROM Game");

					currentPlayerIndex = gameRS.getInt("player_turn");
					windValue = gameRS.getInt("wind");
					difficultyLevel = gameRS.getInt("difficultyLevel");

					ResultSet playersRS = stat
							.executeQuery("SELECT name, hp, score, pos_x, pos_y, angle, power, cpu, character_number FROM Player LIMIT 0,4");
					
					// adding players.
					while (playersRS.next()) {
						Player p = new Player(playersRS.getString("name"),
								playersRS.getBoolean("cpu"), playersRS
										.getInt("character_number"), playersRS
										.getInt("score"), playersRS
										.getInt("hp"), playersRS
										.getInt("angle"), playersRS
										.getInt("power"), playersRS
										.getInt("pos_x"), playersRS
										.getInt("pos_y"));
						players.add(p);

					}

					ResultSet landscapeRS = stat
							.executeQuery("SELECT x, y FROM Landscape_Points");

					while (landscapeRS.next()) {
						landscapePoints.add(new Point(landscapeRS.getInt("x"),
								landscapeRS.getInt("y")));
					}

					Point[] points = new Point[landscapePoints.size()];

					for (int i = 0; i < landscapePoints.size(); i++) {
						points[i] = landscapePoints.get(i);
					}

					returnedGame = new Game(players, points, windValue,
							currentPlayerIndex, difficultyLevel);

					return returnedGame;
				}
			} catch (SQLException e) {
				GeneralFunctions.showDialogBox(e.getMessage());
				System.exit(0);

			}
		}
		return null;
	}

	/**
	 * Saves scores to the database.
	 * @param g Game to save scores from.
	 */
	public static void saveScores(Game g) {
		try {
			Statement stat = connection.createStatement();
			PreparedStatement prep = connection
					.prepareStatement("INSERT INTO HighScores (id, name, score) VALUES ( (SELECT MAX(id) FROM HighScores) + 1 ,?, ?)");

			for (int i = 0; i < g.getPlayers().size(); i++) {
				prep.setString(1, g.getPlayers().get(i).getPlayerName());
				prep.setInt(2, g.getPlayers().get(i).getScore());
				prep.addBatch();
			}

			prep.executeBatch();

			stat
					.execute("DELETE FROM HighScores WHERE id NOT IN ( SELECT id FROM HighScores ORDER BY score DESC LIMIT 0,5 )");

		} catch (SQLException e) {
			GeneralFunctions.showDialogBox(e.getMessage());
			System.exit(0);
		}
	}
}
