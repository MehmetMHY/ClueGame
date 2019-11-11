package clueGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;

public class Block {
	private int x;
	private int y;
	private Color roomColor = new Color(0,204,102);
	private Color walkWayColor = new Color(204,255,255);
	private Color doorColor = Color.blue;
	static final int HEIGHT = 25;
	static final int WIDTH = 25;
	static final int BORDER = 2;
	static final int DOOR = 10;
	
	public Block(int x, int y) {
		this.x = y;
		this.y = x;
	}
	
	public void drawRoom(Graphics g) {
		g.setColor(roomColor);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
	
	public void drawWalkway(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, WIDTH, HEIGHT);
		g.setColor(walkWayColor);
		g.fillRect(x + BORDER, y + BORDER, WIDTH - 2*BORDER, HEIGHT - 2*BORDER);
	}
	
	public void drawDoor(Graphics g, DoorDirection d) {
		g.setColor(roomColor);
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
}
