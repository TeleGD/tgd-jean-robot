package games.jeanRobot.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;

public class FinishMenu extends Menu{

	private int ID;

	private static final String CONFIRM_TEXT="PRESS ENTER";
	private String finish= "Congratulations you finished it !!!";

	public FinishMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void onOptionItemFocusedChanged(int position){
		time=System.currentTimeMillis();
	}

	@Override
	public void onOptionItemSelected(int position) {
			game.enterState(2 /* MainMenu */, new FadeOutTransition(),
					new FadeInTransition());
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		g.drawImage(AppLoader.loadPicture("/images/jeanRobot/congratulations.png"), 0, 0);
	}

	@Override
	public void keyPressed(int key, char c) {

		if(key == Input.KEY_ENTER)
			onOptionItemSelected(0);
		if(key== Input.KEY_ESCAPE)
			System.exit(0);
	}

}
