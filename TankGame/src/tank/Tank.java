package tank;

import java.awt.Graphics2D;
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
		if(degrees >= 0 && degrees < 360) {
			tankRotation = degrees;
		}
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
	public void render(Graphics2D g2d) {
		g2d.drawOval(xLocation + 3, yLocation + 3, 14, 14);
		/* my attempt at trig rendering. good luck
		int centerX = xLocation + 10, centerY = yLocation + 10;
		int offset = 0, base = getTankRotation();
		int refX = 0, refY = 0, adj, opp, hyp = 10;
		while(true) {
			if(base - 90 >= 0) {
				base -= 90;
				offset += 1;
			} else {
				break;
			}
		}		
		adj = (int) Math.round(Math.cos(Math.toRadians(base)) * hyp);
		opp = (int) Math.round(Math.sin(Math.toRadians(base)) * hyp);
		switch(offset) {
		case 0:
			refX = centerX + opp;
			refY = centerY - adj;
			break;
		case 1:
			refX = centerX + opp;
			refY = centerY + adj;
			break;
		case 2:
			refX = centerX - opp;
			refY = centerY + adj;
			break;
		case 3:
			refX = centerX - opp;
			refY = centerY - adj;
			break;
		}
		g2d.drawLine(centerX, centerY, refX, refY);
		*/
	}
}
