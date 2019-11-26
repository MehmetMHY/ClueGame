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
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GuessDialog extends JFrame {
	private static Board board;
	private boolean inRoom;
	private String theRoomsName;
	private Solution suggested;
	private boolean submited;
	private String disprove;
	private Card disproveCard;

	// constructor for GuessDialog Class
	public GuessDialog(Board gameBoard, boolean isInRoom, String roomName) {
		Control.accuseActive = false;
		GuessDialog.board = gameBoard;
		inRoom = isInRoom;
		theRoomsName = roomName;
		disprove = null;
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
				
				if (e.getSource() == submit) {
					submited = true;
				} else {
					submited = false;
				}
				
				if(!inRoom) {
					selectedRoom = roomOptions.comboBox.getSelectedItem().toString();
				}
				String selectedPlayer = personOptions.comboBox.getSelectedItem().toString();
				String selectedWeapon = weaponOptions.comboBox.getSelectedItem().toString();
				
				Card personT = null;
				Card weaponT = null;
				Card roomT = null;
				
				
				for (Card i:board.getPlayerDeck()) {
					if (i.getCardName().equals(personOptions.comboBox.getSelectedItem().toString())) {
						personT = i;
					}
				}
				
				for (Card j:board.getWeaponDeck()) {
					if (j.getCardName().equalsIgnoreCase(weaponOptions.comboBox.getSelectedItem().toString())) {
						weaponT = j;
					}
				}
				
				for (Card k:board.getRoomDeck()) {
					if (k.getCardName().equalsIgnoreCase(roomOptions.comboBox.getSelectedItem().toString())) {
						roomT = k;
					}
				}
 				
				if (submited) {
					//System.out.println(personT.getCardName() + " " + roomT.getCardName() + " " + weaponT.getCardName());
					suggested = new Solution(personT, roomT, weaponT);
					if (inRoom) {
						if (board.getPlayers().get(suggested.getPerson()) != null) {
							board.getPlayers().get(suggested.getPerson()).setRow(board.getP1().row);
							board.getPlayers().get(suggested.getPerson()).setColumn(board.getP1().column);
						}
						for (Map.Entry<String,ComputerPlayer> p : board.getPlayers().entrySet()) {
							disproveCard = board.handleSuggestion(board.getP1(), board.getPlayers(), board.getP1(), suggested);
						}
						if (disproveCard != null) {
							for (Map.Entry<String,ComputerPlayer> p : board.getPlayers().entrySet()) {
								if (disproveCard.getType() == CardType.PERSON) {
									p.getValue().addSeenPlayers(disproveCard);
								} else if (disproveCard.getType() == CardType.WEAPON) {
									p.getValue().addSeenWeapons(disproveCard);
								} else if (disproveCard.getType() == CardType.ROOM) {
									p.getValue().addSeenRooms(disproveCard);
								}								
							}
						}
						//System.out.println(disproveCard.getCardName());
						disprove = new String(disproveCard.getCardName());
						board.setGuess(suggested.getPerson() + " " + suggested.getRoom() + " " + suggested.getWeapon());
						board.setRespond(disprove);
					} else {
						suggested = new Solution(personT, roomT, weaponT);
						//setGuess(suggested.getPerson() + " " + suggested.getRoom() + " " + suggested.getWeapon());
						//setResult("No one can disapprove.");
						checkAccusation(suggested);
						board.setGuess(suggested.getPerson() + " " + suggested.getRoom() + " " + suggested.getWeapon());
						board.setRespond("No one can disprove");
					}
					
				}
				 	
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
	
	public String getDisprove() {
		return disprove;
	}

	public void checkAccusation(Solution suggested) {
		if (board.getAnswer().equals(suggested)) {
			JFrame winning = new JFrame();
			JOptionPane.showMessageDialog(winning, "YOU WIN!", "CONGRAT", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		} else {
			JFrame losing = new JFrame();
			JOptionPane.showMessageDialog(losing, "Sorry, that's not correct", "Attention", JOptionPane.INFORMATION_MESSAGE);
		}
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
	
	public Solution getSuggested() {
		return suggested;
	}
	
}