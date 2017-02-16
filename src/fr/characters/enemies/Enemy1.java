package fr.characters.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;
import fr.game.World;

public class Enemy1 extends EnnemyToppingDecorator implements Ennemy{

	public Enemy1(Ennemy newEnnemy) {
		super(newEnnemy);
		tempEnnemy.setSpeedX(0.5);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		if((tempEnnemy.getX()<tempEnnemy.getInitPlat().getX()+tempEnnemy.getInitPlat().getWidth()/6) && (tempEnnemy.getSpeedX()<0))
			tempEnnemy.setSpeedX(0.1);
		if((tempEnnemy.getX()>(tempEnnemy.getInitPlat().getX()+tempEnnemy.getInitPlat().getWidth()-tempEnnemy.getInitPlat().getWidth()/6)-tempEnnemy.getWidth()) && (tempEnnemy.getSpeedX()>0))
			tempEnnemy.setSpeedX(-0.1);
		tempEnnemy.update(container, game, delta);
	}
	
	public void collPlayer(Player player)  {
		colPlayer=fr.util.Collisions.colPlayerEnnemy(player,tempEnnemy);
		System.out.println("coll = "+colPlayer);
		if (colPlayer == 1|| colPlayer == 3 || colPlayer == 4){
			World.getPlayer().lifelost();
			}else if (colPlayer == 2){
				tempEnnemy.looseLife();
				player.setY(tempEnnemy.getY()-player.getHeight());
				player.jump();
			}
	}
}
