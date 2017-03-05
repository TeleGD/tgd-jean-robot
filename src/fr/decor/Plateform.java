package fr.decor;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.BasicPlayer;
import fr.characters.Player;
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
		super(indexX*Game.DENSITE_X,indexY*Game.DENSITE_Y,sizeX*Game.DENSITE_X,sizeY*Game.DENSITE_Y);
	}
	
	//construteur appele pour charger de niveau.
	public Plateform(String ligne) {
		super(ligne);
	}

	// Fonction de jeu*********************************************
	
	

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.fillRect((float) x, (float) y, (float) width, (float) height);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	}
	

	public double getnewX() {
		return x;
	}

	public double getnewY() {
		return y;
	}

	public void collPlayer(Player player){
		if(fr.util.Collisions.colPlayerPlateform(player,this)==2 && player.getSpeedY()>0){
			player.setY(this.getY() - player.getHeight());
			player.setAccY(0);
			player.setSpeedY(0);
			player.setInCol(true);
			player.setposJump(true);
		}
	}
	
	public String parseString() {
		return "Plateform :  "+super.parseString();
	}

	public Plateform copy() {
		Plateform p=new Plateform((int)x/Game.DENSITE_X,(int)y/Game.DENSITE_Y,(int)x/Game.DENSITE_X,(int) (y/Game.DENSITE_Y));
		p.height=this.height;
		p.width=this.width;
		return p;
	}

	


}
