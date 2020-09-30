package games.jeanRobot.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.jeanRobot.characters.BasicPlayer;
import games.jeanRobot.characters.Player;

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
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		// TODO Auto-generated method stub

	}

}
