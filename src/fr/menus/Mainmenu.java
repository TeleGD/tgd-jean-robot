package fr.menus;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import fr.game.Game;
import fr.game.World;
import fr.jerome.Editor;

public class Mainmenu extends BasicGameState {

	public static int ID = 2;

	static TrueTypeFont font1;
	
	public static  int temps_clignote=400;
	public static final Color couleur_clignote=Color.red;
	
	private int selection=0;
	private String titre = "TELE-ARCADE DESIGN";
	private String nom = "Menu Principal";
	private String pressEnterString="PRESS ENTER";
	private String[] items = { "Jouer","Editeur", "Quitter" };

	public int nbrOption = items.length;

	private long time;
	GameContainer container;
	StateBasedGame game;

	private TrueTypeFont fontTitre,fontTitreBold;

	public String[] getItems() {
		return this.items;
	}
	
	public Mainmenu(){
		Font titre1Font = new Font("Kalinga", Font.BOLD, 14);
		font1 = new TrueTypeFont(titre1Font, false);
		time=System.currentTimeMillis();
		
		Font titreFont;
		try {
			titreFont =Font.createFont(java.awt.Font.TRUETYPE_FONT,
					       ResourceLoader.getResourceAsStream("font/PressStart2P.ttf"));
			
			fontTitre = new TrueTypeFont(titreFont.deriveFont(java.awt.Font.PLAIN, 40.f),false);
			fontTitreBold = new TrueTypeFont(titreFont.deriveFont(java.awt.Font.PLAIN, 40.f),false);

		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}
	
	public void init(GameContainer container, StateBasedGame game)throws SlickException {
		this.container = container;
		container.setShowFPS(true);
		this.game = game;


	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		

		g.setColor(Color.red);
		g.setFont(fontTitreBold);
		g.drawString(this.titre,(Game.longueur-fontTitreBold.getWidth(titre))/2 , 120);
		g.setColor(Color.white);
		g.setFont(fontTitre);
		g.drawString(this.titre,(Game.longueur-fontTitre.getWidth(titre))/2+4 , 122);

		g.setFont(font1);
		g.drawString(this.nom, 550, 320);
		

		for (int i = 0; i < nbrOption; i++) {
			g.setFont(font1);
			g.drawString(this.items[i], 560, 360 + 30 * i);
		}

		if((System.currentTimeMillis()-time)%(2*temps_clignote)<=temps_clignote)g.setColor(Color.white);
		else g.setColor(couleur_clignote);
		
		g.drawString(">>", 535, 360 + 30 * selection);
		g.drawString("<<", 563+font1.getWidth(items[selection]), 360 + 30 * selection);
		
		
		if((System.currentTimeMillis()-time)>10*temps_clignote){
			
			g.drawString(pressEnterString, 660, 360 + 30 * selection);
		}
		
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		if((System.currentTimeMillis()-time)>55*temps_clignote){
			pressEnterString="#### PRESS ENTER FOR MORE RED AND WHITE :D ####";
			temps_clignote=200;
		}else if((System.currentTimeMillis()-time)>45*temps_clignote){
			pressEnterString="#### PRESS ENTER FOR MORE ... ####";
		}else if((System.currentTimeMillis()-time)>35*temps_clignote){
			pressEnterString="#### PRESS ENTER FOR MORE ####";
		}
		else  if((System.currentTimeMillis()-time)>25*temps_clignote){
			pressEnterString="#### PRESS ENTER FOR ####";
		}
		else if((System.currentTimeMillis()-time)>10*temps_clignote && System.currentTimeMillis()-time<14*temps_clignote){
			pressEnterString="#### PRESS ENTER ####";
			int nbLettre=(int) (pressEnterString.length()*(System.currentTimeMillis()-time-10*temps_clignote)/(4*temps_clignote));
			pressEnterString=pressEnterString.substring(0, nbLettre);
		}else {
			temps_clignote=400;
			pressEnterString="#### PRESS ENTER ####";
		}
		
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
			Editor.reset();
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
