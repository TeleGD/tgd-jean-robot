package fr.menus;

import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.game.World;
import fr.jerome.Editor;

public class MenuEditor extends Menu {

	public static final int ID = 5;
	
	public MenuEditor(){
		super();
		super.setTitrePrincipal("EDITOR MENU");
		super.setTitreSecondaire("Que voulez-vous faire ?");
		super.setItems("Nouveau niveau","Modifier un niveau", "Menu précédent");
		
		super.setEnableClignote(true);
	}
	

	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public void onOptionItemSelected(int i) {
		switch (i) {
			case 0:
				Editor.reset();
				game.enterState(Editor.ID, new FadeOutTransition(),new FadeInTransition());
				break;
			case 1:
				Editor.reset();
				game.enterState(MenuLevelEditor.ID, new FadeOutTransition(),new FadeInTransition());
				break;
			case 2:
				game.enterState(Mainmenu.ID, new FadeOutTransition(),new FadeInTransition());
				break;
		}		
	}

	@Override
	public void onOptionItemFocusedChanged(int position) {
		// TODO Auto-generated method stub
		
	}

}
