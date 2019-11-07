/**. 
 * Control Class is the GUI class for the control panel,
 * for the Clue Board Game. This is only one piece,
 * of the code's overall GUI structure.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Control extends JFrame {
	private JTextField character; // stores text value for "Whose turn?" for the Control Panel GUI
	private JTextField dieRoll; // stores text value for dice roll for the Control Panel GUI
	private JTextField guess; // stores a character's guess as a text value for the Control Panel GUI
	private JTextField guessResult; // stores result from guess as a text value for the Control Panel GUI
	
	// setup title, window size, and enable EXIT for the Control Panel GUI window
	public Control(int x, int y) {
		setTitle("Clue Game");
		setSize(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createLayout();
	}
	
	// class for "Whose Turn?" panels for the GUI
	public class whoPlayer extends JPanel{
		public whoPlayer(int x, int y, Color color, boolean editP) {
			// set up grid layout and title border
			setLayout(new GridLayout(x,y));
			setBorder(new TitledBorder(new EtchedBorder(), "Whose Turn?"));
			
			// set up character text panel
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1,2));

			// place text field in the character text panel
			character = new JTextField(5);
			character.setSize(5, 5);
			character.setEditable(editP);
			panel.add(character);
			
			// color the border of the panel with declared color value
			panel.setBorder(new LineBorder(color));
			add(panel);
		}
	}
	
	// class for "Die" panels for the GUI
	public class theDieRoll extends JPanel{
		public theDieRoll(int x, int y, Color color, boolean editP) {
			// set up grid layout and title border
			setLayout(new GridLayout(x,y));
			setBorder(new TitledBorder(new EtchedBorder(), "Die"));
			
			// set up display label
			JLabel displayLabel = new JLabel("Roll");
			
			// set up dice roll text panel
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1,2));

			// place text field in the dice roll text panel
			dieRoll = new JTextField(5);
			dieRoll.setSize(5, 5);
			dieRoll.setEditable(editP);
			panel.add(dieRoll);
			
			// color the border of the panel with declared color value
			panel.setBorder(new LineBorder(color));
			add(displayLabel);
			add(panel);
		}
	}
	
	// class for "Guess" panels for the GUI
	public class theGuess extends JPanel{
		public theGuess(int x, int y, Color color, boolean editP) {
			// set up grid layout and title border
			setLayout(new GridLayout(x,y));
			setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
			
			// set up display label
			JLabel displayLabel = new JLabel("Guess");
			
			// set up guess text panel
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1,2));

			// place text field in the guess text panel
			guess = new JTextField(5);
			guess.setSize(5, 5);
			guess.setEditable(editP);
			panel.add(guess);
			
			// color the border of the panel with declared color value
			panel.setBorder(new LineBorder(color));
			add(displayLabel);
			add(panel);
		}
	}
	
	// class for "Guess Result" panels for the GUI
	public class theGuessResult extends JPanel{
		public theGuessResult(int x, int y, Color color, boolean editP) {
			// set up grid layout and title border
			setLayout(new GridLayout(x,y));
			setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
			
			// set up display label
			JLabel displayLabel = new JLabel("Response");
			
			// set up guess_result text panel
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1,2));

			// place text field in the guess_result text panel
			guessResult = new JTextField(5);
			guessResult.setSize(5, 5);
			guessResult.setEditable(editP);
			panel.add(guessResult);
			
			// color the border of the panel with declared color value
			panel.setBorder(new LineBorder(color));
			add(displayLabel);
			add(panel);
		}
	}
	
	// void class for creating the over all layout of the GUI
	public void createLayout() {
		// create "Whose Turn?" panel of the GUI with a blue bolder and a BorderLayout at the South
		whoPlayer p = new whoPlayer(1, 5, Color.blue, false); // also make setEditable equal to false for whoPlayer
		add(p, BorderLayout.SOUTH);

		// create "Die" panel of the GUI with a red bolder and a BorderLayout at the South
		theDieRoll roll = new theDieRoll(2, 3, Color.red, false); // also make setEditable equal to false for theDieRoll
		add(roll, BorderLayout.SOUTH);

		// create "Guess" panel of the GUI with a black bolder and a BorderLayout at the South
		theGuess guess = new theGuess(2 ,3, Color.black, false); // also make setEditable equal to false for theGuess
		add(guess, BorderLayout.SOUTH);
		
		// create "Response" panel of the GUI with a green bolder and a BorderLayout at the South
		theGuessResult results = new theGuessResult(2 , 3, Color.green, false); // also make setEditable equal to false for theGuessResult
		add(results, BorderLayout.SOUTH);
		
		// create both the Next and Accuse buttons on the GUI as well as create a BorderLayout at the South
		JButton next = new JButton("Next");
		JButton makeAccusation = new JButton("Accuse");
		setLayout(new GridLayout(6,6));
		add(next, BorderLayout.SOUTH);
		add(makeAccusation, BorderLayout.SOUTH);
	
	}
	
	// setters for the character, dieRoll, guess, and guessResult JTextField variable for the Control class
	
	public void setCharacter(String name) {
		character.setText(name);
	}
	
	public void setDieRoll(String diceRoll) {
		dieRoll.setText(diceRoll);
	}
	
	public void setGuess(String thereGuess) {
		guess.setText(thereGuess);
	}
	
	public void setGuessResult(String thereResults) {
		guessResult.setText(thereResults);
	}
	
	// Here is a test in main that shows the GUI working as well as the text values changing in the GUI without any errors
	
	// main method for the GUI in the Control class
	public static void main(String [] args) throws InterruptedException {
		
		// create Control object with a window size of 210 by 500
		Control gui = new Control(210, 500);
		gui.setResizable(false);
		
		// set the "Whose Turn?", "Die", "Guess", and "Guess Result" values for the GUI
		gui.setCharacter("Col. Mustard");
		gui.setDieRoll("5");
		gui.setGuess("My guess");
		gui.setGuessResult("I guessed it!");
		gui.setVisible(true);
		
		// 5 second delay
		TimeUnit.SECONDS.sleep(5);
		
		// update the "Whose Turn?", "Die", "Guess", and "Guess Result" values for the GUI
		gui.setCharacter("Mehmet");
		gui.setDieRoll("1");
		gui.setGuess("Ruidi");
		gui.setGuessResult("Your Guess is Wrong!");
		gui.setVisible(true);
		
	}
}