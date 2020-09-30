package games.jeanRobot.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;

import games.jeanRobot.World;

public class WelcomeMenu extends Menu{

	private int ID;

	private static final String CONFIRM_TEXT="PRESS ENTER";

	public WelcomeMenu(int ID) {
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
		g.drawImage(AppLoader.loadPicture("/images/jeanRobot/accueil.png"), 0, 0);
		g.setColor(Color.black);

		g.drawRect(World.longueur/2-300,25, 600,37);


		g.setFont(fontConfirmText);
		g.drawString(CONFIRM_TEXT, World.longueur/2-fontConfirmText.getWidth(CONFIRM_TEXT)/2,35);

	}

	@Override
	public void keyPressed(int key, char c) {

		if(key == Input.KEY_ENTER)
			onOptionItemSelected(0);
		if(key== Input.KEY_ESCAPE)
			System.exit(0);
	}

}
