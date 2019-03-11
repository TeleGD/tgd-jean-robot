package fr.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Bat;
import fr.characters.Player;
import fr.util.Collisions;

public class BatBonus extends Bonus {

	private boolean destructed;

	public BatBonus(double x,double y,double width,double height,Player player)
	{
		this.x =x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.player=player;
		this.destructed=false;
	}

	@Override
	public void comportment(Player player) {
		fr.game.World.setPlayer(new Bat(player));
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		if(Collisions.intersect(this,player) && !destructed)
		{
			this.comportment(player);
			this.destructed=true;
		}

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		if(!destructed)
		{
			g.setColor(Color.orange);
			g.fillRect((float) x,(float) y, (float) width,(float) height);
		}
	}

	public void destruct(){
		this.destructed = true;
	}

	public boolean isDestructed(){
		return destructed;
	}

}
