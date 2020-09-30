package games.jeanRobot.characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.jeanRobot.util.Rectangle;

public interface Character extends Rectangle{

	public void render(GameContainer container, StateBasedGame game, Graphics g) ;
	public void update(GameContainer container, StateBasedGame game, int delta) ;
	double getSpeedX();
	double getSpeedY();
	double getX();
	double getY();
	double getnewX();
	double getnewY();
}
