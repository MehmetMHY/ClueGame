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
//		for (ComputerPlayer c:board.getPlayegetBoard()rs().values()) {
//			drawPlayer tempDP = new drawPlayer(c.row*100,c.column*100, RADIUS, c.color);
//			player.add(tempDP);
//		}
//		HumanPlayer tempH = board.getP1();
//		drawPlayer humanP = new drawPlayer(tempH.row*100, tempH.column*100, RADIUS, tempH.color);
//		player.add(humanP);
//	}
	
	public void labelBoardRooms() {
		List<String> roomIDList = board.getRoomID();
		List<Card> roomsList = board.getRoomDeck();
		BoardCell boardCopy[][] = board.getBoard();
		JLabel label;
		Dimension size;

		setLayout(null);

		for(int i = 0; i < board.getNumRows(); i++) {
			for(int j = 0; j < board.getNumColumns(); j++) {
				String go = boardCopy[i][j].getRoomType();
				if(go.substring(1).equals("X")) {
					for(int k = 0; k < roomIDList.size(); k++) {
						if(go.substring(0,1).equals(roomIDList.get(k))) {
						    label = new JLabel(roomsList.get(k).toString());
						    add(label);
						    size = label.getPreferredSize();
						    label.setBounds(j*(Block.WIDTH), i*(Block.HEIGHT), size.width, size.height);
						}
					}
				}
			}
		}
	}
	
	public static void setBoard(Board board) {
		DrawBoard.board = board;
	}
}
