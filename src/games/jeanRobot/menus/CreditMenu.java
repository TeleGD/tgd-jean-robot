package games.jeanRobot.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import games.jeanRobot.World;

public class CreditMenu extends Menu{

	private int ID;

	private String graphics = "Graphics:\n\nMartin Sertier\nJulien Michaux\nClémence Picquet";
	private String code= "Development:\n\nJulien Domptail\nAurélien Benoit\nArthur Canal\nJérôme Gauzin\nGuillaume Teset\n";
	private String head= "Project Manager;\n\nNicolas Bernardes";

	public CreditMenu(int ID) {
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
		game.enterState(2 /* MainMenu */, new FadeOutTransition(),new FadeInTransition());
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		g.setColor(Color.white);
		g.drawString(head,World.longueur/2-100, 100);
		g.drawString(code, World.longueur/2-400, 400);
		g.drawString(graphics, World.longueur/2+150, 430);
	}

	@Override
	public void keyPressed(int key, char c) {

		if(key == Input.KEY_ENTER)
			onOptionItemSelected(0);
		if(key== Input.KEY_ESCAPE)
			System.exit(0);
	}
}
