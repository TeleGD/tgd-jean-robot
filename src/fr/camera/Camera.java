package fr.camera;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;
import fr.game.Game;
import fr.util.Movable;

public class Camera extends Movable{

	private double x,y;

	public Camera(){
		super(0,0, 2, 2);
		x=fr.game.World.getPlayer().getX()-Game.hauteur/2;
		y=fr.game.World.getPlayer().getY()-Game.longueur/2;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//g.translate((float)container.getWidth() / 2 - (float)this.x,0);
		g.fillOval((float)x, (float)y, 5,5);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {


		if((this.x>fr.game.World.getPlayer().getX()-Game.longueur/4)&&(this.x<fr.game.World.getPlayer().getX()))
			this.x=fr.game.World.getPlayer().getX()-Game.longueur/4;
		else if((this.x<fr.game.World.getPlayer().getX()-Game.longueur*3/4)&&(this.x>fr.game.World.getPlayer().getX()-Game.longueur))
			this.x=fr.game.World.getPlayer().getX()-Game.longueur*3/4;



		this.y=fr.game.World.getPlayer().getY()-Game.hauteur/2;
	}


	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}




}
