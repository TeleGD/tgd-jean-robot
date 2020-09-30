package games.jeanRobot.characters.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.jeanRobot.World;
import games.jeanRobot.characters.Player;
import games.jeanRobot.decor.Plateform;

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
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		tempEnnemy.render(container, game, g);
		}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
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

	public void collPlayer(StateBasedGame game, Player player)  {
		colPlayer=games.jeanRobot.util.Collisions.colPlayerEnnemy(player,tempEnnemy);
		if (colPlayer == 1|| colPlayer == 3 || colPlayer == 4){
			World.getPlayer().lifelost(game);
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
	public void setY(double d) {
		tempEnnemy.setY(d);
	}

	@Override
	public Plateform getInitPlat() {
		return tempEnnemy.getInitPlat();
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return tempEnnemy.getScore();
	}

	@Override
	public void setImages(String path) {
		// TODO Auto-generated method stub
		tempEnnemy.setImages(path);
	}
}
