package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class is the menu before game starts.This menu include how many players
 * will be chosen,the characters' color and human or PC plays the character.
 * 
 * @author Dong Yu
 * @date 09/12/11
 * @version 1.0
 */

@SuppressWarnings("serial")
public class StartGameMenu extends JPanel implements ActionListener {
	private MainWindow window;

	/**
	 * Constructor, creates new form startGameMenu
	 * 
	 * @param the
	 *            window container of this panel.
	 */
	public StartGameMenu(MainWindow window) {
		initComponents();
		this.window = window;
	}

	/**
	 * initiates Components such as buttons and layouts, this method was created
	 * using the NetBeans visual designer.
	 */
	private void initComponents() {

		numPlayersLabel = new javax.swing.JLabel();
		SpinnerListModel spinnerList = new SpinnerListModel(new Integer[] { 2,
				3, 4 });
		jSpinner1 = new JSpinner(spinnerList);
		playerOnePanel = new javax.swing.JPanel();
		nameLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		Item1 = new javax.swing.JComboBox();
		jLabel3 = new javax.swing.JLabel();
		jRadioButton1 = new javax.swing.JRadioButton();
		jRadioButton2 = new javax.swing.JRadioButton();
		playerPanel2 = new javax.swing.JPanel();
		jLabel4 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		Item2 = new javax.swing.JComboBox();
		jLabel5 = new javax.swing.JLabel();
		jRadioButton3 = new javax.swing.JRadioButton();
		jRadioButton4 = new javax.swing.JRadioButton();
		playerThreePanel = new javax.swing.JPanel();
		jLabel6 = new javax.swing.JLabel();
		jTextField3 = new javax.swing.JTextField();
		Item3 = new javax.swing.JComboBox();
		jLabel7 = new javax.swing.JLabel();
		jRadioButton5 = new javax.swing.JRadioButton();
		jRadioButton6 = new javax.swing.JRadioButton();
		playerFourPanel = new javax.swing.JPanel();
		jLabel8 = new javax.swing.JLabel();
		jTextField4 = new javax.swing.JTextField();
		Item4 = new javax.swing.JComboBox();
		jLabel9 = new javax.swing.JLabel();
		jRadioButton7 = new javax.swing.JRadioButton();
		jRadioButton8 = new javax.swing.JRadioButton();
		numPlayersLabel0 = new javax.swing.JLabel();
		jRadioButton9 = new javax.swing.JRadioButton();

		jRadioButton11 = new javax.swing.JRadioButton();
		jButton1 = new javax.swing.JButton();
		jButton1.addActionListener(this);

		numPlayersLabel.setText("Choose the number of players:");
		numPlayersLabel.setPreferredSize(new java.awt.Dimension(230, 25));

		jSpinner1.addChangeListener(new ChangeListener() {

			/**
			 * Fired whenever the spinner value is changed for choosing the
			 * number of players.
			 * 
			 * @param e Change Event.
			 */
			@Override
			public void stateChanged(ChangeEvent e) {
				switch ((Integer) ((JSpinner) e.getSource()).getValue()) {
				// how many players were chosen to play. 
				case 2:
					playerThreePanel.setVisible(false);
					playerFourPanel.setVisible(false);
					break;
				case 3:
					playerThreePanel.setVisible(true);
					playerFourPanel.setVisible(false);
					break;
				case 4:
					playerThreePanel.setVisible(true);
					playerFourPanel.setVisible(true);
					break;

				}
			}
		});

		playerOnePanel.setPreferredSize(new java.awt.Dimension(800, 70));

		nameLabel1.setText("Name:");

		jTextField1.setPreferredSize(new java.awt.Dimension(150, 21));

		Item1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Green", "Red", "Blue", "Naked" }));

		jLabel3.setText("Character:");

		jRadioButton1.setSelected(true);

		jRadioButton1.setText("Human");
		jRadioButton1.addActionListener(new ActionListener() {

			/**
			 * only can choose one radio button between jRadioButtion1 and 
			 * jRadioButton2.
			 * 
			 * @param e Action event.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				jRadioButton1.setSelected(true);
				jRadioButton2.setSelected(false);
			}
		});

		jRadioButton2.setText("PC");
		jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton2ActionPerformed(evt);
			}
		});

		jRadioButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jRadioButton2.setSelected(true);
				jRadioButton1.setSelected(false);
			}
		});

		javax.swing.GroupLayout playerOnePanelLayout = new javax.swing.GroupLayout(
				playerOnePanel);
		playerOnePanel.setLayout(playerOnePanelLayout);
		playerOnePanelLayout
				.setHorizontalGroup(playerOnePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								playerOnePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												playerOnePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																playerOnePanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jRadioButton1)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jRadioButton2))
														.addGroup(
																playerOnePanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				nameLabel1)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jTextField1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jLabel3)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				Item1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(64, Short.MAX_VALUE)));
		playerOnePanelLayout
				.setVerticalGroup(playerOnePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								playerOnePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												playerOnePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																nameLabel1)
														.addComponent(
																jTextField1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel3)
														.addComponent(
																Item1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												playerOnePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jRadioButton1)
														.addComponent(
																jRadioButton2))
										.addContainerGap(10, Short.MAX_VALUE)));

		playerPanel2.setPreferredSize(new java.awt.Dimension(800, 70));

		jLabel4.setText("Name:");

		jTextField2.setPreferredSize(new java.awt.Dimension(150, 21));

		Item2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Green", "Red", "Blue", "Naked" }));

		jLabel5.setText("Character:");

		jRadioButton3.setSelected(true);
		jRadioButton3.setText("Human");
		jRadioButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jRadioButton3.setSelected(true);
				jRadioButton4.setSelected(false);
			}
		});

		jRadioButton4.setText("PC");
		jRadioButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jRadioButton4.setSelected(true);
				jRadioButton3.setSelected(false);
			}
		});

		javax.swing.GroupLayout playerPanel2Layout = new javax.swing.GroupLayout(
				playerPanel2);
		playerPanel2.setLayout(playerPanel2Layout);
		playerPanel2Layout
				.setHorizontalGroup(playerPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								playerPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												playerPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																playerPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jRadioButton3)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jRadioButton4))
														.addGroup(
																playerPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel4)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jTextField2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jLabel5)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				Item2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(64, Short.MAX_VALUE)));
		playerPanel2Layout
				.setVerticalGroup(playerPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								playerPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												playerPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																jTextField2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel5)
														.addComponent(
																Item2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												playerPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jRadioButton3)
														.addComponent(
																jRadioButton4))
										.addContainerGap(10, Short.MAX_VALUE)));

		playerThreePanel.setPreferredSize(new java.awt.Dimension(800, 70));
		playerThreePanel.setVisible(false);
		jLabel6.setText("Name:");

		jTextField3.setPreferredSize(new java.awt.Dimension(150, 21));

		Item3.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Green", "Red", "Blue", "Naked" }));

		jLabel7.setText("Character:");

		jRadioButton5.setSelected(true);
		jRadioButton5.setText("Human");
		jRadioButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jRadioButton5.setSelected(true);
				jRadioButton6.setSelected(false);
			}
		});

		jRadioButton6.setText("PC");
		jRadioButton6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jRadioButton6.setSelected(true);
				jRadioButton5.setSelected(false);
			}
		});
		javax.swing.GroupLayout playerThreePanelLayout = new javax.swing.GroupLayout(
				playerThreePanel);
		playerThreePanel.setLayout(playerThreePanelLayout);
		playerThreePanelLayout
				.setHorizontalGroup(playerThreePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								playerThreePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												playerThreePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																playerThreePanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jRadioButton5)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jRadioButton6))
														.addGroup(
																playerThreePanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel6)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jTextField3,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jLabel7)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				Item3,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(64, Short.MAX_VALUE)));
		playerThreePanelLayout
				.setVerticalGroup(playerThreePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								playerThreePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												playerThreePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel6)
														.addComponent(
																jTextField3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel7)
														.addComponent(
																Item3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												playerThreePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jRadioButton5)
														.addComponent(
																jRadioButton6))
										.addContainerGap(10, Short.MAX_VALUE)));

		playerFourPanel.setPreferredSize(new java.awt.Dimension(800, 70));
		playerFourPanel.setVisible(false);
		jLabel8.setText("Name:");

		jTextField4.setPreferredSize(new java.awt.Dimension(150, 21));

		Item4.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Green", "Red", "Blue", "Naked" }));

		jLabel9.setText("Character:");

		jRadioButton7.setSelected(true);
		jRadioButton7.setText("Human");
		jRadioButton7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jRadioButton7.setSelected(true);
				jRadioButton8.setSelected(false);
			}
		});

		jRadioButton8.setText("PC");
		jRadioButton8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jRadioButton8.setSelected(true);
				jRadioButton7.setSelected(false);
			}
		});
		javax.swing.GroupLayout playerFourPanelLayout = new javax.swing.GroupLayout(
				playerFourPanel);
		playerFourPanel.setLayout(playerFourPanelLayout);
		playerFourPanelLayout
				.setHorizontalGroup(playerFourPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								playerFourPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												playerFourPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																playerFourPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jRadioButton7)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jRadioButton8))
														.addGroup(
																playerFourPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel8)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jTextField4,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jLabel9)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				Item4,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(64, Short.MAX_VALUE)));
		playerFourPanelLayout
				.setVerticalGroup(playerFourPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								playerFourPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												playerFourPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel8)
														.addComponent(
																jTextField4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel9)
														.addComponent(
																Item4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												playerFourPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jRadioButton7)
														.addComponent(
																jRadioButton8))
										.addContainerGap(10, Short.MAX_VALUE)));

		numPlayersLabel0.setText("Choose difficulty level:");

		jRadioButton9.setText("EASY");
		jRadioButton9.setSelected(true);
		jRadioButton9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jRadioButton9.setSelected(true);

				jRadioButton11.setSelected(false);
			}
		});

		jRadioButton11.setText("HARD");
		jRadioButton11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jRadioButton11.setSelected(true);

				jRadioButton9.setSelected(false);
			}
		});
		jButton1.setText("START");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												numPlayersLabel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(51, 51, 51)
										.addComponent(
												jSpinner1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(100, Short.MAX_VALUE))
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(numPlayersLabel0)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jButton1)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jRadioButton9)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)

																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jRadioButton11)))
										.addContainerGap(99, Short.MAX_VALUE))
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addComponent(
										playerThreePanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()).addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addComponent(
										playerFourPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()).addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addComponent(
										playerPanel2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()).addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addComponent(
										playerOnePanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																numPlayersLabel,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jSpinner1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addComponent(
												playerOnePanel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												playerPanel2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												playerThreePanel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												playerFourPanel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(22, 22, 22)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																numPlayersLabel0)
														.addComponent(
																jRadioButton9)

														.addComponent(
																jRadioButton11))
										.addGap(40, 40, 40).addComponent(
												jButton1).addContainerGap(96,
												Short.MAX_VALUE)));

	}// </editor-fold>




	private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {
	}

	// Variables declaration - do not modify
	private javax.swing.JComboBox Item1;
	private javax.swing.JComboBox Item2;
	private javax.swing.JComboBox Item3;
	private javax.swing.JComboBox Item4;
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel numPlayersLabel;
	private javax.swing.JLabel numPlayersLabel0;
	private javax.swing.JLabel nameLabel1;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel playerOnePanel;
	private javax.swing.JPanel playerPanel2;
	private javax.swing.JPanel playerThreePanel;
	private javax.swing.JPanel playerFourPanel;
	private javax.swing.JRadioButton jRadioButton1;

	private javax.swing.JRadioButton jRadioButton11;
	private javax.swing.JRadioButton jRadioButton2;
	private javax.swing.JRadioButton jRadioButton3;
	private javax.swing.JRadioButton jRadioButton4;
	private javax.swing.JRadioButton jRadioButton5;
	private javax.swing.JRadioButton jRadioButton6;
	private javax.swing.JRadioButton jRadioButton7;
	private javax.swing.JRadioButton jRadioButton8;
	private javax.swing.JRadioButton jRadioButton9;
	private javax.swing.JSpinner jSpinner1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;

	// End of variables declaration

	/**
	 * input players' name in the jTextFields. 
	 * 
	 * @param e Action Event.
	 * @return true if there's no missing fields.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(Item1.getSelectedIndex());

		if (validateForm()) {
			
			Game g = new Game(jRadioButton11.isSelected() ? Game.DIFF_HARD
					: Game.DIFF_EASY);

			g.addPlayer(new Player(jTextField1.getText(), jRadioButton1
					.isSelected() ? false : true, Item1.getSelectedIndex() + 1,
					g));
			g.addPlayer(new Player(jTextField2.getText(), jRadioButton3
					.isSelected() ? false : true, Item2.getSelectedIndex() + 1,
					g));

			if ((Integer) jSpinner1.getValue() == 3) {
				g.addPlayer(new Player(jTextField3.getText(), jRadioButton5
						.isSelected() ? false : true,
						Item3.getSelectedIndex() + 1, g));
			} else if ((Integer) jSpinner1.getValue() == 4) {
				g.addPlayer(new Player(jTextField3.getText(), jRadioButton5
						.isSelected() ? false : true,
						Item3.getSelectedIndex() + 1, g));
				g.addPlayer(new Player(jTextField4.getText(), jRadioButton7
						.isSelected() ? false : true,
						Item4.getSelectedIndex() + 1, g));
			}//
			g.manualInit();
			g.setWindow(window);
			window.switchToPanel(g);
		}
	}

	private boolean validateForm() {

		if (jTextField1.getText().replace(" ", "").length() == 0) {
			GeneralFunctions.showDialogBox("Please choose name for player 1");
			return false;
		}

		if (jTextField2.getText().replace(" ", "").length() == 0) {
			GeneralFunctions.showDialogBox("Please choose name for player 2");
			return false;
		}

		if (jTextField3.getText().replace(" ", "").length() == 0
				&& (Integer) jSpinner1.getValue() == 3) {
			GeneralFunctions.showDialogBox("Please choose name for player 3");
			return false;
		}

		if (jTextField4.getText().replace(" ", "").length() == 0
				&& (Integer) jSpinner1.getValue() == 4) {
			GeneralFunctions.showDialogBox("Please choose name for player 4");
			return false;
		}

		return true;

	}

}
