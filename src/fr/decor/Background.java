package fr.decor;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.game.Game;

public class Background {

	private float positionX;
	private float positionY;
	private Image texture;
	
	float getX()	{
		return positionX;
		
	}
	
	float getY()	{
		return positionY;
		
	}
	
	Image getImage(){
		return texture;
	}
	
	void setPosition(float newPositionX,float newPositionY)	{
		positionX = newPositionX;
		positionY = newPositionY;
	}

	public Background(float positionX, float positionY, Image tex){
		
		this.positionX = positionX;
		this.positionY = positionY;
		this.texture = tex;
	}

	

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(texture, -Game.longueur, 0);//(float)fr.game.World.getPlayer().getY()-Game.hauteur/2);
		
		/*for(int i = (int)((positionX/texture.getWidth())-2);i<(positionX/texture.getWidth())+1;i++){
			g.drawImage(texture, (texture.getWidth()*i), (float)positionY-texture.getHeight()/2);
		}*/
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	}
	


}
