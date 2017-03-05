package fr.characters.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;
import fr.game.World;

public class Enemy1 extends EnnemyToppingDecorator implements Ennemy{

	private double absoluteSpeed=0.1; //vitesse de l'ennemy;
	
	public Enemy1(Ennemy newEnnemy) {
		super(newEnnemy);
		tempEnnemy.setSpeedX(absoluteSpeed);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if((tempEnnemy.getX()<tempEnnemy.getX()+tempEnnemy.getWidth()/6) && (tempEnnemy.getSpeedX()<0))
			tempEnnemy.setSpeedX(absoluteSpeed);
		if((tempEnnemy.getX()>(tempEnnemy.getX()+tempEnnemy.getWidth()-tempEnnemy.getWidth()/6)-tempEnnemy.getWidth()) && (tempEnnemy.getSpeedX()>0))
			tempEnnemy.setSpeedX(-absoluteSpeed);
		tempEnnemy.update(container, game, delta);
	}
	
	
}
