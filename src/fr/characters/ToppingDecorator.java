package fr.characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;

public class ToppingDecorator implements Player{

	protected Player tempPlayer;
	
	public ToppingDecorator(Player newPlayer){
		tempPlayer=newPlayer;
	}

	@Override
	public double getWidth() {
		return tempPlayer.getWidth();
	}

	@Override
	public double getHeight() {
		return tempPlayer.getHeight();
	}

	@Override
	public double getY() {
		return tempPlayer.getY();
	}

	@Override
	public double getX() {
		return tempPlayer.getX();
	}

	@Override
	public double getnewX() {
		return tempPlayer.getnewX();
	}

	@Override
	public double getnewY() {
		return tempPlayer.getnewY();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		tempPlayer.render(container, game, g);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		tempPlayer.update(container, game, delta);
	}

	@Override
	public void loose(StateBasedGame game) {
		tempPlayer.loose(game);
	}

	@Override
	public void keyReleased(int key, char c) {
		tempPlayer.keyReleased(key, c);
	}

	@Override
	public void keyPressed(int key, char c) {
		tempPlayer.keyPressed(key, c);
	}

	@Override
	public void lifelost() {
		tempPlayer.lifelost();
	}

	@Override
	public int getlife() {
		return tempPlayer.getlife();
	}

	@Override
	public void reset() {
		tempPlayer.reset();
	}

	@Override
	public int getType() {
		return tempPlayer.getType();
	}

	@Override
	public void jump() {
		tempPlayer.jump();
	}

	@Override
	public double getSpeedX() {
		return tempPlayer.getSpeedX();
	}

	@Override
	public double getSpeedY() {
		return tempPlayer.getSpeedY();
	}

	@Override
	public void setY(double y) {
		tempPlayer.setY(y);
	}

	@Override
	public void setX(double x) {
		tempPlayer.setX(x);
	}
	
	@Override
	public void setImages(String path){
		tempPlayer.setImages(path);
	}
	
	@Override
	public int getDirerction()
	{
		return tempPlayer.getDirerction();
	}


	@Override
	public void setAccY(double i) {
		tempPlayer.setAccY(i);
	}
	
	public void setInCol(boolean b) {
		tempPlayer.setInCol(b);	
	}

	@Override
	public void setSpeedY(double i) {
		tempPlayer.setSpeedY(i);
	}	
		
	public void setposJump(boolean b) {
		tempPlayer.setposJump(b);
	}

	@Override
	public void addScore(int s) {
		tempPlayer.addScore(s);
		
	}

}
