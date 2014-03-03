package tank;

public class Laser {
	
	private int damageDealt = 0;
	private int storedEnergy = 0;// 1k max
	private int beamIntensity = 0;//percent 0-100
	private double damagePerPercent = 2;
	private double totalDammage = 0;// damagePerPercent*beamIntensity
	
	void reload() {
		// TODO Auto-generated method stub
		
		storedEnergy = 1000;
		beamIntensity = 100;
	}
	void fire() {
		totalDammage = damagePerPercent*beamIntensity;
		storedEnergy -= 125;
		beamIntensity = storedEnergy/10;
		
	}
	
}
