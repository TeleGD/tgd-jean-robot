package fr.characters.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;

public class EnnemyToppingDecorator extends Movable implements Ennemy{

	protected BasicEnnemy tempEnnemy; 
	protected int isCollisionX;// y a t il eu une coll
	// sur l'axe X (gauche ou droite)
	protected int isCollisionY; // y a t il eu une coll
	// sur l'axe Y en haut ou en bas
	
	public EnnemyToppingDecorator(BasicEnnemy newEnnemy){
		this.tempEnnemy=newEnnemy;
		this.isCollisionX = 0;
		this.isCollisionY = 0;

	}
	
	@Override
	public boolean isDestructed() {
		return tempEnnemy.isDestructed();
	}

	@Override
	public void setDestructed(boolean destructed) {
		tempEnnemy.setDestructed(destructed);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		tempEnnemy.render(container, game, g);
		}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		tempEnnemy.update(container, game, delta);
	}

	@Override
	public int getLife() {
		return this.tempEnnemy.getLife();
	}

	@Override
	public void collPlayer() {
	}
	
	@Override
	public double getWidth(){
		return this.tempEnnemy.getWidth();
	}

	
	@Override
	public double getHeight(){
		return this.tempEnnemy.getHeight();
	}
	
	@Override
	public double getX(){
		return this.tempEnnemy.getX();
	}

	
	@Override
	public double getY(){
		return this.tempEnnemy.getY();
	}
}
