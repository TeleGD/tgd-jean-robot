package fr.Behavior;

import fr.util.Entity;

public class CanPassBySide implements BeCollision{

	@Override
	public void effet(Entity e, int a, int b) {
		if (b!=0){
			e.setSpeedY(0);
		}
	}

}
