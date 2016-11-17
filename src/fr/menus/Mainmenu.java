package fr.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.game.World;

public class Mainmenu extends Menu {

	public static int ID = 2;	
	private String pressEnterString="PRESS ENTER";

	public Mainmenu(){
		
		super.setTitrePrincipal("TELE-ARCADE DESIGN");
		super.setTitreSecondaire("Menu Principal");
		super.setItems("Jouer","Editeur", "Quitter");
		
		super.setEnableClignote(true);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
		
	}

	@Override
	public void renderSelectionItem(GameContainer arg0, StateBasedGame arg1, Graphics g,int selection) {
		super.renderSelectionItem(arg0, arg1, g,selection);
		
		if((System.currentTimeMillis()-time)>10*tempsClignote){
			g.drawString(pressEnterString, 660, 360 + 30 * selection);
		}
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		if((System.currentTimeMillis()-time)>55*tempsClignote){
			pressEnterString="#### PRESS ENTER FOR MORE RED AND WHITE :D ####";
			tempsClignote=200;
		}else if((System.currentTimeMillis()-time)>45*tempsClignote){
			pressEnterString="#### PRESS ENTER FOR MORE ... ####";
		}else if((System.currentTimeMillis()-time)>35*tempsClignote){
			pressEnterString="#### PRESS ENTER FOR MORE ####";
		}
		else  if((System.currentTimeMillis()-time)>25*tempsClignote){
			pressEnterString="#### PRESS ENTER FOR ####";
		}
		else if((System.currentTimeMillis()-time)>10*tempsClignote && System.currentTimeMillis()-time<14*tempsClignote){
			pressEnterString="#### PRESS ENTER ####";
			int nbLettre=(int) (pressEnterString.length()*(System.currentTimeMillis()-time-10*tempsClignote)/(4*tempsClignote));
			pressEnterString=pressEnterString.substring(0, nbLettre);
		}else {
			tempsClignote=400;
			pressEnterString="#### PRESS ENTER ####";
		}
		
	}

	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public void onOptionItemFocusedChanged(int position){
		time=System.currentTimeMillis();
	}
	@Override
	public void onOptionItemSelected(int position) {
		switch (position) {
		case 0:
			World.reset();
			game.enterState(World.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 1:
			game.enterState(MenuEditor.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 2:
			game.enterState(MenuSortie.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		 
		}
	}

}
