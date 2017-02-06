package fr.util;

import fr.util.Entity;

public abstract class Movable extends Entity{
	
	// Variables**********************************************
	public double jumppower=0.8;
	//public double gravity=5;//0.04;
	protected boolean posjump;//Jump possible?
	protected int dir=0; //-1:vers la gauche,0:ne bouge pas,1:vers la droite
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
	
	
}
