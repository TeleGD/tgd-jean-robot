package games.jeanRobot.characters.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.jeanRobot.World;
import games.jeanRobot.projectiles.Projectile;

public class EnnemyShooter extends EnnemyToppingDecorator implements Ennemy{

	private boolean leftShoot;
	private boolean rightShoot;
	private int timeBetweenShoot;
	private int timeToShoot;
	private Image sprite;

	public EnnemyShooter(BasicEnnemy newEnnemy) {
		super(newEnnemy);
		sprite=AppLoader.loadPicture("/images/jeanRobot/Ennemy/tank.png");
		this.timeToShoot=0;
		this.timeBetweenShoot=50;
		this.leftShoot=true;
		this.rightShoot=false;

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
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

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(sprite,(float)this.getX(),(float)this.getY());
	}



	public int getScore(){
		return tempEnnemy.getScore() + 50;
	}
}
