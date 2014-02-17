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
		double centerX = (double) xLocation + 10, centerY = (double) yLocation + 10;
		double distX = centerX - (double) e.getX();
		double distY = centerY - (double) e.getY();
		int offset;
		boolean invert = false;
		if(distX >= 0.0) {
			if(distY >= 0.0) {
				offset = 3;
				invert = true;
			} else {
				offset = 2;
			}
		} else {
			if(distY >= 0.0) {
				offset = 0;
			} else {
				offset = 1;
				invert = true;
			}
		}
		distX = Math.abs(distX);
		distY = Math.abs(distY);
		double base = Math.toDegrees(Math.atan(distX / distY));
		if(invert) {
			base = 90.0 - base;
		}
		double angle = offset * 90.0 + base;
		setTankRotation(angle);
	}
	public void handleMouseClick(MouseEvent e) {
		
	}
	
}
