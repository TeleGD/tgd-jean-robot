package games.jeanRobot.camera;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.jeanRobot.World;
import games.jeanRobot.util.Movable;

public class Camera extends Movable{

	private double x,y;

	public Camera(){
		super(0,0, 2, 2);
		x=games.jeanRobot.World.getPlayer().getX()-World.hauteur/2;
		y=games.jeanRobot.World.getPlayer().getY()-World.longueur/2;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		//g.translate((float)container.getWidth() / 2 - (float)this.x,0);
		g.fillOval((float)x, (float)y, 5,5);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {


		if((this.x>games.jeanRobot.World.getPlayer().getX()-World.longueur/4)&&(this.x<games.jeanRobot.World.getPlayer().getX()))
			this.x=games.jeanRobot.World.getPlayer().getX()-World.longueur/4;
		else if((this.x<games.jeanRobot.World.getPlayer().getX()-World.longueur*3/4)&&(this.x>games.jeanRobot.World.getPlayer().getX()-World.longueur))
			this.x=games.jeanRobot.World.getPlayer().getX()-World.longueur*3/4;



		this.y=games.jeanRobot.World.getPlayer().getY()-World.hauteur/2;
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
