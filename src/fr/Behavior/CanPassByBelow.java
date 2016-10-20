
package fr.Behavior;

import fr.util.Entity;

public class CanPassByBelow implements BeCollision{

	@Override
	public void effet(Entity e,int a, int b) {
		if (b!=1){
			//me cogne cote
			if (a!=0){
				e.setSpeedX(0);
				e.setAccelX(0);
				System.out.println("testa");
			}
			//me cogne par dessus
			if (b==-1){
				e.setSpeedY(0);
				e.setAccelY(0);
				System.out.println("testb");
			}
		}
	}
		
}
