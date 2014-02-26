package tank;

abstract class Projectile {
	int damageDealt = 0; 
	 		
	abstract void update(Board b);
	abstract void destroy();

}
