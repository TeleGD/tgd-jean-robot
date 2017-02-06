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
	
}
