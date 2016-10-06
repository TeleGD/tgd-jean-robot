package fr.util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Entity {
	
	protected double x,y;
	protected double newx,newy,oldx,oldy;
	
	//N�cessaire pour les jeux****************************************************
	public abstract void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException;
	public abstract void update(GameContainer container, StateBasedGame game, int delta) throws SlickException;

	
	//Setters*********************************************************************
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	
	
	//Getters***********************************************************************
	public double getY() {
		return y;
	}
	
	public double getX() {
		return x;
	}
	public double getnewY() {
		return y;
	}
	
	public double getnewX() {
		return x;
	}

	



}
