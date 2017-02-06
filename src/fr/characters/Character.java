package fr.characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Rectangle;

public interface Character extends Rectangle{
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException;
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException;
	double getSpeedX();
	double getSpeedY();
	double getX();
	double getY();
	double getnewX();
	double getnewY();
}
