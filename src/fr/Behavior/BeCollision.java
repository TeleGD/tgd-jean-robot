package fr.Behavior;

import fr.decor.DeathBloc;
import fr.decor.Plateform;
import fr.menus.MenuFinPartie;
import fr.util.Collisions;
import fr.util.Entity;
import fr.characters.*;

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
			
			if(plat instanceof DeathBloc && (v[0] == 1|| v[1] == 1 || v[0] == -1|| v[1] == -1) )
			{
				fr.game.World.game.enterState(MenuFinPartie.ID);//d, new FadeOutTransition(),new FadeInTransition());
				((Player)e).reset();
			}
			
			tv[i]=v;
		}
		
		return tv;
		
	}
}
