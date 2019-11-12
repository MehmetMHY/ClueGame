/**. 
 * DetectiveNotes is the class that builds the Detective Notes,
 * GUI for ClueGame. It extends to JDialog and acts as a custom,
 * Dialog for the GUI.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*; 
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNotes extends JDialog {
	private static Board board;

	// constructor for DectiveNotes Class
	public DetectiveNotes(int x, int y, Board gameBoard) {
		DetectiveNotes.board = gameBoard;
		setTitle("Detective Notes");
		setSize(x, y);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		createLayout();
	}
	
	// creates the overall Detective Notes GUI panels layout
	public void createLayout() {
		setLayout(new BorderLayout());
		setLayout(new GridLayout(3,2));
		
		// creates the Detective Note GUI's People Notes element
		detectiveNotes peopleNotes = new detectiveNotes(3, 2, false, "People", "Person Guess", board.getPlayerDeck(), 3, 2);
		add(peopleNotes);
		
		// creates the Detective Note GUI's Rooms Notes element
		detectiveNotes roomNotes = new detectiveNotes(3, 2, false, "Rooms", "Room Guess", board.getRoomDeck(), 5, 2);
		add(roomNotes);

		// creates the Detective Note GUI's Weapons Notes element
		detectiveNotes weaponNotes = new detectiveNotes(3, 2, false, "Weapons", "Weapon Guess", board.getWeaponDeck(), 3, 2);
		add(weaponNotes);
	}

	// method to create Detective Note's check box and combo box GUI elements for People, Rooms, and Weapons
	public class detectiveNotes extends JPanel{
		public detectiveNotes(int x, int y, boolean editP, String labelTile1, String labelTitle2, ArrayList<Card> theDeck, int panelX, int panelY) {
			setLayout(new GridLayout(x,y));
			
			// set up and label the check box panel
			JPanel checkBoxPanel = new JPanel();
			checkBoxPanel.setLayout(new GridLayout(panelX,panelY));
			checkBoxPanel.setBorder(new TitledBorder(new EtchedBorder(), labelTile1));

			setLayout(new GridLayout(1,3));
			
			// convert ArrayList of Card objects into one String array as well as add checkBox GUI elements for each index of this String array
			String boardNewArray[] = new String[theDeck.size()];
			for(int i = 0; i < theDeck.size(); i++) {
				boardNewArray[i] = theDeck.get(i).toString();
				JCheckBox checkBox = new JCheckBox(theDeck.get(i).toString());
				checkBoxPanel.add(checkBox);
			}

			// create combo box element for the GUI
			JPanel comboBoxPanel = new JPanel();
			comboBoxPanel.setLayout(new GridLayout(2,2));
			comboBoxPanel.setBorder(new TitledBorder(new EtchedBorder(), labelTitle2));
			JComboBox comboBox = new JComboBox(boardNewArray);
			
			// add everything for the overall Detective Note GUI Section
			comboBoxPanel.add(comboBox);
			add(checkBoxPanel);
			add(comboBoxPanel);
		}
	}
}