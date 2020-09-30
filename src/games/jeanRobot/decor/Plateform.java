package games.jeanRobot.decor;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import games.jeanRobot.characters.Player;
import games.jeanRobot.World;
import games.jeanRobot.util.Entity;
import games.jeanRobot.util.Rectangle;

public class Plateform extends Entity implements Rectangle {

	Image texture;

	// Setters *****************************************************
	public void setPosition(int indexX, int indexY) {
		x = indexX*World.DENSITE_X;
		y = indexY*World.DENSITE_Y;
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
		this.x = indexX*World.DENSITE_X;
		this.y = indexY*World.DENSITE_Y;
		this.width = sizeX*World.DENSITE_X;
		this.height = sizeY*World.DENSITE_Y;



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
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((float) x, (float) y, (float) width, (float) height);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
	}


	public double getnewX() {
		return x;
	}

	public double getnewY() {
		return y;
	}

	public void collPlayer(StateBasedGame game, Player player){
		if(games.jeanRobot.util.Collisions.colPlayerPlateform(player,this)==2 && player.getSpeedY()>0){
			player.setY(this.getY() - player.getHeight());
			player.setAccY(0);
			player.setSpeedY(0);
			player.setInCol(true);
			player.setposJump(true);
		}
	}

	public String parseString() {
		return "Plateform "+getX()+ ";"+ getY()+";"+getWidth()+";"+getHeight();
	}

	public Plateform copy() {
		Plateform p=new Plateform((int)x/World.DENSITE_X,(int)y/World.DENSITE_Y,(int)x/World.DENSITE_X,(int) (y/World.DENSITE_Y));
		p.height=this.height;
		p.width=this.width;
		return p;
	}




}
