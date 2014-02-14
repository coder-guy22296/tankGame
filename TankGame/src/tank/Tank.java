package tank;

public class Tank {
	private int tankRotation = 0;
	private int xLocation = 0;
	private int yLocation = 0;
	
	public void setTankLocation(int x, int y){
		xLocation = x;
		yLocation = y;
	}
	public void setTankRotation(int degrees){
		
	}
	public int getTankRotation(){
		return tankRotation;
	}

}
