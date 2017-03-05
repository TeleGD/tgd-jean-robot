package fr.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class FinishMenu extends Menu{

	public static int ID = -23;	
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	     super.enter(container, game);
	     super.setBackgroundImage(new Image("img/congratulations.png"));
	}

	
	@Override
	public void onOptionItemFocusedChanged(int position){
		time=System.currentTimeMillis();
	}
	
	@Override
	public void onOptionItemSelected(int position) {
		game.enterState(Mainmenu.ID, new FadeOutTransition(),new FadeInTransition());
	}
	
	@Override
	public int getID() {
		return ID;
	}
	
	
	@Override
	public void keyPressed(int key, char c) {

		if(key == Input.KEY_ENTER)
			onOptionItemSelected(0);
		if(key== Input.KEY_ESCAPE)
			System.exit(0);
	}

}
