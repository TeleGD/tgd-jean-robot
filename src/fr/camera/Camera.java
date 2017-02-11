package fr.camera;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;

public class Camera extends Movable{
	
	public Camera(){
		super(fr.game.World.getPlayer().getX(),fr.game.World.getPlayer().getY(),0,0);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.translate((float)container.getWidth() / 2 - (float)this.x, 
	            container.getHeight() / 2 - (float)this.y);		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.x=fr.game.World.getPlayer().getSpeedX();
		this.y=fr.game.World.getPlayer().getSpeedY();
	}
}
