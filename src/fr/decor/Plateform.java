package fr.decor;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;
import fr.util.Entity;
import fr.util.Rectangle;

public class Plateform extends Entity implements Rectangle {

	private double width; // nombre de tuiles suivant x
	private double height; // nombre de tuiles suivant y
	protected double x; // en pixel
	protected double y; // en pixel
	Image texture;

	// Getters******************************************************
	public double getX() {
		return x;

	}

	public double getY() {
		return y;
	}

	public double getWidth() {
		return width;

	}

	public double getHeight() {
		return height;
	}

	// Setters *****************************************************
	public void setPosition(float newx, float newy) {
		x = newx;
		y = newy;
	}

	public void setSize(float newwidth, float newheight) {
		width = newwidth;
		height = newheight;
	}

	// Constructeur***********************************************
	public Plateform(float width, float height, float x,float y) {

		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	// Fonction de jeu*********************************************
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.fillRect((float) x, (float) y, (float) width, (float) height);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO
	}
	

	@Override
	public double getnewX() {
		return x;
	}

	@Override
	public double getnewY() {
		return y;
	}

	public boolean collPlayer(Player player){
		if(player.getspeedY()<0){return false;}
		if(player.getnewY()+player.getHeight()<this.y){return false;}
		if(player.getY()>this.y){return false;}
		if(player.getX()>this.x+this.width){return false;}
		if(player.getX()+player.getWidth()<this.x){return false;}
		return true;

	}
	
	


}
