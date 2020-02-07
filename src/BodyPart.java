import java.awt.Color;
import java.awt.Graphics;

public class BodyPart {
	
	private int xCoor, yCoor, width, height;
	
	public BodyPart(int xCoor, int yCoor, int tilesize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = tilesize;
		height = tilesize;
		
	}
	
	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}

	public void tick() {
		
	}
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(xCoor * width, yCoor * height, width, height);
		
	}

}
