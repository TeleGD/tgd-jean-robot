package games.jeanRobot.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.jeanRobot.characters.BasicPlayer;
import games.jeanRobot.characters.Player;

public class IncreaseJump extends Bonus{

	@Override
	/**
	 * augmente le vitesse sur l'axe des y
	 */
	public void comportment(Player player) {
		BasicPlayer basicPlayer = (BasicPlayer) player;
		this.tempsActivation = System.currentTimeMillis();
		basicPlayer.jumppower = basicPlayer.jumppower*2;
		while (this.tempsActivation+this.duration>System.currentTimeMillis()){
			//la boucle permet d'attendre la durée duration
		}
		basicPlayer.jumppower = basicPlayer.jumppower/2;
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
