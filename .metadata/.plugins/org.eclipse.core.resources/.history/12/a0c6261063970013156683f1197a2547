package tank;

import java.awt.Rectangle;

public class Tank {
	int tankRotation = 0;  // in degrees
	int xLocation = 0; // Made these so they could be inherited
	int yLocation = 0;
	int health = 100; // hp    
	Rectangle rect = new Rectangle(xLocation, yLocation, 20, 20);
	Board board;
	Tank(int x, int y, Board board) {
		xLocation = x;
		yLocation = y;
		this.board = board;
	}
	public void update() {
		
	}
	public void setTankLocation(int x, int y){
		xLocation = x;
		yLocation = y;
		rect = new Rectangle(xLocation, yLocation, 20, 20);
	}
	public void moveTank(int offX, int offY) {
		Rectangle temp = new Rectangle(xLocation + offX, yLocation + offY, 20, 20);
		boolean intersects = false;
		boolean intersectX = ! ((xLocation + offX) >= 0 && (xLocation + offX) <= 500);
		boolean intersectY = ! ((yLocation + offY) >= 0 && (yLocation + offY) <= 500);
		for(int i = 0; i < board.rects.length; i++) {
			if(temp.intersects(board.rects[i])) {
				intersects = true;
			}
		}
		if(!intersects && !intersectY && !intersectX) {
			xLocation += offX;
			yLocation += offY;
			rect = temp;
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
		//suggestions
		//draw line from center of tank along the tank's rotation
		//if intersects with other tank, minus 25hp
	}
	public void takeDamage(int damage){
		health -= damage;
	}
	public void regenHealth(){
		health = 100;
	}
}
