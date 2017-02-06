package fr.util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Entity {
	
	// les variables
	protected double x,y;
	protected double newX,newY;
	protected double speedX,speedY;
	protected double accelX,accelY;
	protected double width, height;
	protected boolean killable;
	protected double gravity;
	
	
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

	
	
	public void setNewX(double newX) {
		this.newX = newX;
	}
	public void setNewY(double newY) {
		this.newY = newY;
	}
	public double getY() {
		return y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getnewY() {
		//Methode forcee par la bibliotheque...
		return newY;
	}
	
	public double getnewX() {
		//Methode forcee par la bibliotheque...
		return newX;
	}
	
	public boolean isKillable() {
		return killable;
	}
	public void setKillable(boolean killable) {
		this.killable = killable;
	}
	public void setNewy(double newy) {
		this.newY = newy;
	}

	
	public double getSpeedX() {
		return speedX;
	}
	public double getSpeedY() {
		return speedY;
	}
	
	public void setSpeedX(double speedx) {
		this.speedX = speedx;
	}
	
	public void setSpeedY(double speedy) {
		this.speedY = speedy;
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
	@Override
	public String toString(){
		return "x="+x+" y="+y+" width="+width+" height="+height;
	}
	//methode qui determine si un point(x,y) est contenu dans la inbox de l'entity
	public boolean containsPoint(int x,int y){
		return this.x<=x &&  x<=this.x+width && this.y<=y && y<=this.y+height;
	}
	public double getGravity() {
		return gravity;
	}
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}


}
