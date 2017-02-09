package fr.game;


import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.menus.Mainmenu;
import fr.menus.MenuEditor;
import fr.menus.MenuFinPartie;
//import fr.menus.MenuLevelEditor;
import fr.menus.MenuSortie;
import fr.game.World;
import fr.jerome.Editor;


public class Game extends StateBasedGame {

	public static final int longueur=1280;
	public static final int hauteur=720;
	public static final int DENSITE_X = 32;
	public static final int DENSITE_Y = 32;
	
	public static void main(String[] args) throws SlickException {
		System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
		AppGameContainer app = new AppGameContainer(new Game(),longueur,hauteur,false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(true);
		app.start();
	}

	



	public Game() {
		super("blabla");
		
		
	}

		

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new Mainmenu());
		addState(new World());
		addState(new MenuSortie());
		addState(new MenuFinPartie());
		addState(new MenuEditor());
		//addState(new MenuLevelEditor());
		addState(new Editor());
	    
	    
	    this.enterState(Mainmenu.ID);
	}


}
