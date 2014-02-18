package tank;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class Main extends JFrame implements KeyListener, MouseListener, MouseMotionListener, Runnable {
	Board board = new Board();
	Main() {
		board.addTank(new UserTank(0, 0, board));
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		add(board);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
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
		int fps = 60;
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
				
			}
			board.setFPS((1000.0 / (double) (System.currentTimeMillis() - start)));
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
		for(Tank tank : board.tanks) {
			if(tank instanceof UserTank) {
				((UserTank) tank).handleMouseClick(arg0);
			}
		}
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
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		for(Tank tank : board.tanks) {
			if(tank instanceof UserTank) {
				((UserTank) tank).handleMouseMove(arg0);
			}
		}
	}
	
}
