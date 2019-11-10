package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*; 
import javax.swing.BoxLayout;
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

public class CardsAndNotes extends JFrame {
	private JTextField cardPerson;
	private JTextField cardRooms;
	private JTextField cardWeapons;

	public CardsAndNotes(int x, int y, boolean makeLayout) {
		setTitle("Clue Game");
		setSize(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(makeLayout) {
			createLayout();
		}
	}
	
	private static Board board;
	public static void setup() {
		// initialize the board:
		board = Board.getInstance();
		board.setConfigFiles("OurBoardLayout.csv", "OurRooms.txt");
		board.setCardConfigFiles("Weapon.txt", "Players.txt");
		board.initialize();
	}

	public class playersCards extends JPanel{
		public playersCards(int x, int y, boolean editP, Color pColor, Color rColor, Color wColor) {
			setLayout(new GridLayout(x,y));
			setBorder(new TitledBorder(new EtchedBorder(), "My Cards"));
			
			JPanel aCard = new JPanel();
			aCard.setLayout(new GridLayout(1,2));
			aCard.setBorder(new TitledBorder(new EtchedBorder(), "People"));
			cardPerson = new JTextField(10);
			cardPerson.setSize(x,y);
			cardPerson.setEditable(editP);
			aCard.add(cardPerson);
			aCard.setBackground(pColor);
			//aCard.setBorder(new LineBorder(pColor));
			
			JPanel bCard = new JPanel();
			bCard.setLayout(new GridLayout(1,2));
			bCard.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
			cardRooms = new JTextField(10);
			cardRooms.setSize(x,y);
			cardRooms.setEditable(editP);
			bCard.add(cardRooms);
			bCard.setBackground(rColor);
			//bCard.setBorder(new LineBorder(rColor));
			
			JPanel cCard = new JPanel();
			cCard.setLayout(new GridLayout(1,2));
			cCard.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
			cardWeapons = new JTextField(10);
			cardWeapons.setSize(x,y);
			cardWeapons.setEditable(editP);
			cCard.add(cardWeapons);
			cCard.setBackground(wColor);
			//cCard.setBorder(new LineBorder(wColor));
			
			setLayout(new GridLayout(1,3));
			
			add(aCard, BorderLayout.WEST);
			
			add(bCard, BorderLayout.WEST);
			
			add(cCard, BorderLayout.WEST);

		}
	}
	
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createFileExitItem());
		return menu;
	}
	
	private JMenuItem createFileExitItem() {
		JMenuItem item = new JMenuItem("Detective Notes");
		class MenuItemListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
				
				CardsAndNotes newWindow = new CardsAndNotes(210, 210, false);
				
				newWindow.setLayout(new BorderLayout());
				newWindow.setLayout(new GridLayout(3,2));
				detectiveNotesPerson dnp = new detectiveNotesPerson(3, 2, false, Color.green, Color.yellow, Color.red);
				newWindow.add(dnp);
				
				detectiveNotesRoom dnp2 = new detectiveNotesRoom(3, 2, false, Color.green, Color.yellow, Color.red);
				newWindow.add(dnp2);
				
				detectiveNotesWeapons dnp3 = new detectiveNotesWeapons(3, 2, false, Color.green, Color.yellow, Color.red);
				newWindow.add(dnp3);
				
				newWindow.setVisible(true);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	
	public void createLayout() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		
		playersCards tpc = new playersCards(3, 2, false, Color.green, Color.yellow, Color.red);
		add(tpc, BorderLayout.SOUTH);

	}
	
	public void setPersonCard(String person) {
		cardPerson.setText(person);
	}
	
	public void setRoomsCard(String rooms) {
		cardRooms.setText(rooms);
	}
	
	public void setWeaponsCard(String weapon) {
		cardWeapons.setText(weapon);
	}
	
	public class detectiveNotesPerson extends JPanel{
		public detectiveNotesPerson(int x, int y, boolean editP, Color pColor, Color rColor, Color wColor) {
			setLayout(new GridLayout(x,y));
			
			JPanel checkBoxPanel = new JPanel();
			checkBoxPanel.setLayout(new GridLayout(3,2));
			checkBoxPanel.setBorder(new TitledBorder(new EtchedBorder(), "People"));

			ArrayList<Card> boardPlayers = board.getPlayerDeck();
			String boardPlayerArray[] = new String[boardPlayers.size()];
			
			for(int i = 0; i < boardPlayers.size(); i++) {
				boardPlayerArray[i] = boardPlayers.get(i).toString();
			}
			
			JCheckBox n1 = new JCheckBox(boardPlayerArray[0]); JCheckBox n2 = new JCheckBox(boardPlayerArray[1]);
			JCheckBox n3 = new JCheckBox(boardPlayerArray[2]); JCheckBox n4 = new JCheckBox(boardPlayerArray[3]);
			JCheckBox n5 = new JCheckBox(boardPlayerArray[4]); JCheckBox n6 = new JCheckBox(boardPlayerArray[5]);
			
			setLayout(new GridLayout(1,3));
			
			checkBoxPanel.add(n1); checkBoxPanel.add(n2); checkBoxPanel.add(n3); checkBoxPanel.add(n4); checkBoxPanel.add(n5); checkBoxPanel.add(n6);

			JPanel comboBoxPanel = new JPanel();
			comboBoxPanel.setLayout(new GridLayout(2,2));
			comboBoxPanel.setBorder(new TitledBorder(new EtchedBorder(), "Person Guess"));
			
			JComboBox c1 = new JComboBox(boardPlayerArray);
			
			comboBoxPanel.add(c1);
			
			//setLayout(new GridLayout(1,2));
			add(checkBoxPanel);
			add(comboBoxPanel);
		}
	}

	public class detectiveNotesRoom extends JPanel{
		public detectiveNotesRoom(int x, int y, boolean editP, Color pColor, Color rColor, Color wColor) {
			setLayout(new GridLayout(x,y));
			
			JPanel checkBoxPanel = new JPanel();
			checkBoxPanel.setLayout(new GridLayout(5,2));
			checkBoxPanel.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));

			ArrayList<Card> boardRooms = board.getRoomDeck();
			String boardRoomsArray[] = new String[boardRooms.size()];
			
			for(int i = 0; i < boardRooms.size(); i++) {
				boardRoomsArray[i] = boardRooms.get(i).toString();
			}
			
			JCheckBox n1 = new JCheckBox(boardRoomsArray[0]); JCheckBox n2 = new JCheckBox(boardRoomsArray[1]);
			JCheckBox n3 = new JCheckBox(boardRoomsArray[2]); JCheckBox n4 = new JCheckBox(boardRoomsArray[3]);
			JCheckBox n5 = new JCheckBox(boardRoomsArray[4]); JCheckBox n6 = new JCheckBox(boardRoomsArray[5]);
			JCheckBox n7 = new JCheckBox(boardRoomsArray[6]); JCheckBox n8 = new JCheckBox(boardRoomsArray[7]);
			JCheckBox n9 = new JCheckBox(boardRoomsArray[8]);
			
			setLayout(new GridLayout(1,3));
			
			checkBoxPanel.add(n1); checkBoxPanel.add(n2); checkBoxPanel.add(n3); checkBoxPanel.add(n4); checkBoxPanel.add(n5); checkBoxPanel.add(n6);
			checkBoxPanel.add(n7); checkBoxPanel.add(n8); checkBoxPanel.add(n9);

			JPanel comboBoxPanel = new JPanel();
			comboBoxPanel.setLayout(new GridLayout(2,2));
			comboBoxPanel.setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));
			
			JComboBox c1 = new JComboBox(boardRoomsArray);
			
			comboBoxPanel.add(c1);
			
			//setLayout(new GridLayout(1,2));
			add(checkBoxPanel);
			add(comboBoxPanel);
		}
	}

	public class detectiveNotesWeapons extends JPanel{
		public detectiveNotesWeapons(int x, int y, boolean editP, Color pColor, Color rColor, Color wColor) {
			setLayout(new GridLayout(x,y));
			
			JPanel checkBoxPanel = new JPanel();
			checkBoxPanel.setLayout(new GridLayout(3,2));
			checkBoxPanel.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));

			ArrayList<Card> boardWeapons = board.getWeaponDeck();
			String boardPlayerArray[] = new String[boardWeapons.size()];
			
			for(int i = 0; i < boardWeapons.size(); i++) {
				boardPlayerArray[i] = boardWeapons.get(i).toString();
			}
			
			JCheckBox n1 = new JCheckBox(boardPlayerArray[0]); JCheckBox n2 = new JCheckBox(boardPlayerArray[1]);
			JCheckBox n3 = new JCheckBox(boardPlayerArray[2]); JCheckBox n4 = new JCheckBox(boardPlayerArray[3]);
			JCheckBox n5 = new JCheckBox(boardPlayerArray[4]); JCheckBox n6 = new JCheckBox(boardPlayerArray[5]);
			
			setLayout(new GridLayout(1,3));
			
			checkBoxPanel.add(n1); checkBoxPanel.add(n2); checkBoxPanel.add(n3); checkBoxPanel.add(n4); checkBoxPanel.add(n5); checkBoxPanel.add(n6);

			JPanel comboBoxPanel = new JPanel();
			comboBoxPanel.setLayout(new GridLayout(2,2));
			comboBoxPanel.setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
			
			JComboBox c1 = new JComboBox(boardPlayerArray);
			
			comboBoxPanel.add(c1);
			
			//setLayout(new GridLayout(1,2));
			add(checkBoxPanel);
			add(comboBoxPanel);
		}
	}

	public static void main(String [] args) throws InterruptedException {
		setup();
		
		CardsAndNotes gui = new CardsAndNotes(210, 500, true);
		gui.setVisible(true);
		//gui.setResizable(false);
		
	}
}