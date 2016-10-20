package fr.Behavior;

import fr.decor.Plateform;
import fr.util.Collisions;
import fr.util.Entity;

/**
 * interface des objets pouvant etre collisionnes
 * @author P-A
 */
public interface BeCollision {
	void effet(Entity e,int a,int b);
	
	static void altMoveByCollision(Entity e,BeCollision be) {
		double speedX=e.getSpeedX();
		double speedY=e.getSpeedY();
		
		// on parcourt l'ensemble des plateforme pour voir s'y a des collisions
		for (int i = 0; i < fr.game.World.getPlateforms().size(); i++){
			
			Plateform plat = fr.game.World.getPlateforms().get(i);
			
			int[] v = Collisions.isCollision(e, plat);
			int a=v[0];
			int b=v[1];
			
			be.effet(e,a,b);
			
			e.setSpeedX(speedX);
			e.setSpeedY(speedY);
			
		}
		
	}
}
