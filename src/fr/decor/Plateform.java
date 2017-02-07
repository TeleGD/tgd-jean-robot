package fr.decor;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.BasicPlayer;
import fr.game.Game;
import fr.util.Entity;
import fr.util.Rectangle;

public class Plateform extends Entity implements Rectangle {

	Image texture;

	// Setters *****************************************************
	public void setPosition(int indexX, int indexY) {
		x = indexX*Game.DENSITE_X;
		y = indexY*Game.DENSITE_Y;
	}

	public void setSize(float newwidth, float newheight) {
		width = newwidth;
		height = newheight;
	}

	// Constructeur***********************************************
	/**
	 * 
	 * @param indexX  : indice du placement de la plateforme horizontal 0->0 1->32px 2->64px,...
	 * @param indexY  : indice du placement de la plateforme vertical 0->0 1->32px 2->64px,...
	 * @param sizeX : largeur en echelon de 32 pix de la plateforme
	 * @param sizeY : hauteur en echelon de 32 pix de la plateforme
	 */
	public Plateform(int indexX, int indexY, int sizeX, int sizeY) {
		this.x = indexX*Game.DENSITE_X;
		this.y = indexY*Game.DENSITE_Y;
		this.width = sizeX*Game.DENSITE_X;
		this.height = sizeY*Game.DENSITE_Y;
		
		this.newX = this.x;
		this.newY = this.y;
		
		
	}
	
	public Plateform(Plateform p){
		this.x = p.x;
		this.y = p.y;
		this.newX = p.newX;
		this.newY = p.newY;
		this.width = p.width;
		this.height = p.height;
		
	}
	
	//construteur appele pour charger de niveau.
	public Plateform(String ligne) {
		String[] s=ligne.substring(ligne.indexOf(" ")+1).split(";");
		x=Double.parseDouble(s[0]);
		y=Double.parseDouble(s[1]);
		width=Double.parseDouble(s[2]);
		height=Double.parseDouble(s[3]);
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
		this.newX = this.x;
		this.newY = this.y;
	}
	

	@Override
	public double getnewX() {
		return x;
	}

	@Override
	public double getnewY() {
		return y;
	}

	public boolean collPlayer(BasicPlayer player){
		if(player.getSpeedY()<0){return false;}
		if(player.getnewY()+player.getHeight()<this.y){return false;}
		if(player.getY()>this.y){return false;}
		if(player.getX()>this.x+this.width){return false;}
		if(player.getX()+player.getWidth()<this.x){return false;}
		return true;

	}
	
	public String parseString() {
		// TODO Auto-generated method stub
		return "Plateform "+getX()+ ";"+ getY()+";"+getWidth()+";"+getHeight();
	}
	


}
