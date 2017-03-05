package fr.characters.enemies;

import fr.characters.Player;
import fr.game.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;

public class BasicEnnemy extends Movable implements Ennemy{
		
	private int score  ;
	
	private int life;
	// une vie par exemple
	
	private boolean destructed = false;//true si l'ennemi est mort et doit etre supprime
	
	//Pour attacher l'ennemi a une plateforme (histoire de reperer sa position par rapport a elle)
	
	
	public BasicEnnemy(double x,double y) {
		super(x,y,Game.DENSITE_X,Game.DENSITE_Y);
		this.speedX = 0;
		this.speedY = 0;
		this.life=1;
		this.destructed = false;
		this.score = 50;
	}
		
		
	//Constructeur appelé par le chargeur de niveau 
	public BasicEnnemy(String ligne) {
		super(ligne);
		
		String[] split=ligne.split(";");
		life=Integer.parseInt(split[6]);
		score=Integer.parseInt(split[7]);
	}



	public int getLife() {
		return life;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.black);
		g.fillRect((float)x, (float)y, (float)width, (float)height);
	}
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.newY = y + speedY * delta;
		this.newX = x + speedX * delta;
		
		moveY(delta);
		moveX(delta);
		
		
	}
	

	public boolean isDestructed() {
		return destructed;
	}

	public void setDestructed(boolean destructed) {
		this.destructed = destructed;
	}

	public void looseLife() {
		this.life-=1;
		if (this.life<=0)
				this.destructed=true;
	}
	
	public void setScore(int s){
		this.score = s;
	}
	
	public int getScore(){
		return this.score;
	}

	@Override
	public void collPlayer(Player player) {
	}



	public void setLife(int life) {
		this.life=life;
	}



	@Override
	public Ennemy copy() {

		BasicEnnemy enemy=new BasicEnnemy(getX(),getY());
		enemy.setWidth(getWidth());
		enemy.setHeight(getHeight());
		enemy.setDestructed(isDestructed());
		enemy.setLife(getLife());	
		enemy.setScore(getScore());	

		return enemy;
	}



	@Override
	public String parseString() {
		return "BasicEnnemy :  "+super.parseString()+";"+life+";"+score;
	}

	
}
