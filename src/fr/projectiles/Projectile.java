package fr.projectiles;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;
import fr.util.Rectangle;

public class Projectile extends Movable implements Rectangle{
// Base projectile : straight trajectory.
	
	
	private boolean destructed;
	private boolean allied;
	protected Image sprite;
	protected int angle;
	protected double speed;
	
	public Projectile(double x, double y, double speedX, double speedY){
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		speed = Math.sqrt(Math.pow(speedX, 2)+Math.pow(speedY, 2));
		loadImage("img/proj2.png");
		destructed = false;
		angle = 0;
		setAllied(false);
		
	}
	
	public Projectile(double x, double y, double speedX, double speedY, boolean allied){
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		speed = Math.sqrt(Math.pow(speedX, 2)+Math.pow(speedY, 2));
		this.setAllied(allied);
		if(allied) loadImage("img/proj1.png");
		else loadImage("img/proj2.png");
		destructed = false;
		angle = 0;
	}
	
	public Projectile(double x,double y, double speed, int angle, boolean allied){
		this.x = x;
		this.y = y;
		this.speedX = speed*Math.cos(angle);
		this.speedY = speed*Math.sin(angle);
		this.setAllied(allied);
		if(allied) loadImage("img/proj1.png");
		else loadImage("img/proj2.png");
		destructed = false;
		this.angle = angle;
		sprite.rotate(angle);
	}
	
	private void loadImage(String path){
		try {
			sprite=new Image(path);
		} catch (SlickException e){
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if(!destructed) g.drawImage(sprite,(float)x,(float)y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		moveX(delta);
		moveY(delta);
	}
	
	public void destruct(){
		this.destructed = true;
	}
	
	public boolean isDestructed(){
		return destructed;
	}

	public boolean isAllied() {
		return allied;
	}

	public void setAllied(boolean allied) {
		this.allied = allied;
	}
}
