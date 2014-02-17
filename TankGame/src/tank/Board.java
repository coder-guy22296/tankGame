package tank;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.ArrayList;

public class Board extends JPanel {
	public ArrayList<Tank> tanks = new ArrayList<Tank>();
	Random rng = new Random();
	Rectangle[] rects;
	Board() {
		int w;
		rects = new Rectangle[18];
		for(int i = 0; i < rects.length; i++) {
			w = Math.abs((rng.nextInt()) % 25) + 25;
			rects[i] = new Rectangle(Math.abs((rng.nextInt()) % 300) + 50,
					Math.abs((rng.nextInt()) % 300) + 50,
					w,
					w);
		}
	}
	public void update() {
		for(Tank tank : tanks) {
			tank.update();
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
			tank.render(g2d);
		}
	}
	
}
