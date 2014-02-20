package tank;

public class Laser extends Weapon {
	private int beamIntensity = 0;//percent 0-100
	private int damageDealt = 0;
	@Override
	void reload() {
		// TODO Auto-generated method stub
		beamIntensity = 100;
	}
	@Override
	void fire() {
		// TODO Auto-generated method stub
		
	}
	@Override
	void destroy() {
		// TODO Auto-generated method stub
		
	}
}
