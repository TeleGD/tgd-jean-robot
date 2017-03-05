package fr.characters.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;
import fr.decor.Plateform;
import fr.game.World;

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
	public void setX(double d) {
		tempEnnemy.setX(d);
	}
	
	@Override
	public void setY(double d) {
		tempEnnemy.setY(d);
	}


	@Override
	public int getScore() {
		return tempEnnemy.getScore();
	}

	@Override
	public boolean containsPoint(int x, int y) {
		return tempEnnemy.getX()<=x &&  x<=tempEnnemy.getX()+tempEnnemy.getWidth() && tempEnnemy.getY()<=y && y<=tempEnnemy.getY()+tempEnnemy.getHeight();
	}

	@Override
	public Ennemy copy() {
		BasicEnnemy enemy=new BasicEnnemy(tempEnnemy.getX(),tempEnnemy.getY());
		enemy.setWidth(tempEnnemy.getWidth());
		enemy.setHeight(tempEnnemy.getHeight());
		enemy.setDestructed(tempEnnemy.isDestructed());
		enemy.setLife(tempEnnemy.getLife());

		return enemy;
	}

	@Override
	public String parseString() {
		return tempEnnemy.parseString();
	}

	
}
