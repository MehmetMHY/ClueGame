package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class Block {
	private int x;
	private int y;
	private Color roomColor = Color.gray;
	private Color walkWayColor = Color.yellow;
	private Color doorColor = Color.blue;
	static final int HEIGHT = 100;
	static final int WIDTH = 100;
	static final int BORDER = 2;
	static final int DOOR = 10;
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
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
			g.fillRect(x + DOOR, y + HEIGHT, DOOR, HEIGHT);
		} else if (d == DoorDirection.UP) {
			g.setColor(doorColor);
			g.fillRect(x + HEIGHT - DOOR, y, WIDTH, DOOR);
		} else if (d == DoorDirection.LEFT) {
			g.setColor(doorColor);
			g.fillRect(x, y + DOOR, WIDTH, DOOR);	
		} else {
			g.setColor(doorColor);
			g.fillRect(x + WIDTH - DOOR, y, DOOR, HEIGHT);
		}
	}
}
