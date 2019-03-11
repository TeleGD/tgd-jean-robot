package fr.game;


import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.menus.CreditMenu;
import fr.menus.FinishMenu;
import fr.menus.Mainmenu;
import fr.menus.MenuEditor;
import fr.menus.MenuFinPartie;
import fr.menus.MenuLevelEditor;
import fr.menus.MenuSortie;
import fr.menus.WelcomeMenu;
import fr.game.World;
import fr.jerome.Editor;


public class Game extends StateBasedGame {

	public static final int longueur=1280;
	public static final int hauteur=720;
	public static final int DENSITE_X = 32;
	public static final int DENSITE_Y = 32;

	public static void main(String[] args) throws SlickException {
		//System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
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
		long time=System.currentTimeMillis();
		addState(new WelcomeMenu());
		//System.out.println("time MenuMain="+(System.currentTimeMillis()-time));
		addState(new Mainmenu());
		//System.out.println("time MenuMain="+(System.currentTimeMillis()-time));
		addState(new World());
		//System.out.println("time World="+(System.currentTimeMillis()-time));
		addState(new MenuSortie());
		//System.out.println("time MenuSortie="+(System.currentTimeMillis()-time));
		addState(new MenuFinPartie());
		//System.out.println("time MenuFinPartie="+(System.currentTimeMillis()-time));
		addState(new MenuEditor());
		//System.out.println("time MenuEditor="+(System.currentTimeMillis()-time));
		addState(new MenuLevelEditor());
		//System.out.println("time MenuLevelEditor="+(System.currentTimeMillis()-time));
		addState(new Editor());
		//System.out.println("time Editor="+(System.currentTimeMillis()-time));
		addState(new CreditMenu());
		addState(new FinishMenu());


	    this.enterState(WelcomeMenu.ID);
	}


}
