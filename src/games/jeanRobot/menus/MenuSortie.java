package games.jeanRobot.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import app.AppFont;
import app.AppLoader;

import games.jeanRobot.World;

public class MenuSortie extends BasicGameState{

	private int ID;
	private TrueTypeFont font;
	private static final String textOut="Thank you and see you later !";

	public MenuSortie(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {

		font=AppLoader.loadFont("/fonts/press-start-2p.ttf", AppFont.PLAIN, 15);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		//g.drawImage(image, World.longueur/2-image.getWidth()/2, World.hauteu );
		g.setFont(font);
		g.drawString(textOut, World.longueur/2-font.getWidth(textOut)/2,World.hauteur/2+40);

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
		Input input = arg0.getInput();
		if (input.isKeyDown(Input.KEY_ENTER)) {
			arg0.exit();
		}
	}

}
