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
	private SouthPanel south;
	
	public SouthPanel getSouth() {
		return south;
	}
	
	// setup title, window size, and enable EXIT for the Control Panel GUI window
	public Control(int x, int y) {
		setTitle("Clue Game");
		setSize(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createLayout();
	}
	
	// class for "Whose Turn?" panels for the GUI
	public class Control2 extends JPanel{
		
		public JTextField character;
		
		
		public Control2(int x, int y, Color color, boolean editP) {
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
	
	// class for "Guess Result" panels for the GUI
	public class Control1 extends JPanel{
		public JTextField text;
		public Control1(int x, int y, Color color, boolean editP) {
			// set up grid layout and title border
			setLayout(new GridLayout(x,y));
			setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
			
			// set up display label
			JLabel displayLabel = new JLabel("Response");
			
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
	
	public class SouthPanel extends JPanel{
		public Control1 roll;
		public Control1 guess;
		public Control1 results;
		public Control2 p;
		public SouthPanel() {
			// create "Whose Turn?" panel of the GUI with a blue bolder and a BorderLayout at the South
			p = new Control2(1, 5, Color.blue, false); // also make setEditable equal to false for whoPlayer
			add(p, BorderLayout.CENTER);
			
			// create "Die" panel of the GUI with a red bolder and a BorderLayout at the South
			roll = new Control1(2, 3, Color.red, false); // also make setEditable equal to false for theDieRoll
			add(roll, BorderLayout.CENTER);

			// create "Guess" panel of the GUI with a black bolder and a BorderLayout at the South
			guess = new Control1(2 ,3, Color.black, false); // also make setEditable equal to false for theGuess
			add(guess, BorderLayout.CENTER);
			
			// create "Response" panel of the GUI with a green bolder and a BorderLayout at the South
			results = new Control1(2 , 3, Color.green, false); // also make setEditable equal to false for theGuessResult
			add(results, BorderLayout.CENTER);
			
			// create both the Next and Accuse buttons on the GUI as well as create a BorderLayout at the South
			JButton next = new JButton("Next");
			JButton makeAccusation = new JButton("Accuse");
			setLayout(new GridLayout(2,2));
			add(next, BorderLayout.CENTER);
			add(makeAccusation, BorderLayout.SOUTH);
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
	
	// void class for creating the over all layout of the GUI
	public void createLayout() {
		DrawBoard boardPanel = new DrawBoard();
		//setLayout(new GridLayout(1,1));
		add(boardPanel, BorderLayout.CENTER);
		
		south = new SouthPanel();
		add(south, BorderLayout.SOUTH);
	}
		
	// Here is a test in main that shows the GUI working as well as the text values changing in the GUI without any errors
	
	// main method for the GUI in the Control class
	public static void main(String [] args) throws InterruptedException {
		
		// create Control object with a window size of 210 by 500
		Control gui = new Control(600, 600);
		//gui.setResizable(false);
		// set the "Whose Turn?", "Die", "Guess", and "Guess Result" values for the GUI
		gui.getSouth().setP("Col. Mustard");
		gui.getSouth().setRoll(5);
		gui.getSouth().setGuess("My guess");
		gui.getSouth().setResult("I guessed it!");
		gui.setVisible(true);
		
//		// 5 second delay
//		TimeUnit.SECONDS.sleep(5);
//		
//		// update the "Whose Turn?", "Die", "Guess", and "Guess Result" values for the GUI
//		gui.setCharacter("Mehmet");
//		gui.setDieRoll("1");
//		gui.setGuess("Ruidi");
//		gui.setGuessResult("Your Guess is Wrong!");
//		gui.setVisible(true);
		
	}
}