/**. 
 * DrawBoard is the class that builds the GUI for ClueGame's game board,
 * player's Icons on the board, as well as label all the Rooms on the,
 * game board. This class extends to JPanel and is part of ClueGame's,
 * GUI structure. It also acts the the mouse listener for the GUI.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DrawBoard extends JPanel {
	private static Board board; // ClueGame board object for DrawBoard class
	
	static final int RADIUS = 24; // radius for the GUI's player icons
	static final int HEIGHT = 25; // height for GUI's BoardCell
	static final int WIDTH = 25; // width for GUI's BoardCell

	static int num_row;
	static int num_col;

	private int boarderWidth;
	private int boarderHeight;
	
	private List<List<Block>> drawBoard;
	
	private static BoardCell clickedCell;
	
	private static boolean moved;
	private static Stack<String> turn;
	
	public static boolean playersTurn = false;
	
	// constructor for DrawBoard class
	public DrawBoard(Board gameBoard) {
		DrawBoard.board = gameBoard;
		num_row = board.getNumRows();
		num_col = board.getNumColumns();
		drawBoard = new ArrayList<List<Block>>();
		createBoard();
		labelBoardRooms();
		boarderWidth = num_row * WIDTH;
		boarderHeight = num_col * HEIGHT;
		this.setSize(boarderWidth, boarderHeight);
		this.addMouseListener(new ClickLocation(boarderWidth, boarderHeight, num_row, num_col));
		moved = true;
		turn = new Stack<String>();
		clickedCell = new BoardCell(-1,-1);
	}
	
	// creates game board's GUI layout
	public void createBoard() {
		for (int i = 0; i < num_row; i++) {
			ArrayList<Block> temp = new ArrayList<Block>();
			for (int j = 0; j < num_col; j++) {
				temp.add(new Block(i*HEIGHT,j*WIDTH));
			}
			drawBoard.add(temp);
		}
	}

	// method that paints the game board's GUI layout based on isRoom, isDoorway, and isWalkWay
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
		
		// draw and paint players' GUI on top of game board GUI
		for (ComputerPlayer c:board.getPlayers().values()) {
			g.setColor(c.getColor());
			g.fillOval(c.getColumn()*HEIGHT, c.getRow()*WIDTH, RADIUS, RADIUS);
		}
		g.setColor(board.getP1().color);
		g.fillOval(board.getP1().column*HEIGHT, board.getP1().row*WIDTH, RADIUS, RADIUS);
		repaint();
		
		//Check for location mouse clicked
		if (turn.size() == 0 && board.getTargets().contains(clickedCell)) {
			playersTurn = false;
			board.getP1().setRow(getClickedCell().getRow());
			board.getP1().setColumn(getClickedCell().getCol());
			for (BoardCell c:board.getTargets()) {
				getDrawBoard().get(c.getRow()).get(c.getCol()).setTarget(false);
			}
			turn.add(board.getP1().playerName);
			moved = true;
			
			if(clickedCell.isDoorway()) {
				Control.accuseActive = true;
				GuessDialog guessDialog;
				
				String inRoomName = board.getLegend().get(clickedCell.getInitial());				
				guessDialog = new GuessDialog(board, true, inRoomName);
				guessDialog.setVisible(true);
			}
			
			clickedCell = new BoardCell(-1,-1);
			//restartMouse();
			
		
		} else if (clickedCell.getCol() != -1 && playersTurn) {
			clickedCell = new BoardCell(-1,-1);
		
			// ***There is an error with the Dialog, for now we will just print the error message in the terminal:
			//JFrame badTarget = new JFrame();
			//JOptionPane.showMessageDialog(badTarget, "Invalid target selected. Please select again!","Message", JOptionPane.INFORMATION_MESSAGE);			
			//System.out.println("Invalid target selected. Please select again!");
			
			if(!Control.accuseActive) {
				Control.accuseActive = true;
				
				errorMessage guessDialog;
				guessDialog = new errorMessage(board, "Invalid target selected. Please select again!");
				guessDialog.setVisible(true);
			}
			
			restartMouse();
			repaint();
			
			//update(g);
		}
	}

	// method that labels each room on the game board GUI
	public void labelBoardRooms() {
		List<String> roomIDList = board.getRoomID();
		List<Card> roomsList = board.getRoomDeck();
		BoardCell boardCopy[][] = board.getBoard();

		/**. 
		 * loop though each sell on the ClueGame board, if a cell contains a RoomType with "X" in it,
		 * place a JLabel panel at that BoardCell with a correct Room name based on the roomType for,
		 * that BoardCell. The location of that panel is scaled by the HEIGHT and WIDTH int values. 
		 */
		setLayout(null);
		for(int i = 0; i < board.getNumRows(); i++) {
			for(int j = 0; j < board.getNumColumns(); j++) {
				String roomType = boardCopy[i][j].getRoomType();
				if(roomType.substring(1).equals("X")) {
					for(int k = 0; k < roomIDList.size(); k++) {
						if(roomType.substring(0,1).equals(roomIDList.get(k))) {
							JLabel label = new JLabel(roomsList.get(k).toString());
						    add(label);
						    Dimension size = label.getPreferredSize();
						    label.setBounds(j*(Block.WIDTH), i*(Block.HEIGHT), size.width, size.height);
						}
					}
				}
			}
		}
	}
	public static void restartMouse() {
//		clickedCell.setCol(-1);
//		clickedCell.setRow(-1);
		clickedCell = new BoardCell(-1,-1);
	}

	// method for mouse and MouseListener element of the GUI
	public class ClickLocation extends MouseAdapter {
		
		private int width; // mouse listener's width range on GUI
		private int height; // mouse listener's height range on GUI
		private int row; // ClueGame board row size
		private int col; // ClueGame board column size
		private int boxD; // dimension for the boardCells on the GUI
		
		public ClickLocation(int width, int height, int row, int col) {
			this.width = width;
			this.height = height;		
			boxD = this.width/row;
		}
		
		// mouse listener for GUI
		@Override
		public void mouseClicked(MouseEvent t) {
			/**. 
			 * if where the mouse clicks is within the ClueGame board GUI's dimensions,
			 * set clickedCell to the boardCell the user clicked on
			 */
			if (t.getX() <= height && t.getY() <= width) {
				//System.out.println(t.getX()/boxD + ", " + t.getY()/boxD + " ---> " + playersTurn); System.out.println(Control.accuseActive + " " + playersTurn); System.out.println(" ");
				if(!Control.accuseActive && playersTurn) {
					clickedCell = board.getCellAt(t.getY()/boxD, t.getX()/boxD);
					
				}
			}
		}
		
		public int getRow() {
			return row;
		}

		public int getCol() {
			return col;
		}

	}
	
	public BoardCell getClickedCell() {
		return clickedCell;
	}

	public List<List<Block>> getDrawBoard() {
		return drawBoard;
	}

	public static boolean isMoved() {
		return moved;
	}

	public static void setMoved(boolean moved) {
		DrawBoard.moved = moved;
	}
	
	public static Stack<String> getTurn() {
		return turn;
	}

	public static void setTurn(Stack<String> turn) {
		DrawBoard.turn = turn;
	}
}
