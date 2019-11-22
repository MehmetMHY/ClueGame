/**. 
 * Error message GUI for when the human player selects,
 * a non-target boardcell.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class TargetError extends JFrame {
	private static Board board;

	// constructor for TargetError Class
	public TargetError(Board gameBoard) {
		TargetError.board = gameBoard;
		setTitle("Message");
		setSize(439, 137);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		createLayout();
		setResizable(false);
	}
	
	// creates overall layout for NonTargetErrorMessage GUI
	public void createLayout() {
		setLayout(null);
		
		// creates NonTargetErrorMessage GUI's error message
		JLabel message = new JLabel("Invalid target selected. Please select again!");
		add(message);
		message.setSize(message.getPreferredSize());
		message.setLocation(140, 25);
		
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