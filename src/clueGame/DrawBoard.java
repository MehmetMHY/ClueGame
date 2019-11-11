package clueGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawBoard extends JPanel {
	private static Board board;

	static int num_row;
	static int num_col;
		
	static final int RADIUS = 24;
	static final int HEIGHT = 25;
	static final int WIDTH = 25;
	
	private List<List<Block>> drawBoard;
	
	
	public DrawBoard(Board gameBoard) {
		setBoard(gameBoard);
		num_row = board.getNumRows();
		num_col = board.getNumColumns();
		drawBoard = new ArrayList<List<Block>>();
		//setPreferredSize(new Dimension(100*num_row, 100*num_col));
		createBoard();
		labelBoardRooms();
	}

	public void createBoard() {
		for (int i = 0; i < num_row; i++) {
			ArrayList<Block> temp = new ArrayList<Block>();
			for (int j = 0; j < num_col; j++) {
				temp.add(new Block(i*HEIGHT,j*WIDTH));
			}
			drawBoard.add(temp);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < drawBoard.size(); i++) {
			for (int j = 0; j < drawBoard.get(i).size(); j++) {
				if (board.getCellAt(i, j).isRoom()) {
					drawBoard.get(i).get(j).drawRoom(g);
				} else if (board.getCellAt(i, j).isDoorway()){
					drawBoard.get(i).get(j).drawDoor(g, board.getCellAt(i, j).getDoorDirection());
				} else {
					drawBoard.get(i).get(j).drawWalkway(g);
				}
			}
		}
		for (ComputerPlayer c:board.getPlayers().values()) {
			g.setColor(c.getColor());
			g.fillOval(c.getColumn()*HEIGHT, c.getRow()*WIDTH, RADIUS, RADIUS);
		}
		g.setColor(board.getP1().color);
		g.fillOval(board.getP1().column*HEIGHT, board.getP1().row*WIDTH, RADIUS, RADIUS);
	}
	
//	public void createPlayers() {
//		for (ComputerPlayer c:board.getPlayers().values()) {
//			drawPlayer tempDP = new drawPlayer(c.row*100,c.column*100, RADIUS, c.color);
//			player.add(tempDP);
//		}
//		HumanPlayer tempH = board.getP1();
//		drawPlayer humanP = new drawPlayer(tempH.row*100, tempH.column*100, RADIUS, tempH.color);
//		player.add(humanP);
//	}
	
	public void labelBoardRooms() {
		setLayout(null);
		
	    JLabel label = new JLabel("Master Bedroom");
	    add(label);
	    Dimension size = label.getPreferredSize();
	    label.setBounds(18, 60, size.width, size.height);
	    
	    JLabel label2 = new JLabel("Bathroom");
	    add(label2);
	    Dimension size2 = label2.getPreferredSize();
	    label2.setBounds(260, 50, size2.width, size2.height);
	    
	    JLabel label3 = new JLabel("Office");
	    add(label3);
	    Dimension size3 = label3.getPreferredSize();
	    label3.setBounds(500, 25, size3.width, size3.height);

	    JLabel label4 = new JLabel("Game Hour");
	    add(label4);
	    Dimension size4 = label4.getPreferredSize();
	    label4.setBounds(20, 280, size4.width, size4.height);

	    JLabel label5 = new JLabel("Kitchen");
	    add(label5);
	    Dimension size5 = label5.getPreferredSize();
	    label5.setBounds(45, 480, size5.width, size5.height);
	    
	    JLabel label6 = new JLabel("Dining Room");
	    add(label6);
	    Dimension size6 = label6.getPreferredSize();
	    label6.setBounds(240, 460, size6.width, size6.height);
	    
	    JLabel label7 = new JLabel("Theater");
	    add(label7);
	    Dimension size7 = label7.getPreferredSize();
	    label7.setBounds(480, 420, size7.width, size7.height);
	    
	    JLabel label8 = new JLabel("Lounge");
	    add(label8);
	    Dimension size8 = label8.getPreferredSize();
	    label8.setBounds(450, 240, size8.width, size8.height);
	    
	    JLabel label9 = new JLabel("Library");
	    add(label9);
	    Dimension size9 = label9.getPreferredSize();
	    label9.setBounds(450, 120, size9.width, size9.height);
	}
	
	public static void setBoard(Board board) {
		DrawBoard.board = board;
	}
}
