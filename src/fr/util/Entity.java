package fr.util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Entity implements Rectangle{

	// les variables
	protected double x,y;
	protected double speedX,speedY;
	protected double accelX,accelY;
	protected double width, height;
	protected boolean killable;


	//Nececessaire pour les jeux****************************************************
	public abstract void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException;
	public abstract void update(GameContainer container, StateBasedGame game, int delta) throws SlickException;


	//Setters*********************************************************************
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}



	public double getY() {
		return y;
	}

	public double getX() {
		return x;
	}

	public boolean isKillable() {
		return killable;
	}
	public void setKillable(boolean killable) {
		this.killable = killable;
	}

	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}



	//methode qui determine si un point(x,y) est contenu dans la inbox de l'entity
	public boolean containsPoint(int x,int y){
		return this.x<=x &&  x<=this.x+width && this.y<=y && y<=this.y+height;
	}


}
