package fr.characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Bat extends ToppingDecorator implements Player{
//Classe utilisée lorsque le joueur a la batte
	
	public Bat(Player newPlayer) {
		super(newPlayer);
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return this.tempPlayer.getType()+1;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
			super.render(container, game, g);
			
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
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
