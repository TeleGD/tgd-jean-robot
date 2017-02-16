package fr.characters;

import org.newdawn.slick.state.StateBasedGame;

public interface Player extends Character {

	
	public void loose(StateBasedGame game);
	public void keyReleased(int key, char c);
	public void keyPressed(int key, char c);
	public void lifelost();
	public int getlife();
	public void reset();
	public int getType();
	
	public void jump();
	public void setY(double y);
	public void setX(double x);
	
	/**
	 * @param path : le path de l'image avec le début de son nom, par exemple "img/Player/herobotWALK/jeanrobot_marche"
	 */
	public void setImages(String path);
	
	/**
	 * 
	 * @return : 1 si le personnage est tourn& vers la droite, -1 si il est tourn& vers la gauche
	 */
	public int getDirerction();
	
	
}
