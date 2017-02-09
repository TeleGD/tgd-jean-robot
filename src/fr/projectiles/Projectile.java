package fr.projectiles;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;
import fr.util.Rectangle;

public class Projectile extends Movable implements Rectangle{
	private boolean destructed;
	private boolean allied;
	private Image sprite;
	
	public Projectile(double x, double y, double speedX, double speedY){
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		loadImage("img/proj2");
		destructed = false;
		allied = false;
		
	}
	
	public Projectile(double x, double y, double speedX, double speedY, boolean allied){
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.allied = allied;
		if(allied) loadImage("img/proj1");
		else loadImage("img/proj2");
		destructed = false;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}
}
