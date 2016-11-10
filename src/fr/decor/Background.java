package fr.decor;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Background extends BasicGameState {

	private float positionX;
	private float positionY;
	private Image texture;
	
	float getX()
	{
		return positionX;
		
	}
	
	float getY()
	{
		return positionY;
		
	}
	
	Image getImage(){
		return texture;
	}
	
	void setPosition(float newPositionX,float newPositionY)
	{
		positionX = newPositionX;
		positionY = newPositionY;
	}

	public Background(float positionX, float positionY, Image tex){
		
		this.positionX = positionX;
		this.positionY = positionY;
		this.texture = tex;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		//TODO
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//TODO
		/*int idImage=(int)(positionX/1.2f/800);
		for (int i=idImage-2;i<idImage+2;i++)
			g.drawImage(texture, (float)positionX/1.2f+i*800, (float)positionY-300);*/
		
		for(int i = (int)((positionX/texture.getWidth())-2);i<(positionX/texture.getWidth())+1;i++){
			g.drawImage(texture, (texture.getWidth()*i), (float)positionY-texture.getHeight()/2);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		//TODO
	}
	
	public void keyReleased(int key, char c) {
		//TODO
	}


	public void keyPressed(int key, char c) {
		//TODO
	}

	@Override
	public int getID() {
		return 0;
	}

}
