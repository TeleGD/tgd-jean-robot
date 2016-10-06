package fr.main;


import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.menus.Mainmenu;
import fr.menus.MenuFinPartie;
import fr.menus.MenuSortie;
import fr.testjeje.Editor;
import fr.main.World;


public class Main extends StateBasedGame {

	public static final int longueur=1280;
	public static final int hauteur=720;
	
	public static void main(String[] args) throws SlickException {
		
		System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
		AppGameContainer app = new AppGameContainer(new Main(),longueur,hauteur,false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(true);
		app.start();
	}

	



	public Main() {
		super("blabla");
		
		
	}

		

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new Mainmenu());
		addState(new World());
		addState(new MenuSortie());
		addState(new MenuFinPartie());
		addState(new Editor());
	    
	    
	    this.enterState(Mainmenu.ID);
	}


}
