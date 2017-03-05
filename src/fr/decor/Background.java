package fr.decor;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.game.Game;
import fr.util.Movable;

public class Background extends Movable {
	private Image texture;
	
	public Background(Image texture) {
		super(0,0,Game.longueur,Game.hauteur);
		
		//remise a l'échelle de la texture
		double ratio=(double)texture.getWidth()/(double)texture.getHeight();
		this.texture=texture.getScaledCopy( (int)(ratio*Game.hauteur),Game.hauteur);
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(texture,0,(float)y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		moveX(delta);
		moveY(delta);
	}
	

}
