package fr.characters.enemies;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Graphics;
import fr.characters.Player;
import fr.game.World;
import fr.util.Collisions;
import fr.projectiles.Projectile;

public class EnnemyShooter extends EnnemyToppingDecorator implements Ennemy{

	private boolean leftShoot;
	private boolean rightShoot;
	private int timeBetweenShoot;
	private int timeToShoot;
	
	public EnnemyShooter(BasicEnnemy newEnnemy) {
		super(newEnnemy);
		this.timeToShoot=0;
		this.timeBetweenShoot=50;
		this.leftShoot=true;
		this.rightShoot=false;
	}
	
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.timeToShoot+=1;
		if(this.timeToShoot==this.timeBetweenShoot)
		{
			if(this.rightShoot)
			{
				Projectile p =new Projectile(this.getX(),this.getY(),0.5,0,false);
				World.getProjectiles().add(p);
				
			}
			else if(this.leftShoot)
			{
				Projectile p =new Projectile(this.getX(),this.getY(),-0.5,0,false);
				World.getProjectiles().add(p);
			}
			this.timeToShoot=0;
		}
		tempEnnemy.update(container, game, delta);
		collPlayer(fr.game.World.getPlayer(),delta);
	}
	
	public void collPlayer(Player player,double delta)  {
		isCollisionX=Collisions.isCollisionX(player,tempEnnemy, delta)	;
		//System.out.println("collX="+this.isCollisionX);	
		isCollisionY=Collisions.isCollisionY(player,tempEnnemy, delta)	;
		System.out.println("collY="+this.isCollisionY);
		if (isCollisionX == -1|| isCollisionX == 1 || isCollisionY == -1){
				//point de vue de l'ennemi
			World.getPlayer().lifelost();
			}
			else if (isCollisionY == 1){
				tempEnnemy.looseLife();
				player.jump();
				System.out.println("bouh");
			}
	}
}
