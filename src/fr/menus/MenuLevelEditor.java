package fr.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.jerome.Editor;
import fr.util.LevelUtils;

public class MenuLevelEditor extends Menu {

	public static final int ID = 6;
	
	

	

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	    super.enter(container, game);
	    
	    super.setTitrePrincipal("EDITOR MENU");
	 	super.setTitreSecondaire("Quel niveau souhaitez-vous modifier ?");
	 	super.setEnableClignote(true);

	 	
	 	String[] niveaux=LevelUtils.getAllCreatedLevels();
	 	String[] choix=new String[niveaux.length+1];
	 	for(int i=0;i<niveaux.length;i++){
	 		choix[i]=niveaux[i];
	 	}
	 	choix[niveaux.length]="Menu Précédent";
	 	
	 	super.setItems(choix);
	}
	
	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public void onOptionItemSelected(int position) {
	   if(position<getItems().length-1) {
			Editor.reset();
			Editor.setLevel(LevelUtils.loadLevel(getItems()[position]));
			game.enterState(Editor.ID, new FadeOutTransition(),new FadeInTransition());
	   }else if(position==getItems().length-1){
		   game.enterState(MenuEditor.ID, new FadeOutTransition(),new FadeInTransition());	
	  }		
	}

	@Override
	public void onOptionItemFocusedChanged(int position) {
		
	}

	
}
