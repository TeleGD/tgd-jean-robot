package fr.main;


import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.character.Player;
import fr.character.enemies.Enemy;
import fr.decor.Platforms;


public class World extends BasicGameState {

	public static int ID=0;
	private static Player player;
	private static ArrayList<Platforms> platforms = null;
	private static ArrayList<Enemy> ennemies = null;
	public static StateBasedGame game;
	
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		
	}

	//Souris*****************************************************************************
	public void mousePressed(int button,int x,int y){
	}

	public static Player getPlayer(){
		return player;
	}
	
	public static ArrayList<Enemy> getEnemies() {
		return ennemies;
	}
	
	public static ArrayList<Platforms> getPlatforms() {
		return platforms;
	}

	@Override
	public int getID() {
		return ID;
	}

	public void keyReleased(int key, char c) {
	}

	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
	}

	public static String getScore() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void reset() {
		// TODO Auto-generated method stub
		
	}
}
