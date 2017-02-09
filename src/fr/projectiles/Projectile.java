package fr.projectiles;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;

public class Projectile extends Movable{
	public Projectile(double x, double y, double speedX, double speedY){
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
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
