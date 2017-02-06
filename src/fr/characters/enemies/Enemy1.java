package fr.characters.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;
import fr.game.World;
import fr.util.Collisions;
import fr.util.Entity;

public class Enemy1 extends EnnemyToppingDecorator implements Ennemy{

	public Enemy1(BasicEnnemy newEnnemy) {
		super(newEnnemy);
		tempEnnemy.setSpeedX(0.5);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		if((tempEnnemy.getX()<tempEnnemy.getInitPlat().getX()+tempEnnemy.getInitPlat().getWidth()/6) && (tempEnnemy.getSpeedX()<0))
			tempEnnemy.setSpeedX(0.1);
		if((tempEnnemy.getX()>tempEnnemy.getInitPlat().getX()+4*tempEnnemy.getInitPlat().getWidth()/6-tempEnnemy.getWidth()) && (tempEnnemy.getSpeedX()>0))
			tempEnnemy.setSpeedX(-0.1);
		collPlayer(fr.game.World.getPlayer(),delta);
		tempEnnemy.update(container, game, delta);
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
