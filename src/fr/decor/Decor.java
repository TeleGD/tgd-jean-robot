package fr.decor;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.camera.Camera;
import fr.characters.Player;

public class Decor {

	public enum Direction {HAUT,DROITE,BAS,GAUCHE};

	private ArrayList<Plateform> plateforms = null;
	private Image plateformTexture;
	private Background background;
	private Camera cam;

	public Decor(String plateformTexturePath, String backgroundTexturePath) throws SlickException	{
		plateforms = new ArrayList<Plateform>();	//ensemble des plateformes crées
		plateformTexture = new Image(plateformTexturePath);	// chargement image pour plateforme
		background = new Background(0, 0, new Image(backgroundTexturePath));
		cam=new Camera ();
	}

	public void createPlateform(int posX, int posY, int sizeX, int sizeY)	{
		plateforms.add(new Plateform(sizeX, sizeY , posX , posY));
	}

	public ArrayList<Plateform> getObstacles()	{
		return plateforms;
	}



	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//translation de la caméra (fixé sur le personnage)
		g.translate(-(float)cam.getX(),0);



		//rendu du fond d'écran
		background.render(container, game, g);

		//rendu des plateformes
		for(int i=0;i<plateforms.size();i++)
			plateforms.get(i).render(container, game, g);


		cam.render(container, game, g);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		background.setPosition((float)cam.getX(), (float)cam.getY());
		cam.update(container, game, delta);
	}

	public void reset(){
		cam=new Camera ();
	}



}
