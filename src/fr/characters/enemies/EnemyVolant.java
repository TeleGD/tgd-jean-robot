package fr.characters.enemies;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class EnemyVolant extends EnnemyToppingDecorator implements Ennemy{
	
	public double maxHeight;
	public double minHeight;

	public EnemyVolant(Ennemy newEnnemy) {
		super(newEnnemy);
		tempEnnemy.setSpeedY(0.1);
		tempEnnemy.setY(tempEnnemy.getY()-150);
		
		this.maxHeight=tempEnnemy.getY()-150;
		this.minHeight=tempEnnemy.getY();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		//si trop haut
		if(tempEnnemy.getY()<this.maxHeight-40)
			tempEnnemy.setSpeedY(0.1);
		//si trop bas
		if(tempEnnemy.getY()>this.minHeight+40)
			tempEnnemy.setSpeedY(-0.1);
		tempEnnemy.update(container, game, delta);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		super.render(container, game, g);
	}
	
}
