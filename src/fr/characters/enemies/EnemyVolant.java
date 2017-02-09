package fr.characters.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;
import fr.game.World;
import fr.util.Collisions;
import fr.util.Entity;

public class EnemyVolant extends EnnemyToppingDecorator implements Ennemy{
	
	public double maxHeight;
	public double minHeigth;

	public EnemyVolant(BasicEnnemy newEnnemy) {
		super(newEnnemy);
		tempEnnemy.setSpeedX(0.1);
		tempEnnemy.setSpeedY(0.2);
		tempEnnemy.setY(tempEnnemy.getY()-100);
		this.maxHeight=tempEnnemy.getY()-100;
		this.minHeigth=tempEnnemy.getY();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		//si trop à gauche
		if((tempEnnemy.getX()<tempEnnemy.getInitPlat().getX()) && (tempEnnemy.getSpeedX()<0))
			tempEnnemy.setSpeedX(0.1);
		if((tempEnnemy.getX()+tempEnnemy.getWidth()>tempEnnemy.getInitPlat().getX()+tempEnnemy.getInitPlat().getWidth()) && (tempEnnemy.getSpeedX()>0))
			tempEnnemy.setSpeedX(-0.1);
		
		//si trop haut
		if(tempEnnemy.getY()<this.maxHeight)
			tempEnnemy.setSpeedY(0.1);
		//si trop bas
		if(tempEnnemy.getY()>this.minHeigth)
			tempEnnemy.setSpeedY(-0.1);
		
		System.out.println(tempEnnemy.getY());
		
		
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
