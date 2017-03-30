package fr.characters.enemies;

import fr.characters.Player;
import fr.decor.Plateform;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;

public class BasicEnnemy extends Movable implements Ennemy{
		
		int bas = 600;
		private int score;
		protected int direction ; // 1 = droite, -1 = gauche
		//variables d'image
		private Image[] imageDroite=new Image[4];
		private Image[] imageGauche=new Image[4];
		private Image currentImage;
		private int currentIndexImage,compt;
		
		private int life;
		// une vie par exemple)
		
		private boolean destructed = false;//true si l'ennemi est mort et doit etre supprime
		
		//Pour attacher l'ennemi a une plateforme (histoire de reperer sa position par rapport a elle)
		private Plateform initialPlat;
		
		/*
		public BasicEnnemy(double x,double y) {
			super(x,y,32,32);
			this.speedX = 0;
			this.speedY = 0;
			this.life=1;
			this.destructed = false;
			this.score = 50;
		}
		*/
		
		public BasicEnnemy(Plateform plat) {
			super(plat.getX()+plat.getWidth()/2-32,plat.getY()-32,64,32);
			this.initialPlat=plat;
			this.speedX = 0;
			this.speedY = 0;
			this.life=1;
			this.destructed = false;
			this.score = 50;
			try {
				this.currentImage=new Image("img/Ennemy/croquebot1.png");
			} catch (SlickException e) {
				System.out.println("image croquebot non chargée");
				e.printStackTrace();
			}
		}
		
	public int getLife() {
		return life;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.black);
		g.drawImage(currentImage, (float)x, (float)y);
	}
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.newY = y + speedY * delta;
		this.newX = x + speedX * delta;
		moveY(delta);
		moveX(delta);
		
		compt++;
		compt=compt%6;
		if(compt==0)chooseImg();
		
		if (speedX > 0)
		{
			direction = 1;
		}
		else
		{
			if (speedX < 0)
			{
				direction = -1;
			}
		}
	}
	
	private void chooseImg(){
		if (speedX>0){
			currentIndexImage++;
			if(currentIndexImage==4){
				currentImage=imageDroite[1];
				currentIndexImage=-1;
			}
			else{
				currentImage=imageDroite[currentIndexImage];
			}

		}
		if (speedX<0){
			currentIndexImage++;
			if(currentIndexImage==4){
				currentImage=imageGauche[1];
				currentIndexImage=-1;
			}
			else{
				currentImage=imageGauche[currentIndexImage];
			}
		}
	}
	
	/**
	 * @param path : le path de l'image avec le début de son nom, par exemple "img/Player/herobotWALK/jeanrobot_marche"
	 */
	public void setImages(String path)
	{
		try{
			for (int i=0; i<imageDroite.length; i++){
				imageDroite[i] = new Image(path + (i+1) + ".png");
			}
			for (int i=0; i<imageGauche.length; i++){
				imageGauche[i] = new Image(path + (i+1) + "g" + ".png");
			}
			currentImage=imageDroite[currentIndexImage];
		}catch (Exception e){
			System.out.println("Attention les images ne peuvent etre chargees correctement, le path etant : " + path);
		}
	}
	
	private boolean isTooLow() { //renvoie true si la personne touche le bas de l'ecran
		if (speedY < 0) {
			return false;
		}
		if (newY + height < 720) {
			return false;
		}
		return true;
	}

	public boolean isDestructed() {
		return destructed;
	}

	public void setDestructed(boolean destructed) {
		this.destructed = destructed;
	}

	public void looseLife() {
		this.life-=1;
		if (this.life<=0)
				this.destructed=true;
	}
	
	public void setScore(int s){
		this.score = s;
	}
	
	public int getScore(){
		return this.score;
	}

	@Override
	public void collPlayer(Player player) {
	}

	@Override
	public Plateform getInitPlat() {
		return this.initialPlat;
	}
	

	public int getDirection()
	{
		return this.direction;
	}
}
