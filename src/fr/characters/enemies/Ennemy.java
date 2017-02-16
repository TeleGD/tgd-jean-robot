package fr.characters.enemies;

import fr.characters.Character;
import fr.characters.Player;
import fr.decor.Plateform;;

public interface Ennemy extends Character{
	
	public boolean isDestructed();
	public void setDestructed(boolean destructed);
	public int getLife();
	public void collPlayer(Player player);
	public void looseLife();
	public void setSpeedX(double d);
	public void setSpeedY(double d);
	public void setY(double d);
	public Plateform getInitPlat();
}
