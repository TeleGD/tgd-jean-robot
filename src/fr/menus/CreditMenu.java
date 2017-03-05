package fr.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.game.Game;

public class CreditMenu extends Menu{

	private String graphics = "Graphics:\n\nMartin Sertier\nJulien Michaux\nClémence Picquet";
	private String code= "Development:\n\nJulien Domptail\nAurélien Benoit\nArthur Canal\nJérôme Gauzins\nGuillaume Teset\n";
	private String head= "Project Manager;\n\nNicolas Bernardes";


	public static int ID = -24;

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
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString(head,Game.longueur/2-100, 100);
		g.drawString(code, Game.longueur/2-400, 400);
		g.drawString(graphics, Game.longueur/2+150, 430);
	}

	@Override
	public void keyPressed(int key, char c) {

		if(key == Input.KEY_ENTER)
			onOptionItemSelected(0);
		if(key== Input.KEY_ESCAPE)
			System.exit(0);
	}
}
