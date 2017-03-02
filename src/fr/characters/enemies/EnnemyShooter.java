package fr.characters.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import fr.characters.Player;
import fr.game.World;
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
	}
	
	public void collPlayer(Player player)  {
		colPlayer=fr.util.Collisions.colPlayerEnnemy(player,tempEnnemy);
		if (colPlayer == 1|| colPlayer == 3 || colPlayer == 4){
			World.getPlayer().lifelost();
			}else if (colPlayer == 2){
				tempEnnemy.looseLife();
				player.setY(tempEnnemy.getY()-player.getHeight());
				player.jump();
			}
	}
	
	public int getScore(){
		return tempEnnemy.getScore() + 50;
	}
}
