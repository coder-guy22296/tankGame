package tank;

public class Tank {
	private int tankRotation = 0;
	private int xLocation = 0;
	private int yLocation = 0;
	private int health = 100;
	
	public void setTankLocation(int x, int y){
		xLocation = x;
		yLocation = y;
	}
	public void setTankRotation(int degrees){
		
	}
	public int getTankRotation(){
		return tankRotation;
	}
	public void fireCannon(){
		
	}
	public void takeDammage(int dammage){
		health -= dammage;
	}
	public void regenHealth(){
		health = 100;
	}
}
