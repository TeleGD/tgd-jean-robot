package fr.characters.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;
import fr.game.World;

public class EnemyVolant extends EnnemyToppingDecorator implements Ennemy{
	
	public double maxHeight;
	public double minHeigth;

	public EnemyVolant(BasicEnnemy newEnnemy) {
		super(newEnnemy);
		tempEnnemy.setSpeedX(0.1);
		tempEnnemy.setSpeedX(0.2);
		tempEnnemy.setY(tempEnnemy.getY()-100);
		this.maxHeight=tempEnnemy.getY()-100;
		this.minHeigth=tempEnnemy.getY();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		//si trop haut
		if(tempEnnemy.getY()<this.maxHeight)
			tempEnnemy.setSpeedX(0.1);
		//si trop bas
		if(tempEnnemy.getY()>this.minHeigth)
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
