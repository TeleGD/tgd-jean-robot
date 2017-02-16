package fr.characters.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;
import fr.decor.Plateform;

public class EnnemyToppingDecorator implements Ennemy{

	protected Ennemy tempEnnemy; 
	protected int colPlayer;
	
	public EnnemyToppingDecorator(Ennemy newEnnemy){
		this.tempEnnemy=newEnnemy;
		this.colPlayer=0;

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

	@Override
	public double getSpeedX() {
		return tempEnnemy.getSpeedX();
	}

	@Override
	public double getSpeedY() {
		return tempEnnemy.getSpeedY();
	}

	@Override
	public double getnewX() {
		return tempEnnemy.getnewX();
	}

	@Override
	public double getnewY() {
		return tempEnnemy.getnewY();
	}

	@Override
	public void collPlayer(Player player) {
	}

	@Override
	public void looseLife() {
		tempEnnemy.looseLife();
	}

	@Override
	public void setSpeedX(double d) {
		tempEnnemy.setSpeedX(d);
	}

	@Override
	public void setSpeedY(double d) {
		tempEnnemy.setSpeedY(d);
	}
	
	@Override
	public void setY(double d) {
		tempEnnemy.setY(d);
	}

	@Override
	public Plateform getInitPlat() {
		return tempEnnemy.getInitPlat();
	}
}
