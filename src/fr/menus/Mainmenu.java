package fr.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.bonus.Bonus;
import fr.game.World;
import fr.projectiles.Projectile;

public class Mainmenu extends Menu {

	public static int ID = 2;

	public Mainmenu(){

		super.setTitrePrincipal("TELE-ARCADE DESIGN");
		super.setTitreSecondaire("Menu Principal");
		super.setItems("Jouer","Editeur","Crédits","Quitter");
		super.setEnableClignote(true);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);

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
			game.enterState(CreditMenu.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 3:
			game.enterState(MenuSortie.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;

		}
	}


	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
