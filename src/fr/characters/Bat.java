package fr.characters;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.enemies.Ennemy;

public class Bat extends ToppingDecorator implements Player{
//Classe utilisée lorsque le joueur a la batte

	public Bat(Player newPlayer) {
		super(newPlayer);
		tempPlayer.setImages("img/Player/herobotWALK/bat/marcheBatte");
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


	public void keyReleased(int key, char c)
	{
		super.keyReleased(key, c);
		if (key == Input.KEY_A)
		{
			attaque(100);
			//lance l'animation d'attaque
		}
	}

	/**
	 * Attaque l'ennemi au corps à corps avec la port&e suivante
	 */
	private void attaque(int portee)
	{
		ArrayList<Ennemy> listEnnemy = fr.game.World.getEnemies();
		switch (tempPlayer.getDirerction())
		{
			//Si le joueur regarde vers la droite
			case 1:
				for (Ennemy e : listEnnemy)
				{
					if ((e.getX() >= this.getX()) && (e.getX() <= this.getX() + portee))
					{
						e.looseLife();
						System.out.println("Batte touch& droite");
					}
				}
				break;

			//Si le joueur regarde vers la gauche
			case -1:
				for (Ennemy e: listEnnemy)
				{
					if ((e.getX() <= this.getX()) && (e.getX() >= this.getX() - portee))
					{
						e.looseLife();
						System.out.println("Batte touch& gauche");
					}
				}
				break;
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
	}

}
