package games.jeanRobot.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MenuFinPartie extends Menu {

	private int ID;

	public MenuFinPartie(int ID) {
		super.setTitrePrincipal("GAME OVER");
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		System.out.println("score mis a jour");
		this.game=game;
		super.setTitreSecondaire("Ton score n'est que de "+games.jeanRobot.World.getScore());
	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_ENTER:
			game.enterState(2 /* MainMenu */, new FadeOutTransition(),new FadeInTransition());
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
