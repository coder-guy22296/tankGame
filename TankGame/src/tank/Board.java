package tank;

import javax.swing.*;

import java.awt.*;
import java.util.*;

public class Board extends JPanel {

	Random rng = new Random();
	Rectangle[] rects;
	Tank[] tanks = new Tank[2];
	
	Board() {
		rects = new Rectangle[7];
		for(int i = 0; i < 7; i++) {
			rects[i] = new Rectangle(Math.abs((rng.nextInt()) % 300) + 50, Math.abs((rng.nextInt()) % 300) + 50, Math.abs((rng.nextInt()) % 50) + 50, Math.abs((rng.nextInt()) % 50) + 50);
		}
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for(int i = 0; i < rects.length; i++) {
			g2d.draw(rects[i]);
		}
	}
	
}
