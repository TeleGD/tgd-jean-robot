package fr.characters.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Character;;

public interface Ennemy extends Character{
	
	public boolean isDestructed();
	public void setDestructed(boolean destructed);
	public int getLife();
	public void collPlayer();
}
