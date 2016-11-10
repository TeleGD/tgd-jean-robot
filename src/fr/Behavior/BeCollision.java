package fr.Behavior;

import fr.decor.*;
import fr.menus.MenuFinPartie;
import fr.util.Collisions;
import fr.util.Entity;

import java.util.*;

import fr.characters.*;

/**
 * interface des objets pouvant etre collisionnes
 * @author P-A
 */
public interface BeCollision {
	//void effet(Entity e,int a,int b);
	
	
	static int[][] altMoveByCollision(Entity e,BeCollision be) {
		boolean encolli = false;
		ArrayList<DeathBloc> listeDeathEnColl = new ArrayList<DeathBloc>(); 
		ArrayList<ElevatorTrap> listeElevatorEnColl = new ArrayList<ElevatorTrap>();
		int[][] tv = new int[fr.game.World.getPlateforms().size()][2];
		// on parcourt l'ensemble des plateforme pour voir s'y a des collisions
		for (int i = 0; i < fr.game.World.getPlateforms().size(); i++){
			Plateform plat = fr.game.World.getPlateforms().get(i);
			
			int[] v = Collisions.isCollision(e, plat );
			
			//Ici enregistrer les blocs mortels pour faire les tests après
			if(plat instanceof DeathBloc)
			{
				listeDeathEnColl.add((DeathBloc)plat);
			}
			
			
			//Ici mettre toutes les plateformes de type mouvement
			if(plat instanceof ElevatorTrap && (v[1] == -1) && (encolli == false)){
				((Player)e).setY(((Player)e).getY() + ((ElevatorTrap)plat).getVitesse());
				((ElevatorTrap)plat).setY(((ElevatorTrap)plat).getVitesse()+((ElevatorTrap)plat).getY());
				((ElevatorTrap)plat).setMvmnt(true);
				((Player)e).setSpeedY(((ElevatorTrap)plat).getVitesse());
			}
			tv[i]=v;
		}
		
		//Ici mettre toutes les plateformes de type : le perso doit mourrir
		for(DeathBloc d : listeDeathEnColl){
			if (Collisions.inCollision(e, d)){
				fr.game.World.game.enterState(MenuFinPartie.ID);//d, new FadeOutTransition(),new FadeInTransition());
				((Player)e).reset();
			}
		}
		
		return tv;
		
	}

}
