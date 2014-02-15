package tank;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.ArrayList;

public class Board extends JPanel {
	public ArrayList<Tank> tanks = new ArrayList<Tank>();
	Random rng = new Random();
	Rectangle[] rects;
	Tank tank1, tank2;
	Board() {
		rects = new Rectangle[7];
		for(int i = 0; i < 7; i++) {
			rects[i] = new Rectangle(Math.abs((rng.nextInt()) % 300) + 50,
					Math.abs((rng.nextInt()) % 300) + 50,
					Math.abs((rng.nextInt()) % 50) + 50,
					Math.abs((rng.nextInt()) % 50) + 50);
		}
	}
	public void addTank(Tank t) {
		this.tanks.add(t);
	}
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for(int i = 0; i < rects.length; i++) {
			g2d.fill(rects[i]);
		}
		for(Tank tank : this.tanks){ 
			int x, y;
			x = tank.getTankX();
			y = tank.getTankY();
			g2d.drawOval(x, y, 20, 20);
		}
	}
	
}
