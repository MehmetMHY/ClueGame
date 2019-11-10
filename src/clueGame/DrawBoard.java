package clueGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class DrawBoard extends JPanel {

	private static Board board;
	static int num_row;
	static int num_col;
	private List<List<Block>> drawBoard;
	public DrawBoard() {
		initializeBoard();
		num_row = board.getNumRows();
		num_col = board.getNumColumns();
		drawBoard = new ArrayList<List<Block>>();
		setPreferredSize(new Dimension(100*num_row, 100*num_col));
		createBoard();
	}

	public void initializeBoard() {
		board = Board.getInstance(); // only set instance variable for Board
		board.setConfigFiles("OurBoardLayout.csv", "OurRooms.txt"); // set the file names for setConfigFiles()
		board.setCardConfigFiles("Weapon.txt", "Players.txt");
		board.initialize(); // load both config files for tests
	}

	public void createBoard() {
		for (int i = 0; i < num_row; i++) {
			ArrayList<Block> temp = new ArrayList<Block>();
			for (int j = 0; j < num_col; j++) {
				temp.add(new Block(i*num_row,j*num_col));
			}
			drawBoard.add(temp);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int counter = 0;
		for (int i = 0; i < drawBoard.size(); i++) {
			for (int j = 0; j < drawBoard.get(i).size(); j++) {
				if (board.getCellAt(i, j).isRoom()) {
					drawBoard.get(i).get(j).drawRoom(g);
				} else if (board.getCellAt(i, j).isDoorway()){
					counter++;
					drawBoard.get(i).get(j).drawDoor(g, board.getCellAt(i, j).getDoorDirection());
				} else {
					drawBoard.get(i).get(j).drawWalkway(g);
				}
			}
		}
		System.out.println(counter);
	}

//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		for (int i = 0; i < num_row; i++) {
//			for (int j = 0; j < num_col; j++) {
//				if (board.getCellAt(i, j).isRoom()) {
//					drawBoard.get(i).get(j).drawRoom(g);
//				} else if (board.getCellAt(i, j).isDoorway()){
//					drawBoard.get(i).get(j).drawDoor(g, board.getCellAt(i, j).getDoorDirection());
//				} else {
//					drawBoard.get(i).get(j).drawWalkway(g);
//				}
//			}
//		}
//	}
}
