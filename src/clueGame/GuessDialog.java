/**. 
 * GuessDialog is the class that builds the Make-a-Guess,
 * GUI for ClueGame. It extends to JFrame and allows to,
 * human player to input a guess for the ClueGame. This,
 * works for both when the Player hits the Accuse button,
 * and when the player enters into a room.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GuessDialog extends JFrame {
	private static Board board;
	private boolean inRoom;
	private String theRoomsName;

	// constructor for GuessDialog Class
	public GuessDialog(Board gameBoard, boolean isInRoom, String roomName) {
		GuessDialog.board = gameBoard;
		inRoom = isInRoom;
		theRoomsName = roomName;
		setTitle("Make a Guess");
		setSize(350, 150);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		createLayout();
	}
	
	// creates overall layout for GuessDialog GUI
	public void createLayout() {
		setLayout(new BorderLayout());
		setLayout(new GridLayout(4,1));
		
		// create label and ComboBox/label GUI element for Rooms on the GuessDialog GUI
		guessingOptions roomOptions = new guessingOptions("		Your Room", board.getRoomDeck(), inRoom);
		add(roomOptions);
		
		// create label and ComboBox GUI element for Players on the GuessDialog GUI
		guessingOptions personOptions = new guessingOptions("		Person", board.getPlayerDeck(), false);
		add(personOptions);
		
		// create label and ComboBox GUI element for Weapons on the GuessDialog GUI
		guessingOptions weaponOptions = new guessingOptions("		Weapon", board.getWeaponDeck(), false);
		add(weaponOptions);
		
		// creates Submit and Cancel buttons for GuessDialog GUI
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,2));
		JButton submit = new JButton("Submit");
		JButton cancel = new JButton("Cancel");
		buttons.add(submit);
		buttons.add(cancel);
		add(buttons);
		
		// Submit and Cancel buttons listeners
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedRoom = "";
				if(!inRoom) {
					selectedRoom = roomOptions.comboBox.getSelectedItem().toString();
				}
				String selectedPlayer = personOptions.comboBox.getSelectedItem().toString();
				String selectedWeapon = weaponOptions.comboBox.getSelectedItem().toString();
				
				System.out.println("SELECTED: " + selectedRoom + ", " + selectedPlayer + ", " + selectedWeapon);
				dispose();
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Control.accuseActive = false;
				dispose();
			}
		});
	}

	// main method for creating Label and ComboBox/Label for Rooms, Players, and Weapons
	public class guessingOptions extends JPanel{
		private JComboBox comboBox;
		
		public guessingOptions(String labelTile1, ArrayList<Card> theDeck, boolean disPlayRooms) {
			setLayout(new BorderLayout());
			setLayout(new GridLayout(1,2));
			
			String boardNewArray[] = new String[theDeck.size()];
			for(int i = 0; i < theDeck.size(); i++) {
				boardNewArray[i] = theDeck.get(i).toString();
			}
			
			comboBox = new JComboBox(boardNewArray);

			JLabel label1 = new JLabel(labelTile1);

			add(label1);
			
			if(disPlayRooms) {
				JLabel label2 = new JLabel("	" + theRoomsName);
				add(label2);
			}else {
				add(comboBox);
			}
		}
	}
	
	
}