package fr.characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Rectangle;

public interface Character extends Rectangle{
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException;
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException;
	public double getSpeedX();
	public double getSpeedY();
	public double getnewX();
	public double getnewY();
}
