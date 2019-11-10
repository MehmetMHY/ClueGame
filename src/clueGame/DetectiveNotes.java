package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNotes extends JFrame {
	private static Board board;
	
	// initialize the board:
	public static void setup() {
		board = Board.getInstance();
		board.setConfigFiles("OurBoardLayout.csv", "OurRooms.txt");
		board.setCardConfigFiles("Weapon.txt", "Players.txt");
		board.initialize();
	}
	
	public DetectiveNotes(int x, int y) {
		setup();
		setTitle("Detective Notes");
		setSize(x, y);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		createLayout();
	}
	
	public void viewIt() {
		setVisible(true);
	}
	
	
	public void createLayout() {
		setLayout(new BorderLayout());
		setLayout(new GridLayout(3,2));
		
		detectiveNotesPerson dnp = new detectiveNotesPerson(3, 2, false, Color.green, Color.yellow, Color.red);
		add(dnp);
		
		detectiveNotesRoom dnp2 = new detectiveNotesRoom(3, 2, false, Color.green, Color.yellow, Color.red);
		add(dnp2);
		
		detectiveNotesWeapons dnp3 = new detectiveNotesWeapons(3, 2, false, Color.green, Color.yellow, Color.red);
		add(dnp3);
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

			add(checkBoxPanel);
			add(comboBoxPanel);
		}
	}
}