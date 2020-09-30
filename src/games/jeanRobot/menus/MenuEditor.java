package games.jeanRobot.menus;

import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import games.jeanRobot.Editor;

public class MenuEditor extends Menu {

	private int ID;

	public MenuEditor(int ID) {
		super.setTitrePrincipal("EDITOR MENU");
		super.setTitreSecondaire("Que voulez-vous faire ?");
		super.setItems("Nouveau niveau","Modifier un niveau", "Menu précédent");

		super.setEnableClignote(true);
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void onOptionItemSelected(int i) {
		switch (i) {
			case 0:
				Editor.reset();
				game.enterState(1 /* Editor */, new FadeOutTransition(),new FadeInTransition());
				break;
			case 1:
				Editor.reset();
				game.enterState(6 /* MenuLevelEditor */, new FadeOutTransition(),new FadeInTransition());
				break;
			case 2:
				game.enterState(2 /* MainMenu */, new FadeOutTransition(),new FadeInTransition());
				break;
		}
	}

	@Override
	public void onOptionItemFocusedChanged(int position) {
		// TODO Auto-generated method stub

	}

}
