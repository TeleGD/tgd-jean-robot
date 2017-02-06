package fr.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.BasicPlayer;

public class InverseControl extends Bonus{

	@Override
	/**
	 * multiplie par -1 la vitesse celon l'axe x
	 */
	public void comportment(BasicPlayer player) {
		player.setSpeedX(-player.getSpeedX());
		while (this.tempsActivation+this.duration>System.currentTimeMillis()){
			//la boucle permet d'attendre la durée duration
		}
		player.setSpeedX(-player.getSpeedX());
		//TODO créer une varriable inverseControl dans le player + une autre fonction de récupération des inputs + un if dans la fonction update
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
