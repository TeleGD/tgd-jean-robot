package fr.menus;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.game.Game;
import fr.util.FontUtils;

public class MenuSortie extends BasicGameState{

	
	GameContainer container;
	StateBasedGame game;
	//private Image image;
	public static int ID = 3;
	private TrueTypeFont font;
	private static final String textOut="Thank you and see you later !";
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.container = container;
		this.game = game;

		//image =new Image("bin/Images/imagefin.png");  
		font=FontUtils.chargerFont("font/PressStart2P.ttf", Font.PLAIN, 15, false);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		//g.drawImage(image, Game.longueur/2-image.getWidth()/2, Game.hauteur/2-image.getHeight()/2);
		g.setFont(font);
		g.drawString(textOut, Game.longueur/2-font.getWidth(textOut)/2,Game.hauteur/2+40);

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
			container.exit();
			break;
		}
	}
	
}
