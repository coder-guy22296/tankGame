package tank;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;

public class Board extends JPanel implements MouseListener, MouseMotionListener {
	public ArrayList<Tank> tanks = new ArrayList<Tank>();
	Random rng = new Random();
	double FPS = 0.0;
	Rectangle[] rects;
	
	Board() {
		InitializeBoard(15, 50, 25, 40, 20);
		
	}
	private void InitializeBoard(int Rectangles, int MaxRectSize, int MinRectSize, int MaxSpacing, int MinSpacing){
		int w;
		rects = new Rectangle[Rectangles];
		for(int i = 0; i < rects.length; i++) {
			w = Math.abs((rng.nextInt()) % (MaxRectSize-MinRectSize)) + MinRectSize;
			boolean intersectsBox = false;
			boolean closeToBox = false;
			
			do{
				intersectsBox = false;
				closeToBox = false;
				if(i==0){
					intersectsBox =false;
					closeToBox =true;
				}
				rects[i] = new Rectangle(Math.abs((rng.nextInt()) % 300) + 50,
						Math.abs((rng.nextInt()) % 300) + 50,
						w,
						w);

				for(int j = 0; j<i;j++ ){
					
					Rectangle tempMinBox = new Rectangle(rects[j].x - MinSpacing, rects[j].y - MinSpacing, rects[j].width + 2*MinSpacing , rects[j].height + 2*MinSpacing);
					Rectangle tempMaxBox = new Rectangle(rects[j].x - MaxSpacing, rects[j].y - MaxSpacing, rects[j].width + 2*MaxSpacing , rects[j].height + 2*MaxSpacing);
					if((rects[i].intersects(tempMinBox))){
						intersectsBox = true;
					} 
					if(rects[i].intersects(tempMaxBox)){
						closeToBox = true;
					}
				}
				
			}while(intersectsBox || (!closeToBox));
		}
	}
	public void setFPS(double fps) {
		FPS = fps;
	}
	public void update() {
		for(Tank tank : tanks) {
			tank.logicUpdate();
		}
	}
	public void addTank(Tank t) {
		this.tanks.add(t);
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		for(int i = 0; i < rects.length; i++) {
			g2d.fill(rects[i]);
		}
		for(Tank tank : this.tanks){ 
			tank.render(g2d);
		}
		g2d.drawString(Double.toString(FPS), 455, 20);
	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		for(Tank tank : this.tanks) {
			if(tank instanceof UserTank) {
				((UserTank) tank).handleMouseMove(arg0);
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
		for(Tank tank : this.tanks) {
			if(tank instanceof UserTank) {
				((UserTank) tank).handleMouseClick(arg0);
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
