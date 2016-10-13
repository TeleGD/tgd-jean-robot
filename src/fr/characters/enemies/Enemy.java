package fr.characters.enemies;

import fr.characters.Player;
import fr.game.World;
import fr.menus.MenuFinPartie;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Collisions;
import fr.util.Entity;
import fr.util.Movable;
import fr.util.Rectangle;

public class Enemy extends Movable implements Rectangle{
		protected double width, height;
		private int isCollisionX;// y a t il eu une coll
		// sur l'axe X � gauche ou � droite
		private int isCollisionY; // y a t il eu une coll
		// sur l'axe Y en haut ou en bas
		int bas = 600;
	
		private int life;
		private boolean invincible=true;// le joueur est invincible (apres avoir perdu
		// une vie par exemple)
		private long timeInvincibleDying=3000;// temps d'invincibilite apres une mort
		private double comptInvincible;//compteur servant au clignotement durant l'invincibilite apres la mort
		
		private boolean destructed = false;//true si l'ennemi est mort et doit etre supprime
		
		public Enemy() {
			this.x = 150;
			this.y = 630;// 250-32;
			this.width = 32;
			this.height = 32;
			this.speedX = 0;
			this.speedY = 0;
			this.accelY = gravity;
			this.accelX = 0;
			this.isCollisionX = 0;
			this.isCollisionY = 0;
			this.invincible = false;
			this.life=1;
			this.destructed = false;
		}
		
	public void collPlayer(Entity ent)  {
		if (ent instanceof Player) {
			// si c'est un joueur
			if (isCollisionX == -1|| isCollisionX == 1 || isCollisionY == -1){
				//point de vue de l'ennemi
			World.getPlayer().lifelost();
			}
			else if (isCollisionY == 1){
				this.life-=1;
			}
		}
	}
	
	public void collPlat (Entity ent){
		if (ent instanceof Player){
			
		}
	}
		
	

	public int getLife() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.fillRect((float)x, (float)y, (float)width, (float)height);
	}
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
		this.newY = y + speedY * delta;
		this.verticalMove();
		
		if(this.isTooLow())//si l'ennemi est trop bas : il meurt
			this.destructed = true;
		
		moveY(delta);
	}
	
	public void verticalMove() {
		this.accelY = gravity;
		//movement vertical de l'ennemi (tombe si rien en dessous)
		for (int i = 0; i < fr.game.World.getPlateforms().size(); i++) {
			
			if ((Collisions.isCollisionY(this, fr.game.World.getPlateforms().get(i)) == -1)) {
				this.accelY = 0;
				this.speedY = 0;
			} 
		}
		
		//on ajoute accelY a la vitesse
		//si l'ennemi est en l'air, accelY = gravity, sinon accelY et speedY = 0
		this.speedY += this.accelY;
	}
	
	private boolean isTooLow() { //renvoie true si le personne touche le bas de l'Ã©cran
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

}
