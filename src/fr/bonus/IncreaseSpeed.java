package fr.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;

public class IncreaseSpeed extends Bonus{

	@Override
	/**
	 * augmente la vitesse sur l'axe des x
	 */
	public void comportment(Player player) {
		this.tempsActivation = System.currentTimeMillis();
		player.setSpeedX(player.getSpeedX()*2);
		// TODO verriefier si le wait est juste pour la fonction ou pour tout le jeu
		while (this.tempsActivation+this.duration>System.currentTimeMillis()){
			//la boucle permet d'attendre la durée duration
		}
		player.setSpeedX(player.getSpeedX()/2);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	
}
