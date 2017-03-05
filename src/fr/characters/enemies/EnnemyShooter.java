package fr.characters.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.game.World;
import fr.projectiles.Projectile;

public class EnnemyShooter extends EnnemyToppingDecorator{

	private boolean leftShoot;
	private boolean rightShoot; //direction du shoot left or right or twiice?
	private boolean shooting; //detemine si l'ennemie peut tirer;
	private int timeBetweenShoot;
	private int timeToShoot;
	private Image sprite;
	
	public EnnemyShooter(BasicEnnemy newEnnemy) {
		super(newEnnemy);
		
		this.timeToShoot=0;
		this.timeBetweenShoot=50;
		this.leftShoot=true;
		this.rightShoot=false;
		this.shooting=true;
		chargerImage();
		
	}
	
	//Construteur appelé par le chargement d'un niveau, on parse les paramètres depuis un string
	public EnnemyShooter(String ligne) {
		super(new BasicEnnemy(ligne));
		
		String[] split=ligne.split(";");
		leftShoot=Boolean.parseBoolean(split[8]);
		rightShoot=Boolean.parseBoolean(split[9]);
		timeBetweenShoot=Integer.parseInt(split[11]);
		
		this.timeToShoot=0;
		this.shooting=true;

		chargerImage();

	}

	private void chargerImage() {
		try {
			sprite=new Image("img/Ennemy/tank.png");
		} catch (SlickException e){
			System.out.println("LE SPRITE TANK.PNG NE PEUT ETRE CHARGE");
			e.printStackTrace();
		}		
	}
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.timeToShoot+=1;
		if(this.timeToShoot==this.timeBetweenShoot)
		{
			if(shooting){
				if(rightShoot)
				{
					Projectile p =new Projectile(this.getX(),this.getY(),0.5,0,false);
					World.getProjectiles().add(p);
					
				}
				else if(leftShoot)
				{
					Projectile p =new Projectile(this.getX(),this.getY(),-0.5,0,false);
					World.getProjectiles().add(p);
				}
			}
			this.timeToShoot=0;
		}
		tempEnnemy.update(container, game, delta);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(sprite,(float)this.getX(),(float)this.getY());
	}
	
	public void setIsShooting(boolean isShooting){
		this.shooting=isShooting;
	}
	
	public int getScore(){
		return tempEnnemy.getScore() + 50;
	}
	
	@Override
	public Ennemy copy(){

		EnnemyShooter enemy=new EnnemyShooter(((BasicEnnemy)tempEnnemy.copy()));
		enemy.setTimeToShoot(timeToShoot);
		enemy.setTimeBetweenShoot(timeBetweenShoot);
		enemy.setLeftShoot(leftShoot);
		enemy.setRightShoot(rightShoot);

		return enemy;
	}

	private void setRightShoot(boolean rightShoot) {
		this.rightShoot=rightShoot;
	}

	private void setLeftShoot(boolean leftShoot) {
		this.leftShoot=leftShoot;
	}

	private void setTimeBetweenShoot(int timeBetweenShoot) {
		this.timeBetweenShoot=timeBetweenShoot;
	}

	private void setTimeToShoot(int timeToShoot) {
		this.timeToShoot=timeToShoot;
	}
	
	@Override
	public String parseString() {
		return "EnnemyShooter :  "+super.parseString()+";"+leftShoot+";"+rightShoot+";"+timeBetweenShoot;
	}

}
