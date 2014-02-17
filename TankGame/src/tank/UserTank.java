package tank;

import java.awt.*;
import java.awt.event.*;


public class UserTank extends Tank {
	int[] bindings = {KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S};
	int xSpeed = 0, ySpeed = 0;
	UserTank(int x, int y, Board board){
		super(x, y, board);
	}
	public void update() {
		moveTank(xSpeed, ySpeed);
	}
	public void moveTank(int offX, int offY) {
		Rectangle temp = new Rectangle(xLocation + offX, yLocation + offY, 20, 20);
		boolean intersectRect = false;
		boolean intersectX = ! ((xLocation + offX) >= 0 && (xLocation + offX) <= 500);
		boolean intersectY = ! ((yLocation + offY) >= 0 && (yLocation + offY) <= 500);
		for(int i = 0; i < board.rects.length; i++) {
			if(temp.intersects(board.rects[i])) {
				intersectRect = true;
			}
		}
		if(!intersectRect) {
			xLocation += offX;
			yLocation += offY;
			rect = temp;
		} else {
			xSpeed = -xSpeed;
			ySpeed = -ySpeed;
		}
		if(intersectX) {
			xSpeed = -xSpeed;
		} else if(intersectY) {
			ySpeed = -ySpeed;
		}
	}
	public void setBindings(int[] b) {
		bindings = b;
	}
	public void handleKeyPress(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		if(key == bindings[0]){
			if(xSpeed > -3) {
				xSpeed -= 1;
			}
		} else if(key == bindings[1]) {
			if(xSpeed < 3) {
				xSpeed += 1;
			}
		} else if(key == bindings[2]) {
			if(ySpeed > -3) {
				ySpeed -= 1;
			}
		} else if(key == bindings[3]) {
			if(ySpeed < 3) {
				ySpeed += 1;
			}
		} else if(key == KeyEvent.VK_SPACE) {
			xSpeed = 0;
			ySpeed = 0;
		}
	}
	public void handleKeyRelease(KeyEvent arg0) {//	STOPS THE TANK WHEN THE KEY NOT PRESSED
		int key = arg0.getKeyCode();
		if(key == bindings[0] || key == bindings[1]){
			xSpeed = 0;
		} else if(key == bindings[2] || key == bindings[3]) {
			ySpeed = 0;
		}
	}
	public void handleMouseMove(MouseEvent e) {
	/* My attempt at some game trig... I'm working on some working code but see if you can 
	 * try and make some of your own. Maybe you cant get it to work.
		int centerX = xLocation + 10, centerY = yLocation + 10;
		int distX = centerX - e.getX();
		int distY = centerY - e.getY();
		int angle, offset, base;
		if(distX >= 0) {
			if(distY >= 0) {
				offset = 0;
			} else {
				offset = 1;
			}
		} else { 
			if(distY >= 0) {
				offset = 2;
			} else {
				offset = 3;
			}
		}
		distX = Math.abs(distX);
		distY = Math.abs(distY);
		base = (int) Math.round(Math.toDegrees((Math.atan(Math.toRadians(((double) distY / (double) distX))))));
		angle = offset * 90 + base;
		setTankRotation(angle);
	*/
	}
	public void handleMouseClick(MouseEvent e) {
		
	}
	
}
