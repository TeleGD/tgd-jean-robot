package fr.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MenuFinPartie extends BasicGameState {
	
	public static int ID=4;
	GameContainer container;
	StateBasedGame game;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		container=arg0;
		game=arg1;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		
		arg2.drawString("Tu as perdu, ton score n'est que de ", 500, 300);
		//arg2.drawString(""+fr.game.World.getScore(),600,400);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		
	}

	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_ENTER:
			game.enterState(Mainmenu.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;

		case Input.KEY_ESCAPE:
			this.container.exit();
			break;
		}
	}

}
