package tank;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

public class Tank {
	protected double tankRotation = 0.0;  // in degrees -- long story short, trig calculations are easier when this is a double
	protected double xLocation = 0; // Made these so they could be inherited
	protected double yLocation = 0;
	protected int health = 100; // hp 
	protected boolean cannonFiring = false;
	protected boolean aUp = false;
	protected boolean aDown = false;
	protected boolean aLeft = false;
	protected boolean aRight = false;
	protected boolean applyBrakes = false;
	protected double acceleration = .1;
	protected double friction = 1;
	protected double xSpeed = 0, ySpeed = 0, MaxXSpeed = 6, MaxYSpeed =6;
	Rectangle hitBox = new Rectangle((int)xLocation-6, (int)yLocation-6, 12, 12);
	Board board;
	
	double centerX = 0;
	double centerY = 0;
	double distX = 0;
	double distY = 0;
	
	Tank(int x, int y, Board board) {
		xLocation = x;
		yLocation = y;
		this.board = board;
	}
	public void setTankLocation(int x, int y){
		xLocation = x;
		yLocation = y;
		hitBox = new Rectangle((int)xLocation, (int)yLocation, 20, 20);
	}
	public void moveTank(int offX, int offY) {
		Rectangle temp = new Rectangle((int)xLocation + offX, (int)yLocation + offY, 20, 20);
		boolean intersects = false;
		boolean intersectX = ! ((xLocation + offX) >= 0 && (xLocation + offX) <= 500);
		boolean intersectY = ! ((yLocation + offY) >= 0 && (yLocation + offY) <= 500);
		for(int i = 0; i < board.rects.length; i++) {
			if(temp.intersects(board.rects[i])) {
				intersects = true;
			}
		}
		if(!intersects && !intersectY && !intersectX) {
			xLocation += offX;
			yLocation += offY;
			hitBox = temp;
		}
	}
	
	public void setTankRotation(double d){
		if(d >= 0.0 && d < 360.0) {
			tankRotation = d;
		}
	}
	public double getTankX() {
		return xLocation;
	}
	public double getTankY() {
		return yLocation;
	}
	public void rotateTank(double  degrees){
		tankRotation += degrees;
	}
	public double getTankRotation(){
		return tankRotation;
	}
	public void fireCannon(){
		cannonFiring = true;
		//suggestions
		//draw line from center of tank along the tank's rotation
		//if intersects with other tank, minus 25hp
	}
	public void takeDamage(int damage){
		health -= damage;
	}
	public void regenHealth(){
		health = 100;
	}
	private double[] calculateRotationQuadrant(double angle) {
		double offset = 0.0, base = angle;
		while(true) {
			if(base - 90.0 >= 0.0) {
				base -= 90.0;
				offset += 1.0;
			} else {
				break;
			}
		}
		double[] ret = {base, offset};
		return ret;
	}
	public void logicUpdate() {
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
		if(applyBrakes){
			ApplyBrakes();
		}
		else if(!applyBrakes){
			disengageBrakes();
		}
		updateUserTankLogic();
		moveTank(xSpeed, ySpeed);
	}
	
