package tank;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
	
	Main() {
		Board b = new Board(null, null);
		add(b);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(500, 500);
		setTitle("Tank Game");
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main m = new Main();
			}
		});
	}
	
}
