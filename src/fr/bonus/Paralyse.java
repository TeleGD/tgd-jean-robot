package fr.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.BasicPlayer;
import fr.characters.Player;

public class Paralyse extends Bonus{

	@Override
	/**
	 * met la vitesse du joueur à 0 en x et en y pendant la durée duration
	 */
	public void comportment(Player player) {
		BasicPlayer basicPlayer = (BasicPlayer) player;
		double speed = basicPlayer.getSpeedX();
		basicPlayer.setSpeedX(0);
		while (this.tempsActivation+this.duration>System.currentTimeMillis()){
			//la boucle permet d'attendre la durée duration
		}
		basicPlayer.setSpeedX(speed);
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub

	}

}