	public void updateUserTankLogic(){
		centerX = (double) xLocation;
		centerY = (double) yLocation;
		distX = centerX - (double) UserTank.pointerX;
		distY = centerY - (double) UserTank.pointerY;
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
	
	public void moveTank(double fXSpeed, double fYSpeed) {
		boolean intersectRect = false;
		boolean offLeft   = (xLocation + fXSpeed) <= 0;
		boolean offRight  = (xLocation + fXSpeed) >= board.getWidth();
		boolean offTop    = (yLocation + fYSpeed) <= 0;
		boolean offBottom = (yLocation + fYSpeed) >= board.getHeight();
		
		for(int i = 0; i < board.rects.length; i++) {
			if(hitBox.intersects(board.rects[i])) {
				double xLeft = xLocation - hitBox.width/2;
				double xRight = xLocation + hitBox.width/2;
				double ybottom = yLocation + hitBox.width/2;
				double ytop = yLocation - hitBox.width/2;
				double rectLeft = board.rects[i].x;
				double rectRight = board.rects[i].x + board.rects[i].width;
				double rectBottom = board.rects[i].y + board.rects[i].height;
				double rectTop = board.rects[i].y;
				if(ySpeed > 0 && xSpeed > 0){
					double bValue = ybottom - (ySpeed/xSpeed)*xRight;
					double yIntersect = (ySpeed/xSpeed)*rectLeft + bValue;
					if(yIntersect < rectTop){
						ySpeed = -ySpeed;
					}else if(yIntersect > rectTop){
						xSpeed = -xSpeed;
					}
					
				}
				else if(ySpeed < 0 && xSpeed > 0){
					double bValue = ytop - (ySpeed/xSpeed)*xRight;
					double yIntersect = (ySpeed/xSpeed)*rectLeft + bValue;
					if(yIntersect > rectBottom){
						ySpeed = -ySpeed;
					}else if(yIntersect < rectBottom){
						xSpeed = -xSpeed;
					}
					
				}
				else if(ySpeed > 0 && xSpeed < 0){
					double bValue = ybottom - (ySpeed/xSpeed)*xLeft;
					double yIntersect = (ySpeed/xSpeed)*rectRight + bValue;
					if(yIntersect > rectTop){
						xSpeed = -xSpeed;
					}else if(yIntersect < rectTop){
						ySpeed = -ySpeed;
					}
					
				}
				else if(ySpeed < 0 && xSpeed < 0){
					double bValue = ytop - (ySpeed/xSpeed)*xLeft;
					double yIntersect = (ySpeed/xSpeed)*rectRight + bValue;
					if(yIntersect > rectBottom){
						ySpeed = -ySpeed;
					}else if(yIntersect < rectBottom){
						xSpeed = -xSpeed;
					}
					
				}
				
				
				
			}
		}
		if(intersectRect == true){
			
		}
		
		if(offLeft) {
			xSpeed = -xSpeed;
			xLocation = 0;
		} 
		if(offRight) {
			xSpeed = -xSpeed;
			xLocation = board.getWidth();
		} 
		if(offBottom) {
			ySpeed = -ySpeed;
			yLocation = board.getHeight();
		}
		if(offTop) {
			ySpeed = -ySpeed;
			yLocation = 0;
		}
		xLocation += xSpeed;
		yLocation += ySpeed;
		hitBox.x = (int) xLocation - 6;
		hitBox.y = (int) yLocation - 6;
	}
	
	public void ApplyBrakes(){
		friction = .80;
	}
	public void disengageBrakes(){
		friction = .98;
	}
	
	public void render(Graphics2D g2d) {
		g2d.setRenderingHint(
		        RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawOval((int)xLocation - 7, (int)yLocation - 7, 14, 14);
		double centerX = (double) xLocation , centerY = (double) yLocation ;	
		double[] ret = calculateRotationQuadrant(getTankRotation());
		double base = ret[0];
		int offset = (int) ret[1];
		double adj, opp, refX = 0.0, refY = 0.0;
		adj = Math.cos(Math.toRadians(base)) * 10;
		opp = Math.sin(Math.toRadians(base)) * 10;
		switch(offset) {
		case 0:
			// Upper right
			refX = centerX + opp;
			refY = centerY - adj;
			break;
		case 1:
			// Lower right
			refX = centerX + adj;
			refY = centerY + opp;
			break;
		case 2:
			// Lower left
			refX = centerX - opp;
			refY = centerY + adj;
			break;
		case 3:
			// Upper left
			refX = centerX - adj;
			refY = centerY - opp;
			break;
		}
		
		g2d.drawLine((int) Math.round(centerX), (int) Math.round(centerY), (int) Math.round(refX), (int) Math.round(refY));
		if(cannonFiring){
			g2d.setColor(Color.RED);
			g2d.drawLine((int) Math.round(centerX), (int) Math.round(centerY), UserTank.getMouseX(), UserTank.getMouseY());
			cannonFiring = false;
			g2d.setColor(Color.BLACK);
		}
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(hitBox);
	}
}
