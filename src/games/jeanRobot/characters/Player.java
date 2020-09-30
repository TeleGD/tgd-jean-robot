package games.jeanRobot.characters;

import org.newdawn.slick.state.StateBasedGame;

public interface Player extends Character {


	public void loose(StateBasedGame game);
	public void keyReleased(int key, char c);
	public void keyPressed(int key, char c);
	public void lifelost(StateBasedGame game);
	public int getlife();
	public void reset();
	public int getType();

	public void jump();
	public void setY(double y);
	public void setX(double x);
	public void addScore(int s);

	/**
	 * @param path : le path de l'image avec le début de son nom, par exemple "/images/jeanRobot/Player/herobotWALK/jeanrobot_marche"
	 */
	public void setImages(String path);

	/**
	 *
	 * @return : 1 si le personnage est tourn& vers la droite, -1 si il est tourn& vers la gauche
	 */
	public int getDirerction();

	public void setAccY(double i);
	public void setSpeedY(double i);
	public void setInCol(boolean b);
	public void setposJump(boolean b);


}
