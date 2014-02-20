package tank;

abstract class Wepon {
	int ammo = 0;
	int damageDealt = 0; 
	 		
	abstract void reload();
	abstract void fireWepon();

}
