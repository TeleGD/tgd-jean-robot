package fr.Behavior;

import fr.decor.Plateform;
import fr.util.Collisions;
import fr.util.Entity;

/**
 * interface des objets pouvant etre collisionnes
 * @author P-A
 */
public interface BeCollision {
	//void effet(Entity e,int a,int b);
	
	static int[][] altMoveByCollision(Entity e,BeCollision be) {
		int[][] tv = new int[fr.game.World.getPlateforms().size()][2];
		// on parcourt l'ensemble des plateforme pour voir s'y a des collisions
		for (int i = 0; i < fr.game.World.getPlateforms().size(); i++){
			
			Plateform plat = fr.game.World.getPlateforms().get(i);
			
			int[] v = Collisions.isCollision(e, plat );
			tv[i]=v;
		}
		
		return tv;
		
	}
}
