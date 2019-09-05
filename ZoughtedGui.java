package zoughted;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class ZoughtedGui extends JFrame {

	private static final long serialVersionUID = 1L;
	Random rand = new Random();
	Game game = new Game();
	Dice dice = new Dice();

	// initial prompts
	private String name1 = JOptionPane.showInputDialog("Please enter player one's name: ");
	private String name2 = JOptionPane.showInputDialog("Please enter player two's name: ");
	Player player1 = new Player(name1, true);
	Player player2 = new Player(name2, false);
	private JLabel lblPlayerTotalScore1;
	private JLabel lblPlayerTotalScore2;
	private JPanel panel_2;
	private int status1 = 0;
	private int status2 = 0;
	private int status3 = 0;
	private int status4 = 0;
	private int status5 = 0;
	private int status6 = 0;
	protected JLabel lblPlayerXTurn;
	protected JButton btnLockDie1;
	protected JButton btnLockDie2;
	protected JButton btnLockDie3;
	protected JButton btnLockDie4;
	protected JButton btnLockDie5;
	protected JButton btnLockDie6;
	protected JButton btnEndTurn;
	protected static JLabel lblDice1;
	protected JLabel lblDice2;
	protected JLabel lblDice3;
	protected JLabel lblDice4;
	protected JLabel lblDice5;
	protected JLabel lblDice6;
	protected int next1;
	protected int next2;
	protected int next3;
	protected int next4;
	protected int next5;
	protected int next6;
	protected JButton lblFinalRound;
	protected JLabel[] labels = new JLabel[6];
	private boolean possibleWinner = false;
	

	private JPanel contentPane;
	private static JPanel panel_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZoughtedGui frame = new ZoughtedGui();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ZoughtedGui() {
		setContentPane();
		dice.initializeDice();
		JPanel panel = panel();
		JButton btnRollAgain = btnRollAgain(panel);
		btnEndTurn(panel);

		JPanel panel_1 = panel1();
		JLabel lblDice1 = lblDice1(panel_1);
		JLabel lblDice2 = lblDice2(panel_1);
		JLabel lblDice3 = lblDice3(panel_1);
		JLabel lblDice4 = lblDice4(panel_1);
		JLabel lblDice5 = lblDice5(panel_1);
		JLabel lblDice6 = lblDice6(panel_1);

		btnLockDie1(panel_1);
		btnLockDie2(panel_1);
		btnLockDie3(panel_1);
		btnLockDie4(panel_1);
		btnLockDie5(panel_1);
		btnLockDie6(panel_1);
		{
			JLabel label = new JLabel("");
			panel_4.add(label);
		}
		{
			JLabel label = new JLabel("");
			panel_4.add(label);
		}
		{
			JLabel label = new JLabel("");
			panel_4.add(label);
		}
		{
			JLabel label = new JLabel("");
			panel_4.add(label);
		}
		{
			JLabel label = new JLabel("");
			panel_4.add(label);
		}
		{
			JLabel label = new JLabel("");
			panel_4.add(label);
		}

		panel_2 = panel2();
		lblPlayerTotalScore1 = lblPlayerTotalScore1(panel_2);
		lblPlayerTotalScore2 = lblPlayerTotalScore2(panel_2);

		JLabel label = new JLabel("");
		label.setBackground(new Color(102, 205, 170));
		panel_2.add(label);

		lblFinalRound = new JButton("Enter final round score");
		lblFinalRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String finalScore = JOptionPane.showInputDialog("Please enter your final score: ");
				Integer.parseInt(finalScore);
				dice.setAddToTotal(true);
				player1.getPlayerStatus();
				player2.getPlayerStatus();
				if (player2.getPlayerStatus() == true)
				{
					player2.setTotalScore(Integer.parseInt(finalScore) + player2.getTotalScore());
					lblPlayerTotalScore2.setText(player2.toString() + "score: " + player2.getTotalScore());
				}else
				{
					player1.setTotalScore(Integer.parseInt(finalScore) + player1.getTotalScore());
					lblPlayerTotalScore1.setText(player1.toString() + "score: " + player1.getTotalScore());
				}
				
	            if(player1.getTotalScore() > player2.getTotalScore())
	            {
	            	UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 30));
	            	JOptionPane.showMessageDialog(contentPane,player1.getPlayerName().toString() + " WINS!!!");
	            }
	            else
	            {
	            	UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 30));
	            	JOptionPane.showMessageDialog(contentPane,player2.getPlayerName().toString() + " WINS!!!");
	            }
			}
		});

		lblFinalRound.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFinalRound.setForeground(new Color(255, 255, 255));
		lblFinalRound.setBackground(new Color(0, 0, 128));
		lblFinalRound.setVisible(false);
		panel_2.add(lblFinalRound);


		JPanel panel_3 = panel3();

		lblZoughtedTheDice(panel_3);
		lblPlayerXTurn = lblPlayerXTurn(panel_3);

		JMenuItem rules = menu();
		rules(rules);

		// this is where stuff starts happening
		// game.playerSetup(2);

		if (player1.getPlayerStatus())
			lblPlayerXTurn.setText(player1 + "Turn");
		else
			lblPlayerXTurn.setText(player2 + "Turn");

		lblPlayerTotalScore1.setText(player1.toString() + "score: 0");
		lblPlayerTotalScore2.setText(player2.toString() + "score: 0");

		if(getPossibleWinner())
			lastRound();
		
		btnRollAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Random rand = new Random();
				labels[0] = lblDice1;
				labels[1] = lblDice2;
				labels[2] = lblDice3;
				labels[3] = lblDice4;
				labels[4] = lblDice5;
				labels[5] = lblDice6;
				
				keepDice(rand);

					
				}

			private void keepDice(Random rand) {
				if (!dice.getKeepArray(0)) {
					next1 = rand.nextInt(6) + 1;
					dice.setPlayingSet(0, next1);
					labels[0].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die" + next1 + ".png")));

				}
				if (!dice.getKeepArray(1)) {
					next2 = rand.nextInt(6) + 1;
					dice.setPlayingSet(1, next2);
					labels[1].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die" + next2 + ".png")));

				}
				if (!dice.getKeepArray(2)) {
					next3 = rand.nextInt(6) + 1;
					dice.setPlayingSet(2, next3);
					labels[2].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die" + next3 + ".png")));

				}
				if (!dice.getKeepArray(3)) {
					next4 = rand.nextInt(6) + 1;
					dice.setPlayingSet(3, next4);
					labels[3].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die" + next4 + ".png")));

				}
				if (!dice.getKeepArray(4)) {
					next5 = rand.nextInt(6) + 1;
					dice.setPlayingSet(4, next5);
					labels[4].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die" + next5 + ".png")));

				}
				if (!dice.getKeepArray(5)) {
					next6 = rand.nextInt(6) + 1;
					dice.setPlayingSet(5, next6);
					labels[5].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die" + next6 + ".png")));
				}
			}
			

		});
	}

	private void rules(JMenuItem rules) {
		rules.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 22));
				JOptionPane.showMessageDialog(menu(),
						"Welcome to The Dice Game. Here are the rules: You start with rolling six dice. Every turn you must keep at least one dice. You must have a 1 or a 5 to continue or\n "
								+ "else your turn is over. 1's are worth 100 points, and 5's are worth 50. If at any point you roll your dice and you don't get any points you 'zoughted' and your turn\n"
								+ " is over. The exceptions to getting points besides in a 1 or a 5 are as follows. If you roll three 1's at once you get 1000 points, three\n"
								+ " 2's is 200 points, three 3's is 300 points, three 4's is 400 points, three 5's is 500 points, and three 6's is 600 points. If you roll four\n"
								+ " of the same number at once then you add that amount to the points. So four 5's for instance is 1000 points. 500 for the inital three dice, then\n"
								+ " 500 for the one extra 5. Five 5's would be 1500 points. If you roll 3 pair it's 1200 points. An example of this would be two 2's, two 3's, and two\n"
								+ " 6's. A straight is worth 1500 points. A straight has to be 1-6 in one roll. If you happen to use all 6 of your dice, and they are all valid points\n"
								+ " then you get to re-roll all 6 dice again and you keep your points. If you 'zought' though then you lose it all. If you get six of a kind of any number\n"
								+ " in one roll then you automatically win the game! Once someone reaches 10,000 points everybody else gets one more turn to try and win and\n"
								+ " pass their score. After the last round the person with the higest score wins. Have fun! \n\n");
			}
		});
	}

	private JMenuItem menu() {
		JMenuBar menuBar = new JMenuBar();
		JMenuItem rules, exit;
		JMenu fileMenu = new JMenu("File");
		rules = new JMenuItem("Rules of the Game");
		exit = new JMenuItem("Exit");
		fileMenu.add(rules);
		fileMenu.add(exit);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		fileMenu.setFont(new Font("Arial", Font.PLAIN, 15));
		rules.setFont(new Font("Arial", Font.PLAIN, 25));
		exit.setFont(new Font("Arial", Font.PLAIN, 25));
		exit.addActionListener(new exitApp());
		return rules;
	}
    private class exitApp implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
	private JLabel lblPlayerXTurn(JPanel panel_3) {
		lblPlayerXTurn = new JLabel("Player x Turn");
		lblPlayerXTurn.setForeground(new Color(255, 255, 255));
		lblPlayerXTurn.setBackground(new Color(102, 205, 170));
		lblPlayerXTurn.setFont(new Font("Stencil", Font.PLAIN, 45));
		lblPlayerXTurn.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblPlayerXTurn);
		return lblPlayerXTurn;
	}

	private void lblZoughtedTheDice(JPanel panel_3) {
		JLabel lblZoughtedTheDice = new JLabel("Game of Zoughts");
		lblZoughtedTheDice.setForeground(new Color(255, 255, 255));
		lblZoughtedTheDice.setBackground(new Color(102, 205, 170));
		lblZoughtedTheDice.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblZoughtedTheDice.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblZoughtedTheDice);
	}

	private JPanel panel3() {
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 128));
		contentPane.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(0, 1, 25, 100));
		return panel_3;
	}

	private JLabel lblPlayerTotalScore2(JPanel panel_2) {
		JLabel lblPlayerTotalScore2 = new JLabel("Player2 Total Score");
		lblPlayerTotalScore2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerTotalScore2.setForeground(new Color(255, 255, 255));
		lblPlayerTotalScore2.setBackground(new Color(0, 0, 128));
		lblPlayerTotalScore2.setPreferredSize(new Dimension(200, 150));
		lblPlayerTotalScore2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPlayerTotalScore2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_2.add(lblPlayerTotalScore2);
		return lblPlayerTotalScore2;
	}

	private JLabel lblPlayerTotalScore1(JPanel panel_2) {
		JLabel lblPlayerTotalScore1 = new JLabel("Player1 Total Score");
		lblPlayerTotalScore1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerTotalScore1.setForeground(new Color(255, 255, 255));
		lblPlayerTotalScore1.setBackground(new Color(0, 0, 128));
		lblPlayerTotalScore1.setPreferredSize(new Dimension(200, 150));
		lblPlayerTotalScore1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPlayerTotalScore1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_2.add(lblPlayerTotalScore1);
		return lblPlayerTotalScore1;
	}

	private JPanel panel2() {
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 128));
		contentPane.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		return panel_2;
	}

	private void btnLockDie6(JPanel panel_1) {
		ImageIcon imageLockSix = new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/Unlocked.png"));
		btnLockDie6 = new JButton(imageLockSix);
		btnLockDie6.setBorder(null);
		btnLockDie6.setBackground(new Color(0, 0, 128));
		btnLockDie6.setPreferredSize(new Dimension(150, 150));
		btnLockDie6.setBackground(new Color(0, 0, 128));
		btnLockDie6.setMargin(new Insets(10, 38, 10, 38));
		btnLockDie6.setFont(new Font("Dialog", Font.PLAIN, 25));
		panel_1.add(btnLockDie6);

		btnLockDie6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (status6 == 0) {
					dice.setKeepArray(5, true);
					// game.chooseDice();
					ImageIcon imageLockOne = new ImageIcon(
							ZoughtedGui.class.getResource("/zoughted/Images/Locked.png"));
					btnLockDie6.setIcon(imageLockOne);
					labels[5].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/blackdie" + next6 + ".png")));
					status6 = 1;
				} else {
					dice.setKeepArray(5, false);
					// game.chooseDice();
					ImageIcon imageUnlockOne = new ImageIcon(
							ZoughtedGui.class.getResource("/zoughted/Images/Unlocked.png"));
					btnLockDie6.setIcon(imageUnlockOne);
					labels[5].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die" + next6 + ".png")));
					status6 = 0;
				}
			}
		});
	}

	private void btnLockDie5(JPanel panel_1) {
		ImageIcon imageLockFive = new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/Unlocked.png"));
		btnLockDie5 = new JButton(imageLockFive);
		btnLockDie5.setBorder(null);
		btnLockDie5.setBackground(new Color(0, 0, 128));
		btnLockDie5.setPreferredSize(new Dimension(150, 150));
		btnLockDie5.setBackground(new Color(0, 0, 128));
		btnLockDie5.setMargin(new Insets(10, 38, 10, 38));
		btnLockDie5.setFont(new Font("Dialog", Font.PLAIN, 25));
		panel_1.add(btnLockDie5);

		btnLockDie5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (status5 == 0) {
					dice.setKeepArray(4, true);
					// game.chooseDice();
					ImageIcon imageLockOne = new ImageIcon(
							ZoughtedGui.class.getResource("/zoughted/Images/Locked.png"));
					btnLockDie5.setIcon(imageLockOne);
					labels[4].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/blackdie" + next5 + ".png")));
					status5 = 1;
				} else {
					dice.setKeepArray(4, false);
					// game.chooseDice();
					ImageIcon imageUnlockOne = new ImageIcon(
							ZoughtedGui.class.getResource("/zoughted/Images/Unlocked.png"));
					btnLockDie5.setIcon(imageUnlockOne);
					labels[4].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die" + next5 + ".png")));
					status5 = 0;
				}
			}
		});
	}

	private void btnLockDie4(JPanel panel_1) {
		ImageIcon imageLockFour = new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/Unlocked.png"));
		btnLockDie4 = new JButton(imageLockFour);
		btnLockDie4.setBorder(null);
		btnLockDie4.setBackground(new Color(0, 0, 128));
		btnLockDie4.setPreferredSize(new Dimension(150, 150));
		btnLockDie4.setBackground(new Color(0, 0, 128));
		btnLockDie4.setMargin(new Insets(10, 38, 10, 38));
		btnLockDie4.setFont(new Font("Dialog", Font.PLAIN, 25));
		panel_1.add(btnLockDie4);

		btnLockDie4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (status4 == 0) {
					dice.setKeepArray(3, true);
					// game.chooseDice();
					ImageIcon imageLockOne = new ImageIcon(
							ZoughtedGui.class.getResource("/zoughted/Images/Locked.png"));
					btnLockDie4.setIcon(imageLockOne);
					labels[3].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/blackdie" + next4 + ".png")));
					status4 = 1;
				} else {
					dice.setKeepArray(3, false);
					// game.chooseDice();
					ImageIcon imageUnlockOne = new ImageIcon(
							ZoughtedGui.class.getResource("/zoughted/Images/Unlocked.png"));
					btnLockDie4.setIcon(imageUnlockOne);
					labels[3].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die" + next4 + ".png")));
					status4 = 0;
				}
			}
		});
	}

	private void btnLockDie3(JPanel panel_1) {
		ImageIcon imageLockThree = new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/Unlocked.png"));
		btnLockDie3 = new JButton(imageLockThree);
		btnLockDie3.setBorder(null);
		btnLockDie3.setBackground(new Color(0, 0, 128));
		btnLockDie3.setPreferredSize(new Dimension(150, 150));
		btnLockDie3.setBackground(new Color(0, 0, 128));
		btnLockDie3.setMargin(new Insets(10, 38, 10, 38));
		btnLockDie3.setFont(new Font("Dialog", Font.PLAIN, 25));
		panel_1.add(btnLockDie3);

		btnLockDie3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (status3 == 0) {
					dice.setKeepArray(2, true);
					// game.chooseDice();
					ImageIcon imageLockOne = new ImageIcon(
							ZoughtedGui.class.getResource("/zoughted/Images/Locked.png"));
					btnLockDie3.setIcon(imageLockOne);
					labels[2].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/blackdie" + next3 + ".png")));
					status3 = 1;
				} else {
					dice.setKeepArray(2, false);
					// game.chooseDice();
					ImageIcon imageUnlockOne = new ImageIcon(
							ZoughtedGui.class.getResource("/zoughted/Images/Unlocked.png"));
					btnLockDie3.setIcon(imageUnlockOne);
					labels[2].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die" + next3 + ".png")));
					status3 = 0;
				}
			}
		});
	}

	private void btnLockDie2(JPanel panel_1) {
		ImageIcon imageLockTwo = new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/Unlocked.png"));
		btnLockDie2 = new JButton(imageLockTwo);
		btnLockDie2.setBorder(null);
		btnLockDie2.setBackground(new Color(0, 0, 128));
		btnLockDie2.setPreferredSize(new Dimension(150, 150));
		btnLockDie2.setBackground(new Color(0, 0, 128));
		btnLockDie2.setMargin(new Insets(10, 38, 10, 38));
		btnLockDie2.setFont(new Font("Dialog", Font.PLAIN, 25));
		panel_1.add(btnLockDie2);

		btnLockDie2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (status2 == 0) {
					dice.setKeepArray(1, true);
					// game.chooseDice();
					ImageIcon imageLockOne = new ImageIcon(
							ZoughtedGui.class.getResource("/zoughted/Images/Locked.png"));
					btnLockDie2.setIcon(imageLockOne);
					labels[1].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/blackdie" + next2 + ".png")));
					status2 = 1;
				} else {
					dice.setKeepArray(1, false);
					// game.chooseDice();
					ImageIcon imageUnlockOne = new ImageIcon(
							ZoughtedGui.class.getResource("/zoughted/Images/Unlocked.png"));
					btnLockDie2.setIcon(imageUnlockOne);
					labels[1].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die" + next2 + ".png")));
					status2 = 0;
				}
			}
		});
	}

	private void btnLockDie1(JPanel panel_1) {
		ImageIcon imageLockOne = new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/Unlocked.png"));
		btnLockDie1 = new JButton(imageLockOne);
		btnLockDie1.setBorder(null);
		btnLockDie1.setBackground(new Color(0, 0, 128));
		btnLockDie1.setPreferredSize(new Dimension(150, 150));
		btnLockDie1.setMargin(new Insets(10, 38, 10, 38));
		btnLockDie1.setFont(new Font("Dialog", Font.PLAIN, 25));
		panel_1.add(btnLockDie1);

		btnLockDie1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (status1 == 0) {
					dice.setKeepArray(0, true);
					// game.chooseDice();
					ImageIcon imageLockOne = new ImageIcon(
							ZoughtedGui.class.getResource("/zoughted/Images/Locked.png"));
					btnLockDie1.setIcon(imageLockOne);
					labels[0].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/blackdie" + next1 + ".png")));
					status1 = 1;
				} else {
					dice.setKeepArray(0, false);
					// game.chooseDice();
					ImageIcon imageUnlockOne = new ImageIcon(
							ZoughtedGui.class.getResource("/zoughted/Images/Unlocked.png"));
					btnLockDie1.setIcon(imageUnlockOne);
					labels[0].setIcon(
							new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die" + next1 + ".png")));
					status1 = 0;
				}
			}
		});
	}

	private JPanel panel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		contentPane.add(panel, BorderLayout.SOUTH);
		return panel;
	}

	private void setContentPane() {
		setTitle("Zoughted");
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(175, 175));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 900);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	private JLabel lblDice6(JPanel panel_1) {
		lblDice6 = new JLabel("");
		lblDice6.setBackground(new Color(102, 205, 170));
		lblDice6.setPreferredSize(new Dimension(200, 200));
		lblDice6.setIcon(new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die6.png")));
		lblDice6.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDice6.setHorizontalAlignment(SwingConstants.CENTER);
		lblDice6.setFont(new Font("Tahoma", Font.PLAIN, 40));
		panel_1.add(lblDice6);
		return lblDice6;
	}

	private JLabel lblDice5(JPanel panel_1) {
		lblDice5 = new JLabel("");
		lblDice5.setBackground(new Color(102, 205, 170));
		lblDice5.setPreferredSize(new Dimension(200, 200));
		lblDice5.setIcon(new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die5.png")));
		lblDice5.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDice5.setHorizontalAlignment(SwingConstants.CENTER);
		lblDice5.setFont(new Font("Tahoma", Font.PLAIN, 40));
		panel_1.add(lblDice5);
		return lblDice5;
	}

	private JLabel lblDice4(JPanel panel_1) {
		lblDice4 = new JLabel("");
		lblDice4.setBackground(new Color(102, 205, 170));
		lblDice4.setPreferredSize(new Dimension(200, 200));
		lblDice4.setIcon(new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die4.png")));
		lblDice4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDice4.setHorizontalAlignment(SwingConstants.CENTER);
		lblDice4.setFont(new Font("Tahoma", Font.PLAIN, 40));
		panel_1.add(lblDice4);
		return lblDice4;
	}

	private JLabel lblDice3(JPanel panel_1) {
		lblDice3 = new JLabel("");
		lblDice3.setBackground(new Color(102, 205, 170));
		lblDice3.setPreferredSize(new Dimension(200, 200));
		lblDice3.setIcon(new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die3.png")));
		lblDice3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDice3.setHorizontalAlignment(SwingConstants.CENTER);
		lblDice3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		panel_1.add(lblDice3);
		return lblDice3;
	}

	private JLabel lblDice2(JPanel panel_1) {
		lblDice2 = new JLabel("");
		lblDice2.setBackground(new Color(102, 205, 170));
		lblDice2.setPreferredSize(new Dimension(200, 200));
		lblDice2.setIcon(new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die2.png")));
		lblDice2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDice2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDice2.setFont(new Font("Tahoma", Font.PLAIN, 35));
		panel_1.add(lblDice2);
		return lblDice2;
	}

	public static JLabel lblDice1(JPanel panel_1) {
		panel_4.setLayout(new GridLayout(2, 6, 0, 0));
		panel_4.setLayout(new GridLayout(2, 6, 0, 0));
		panel_4.setLayout(new GridLayout(3, 6, 0, 0));
		lblDice1 = new JLabel("");
		lblDice1.setBackground(new Color(102, 205, 170));
		lblDice1.setPreferredSize(new Dimension(200, 200));
		lblDice1.setIcon(new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die1.png")));
		lblDice1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDice1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDice1.setFont(new Font("Tahoma", Font.PLAIN, 40));
		panel_1.add(lblDice1);
		return lblDice1;
	}

	private JPanel panel1() {
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 0, 128));
		contentPane.add(panel_4, BorderLayout.CENTER);
		return panel_4;
	}

	private void btnEndTurn(JPanel panel) {
		btnEndTurn = new JButton("End Turn");
		btnEndTurn.setForeground(new Color(255, 255, 255));
		btnEndTurn.setBackground(new Color(0, 0, 128));
		btnEndTurn.setFont(new Font("Stencil", Font.PLAIN, 45));
		btnEndTurn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{

				if (player1.getPlayerStatus()) 
				{
					String scores1 = JOptionPane.showInputDialog("Please enter your score for this round: ");
					Integer.parseInt(scores1);
					dice.setAddToTotal(true);
					player1.setTotalScore(Integer.parseInt(scores1) + player1.getTotalScore());
					lblPlayerTotalScore1.setText(player1.toString() + "score: " + player1.getTotalScore());
					player1.setPlayerStatus(false);
					player2.setPlayerStatus(true);

				} else 
				{
					String scores2 = JOptionPane.showInputDialog("Please enter your score for this round: ");
					Integer.parseInt(scores2);
					dice.setAddToTotal(true);
					player2.setTotalScore(Integer.parseInt(scores2) + player2.getTotalScore());
					lblPlayerTotalScore2.setText(player2.toString() + "score: " + player2.getTotalScore());
					player2.setPlayerStatus(false);
					player1.setPlayerStatus(true);

				}
				if (player1.getPlayerStatus())
					lblPlayerXTurn.setText(player1 + "Turn");
				else
					lblPlayerXTurn.setText(player2 + "Turn");

				getPossibleWinner();
				if(possibleWinner == true)
					lastRound();
				
				dice.initializeDice();
				status1 = 0;
				status2 = 0;
				status3 = 0;
				status4 = 0;
				status5 = 0;
				status6 = 0;
				changeLockIcons();
				defaultDice();

			}
		});

		panel.add(btnEndTurn);
	}

	private void changeLockIcons() {
		ImageIcon imageUnlockOne = new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/Unlocked.png"));
		btnLockDie1.setIcon(imageUnlockOne);
		btnLockDie2.setIcon(imageUnlockOne);
		btnLockDie3.setIcon(imageUnlockOne);
		btnLockDie4.setIcon(imageUnlockOne);
		btnLockDie5.setIcon(imageUnlockOne);
		btnLockDie6.setIcon(imageUnlockOne);
	}

	private void defaultDice() {
		lblDice1.setIcon(new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die1.png")));
		lblDice2.setIcon(new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die2.png")));
		lblDice3.setIcon(new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die3.png")));
		lblDice4.setIcon(new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die4.png")));
		lblDice5.setIcon(new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die5.png")));
		lblDice6.setIcon(new ImageIcon(ZoughtedGui.class.getResource("/zoughted/Images/die6.png")));
		
	}

	private JButton btnRollAgain(JPanel panel) {
		JButton btnRollAgain = new JButton("Roll Dice");
		btnRollAgain.setForeground(new Color(255, 255, 255));
		btnRollAgain.setBackground(new Color(0, 0, 128));
		btnRollAgain.setFont(new Font("Stencil", Font.PLAIN, 45));
		panel.add(btnRollAgain);
		return btnRollAgain;
	}
	
 private boolean getPossibleWinner()        
    {
    		
            if((player1.getTotalScore() > 9999) || player2.getTotalScore() > 9999)
            {
                possibleWinner = true;
            }
            else
            	possibleWinner =  false;
        
        
        return possibleWinner;
    }
	 private void lastRound()
	    {	     

	            if(player1.getTotalScore() > 9999)
	            {
	            	lblPlayerXTurn.setText(player2.getPlayerName().toString() + "\'s Last Turn!");
					player1.setPlayerStatus(false);
					player2.setPlayerStatus(true);
	            }
	            if (player2.getTotalScore() > 9999)
	            {
	            	lblPlayerXTurn.setText(player1.getPlayerName().toString() +  "\'s Last Turn!");
					player1.setPlayerStatus(true);
					player2.setPlayerStatus(false);
	            }
	            lblFinalRound.setVisible(true);

				
			

	    }


}
