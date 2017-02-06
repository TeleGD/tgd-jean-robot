package fr.decor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;//ajouté

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;
import java.util.Random;

import fr.util.Rectangle;

public class Decor extends BasicGameState {
	
	public enum Direction {HAUT,DROITE,BAS,GAUCHE};
	
	private ArrayList<Plateform> plateforms = null;
	private int characterPosX;
	private int characterPosY;
	private Direction cameraDirection;
	private boolean cameraMove;
	private Image plateformTexture;
	private Background background;
	private static Plateform plateform;
	
	private int negativeLimit;
	private int positiveLimit;
	
	
	
	public Decor(String plateformTexturePath, String backgroundTexturePath) throws SlickException
	{
		plateforms = new ArrayList<Plateform>();	//ensemble des plateformes crées
		characterPosX=200;	//variable servant pour la caméra
		characterPosY=0;	//variable servant pour la caméra
		cameraDirection = Direction.HAUT;	//Direction du défilement de caméra
		cameraMove = false;		// caméra en train de bouger dans cameraDirection
		plateformTexture = new Image(plateformTexturePath);	// chargement image pour plateforme
		background = new Background(characterPosX, characterPosY, new Image(backgroundTexturePath));
		
	}
	
	public void createPlateform(int posX, int posY, int sizeX, int sizeY)
	{
		//ajout d'une plateforme
		plateforms.add(new Plateform(sizeX, sizeY , posX , posY));
	}
	
	public ArrayList<Plateform> getObstacles()
	{
		return plateforms;
	}
	
	public void updateCharacterPosition(int posX, int posY)
	{
		characterPosX = posX;
		characterPosY = posY;
	}
	
	/*public void generatePlateform(float charPosX)
	{
		Random rand = new Random();
		Vector<Integer> positions = new Vector<Integer>();
		Vector<Integer> sizes = new Vector<Integer>();
		
		for (int i = 0; i < 10; ++i)
		{
			boolean ok = false;
			
			while (!ok)
			{
				int posX = rand.nextInt(25) * 32 + (int)charPosX;
				int posY = rand.nextInt(16) * 32;
				int sizeX = rand.nextInt(8) + 3;
				int sizeY = rand.nextInt(2) + 1;
				
				for (int j = 0; j < positions.size()/2; ++j)
				{
					if (Math.abs(posX - positions.get(j * 2)) < (sizeX + sizes.get(j * 2))*32 / 2 &&
							Math.abs(posY - positions.get(j * 2 + 1)) < (sizeY + sizes.get(j * 2 + 1))*32 / 2)
					{
						ok = true;
						break;
					}
				}
				
				if (!ok)
				{
					positions.add(posX);
					positions.add(posY);
					sizes.add(sizeX);
					sizes.add(sizeY);
					createPlateform(posX, posY , sizeX, sizeY);
					ok = true;
				}
			}
		}
	}*/
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		//TODO
		
		negativeLimit = -1;
		positiveLimit = 1;
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//TODO
		//translation de la caméra (fixé sur le personnage)
		g.translate(container.getWidth() / 2 - characterPosX, container.getHeight() / 2 - characterPosY);
		
		//rendu du fond d'écran
		background.render(container, game, g);
		
		//rendu des plateformes
		for(int i=0;i<plateforms.size();i++)
			plateforms.get(i).render(container, game, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		//TODO
		float speed = 1f;
		/*if (cameraMove)
		{
			//modif de la position de la caméra
			switch (cameraDirection) {
		        case HAUT: characterPosY-=delta*speed;    break;
		        case GAUCHE: characterPosX-=delta*speed;  break;
		        case BAS:  characterPosY+=delta*speed; break;
		        case DROITE: characterPosX+=delta*speed; break;
		    }
		}*/
		//maj du fond d'écran
		background.setPosition(characterPosX, characterPosY);
		
		/*if (characterPosX > positiveLimit * background.getImage().getHeight())
		{
			positiveLimit++;
			generatePlateform(800 * positiveLimit);
		}
		else if (characterPosX < (negativeLimit + 1) * background.getImage().getHeight())
		{
			negativeLimit--;
			generatePlateform(800 * negativeLimit);
		}*/
	}
	
	public void keyReleased(int key, char c) {
		//TODO
		//annulation de la caméra
		switch (key) {
        case Input.KEY_UP: cameraMove=false;    break;
        case Input.KEY_LEFT: cameraMove=false; break;
        case Input.KEY_DOWN:  cameraMove=false; break;
        case Input.KEY_RIGHT: cameraMove=false; break;
    }
	}


	public void keyPressed(int key, char c) {
		//TODO
		//caméra en fonction des touches
		float speed = 1f;
		switch (key) {
	        case Input.KEY_UP: cameraDirection=Direction.HAUT; cameraMove=true;    break;
	        case Input.KEY_LEFT: cameraDirection=Direction.GAUCHE; cameraMove=true; break;
	        case Input.KEY_DOWN:  cameraDirection=Direction.BAS;cameraMove=true; break;
	        case Input.KEY_RIGHT: cameraDirection=Direction.DROITE;cameraMove=true; break;
	    }
	}

	@Override
	public int getID() {
		return 0;
	}

	
	
}
