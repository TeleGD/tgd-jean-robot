package fr.characters.enemies;

import fr.characters.Player;
import fr.decor.Plateform;
import fr.game.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Collisions;
import fr.util.Movable;

public class BasicEnnemy extends Movable implements Ennemy{
		protected double width, height;
		int bas = 600;
		
		private int life;
		// une vie par exemple)
		
		private boolean destructed = false;//true si l'ennemi est mort et doit etre supprime
		
		//Pour attacher l'ennemi a une plateforme (histoire de reperer sa position par rapport a elle)
		private Plateform initialPlat;
		
		public BasicEnnemy(double x,double y) {
			this.x = x;
			this.y = y;
			this.width = 32;
			this.height = 32;
			this.speedX = 0;
			this.speedY = 0;
			this.life=1;
			this.destructed = false;
		}
		
		public BasicEnnemy(Plateform plat) {
			this.width = 64;
			this.height = 32;
			this.initialPlat=plat;
			this.x = plat.getX()+plat.getWidth()/2;
			this.y = plat.getY()-this.height;
			this.speedX = 0;
			this.speedY = 0;
			this.life=1;
			this.destructed = false;
		}
		
	public int getLife() {
		return life;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.black);
		g.fillRect((float)x, (float)y, (float)width, (float)height);
	}
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.newY = y + speedY * delta;
		this.newX = x + speedX * delta;
		moveY(delta);
		moveX(delta);
	}
	
	private boolean isTooLow() { //renvoie true si la personne touche le bas de l'ecran
		if (speedY < 0) {
			return false;
		}
		if (newY + height < 720) {
			return false;
		}
		return true;
	}

	public boolean isDestructed() {
		return destructed;
	}

	public void setDestructed(boolean destructed) {
		this.destructed = destructed;
	}

	public Plateform getInitPlat(){
		return this.initialPlat;
	}

	@Override
	public void collPlayer() {
	}

	public void looseLife() {
		this.life-=1;
		if (this.life<=0)
				this.destructed=true;
	}
	
}
