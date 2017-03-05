package fr.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MenuFinPartie extends Menu {
	
	public static int ID=4;
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	    super.enter(container, game);
		
	    super.setTitreSecondaire("Ton score n'est que de "+fr.game.World.getScore());
		super.setTitrePrincipal("GAME OVER");
	}

	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_ENTER:
			game.enterState(Mainmenu.ID, new FadeOutTransition(),new FadeInTransition());
			break;

		case Input.KEY_ESCAPE:
			this.container.exit();
			break;
		}
	}

	@Override
	public void onOptionItemFocusedChanged(int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOptionItemSelected(int position) {
		// TODO Auto-generated method stub
		
	}

}
