package games.jeanRobot.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import games.jeanRobot.characters.Player;
import games.jeanRobot.util.Collisions;

public class LevelEnd extends Bonus {

	private boolean destructed;

	public LevelEnd(double x,double y,double width,double height,Player player)
	{
		this.x =x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.player=player;
		destructed = false;
	}


	@Override
	public void comportment(Player player){
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		if(Collisions.intersect(this,player) && !destructed)
		{
			game.enterState(-23 /* FinishMenu */,new FadeOutTransition(),new FadeInTransition());
		}

	}

}
