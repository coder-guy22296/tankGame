package tank;
//PrivateMessage for R.B in Binary
//01111001011011110111010101110010001000000110010101101101011000010110100101101100001000000110010001101001011001000010000001101110011011110111010000100000011101110110111101110010011010110010110000100000011001010110110101100001011010010110110000100000011011010110010100100000011100110110111100100000011010010010000001101000011000010111011001100101001000000111100101101111011101010111001000100000011001010110110101100001011010010110110000111010001000000110001101100001011011100111000001111001001100110011000001000000011001110110110101100001011010010110110000101110011000110110111101101101
//delete once you have read and done the requested action
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class Main extends JFrame implements KeyListener, MouseListener, Runnable {
	Board board = new Board();
	Main() {
		board.addTank(new UserTank(0, 0, board));
		int[] bindings = {KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN};
		UserTank temp = new UserTank(0, 0, board);
		temp.setBindings(bindings);
		board.addTank(temp);
		this.addKeyListener(this);
		this.addMouseListener(this);
		add(board);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(500, 500);
		setTitle("Tank Game");
		setVisible(true);
	}
	public void update() {
		board.update();
	}
	public void repaint() {
		super.repaint();
		board.repaint();
	}
	public void run() {
		int fps = -1;
		long waitTime = (long) Math.floor(((float) 1000 / (float) fps));
		long start, wait;
		while(true) {
			start = System.currentTimeMillis();
			update();
			repaint();
			wait = (waitTime - (System.currentTimeMillis() - start));
			try{
				if(fps != -1) {
					Thread.sleep(wait);
				} else {
					System.out.println("Actual FPS: " + (1000.0 / (double) (System.currentTimeMillis() - start)));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Thread m = new Thread(new Main());
				m.start();
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		for(Tank tank : board.tanks) {
			if(tank instanceof UserTank) {
				((UserTank) tank).handleKeyPress(arg0);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		for(Tank tank : board.tanks) {
			if(tank instanceof UserTank) {
				((UserTank) tank).handleKeyRelease(arg0);
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
