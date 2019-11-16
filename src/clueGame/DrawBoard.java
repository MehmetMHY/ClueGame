/**. 
 * DrawBoard is the class that builds the GUI for ClueGame's game board,
 * player's Icons on the board, as well as label all the Rooms on the,
 * game board. This class extends to JPanel and is part of ClueGame's,
 * GUI structure.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

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
		
	static final int RADIUS = 24; // radius for the GUI's player icons
	static final int HEIGHT = 25; // height for GUI's BoardCell
	static final int WIDTH = 25; // width for GUI's BoardCell
	private int boarderWidth;
	private int boarderHeight;
	
	private List<List<Block>> drawBoard;
	
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
	}
	
	// method that labels each room on the game board GUI
	public void labelBoardRooms() {
		List<String> roomIDList = board.getRoomID();
		List<Card> roomsList = board.getRoomDeck();
		BoardCell boardCopy[][] = board.getBoard();

		/**. 
		 * loop though each sell on the ClueGame board, if a cell contains a RoomType with "X" in is,
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
	
	public int getBoarderWidth() {
		return boarderWidth;
	}

	public int getBoarderHeight() {
		return boarderHeight;
	}

}
