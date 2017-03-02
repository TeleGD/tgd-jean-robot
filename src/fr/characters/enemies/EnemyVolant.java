package fr.characters.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;
import fr.game.World;

public class EnemyVolant extends EnnemyToppingDecorator implements Ennemy{
	
	public double maxHeight;
	public double minHeight;
	public boolean monte = true;

	public EnemyVolant(BasicEnnemy newEnnemy) {
		super(newEnnemy);
		tempEnnemy.setSpeedY(0.1);
		tempEnnemy.setY(tempEnnemy.getY()-20);
		this.maxHeight=tempEnnemy.getY()-120;
		this.minHeight=tempEnnemy.getY();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		//si trop haut
		if(tempEnnemy.getY()<this.maxHeight+1)
			 monte = false;
		//si trop bas
		if(tempEnnemy.getY()>this.minHeight-1)
			 monte = true;
		
		if(monte)
			tempEnnemy.setSpeedY(Math.sin((this.getY()-maxHeight)/maxHeight)/4);
		else
			tempEnnemy.setSpeedY(Math.sin((minHeight-this.getY())/maxHeight)/-4);
		
		
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
}
