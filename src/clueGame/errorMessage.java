/**. 
 * errorMessage extends JFrame which acts as an custom, 
 * error message GUI for ClueGame.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class errorMessage extends JFrame {
	private static Board board;
	private String eMessage;

	// constructor for TargetError Class
	public errorMessage(Board gameBoard, String message) {
		errorMessage.board = gameBoard;
		eMessage = message;
		setTitle("Message");
		setSize(439, 137);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		createLayout();
		setResizable(false);
	}
	
	// creates overall layout for NonTargetErrorMessage GUI
	public void createLayout() {
		setLayout(null);
		
		// creates error message for the GUI's error message
		JLabel message = new JLabel(eMessage);
		add(message);
		message.setSize(message.getPreferredSize());
		message.setLocation(140, 25);
		
		// creates how-to-exit message for GUI
		Font f = new Font("monospace",Font.ITALIC + Font.BOLD,15);
		JLabel howToExit = new JLabel("hit OK to exit");
		howToExit.setFont(f);
		add(howToExit);
		howToExit.setSize(howToExit.getPreferredSize());
		howToExit.setLocation(140, 50);
		
		// creates NonTargetErrorMessage GUI's "OK" button
		JButton cancel = new JButton("OK");
		add(cancel);
		cancel.setSize(cancel.getPreferredSize());
		cancel.setLocation((439-100), (137-70));
		
		// creates NonTargetErrorMessage GUI's error icon image
		ImageIcon aImage = new ImageIcon("errorIcon.png");
		JLabel seeImage = new JLabel(aImage);
		add(seeImage);
		seeImage.setSize(seeImage.getPreferredSize());
		seeImage.setLocation(40, 20);
		
		// action listener for "OK" button
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Control.accuseActive = false;
				dispose();
			}
		});
	}
}