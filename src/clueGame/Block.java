/**. 
 * Block class that acts as an object for the BoardCell elements,
 * for the game board GUI
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JLabel;

public class Block {
	private int x;
	private int y;
	private Color roomColor = new Color(0,204,102); // color for the room GUI elements
	private Color walkWayColor = new Color(204,255,255); // color for the walkway GUI elements
	private Color doorColor = Color.blue; // color for the door way GUI elements
	private Color targetColor = Color.yellow;
	private boolean isTarget = false;
	
	static final int HEIGHT = 25;
	static final int WIDTH = 25;
	static final int BORDER = 2;
	static final int DOOR = 10;
	
	// constructor for the Block class
	public Block(int x, int y) {
		this.x = y;
		this.y = x;
	}
	
	// draw over all background for the game board GUI
	public void drawRoom(Graphics g) {
		g.setColor(roomColor);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
	
	// draw walkways for the game board GUI based on the positioning
	public void drawWalkway(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, WIDTH, HEIGHT);
		if (isTarget) {
			g.setColor(targetColor);
		} else {
			g.setColor(walkWayColor);
		}
		g.fillRect(x + BORDER, y + BORDER, WIDTH - 2 * BORDER, HEIGHT - 2 * BORDER);
	}
	
	// draw the doorways for the game board GUI based on the position of the doorway and its entrance direction
	public void drawDoor(Graphics g, DoorDirection d) {
		if (isTarget) {
			g.setColor(targetColor);
		} else {
			g.setColor(roomColor);
		}
		g.fillRect(x, y, WIDTH, HEIGHT);
		if (d == DoorDirection.DOWN) {
			g.setColor(doorColor);
			g.fillRect(x, y + 3*DOOR/2, WIDTH, DOOR);
		} else if (d == DoorDirection.UP) {
			g.setColor(doorColor);
			g.fillRect(x , y, WIDTH, DOOR);
		} else if (d == DoorDirection.LEFT) {
			g.setColor(doorColor);
			g.fillRect(x, y, DOOR, HEIGHT);	
		} else if (d == DoorDirection.RIGHT){
			g.setColor(doorColor);
			g.fillRect(x + 3*DOOR/2, y, DOOR, HEIGHT);
		}
	}
	
	public void setTarget(boolean isTarget) {
		this.isTarget = isTarget;
	}

}
