package fr.util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Entity implements Rectangle{
	
	// les variables
	protected double x,y;
	protected double width, height;
	protected boolean killable;
	
	
	//Nececessaire pour les jeux****************************************************
	public abstract void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException;
	public abstract void update(GameContainer container, StateBasedGame game, int delta) throws SlickException;

	public Entity(double x,double y,double width,double height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	public Entity(String ligne) {
		String[] split=ligne.split(";");
		this.x=Double.parseDouble(split[0].substring(split[0].lastIndexOf(" ")+1));
		this.y=Double.parseDouble(split[1]);
		this.width=Double.parseDouble(split[2]);
		this.height=Double.parseDouble(split[3]);
	}
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
	@Override
	public boolean containsPoint(int x,int y){
		return this.x<=x &&  x<=this.x+width && this.y<=y && y<=this.y+height;
	}
	public String parseString() {
		return x+";"+y+";"+width+";"+height;
	}


}
