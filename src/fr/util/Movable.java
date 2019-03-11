package fr.util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Entity;

public class Movable extends Entity{

	// Variables**********************************************
	protected double speedX,speedY;
	protected double newX,newY;
	protected boolean posjump;//Jump possible?
	protected int dir=0; //-1:vers la gauche,0:ne bouge pas,1:vers la droite



	public Movable (double x,double y,double width,double height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	}

	//Setters*************************************************
	public void setAccelX(int a){
		accelX=a;
	}
	public void setAccelY(int a){
		accelY=a;
	}
	//Getters***************************************************
	public boolean canJump(){
		return posjump;
	}

	public void setPosJump(boolean b){
		posjump = b;
	}

	//Other movements**********************************************
	public void moveX(int delta){
		x+=speedX*delta;
	}

	public void moveY(int delta){
		y+=speedY*delta;
	}

	public double getnewX(){
		return newX;
	}
	public double getnewY(){
		return newY;
	}

	public double getSpeedX() {
		return speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedX(double a){
		speedX=a;
	}

	public void setSpeedY(double a){
		speedY=a;
	}

	public void setWidth(double w){
		this.width = w;
	}

	public void setHeight(double h){
		this.height = h;
	}


}
