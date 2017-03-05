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

public class WelcomeMenu extends Menu{

	public static int ID = -25;	
	
	private static final String CONFIRM_TEXT="PRESS ENTER";
	private boolean PRESS_ENTERED;
	
	public WelcomeMenu() throws SlickException{
		super.setBackgroundImage(new Image("img/accueil.png"));
	}
	
	@Override
	public void onOptionItemFocusedChanged(int position){}
	
	@Override
	public void onOptionItemSelected(int position) {
		game.enterState(Mainmenu.ID, new FadeOutTransition(),new FadeInTransition());
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	     super.enter(container, game);
	 }
	
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.setColor(Color.black);
		g.drawRect(Game.longueur/2-300,25, 600,37);
		
		if(PRESS_ENTERED)
			g.setColor(Color.red);

		g.setFont(fontConfirmText);
		g.drawString(CONFIRM_TEXT, Game.longueur/2-fontConfirmText.getWidth(CONFIRM_TEXT)/2,35);

	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(key == Input.KEY_ENTER)
			PRESS_ENTERED=true;

		if(key== Input.KEY_ESCAPE)
			System.exit(0);
	}
	
	@Override
	public void keyReleased(int key, char c){
		if(key == Input.KEY_ENTER){
			PRESS_ENTERED=false;
			this.onOptionItemSelected(0);
		}
	}
	
	@Override
	public int getID(){
		return ID;
	}

	
}
