package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Control extends JFrame {
	
	public Control() {
		setTitle("Clue Game");
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createLayout();
	}
	
	public class ControlLayout extends JPanel{
		
		private JTextArea display;
		
		public ControlLayout(String name, int x, int y, String label) {
			setLayout(new GridLayout(x,y));
			setBorder(new TitledBorder(new EtchedBorder(), name));
			JLabel displayLabel = new JLabel(label);
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(Color.blue));
			//updateDisplay(content);
			add(displayLabel);
			add(panel);
			//add(display);
		}
		
	}
	
	public class Turn extends JPanel {
		public Turn(String name) {
			setLayout(new GridLayout(2,2));
			JLabel turn = new JLabel(name);
			JPanel panel = new JPanel();
			add(turn);
			panel.setBorder(new LineBorder(Color.blue));
			add(panel);
		}
	}
	
	public void createLayout() {
		
		JButton next = new JButton("Next");
		JButton makeAccusation = new JButton("Accuse");
		setLayout(new GridLayout(6,6));
		add(next, BorderLayout.SOUTH);
		add(makeAccusation, BorderLayout.SOUTH);
		ControlLayout guess = new ControlLayout("Guess", 2 ,2, "Guess");
		ControlLayout results = new ControlLayout("Guess Result", 2 ,2, "Response");
		ControlLayout die = new ControlLayout("Die", 2 ,1, "Roll");
		add(guess, BorderLayout.SOUTH);
		add(results, BorderLayout.SOUTH);
		add(die, BorderLayout.SOUTH);
		Turn turn = new Turn("Player turn");
		add(turn);
	
	}
	

	
	public static void main(String [] args) {
		Control gui = new Control();
		gui.setVisible(true);
		
	}
}
