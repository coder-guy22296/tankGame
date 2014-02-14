package tank;

public class Tank {
	private int tankRotation = 0;  //in degrees
	private int xLocation = 0;
	private int yLocation = 0;
	private int health = 100;//hp      
	
	public void setTankLocation(int x, int y){
		xLocation = x;
		yLocation = y;
	}
	public void setTankRotation(int degrees){
	tankRotation = degrees;
	}
	public void rotateTank(int degrees){
		tankRotation += degrees;
	}
	public int getTankRotation(){
		return tankRotation;
	}
	public void fireCannon(){
		//draw line from center of tank along the tank's rotation
		//if intersects with other tank, minus 25hp
	}
	public void takeDammage(int dammage){
		health -= dammage;
	}
	public void regenHealth(){
		health = 100;
	}
}
