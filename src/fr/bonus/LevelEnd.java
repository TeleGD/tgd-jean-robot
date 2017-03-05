package fr.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.characters.Player;
import fr.game.World;
import fr.menus.FinishMenu;
import fr.util.Collisions;

public class LevelEnd extends Bonus {

	private boolean destructed;
	
	public LevelEnd(double x,double y,double width,double height)
	{
		this.x =x;
		this.y=y;
		this.width=width;
		this.height=height;
		destructed = false;
	}
	
	
	@Override
	public void comportment(Player player){
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(Collisions.intersect(this,World.getPlayer()) && !destructed)
		{
			game.enterState(FinishMenu.ID,new FadeOutTransition(),new FadeInTransition());
		}
		
	}
	
}
