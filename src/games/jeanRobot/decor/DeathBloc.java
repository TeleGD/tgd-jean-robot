package games.jeanRobot.decor;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.jeanRobot.characters.Player;
import games.jeanRobot.World;

public class DeathBloc extends Plateform {

	public DeathBloc(int indexX, int indexY, int sizeX, int sizeY) {
		super(indexX, indexY, sizeX, sizeY);
	}

	//test
	//construteur appele pour charger de niveau.
	public DeathBloc(String ligne)
	{
		super(ligne);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.red);
		g.fillRect((float) x, (float) y, (float) width, (float) height);
	}

	@Override
	public String parseString() {
		return "DeathBloc "+getX()+ ";"+ getY()+";"+getWidth()+";"+getHeight();
	}

	@Override
	public void collPlayer(StateBasedGame game, Player player){

		if(games.jeanRobot.util.Collisions.colPlayerPlateform(player,this)!=0){
			player.setY(this.getY() - player.getHeight());
			player.setAccY(0);
			player.setSpeedY(0);
			player.setInCol(true);
			player.setposJump(true);
			player.lifelost(game);
		}
	}

	@Override
	public Plateform copy() {
		DeathBloc p=new DeathBloc((int)x/World.DENSITE_X,(int)y/World.DENSITE_Y,(int)x/World.DENSITE_X,(int) (y/World.DENSITE_Y));
		p.height=this.height;
		p.width=this.width;
		return p;
	}

}
