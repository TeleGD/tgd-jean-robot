package games.jeanRobot.characters.enemies;

import org.newdawn.slick.state.StateBasedGame;

import games.jeanRobot.characters.Character;
import games.jeanRobot.characters.Player;
import games.jeanRobot.decor.Plateform;;

public interface Ennemy extends Character{

	public boolean isDestructed();
	public void setDestructed(boolean destructed);
	public int getLife();
	public void collPlayer(StateBasedGame game, Player player);
	public void looseLife();
	public void setSpeedX(double d);
	public void setSpeedY(double d);
	public void setY(double d);
	public Plateform getInitPlat();
	public int getScore();
	public void setImages(String path);
}
