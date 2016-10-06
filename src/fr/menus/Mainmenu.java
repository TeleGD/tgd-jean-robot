package fr.menus;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.main.World;
import fr.testjeje.Editor;

public class Mainmenu extends BasicGameState {

	public static int ID = 2;

	static TrueTypeFont font1;
	
	public static final int temps_clignote=400;
	public static final Color couleur_clignote=Color.red;
	
	private int selection=0;
	private String nom = "Menu Principal - TELE ARCADE DESIGN";
	private String[] items = { "Jouer","Editeur", "Quitter" };

	public int nbrOption = items.length;

	private long time;
	GameContainer container;
	StateBasedGame game;

	public String[] getItems() {
		return this.items;
	}
	
	public Mainmenu(){
		Font titre1Font = new Font("Kalinga", Font.BOLD, 12);
		font1 = new TrueTypeFont(titre1Font, false);
		time=System.currentTimeMillis();
	}
	
	public void init(GameContainer container, StateBasedGame game)throws SlickException {
		this.container = container;
		container.setShowFPS(true);
		this.game = game;


	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString(this.nom, 550, 320);
		

		for (int i = 0; i < nbrOption; i++) {
			g.setFont(font1);
			g.drawString(this.items[i], 560, 360 + 30 * i);
		}

		if((System.currentTimeMillis()-time)%(2*temps_clignote)<=temps_clignote)g.setColor(Color.white);
		else g.setColor(couleur_clignote);
		
		g.drawString(">> ", 535, 360 + 30 * selection);
		if(font1!=null)g.drawString(" <<", 565+font1.getWidth(items[selection]), 360 + 30 * selection);
		
		
		if((System.currentTimeMillis()-time)>10*temps_clignote){
			
			g.drawString("#### PRESS ENTER ####", 660, 360 + 30 * selection);
		}
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		time=System.currentTimeMillis();
		switch (key) {
		case Input.KEY_DOWN:
			if (selection < nbrOption - 1)
				selection++;
			else
				selection = 0;
			break;
		case Input.KEY_UP:
			if (selection > 0)
				selection--;
			else
				selection = nbrOption - 1;
			break;
		case Input.KEY_ENTER:
			execOption();
			break;

		case Input.KEY_ESCAPE:
			this.container.exit();
			break;
		/*case Input.KEY_C:
			game.enterState(CreditsMenu.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case Input.KEY_M:
			game.enterState(MissionMenu.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;*/
		}
	}

	public void execOption() {
		switch (selection) {
		case 0:
			World.reset();
			game.enterState(World.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 1:
			game.enterState(Editor.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 2:
			game.enterState(MenuSortie.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		
		 
		}
	}

}
