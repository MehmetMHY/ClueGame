/**. 
 * Control Class is the main GUI class for the over all,
 * GUI for ClueGame. Here, the Player's cards, Control Panel,
 * Player Icons, Menu, Detective Notes, the the board GUI,
 * is initialize and/or built.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Control extends JFrame {
	private static Board board; // ClueGame board object

	private JTextField character; // stores text value for "Whose turn?" for the Control Panel GUI
	private JTextField dieRoll; // stores text value for dice roll for the Control Panel GUI
	private JTextField guess; // stores a character's guess as a text value for the Control Panel GUI
	private JTextField guessResult; // stores result from guess as a text value for the Control Panel GUI
	private SouthPanel south; // control panel object for ClueGame GUI

	DetectiveNotes detectiveNotes; // detectiveNotes object for ClueGame detective notes

	private JTextField cardPerson; // Person's name for People part of My Cards GUI
	private JTextField cardRooms; // the room's name for Rooms part of My Cards GUI
	private JTextField cardWeapons; // the Weapon's name for Weapons part of My Cards GUI


	private static int diceNum;

	private static DrawBoard boardPanel;

	// initialize board object from Board class
	public void initializeBoard() {
		board = Board.getInstance(); // only set instance variable for Board
		board.setConfigFiles("OurBoardLayout.csv", "OurRooms.txt"); // set the file names for setConfigFiles()
		board.setCardConfigFiles("Weapon.txt", "Players.txt");
		board.initialize(); // load both config files for tests
	}

	// setup title, window size, and enable EXIT for the Control Panel GUI window
	public Control(int x, int y) {
		setTitle("Clue Game");
		setSize(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createLayout();
	}

	// class for "Whose Turn?" panels for the GUI
	public class ControlPlayer extends JPanel{
		public JTextField character;

		public ControlPlayer(int x, int y, Color color, boolean editP) {
			// set up grid layout and title border
			setLayout(new GridLayout(x,y));
			setBorder(new TitledBorder(new EtchedBorder(), "Whose Turn?"));

			// set up character text panel
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1,2));

			// place text field in the character text panel
			character = new JTextField();
			character.setSize(5, 5);
			character.setEditable(editP);
			panel.add(character);

			// color the border of the panel with declared color value
			panel.setBorder(new LineBorder(color));
			add(panel);	     
		}
	}

	// class for "Guess Result", "Die", and "Guess" panels for the GUI
	public class ControlPanel extends JPanel{
		public JTextField text;
		public ControlPanel(int x, int y, Color color, boolean editP, String one, String two) {
			// set up grid layout and title border
			setLayout(new GridLayout(x,y));
			setBorder(new TitledBorder(new EtchedBorder(), one));

			// set up display label
			JLabel displayLabel = new JLabel(two);

			// set up guess_result text panel
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1,2));

			// place text field in the guess_result text panel
			text = new JTextField();
			text.setSize(5, 5);
			text.setEditable(editP);
			panel.add(text);

			// color the border of the panel with declared color value
			panel.setBorder(new LineBorder(color));
			add(displayLabel);
			add(panel);
		}
	}

	// class for creating Control Panel GUI for the overall ClueGame GUI
	public class SouthPanel extends JPanel {
		private ControlPanel roll; // dice roll value for Control Panel ClueGame GUI
		private ControlPanel guess; // guess value for Control Panel ClueGame GUI
		private ControlPanel results; // guess result value for Control Panel ClueGame GUI
		private ControlPlayer p; // "Whose's Turn" value for Control Panel ClueGame GUI
		private JButton next;
		private JButton makeAccusation;

		public SouthPanel() {
			// create "Whose Turn?" panel of the GUI with a blue bolder and a BorderLayout at the South
			p = new ControlPlayer(1, 5, Color.blue, false); // also make setEditable equal to false for whoPlayer
			add(p, BorderLayout.CENTER);

			// create "Die" panel of the GUI with a red bolder and a BorderLayout at the South
			roll = new ControlPanel(2, 3, Color.red, false, "Die", "Roll"); // also make setEditable equal to false for theDieRoll
			add(roll, BorderLayout.CENTER);

			// create "Guess" panel of the GUI with a black bolder and a BorderLayout at the South
			guess = new ControlPanel(2 ,3, Color.black, false, "Guess", "Guess"); // also make setEditable equal to false for theGuess
			add(guess, BorderLayout.CENTER);

			// create "Response" panel of the GUI with a green bolder and a BorderLayout at the South
			results = new ControlPanel(2 , 3, Color.green, false, "Guess Result", "Response"); // also make setEditable equal to false for theGuessResult
			add(results, BorderLayout.CENTER);



			// create both the Next and Accuse buttons on the GUI as well as create a BorderLayout at the South
			next = new JButton("Next");
			
			//Add button listener that react to button press
			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Random randInt = new Random();
					diceNum = randInt.nextInt(6) + 1;
					if (boardPanel.getTurn().size() > 0) {	
						String currentPlayerName = new String("");
						for (String s:board.getPlayers().keySet()) {
							if (!boardPanel.getTurn().contains(s)) {
								currentPlayerName = new String(s);
								break;
							}
						}
						board.getTargets().clear();
						board.calcTargets(board.getPlayers().get(currentPlayerName).row, board.getPlayers().get(currentPlayerName).column, diceNum);
						setRoll(diceNum);
						setP(currentPlayerName);
						BoardCell temp = board.getPlayers().get(currentPlayerName).pickLocation(board.getTargets());
						board.getPlayers().get(currentPlayerName).setColumn(temp.getCol());
						board.getPlayers().get(currentPlayerName).setRow(temp.getRow());
						boardPanel.getTurn().add(currentPlayerName);
						if (boardPanel.getTurn().size() == 6) {
							boardPanel.getTurn().clear();
						}
						
					} else if (!boardPanel.isMoved()) {
						JFrame notMoved = new JFrame();
						JOptionPane.showMessageDialog(notMoved, "You need to make a move before you can go next!","Message", JOptionPane.INFORMATION_MESSAGE);
					} else if (boardPanel.getTurn().size() == 0) {
						board.calcTargets(board.getP1().row, board.getP1().column, diceNum);
						setRoll(diceNum);
						setP(board.getP1().playerName);
						for (BoardCell c:board.getTargets()) {
							boardPanel.getDrawBoard().get(c.getRow()).get(c.getCol()).setTarget(true);
						}
						boardPanel.setMoved(false);
					}
				}
			});
			makeAccusation = new JButton("Accuse");
			setLayout(new GridLayout(2,2));
			add(next, BorderLayout.CENTER);
			add(makeAccusation, BorderLayout.SOUTH);
		}

		public JButton getNext() {
			return next;
		}

		public JButton getMakeAccusation() {
			return makeAccusation;
		}

		public void setRoll(int num) {
			roll.text.setText(String.valueOf(num));
		}

		public void setGuess(String g) {
			guess.text.setText(g);
		}

		public void setResult(String r) {
			results.text.setText(r);
		}

		public void setP(String c) {
			p.character.setText(c);
		}
	}

	// void class for creating the over all layout of the ClueGame GUI
	public void createLayout() {
		// initialize board
		initializeBoard();

		// create ClueGame board GUI
		boardPanel = new DrawBoard(board);
		//		mouseClick = new ClickLocation(centerWidth, centerHeight, board.getNumRows(),board.getNumColumns());
		//		boardPanel.addMouseListener(mouseClick);
		add(boardPanel, BorderLayout.CENTER);

		// create control panel GUI
		south = new SouthPanel();
		add(south, BorderLayout.SOUTH);

		// create menuBar for GUI
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());

		// create Detective Notes GUI for menuBar
		detectiveNotes = new DetectiveNotes(700, 500, board);

		// create and place players' GUIs
		playersCards tpc = new playersCards(3, 2, false, Color.green, Color.yellow, Color.red);
		add(tpc, BorderLayout.EAST);

	}

	// class that creates the GUI for the Human Player's cards
	public class playersCards extends JPanel{
		public playersCards(int x, int y, boolean editP, Color pColor, Color rColor, Color wColor) {
			setLayout(new GridLayout(x,y));

			// set main label for the Player's Card GUI
			setBorder(new TitledBorder(new EtchedBorder(), "My Cards"));

			// create the Human Player's People Card GUI
			JPanel aCard = new JPanel();
			aCard.setLayout(new GridLayout(1,2));
			aCard.setBorder(new TitledBorder(new EtchedBorder(), "People"));
			cardPerson = new JTextField(10);
			cardPerson.setSize(x,y);
			cardPerson.setEditable(editP);
			aCard.add(cardPerson);
			aCard.setBackground(pColor);

			// create the Human Player's Rooms Card GUI
			JPanel bCard = new JPanel();
			bCard.setLayout(new GridLayout(1,2));
			bCard.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
			cardRooms = new JTextField(10);
			cardRooms.setSize(x,y);
			cardRooms.setEditable(editP);
			bCard.add(cardRooms);
			bCard.setBackground(rColor);

			// create the Human Player's Weapons Card GUI
			JPanel cCard = new JPanel();
			cCard.setLayout(new GridLayout(1,2));
			cCard.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
			cardWeapons = new JTextField(10);
			cardWeapons.setSize(x,y);
			cardWeapons.setEditable(editP);
			cCard.add(cardWeapons);
			cCard.setBackground(wColor);

			// add all the handed cards GUI to the overall GUI
			setLayout(new GridLayout(3,1));
			add(aCard, BorderLayout.WEST);
			add(bCard, BorderLayout.WEST);
			add(cCard, BorderLayout.WEST);

		}
	}

	// creates GUI Menu named "File" with tabs for "Show Notes" and "Exit"
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(showDetectiveNotes());
		menu.add(exitProgram());
		return menu;
	}

	// creates GUI Menu Tab "Show Notes" which displays the detective notes GUI when pressed
	private JMenuItem showDetectiveNotes() {
		JMenuItem item = new JMenuItem("Show Notes");
		class MenuItemListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				detectiveNotes.setVisible(true);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}

	// create GUI Menu Tab "Exit" which closes the program when pressed
	private JMenuItem exitProgram() {
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}

	// main method for the ClueGame GUI
	public static void main(String [] args) {

		// create Control object with a window size of 210 by 500
		Control gui = new Control(735, 770);

		// set My Cards: "People", "Rooms", and "Weapons" values for the GUI

		HumanPlayer temp = board.getP1();
		//		for (Card c:board.getPlayerDeck()) {
		//			if (c.getCardName().equals(board.getP1().playerName)) {
		//				gui.setCardPerson(c);
		//			}
		//		}
		for(int i = 0; i < temp.getMyCards().size(); i++) {
			if(temp.getMyCards().get(i).getType() == CardType.PERSON) {
				gui.setCardPerson(temp.getMyCards().get(i));
			}
			if(temp.getMyCards().get(i).getType() == CardType.ROOM) {
				gui.setCardRooms(temp.getMyCards().get(i));
			}
			if(temp.getMyCards().get(i).getType() == CardType.WEAPON) {
				gui.setCardWeapons(temp.getMyCards().get(i));
			}
		}

		// Splash Screen for the Start of the Game
		JFrame startScreen = new JFrame();
		JOptionPane.showMessageDialog(startScreen, "You are " + board.getP1().playerName + ", press Next Player to begin play", "Welcome to Clue", JOptionPane.INFORMATION_MESSAGE);

		// Take care of player movements
		//gui.getSouth().setP("Col. Mustard");
		//gui.getSouth().setRoll(5);
		//gui.getSouth().setGuess("My guess");
		//gui.getSouth().setResult("I guessed it!");

		// make GUI visible
		gui.setVisible(true);
	}

	public void setCardPerson(Card cardPerson) {
		this.cardPerson.setText(cardPerson.getCardName());
	}

	public void setCardRooms(Card cardRooms) {
		this.cardRooms.setText(cardRooms.getCardName());
	}

	public void setCardWeapons(Card cardWeapons) {
		this.cardWeapons.setText(cardWeapons.getCardName());
	}

	public SouthPanel getSouth() {
		return south;
	}
}