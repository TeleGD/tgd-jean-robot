package games.jeanRobot.menus;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;

import games.jeanRobot.Editor;

public class MenuLevelEditor extends Menu {

	private int ID;

	public MenuLevelEditor(int ID) {
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
		this.ID = ID;
	}

	private String[] getAllLevelsCreated() {
		String levels = AppLoader.restoreData("/jeanRobot/levels.txt");
		BufferedReader reader = new BufferedReader(new StringReader(levels));
		List<String> items = new ArrayList<String>();
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				items.add(line);
			}
			reader.close();
		} catch (Exception error) {}
		return items.toArray(new String[0]);
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
			game.enterState(1 /* Editor */, new FadeOutTransition(),new FadeInTransition());
	   }else if(position==getItems().length-1){
		   game.enterState(5 /* MenuEditor */, new FadeOutTransition(),new FadeInTransition());
	  }
	}

	@Override
	public void onOptionItemFocusedChanged(int position) {
		// TODO Auto-generated method stub

	}


}
