package tank;

import java.awt.*;
import java.awt.event.*;


public class UserTank extends Tank {
	int[] bindings = {KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_SPACE};
	double xSpeed = 0, ySpeed = 0, MaxXSpeed = 6, MaxYSpeed =6;
	static int pointerX = 0;
	static int pointerY = 0;
	double centerX = 0;
	double centerY = 0;
	double distX = 0;
	double distY = 0;
	public void setBindings(int[] b) {
		bindings = b;
	}
	UserTank(int x, int y, Board board){
		super(x, y, board);
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
		} else if(key == bindings[4]) {
			applyBrakes = true;
		} 
		
	}
	public void handleKeyRelease(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		if(key == bindings[0]){
			aLeft = false;
		} else if(key == bindings[1]) {
			aRight = false;
		} else if(key == bindings[2]) {
			aUp = false;
		} else if(key == bindings[3]) {
			aDown = false;
		} else if(key == bindings[4]) {
			applyBrakes = false;
		} 
	}
	public void handleMouseMove(MouseEvent e) {
		pointerX = e.getX();
		pointerY = e.getY();
		updateUserTankLogic();
/*		centerX = (double) xLocation;
		centerY = (double) yLocation;
		distX = centerX - (double) pointerX;
		distY = centerY - (double) pointerY;
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
*/	}
	public void handleMouseClick(MouseEvent e) {
		cannonFiring = true;
		System.out.println("---");
		System.out.println(e.getX());
		System.out.println(e.getY());
		System.out.println("---");
	}
	public static int getMouseX(){
		return pointerX;
	}
	public static int getMouseY(){
		return pointerY;
	}
	
	
}
