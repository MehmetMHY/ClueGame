package clueGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickLocation extends MouseAdapter {
	
	private int width;
	private int height;
	private int row;
	private int col;
	private int boxD;
	
	public ClickLocation(int width, int height, int row, int col) {
		this.width = width;
		this.height = height;
		this.row = row;
		this.col = col;		
		boxD = this.width/row;
		
	}
	
	@Override
	public void mouseClicked(MouseEvent t) {
		if (t.getX() <= height && t.getY() <= width) {
			System.out.println(t.getX()/boxD + ", " + t.getY()/boxD);
		}
	}
}
