package tank;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class Tank {
	protected double tankRotation = 0.0;  // in degrees -- long story short, trig calculations are easier when this is a double
	protected double xLocation = 0; // Made these so they could be inherited
	protected double yLocation = 0;
	protected int health = 100; // hp 
	protected boolean cannonFiring = false;
	Rectangle rect = new Rectangle((int)xLocation, (int)yLocation, 20, 20);
	Board board;
	Tank(int x, int y, Board board) {
		xLocation = x;
		yLocation = y;
		this.board = board;
	}
	public void update() {
		
	}
	public void setTankLocation(int x, int y){
		xLocation = x;
		yLocation = y;
		rect = new Rectangle((int)xLocation, (int)yLocation, 20, 20);
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
			rect = temp;
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
	public void render(Graphics2D g2d) {
		g2d.setRenderingHint(
		        RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawOval((int)xLocation + 3, (int)yLocation + 3, 14, 14);
		double centerX = (double) xLocation + 10.0, centerY = (double) yLocation + 10.0;	
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
	}
}
