package fr.menus;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.jerome.Editor;

public class MenuLevelEditor extends Menu {

	public static final int ID = 6;
	
	public MenuLevelEditor(){
		super();
		super.setTitrePrincipal("EDITOR MENU");
		super.setTitreSecondaire("Quel niveau souhaitez-vous modifier ?");
		
		
		String[] niveaux=getAllLevelsCreated();
		String[] choix=new String[niveaux.length+1];
		for(int i=0;i<niveaux.length;i++){
			choix[i]=niveaux[i];
		}
		choix[niveaux.length]="Menu Précédent";
		super.setItems(choix);
		
		super.setEnableClignote(true);
	}
	

	private String[] getAllLevelsCreated() {
		ArrayList<String> niveaux=new ArrayList<String>();
		String[] fileName=new File("levels/").list();
		for(int i=0;i<fileName.length;i++)
		{
			if(!fileName[i].startsWith("."))niveaux.add(fileName[i]);
		}
		
		return niveaux.toArray(new String[niveaux.size()]);
	}


	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public void onOptionItemSelected(int position) {
	   if(position<getItems().length-1) {
			Editor.reset();
			Editor.loadLevel(getItems()[position]);
			game.enterState(Editor.ID, new FadeOutTransition(),new FadeInTransition());
	   }else if(position==getItems().length-1){
		   game.enterState(MenuEditor.ID, new FadeOutTransition(),new FadeInTransition());	
	  }		
	}

	@Override
	public void onOptionItemFocusedChanged(int position) {
		// TODO Auto-generated method stub
		
	}

	
}
