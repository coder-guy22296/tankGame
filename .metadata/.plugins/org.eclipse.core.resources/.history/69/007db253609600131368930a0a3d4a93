package tank;

import java.awt.*;
import java.awt.event.*;


public class UserTank extends Tank {
	UserTank(int x, int y, Board b){
		super(x, y, b);
	}
	public void mouseDown(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_A) {
			moveTank(-3, 0);
		} else if (arg0.getKeyCode() == KeyEvent.VK_D) {
			moveTank(3, 0);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_W) {
			moveTank(0, -3);
		} else if (arg0.getKeyCode() == KeyEvent.VK_S) {
			moveTank(0, 3);
		}
	}
	
}
