package games.jeanRobot.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.jeanRobot.characters.BasicPlayer;
import games.jeanRobot.characters.Player;

public class InverseControl extends Bonus{

	@Override
	/**
	 * multiplie par -1 la vitesse celon l'axe x
	 */
	public void comportment(Player player) {
		BasicPlayer basicPlayer = (BasicPlayer) player;
		basicPlayer.setSpeedX(-basicPlayer.getSpeedX());
		while (this.tempsActivation+this.duration>System.currentTimeMillis()){
			//la boucle permet d'attendre la durée duration
		}
		basicPlayer.setSpeedX(-basicPlayer.getSpeedX());
		//TODO créer une varriable inverseControl dans le player + une autre fonction de récupération des inputs + un if dans la fonction update
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
