package games.jeanRobot.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import games.jeanRobot.World;

public class Mainmenu extends Menu {

	private int ID;

	public Mainmenu(int ID) {
		super.setTitrePrincipal("TELE-ARCADE DESIGN");
		super.setTitreSecondaire("Menu Principal");
		super.setItems("Jouer","Editeur","Crédits","Quitter");
		super.setEnableClignote(true);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
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
		switch (position) {
		case 0:
			World.reset();
			game.enterState(0 /* World */, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 1:
			game.enterState(5 /* MenuEditor */, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 2:
			game.enterState(-24 /* CreditMenu */, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 3:
			game.enterState(3 /* MenuSortie */, new FadeOutTransition(),
					new FadeInTransition());
			break;

		}
	}

}
