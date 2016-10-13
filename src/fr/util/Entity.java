package fr.util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Entity {
	
	// les variables
	protected double x,y;
	protected double newx,newy;
	protected double speedx,speedy;
	protected double width, height;
	protected int life;
	protected boolean killable;
	
	
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
	
	public boolean isKillable() {
		return killable;
	}
	public void setKillable(boolean killable) {
		this.killable = killable;
	}
	public double getNewx() {
		return newx;
	}
	public void setNewx(double newx) {
		this.newx = newx;
	}
	public double getNewy() {
		return newy;
	}
	public void setNewy(double newy) {
		this.newy = newy;
	}
	public double getSpeedx() {
		return speedx;
	}
	public void setSpeedx(double speedx) {
		this.speedx = speedx;
	}
	public double getSpeedy() {
		return speedy;
	}
	public void setSpeedy(double speedy) {
		this.speedy = speedy;
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
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	
	public boolean containsPoint(int x,int y){
		return this.newx<=x &&  x<=this.newx+width && this.newy<=y && y<=this.newy+height;
	}

	



}
