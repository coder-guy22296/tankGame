package tank;

abstract class Projectile {
	int ammo = 0;
	int damageDealt = 0; 
	 		
	abstract void reload();
	abstract void fire();
	abstract void destroy();

}
