package tank;

import java.awt.Rectangle;

public class Tank {
	private int tankRotation = 0;  // in degrees
	private int xLocation = 0;
	private int yLocation = 0;
	private int health = 100; // hp    
	private Rectangle rect = new Rectangle(xLocation, yLocation, 20, 20);
	private Board b;
	Tank(int x, int y, Board b) {
		xLocation = x;
		yLocation = y;
		this.b = b;
	}
	public void setTankLocation(int x, int y){
		xLocation = x;
		yLocation = y;
		rect = new Rectangle(xLocation, yLocation, 20, 20);
	}
	public void moveTank(int offX, int offY) {
		xLocation += offX;
		yLocation += offY;
		rect = new Rectangle(xLocation, yLocation, 20, 20);
		for(int i = 0; i < b.rects.length; i++) {
			if(rect.intersects(b.rects[i])) {
				xLocation -= offX;
				yLocation -= offY;
				rect = new Rectangle(xLocation, yLocation, 20, 20);
			}
		}
	}
	public void setTankRotation(int degrees){
		tankRotation = degrees;
	}
	public int getTankX() {
		return xLocation;
	}
	public int getTankY() {
		return yLocation;
	}
	public void rotateTank(int degrees){
		tankRotation += degrees;
	}
	public int getTankRotation(){
		return tankRotation;
	}
	public void fireCannon(){
		//draw line from center of tank along the tank's rotation
		//if intersects with other tank, minus 25hp
	}
	public void takeDammage(int dammage){
		health -= dammage;
	}
	public void regenHealth(){
		health = 100;
	}
}
