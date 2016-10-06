package fr.util;

import fr.util.Entity;

public abstract class Movable extends Entity{
	
	// Variables**********************************************
	public double speedX,speedY,accelX,accelY;
	public double jumppower=0.6;
	public double gravity=0.04;
	protected boolean posjump;//Jump possible?
	protected int dir=0; //-1:vers la gauche,0:ne bouge pas,1:vers la droite
	//Setters*************************************************
	public void setAccelX(int a){
		accelX=a;
	}
	public void setAccelY(int a){
		accelY=a;
	}
	public void setSpeedX(double d){
		speedX=d;
	}
	public void setSpeedY(double a){
		speedY=a;
	}
	
	//Getters***************************************************
	public double getspeedX(){
		return speedX;
	}
	public double getspeedY(){
		return speedY;
	}
	public double getaccelX(){
		return accelX;
	}
	public double getaccelY(){
		return accelY;
	}
	public double getNewY(){
		return newy;
	}
	public double getNewX(){
		return newx;
	}
	
	//Modifier**************************************************
	public void modifyX(int a){
		x+=a;
	}
	public void modifyY(int a){
		y+=a;
	}
	public void modifyAccelX(int a){
		accelX+=a;
	}
	public void modifyAccelY(int a){
		accelY+=a;
	}
	public void modifySpeedX(int a){
		speedX+=a;
	}
	public void modifySpeedY(int a){
		speedY+=a;
	}
	
	//Other movements**********************************************
	public void moveX(int delta){
		x+=speedX*delta;	
	}
	
	public void moveY(int delta){
		y+=speedY*delta;
	}
	
	
}
