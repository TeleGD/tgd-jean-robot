package games.jeanRobot.characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Gun extends ToppingDecorator implements Player{
//La classe qui va être utilisée tant que le joueur a le pistolet

	public Gun(Player newPlayer) {
		super(newPlayer);
		tempPlayer.setImages("/images/jeanRobot/Player/herobotWALK/gun/marcheGun");
	}

	@Override
	public int getType() {
		return this.tempPlayer.getType()+1;
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
			super.render(container, game, g);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		super.update(container, game, delta);
	}

	public void keyReleased(int key, char c) {
		super.keyReleased(key, c);
	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
	}

}
