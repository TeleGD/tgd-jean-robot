package fr.util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Entity;

public class Movable extends Entity{
	
	// Variables**********************************************
	protected double speedX,speedY;
	protected double accelX,accelY;
	protected double newX,newY;
	protected boolean posjump;//Jump possible?
	protected int dir=0; //-1:vers la gauche,0:ne bouge pas,1:vers la droite
	
	
	public Movable(double x, double y, double width, double height) {
		super(x,y,width,height);
	}
	
	//Constructeur qui construit l'objet a partir d'une string;
	public Movable(String ligne) {
		super(ligne);
		String[] split=ligne.split(";");
		speedX=Double.parseDouble(split[4]);
		speedY=Double.parseDouble(split[5]);

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

	public String parseString() {
		return super.parseString()+";"+speedX+";"+speedY;
	}
	
	
}
