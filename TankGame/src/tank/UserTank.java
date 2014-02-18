package tank;

import java.awt.*;
import java.awt.event.*;


public class UserTank extends Tank {
	int[] bindings = {KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S};
	double xSpeed = 0, ySpeed = 0, MaxXSpeed = 2, MaxYSpeed =2;
	double acceleration = .1;
	double friction = 1;
	boolean aUp = false;
	boolean aDown = false;
	boolean aLeft = false;
	boolean aRight = false;
	
	UserTank(int x, int y, Board board){
		super(x, y, board);
	}
	public void update() {
		
		
		if(aLeft){
			xSpeed -=acceleration;
			if(xSpeed < -MaxXSpeed) {
				xSpeed = -MaxXSpeed;
			}
		} 
		if(aRight) {
			xSpeed +=acceleration;
			if(xSpeed > MaxXSpeed) {
				xSpeed = MaxXSpeed;
			}
		} 
		if(aUp) {
			ySpeed -=acceleration;
			if(ySpeed < -MaxYSpeed) {
				ySpeed = -MaxYSpeed;
			}
		} 
		if(aDown) {
			ySpeed +=acceleration;
			if(ySpeed > MaxYSpeed) {
				ySpeed = MaxYSpeed;
			}
		} 
		if(!(aLeft || aRight || aDown || aUp)){
			ySpeed *= friction;
			xSpeed *= friction;
		}
		
		moveTank(xSpeed, ySpeed);
	}
	public void moveTank(double fXSpeed, double fYSpeed) {
		Rectangle temp = new Rectangle((int)xLocation + (int)fXSpeed, (int)yLocation + (int)fYSpeed, 20, 20);
		boolean intersectRect = false;
		boolean intersectX = ! ((xLocation + fXSpeed) >= 0 && (xLocation + fXSpeed) <= 500);
		boolean intersectY = ! ((yLocation + fYSpeed) >= 0 && (yLocation + fYSpeed) <= 500);
		for(int i = 0; i < board.rects.length; i++) {
			if(temp.intersects(board.rects[i])) {
				intersectRect = true;
			}
		}
		if(!intersectRect) {
			xLocation += fXSpeed;
			yLocation += fYSpeed;
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
			aLeft = true;
		} else if(key == bindings[1]) {
			aRight = true;
		} else if(key == bindings[2]) {
			aUp = true;
		} else if(key == bindings[3]) {
			aDown = true;
		} 
		
	}
	public void handleKeyRelease(KeyEvent arg0) {//	STOPS THE TANK WHEN THE KEY NOT PRESSED
		int key = arg0.getKeyCode();
		if(key == bindings[0]){
			aLeft = false;
		} else if(key == bindings[1]) {
			aRight = false;
		} else if(key == bindings[2]) {
			aUp = false;
		} else if(key == bindings[3]) {
			aDown = false;
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
