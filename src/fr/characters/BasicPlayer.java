package fr.characters;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.Behavior.BeCollision;
import fr.Behavior.CanBounce;
import fr.characters.enemies.Ennemy;
import fr.decor.Plateform;
import fr.menus.MenuFinPartie;
import fr.projectiles.Projectile;
import fr.util.Movable;


public class BasicPlayer extends Movable implements Player {
	
	private boolean pad1,pad3,pad4,pad6,pad7,pad8,pad9,leftright;
	private boolean inCol;
	private int life;
	private long timeOfDeath;
	protected boolean leftclick=false;
	protected BeCollision coli = new CanBounce();
	protected int[][] tv;
	protected double speed; 
	private int score;
	private double jumpPower = -1.2;
	private double gravity= 0.05;
	public int lifeLostScore = 1000; //constante pour gérer le score
	protected int direction ; // 1 = droite, -1 = gauche
	
	//variables d'image
	private Image[] imageDroite=new Image[8];
	private Image[] imageGauche=new Image[8];
	private Image[] imageIdle = new Image[2];
	private Image currentImage;
	private int currentIndexImage,compt;
	
	public BasicPlayer() {
		super(100,0,32,64);
		this.newX =  this.x;
		this.newY = this.y;
		this.speedX = 0;
		this.speedY = 0;
		this.accelY = 0;
		this.timeOfDeath = -3000;
		this.life=3;
		this.posjump=false;
		this.compt =0;
		this.currentIndexImage=2;
		this.score = 0;
		this.setImages("img/Player/herobotWALK/jeanrobot_marche");
		this.direction = 1;
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.drawImage(currentImage, (float)x, (float)y);
		g.drawString(""+life+" vies", (float)x, (float)y-40);
		g.drawString("score = " + score, (float)x+500, (float)y-300);	
		g.drawString("x = " + x, (float)x+500, (float)y);
		g.drawString("y = " + y, (float)x+500, (float)y+20);	
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.newX = x + speedX * delta;
		this.newY = y + speedY * delta;
		this.speedY += accelY;
		
		horizontalMove2();
		verticalMove2(game);
		
		moveX(delta);
		moveY(delta);
		
		
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
	
	public void loose(StateBasedGame game){
		game.enterState(fr.menus.Mainmenu.ID, new FadeOutTransition(),
		new FadeInTransition());
	}

	// Mouvements************************************************************************


	private void horizontalMove2() {
		speedX = 0;
		if (((pad1 || pad4 || pad7) && !(pad3 || pad9 || pad6))||((pad1 || pad4 || pad7) && (pad3 || pad9 || pad6)) && !leftright) {
			speedX = (float) -0.5;

		}
		if(((pad3 || pad9 || pad6) &&!(pad1 || pad4 || pad7)) || ((pad1 || pad4 || pad7) && (pad3 || pad9 || pad6)) && leftright){
			speedX = (float) 0.5;
		}
	}
	
	
	private void verticalMove2(StateBasedGame game) {
		posjump = false;
		inCol=false;
		for (Plateform p : fr.game.World.getPlateforms()){
			p.collPlayer(this);
		}
		for (Ennemy e : fr.game.World.getEnemies()){
			e.collPlayer(this);
		}
		for (Projectile p : fr.game.World.getProjectiles()){
			p.collPlayer(this);
		}
		if (isTooLow()) {
			loose(game);
		}
		if (posjump && (pad7 || pad8 || pad9) ) {
			jump();
		} else if (!inCol) {
			this.accelY = gravity;
		} /*else if ((pad7 || pad8 || pad9) && jumpLeft>=0){
			jumpLeft-=1;
			jump();
		}*/
		this.speedY += this.accelY;
		
	}
	
	
	
	public void jump() {
		this.speedY = jumpPower;
		inCol=false;
		//posjump=false;
	}
	
	private boolean isTooLow() { //renvoie true si le personne touche le bas de l'ecran
		if (this.getSpeedY() < 0) {
			return false;
		}
		if (this.getnewY() + this.getHeight() < 2000) {
			return false;
		}
		return true;
	}
	
	
	// Les touches*******************************************************
	public void keyReleased(int key, char c) {

		switch (key) {
		/*case Input.KEY_NUMPAD1:
			pad1 = false;
			leftright=false;
			break;
		case Input.KEY_NUMPAD2:
			break;
		case Input.KEY_NUMPAD3:
			pad3 = false;
			leftright=true;
			break;
		case Input.KEY_NUMPAD4:
			pad4 = false;
			leftright=false;
			break;
		case Input.KEY_NUMPAD6:
			pad6 = false;
			leftright=true;
			break;
		case Input.KEY_NUMPAD7:
			leftright=false;
			pad7 = false;
			break;
		case Input.KEY_NUMPAD8:
			pad8 = false;
			break;
		case Input.KEY_NUMPAD9:
			pad9 = false;
			leftright=true;
			break;*/
		case Input.KEY_UP:
			pad8=false;
			break;
		case Input.KEY_RIGHT:
			pad6=false;
			leftright=true;
			break;
		case Input.KEY_LEFT:
			pad4=false;
			leftright=false;
			break;
		}
			
		
	}

	public void keyPressed(int key, char c) {
		switch (key) {
		
		//mouvement
		/*case Input.KEY_NUMPAD1:
			pad1 = true;
			break;
		case Input.KEY_NUMPAD2:
			break;
		case Input.KEY_NUMPAD3:
			pad3 = true;
			break;
		case Input.KEY_NUMPAD4:
			pad4 = true;
			break;
		case Input.KEY_NUMPAD6:
			pad6 = true;
			break;
		case Input.KEY_NUMPAD7:
			pad7 = true;
			break;
		case Input.KEY_NUMPAD8:
			pad8 = true;
			break;
		case Input.KEY_NUMPAD9:
			pad9 = true;
			break;*/
		case Input.KEY_UP:
			pad8=true;
			break;
		case Input.KEY_RIGHT:
			pad6=true;
			break;
		case Input.KEY_LEFT:
			pad4=true;
			break;
				
		//actions
		case Input.KEY_Y:
			System.out.println("y");
			break;
		case Input.KEY_U:
			System.out.println("u");
			break;
		case Input.KEY_I:
			System.out.println("i");
			break;
		case Input.KEY_H:
			System.out.println("h");
			break;
		case Input.KEY_J:
			System.out.println("j");
			break;
		case Input.KEY_K:
			System.out.println("k");
			break;
		}
	}
	
	// Autres
	// **************************************************************************
	public void lifelost() {
		this.life -= 1;
		addScore(-lifeLostScore); //
		this.timeOfDeath = System.currentTimeMillis();
		if (life == 0) {
			fr.game.World.game.enterState(MenuFinPartie.ID, new FadeOutTransition(),
					new FadeInTransition());
		}
	}

	public boolean iskillable(){
		return killable;
	}

	public int getlife(){
		return life;
	}

	public void reset(){
		this.x=200;
		this.y=0;
		this.newX =  this.x;
		this.newY = this.y;
		this.height=64;
		this.width=32;
		this.speedX = 0;
		this.speedY = 0;
		this.accelY = 0;
		this.accelX = 0;
		this.timeOfDeath = -3000;
		this.life=3;
		this.gravity=0.5;
		this.speed = 5;
		this.score = 0;
		}
	
	
	public int getType(){
		return 0;
	}
	
	
	//chooseimg pour choisir l'image a afficher en fonction de la direction
	private void chooseImg(){
		if (speedX>0){
			currentIndexImage++;
			if(currentIndexImage==8){
				currentImage=imageDroite[1];
				currentIndexImage=-1;
			}
			else{
				currentImage=imageDroite[currentIndexImage];
			}

		}
		if (speedX<0){
			currentIndexImage++;
			if(currentIndexImage==8){
				currentImage=imageGauche[1];
				currentIndexImage=-1;
			}
			else{
				currentImage=imageGauche[currentIndexImage];
			}
		}
		if (speedX == 0){
			currentIndexImage=0;
			if (this.getDirerction() == 1){
				currentImage=imageIdle[0];
			}
			else {
				currentImage=imageIdle[1];
			}	
		}
	}
	
	public void addScore(int s){
		this.score += s;
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
			{
				imageIdle[0] = new Image(path + "Idle.png");
				imageIdle[1] = new Image (path + "Idleg.png");
			}
			
			currentImage=imageDroite[currentIndexImage];
		}catch (Exception e){
			System.out.println("Attention les images ne peuvent etre chargees correctement, le path etant : " + path);
		}
	}
	
	/**
	 * 
	 * @return nombre entier : 1 si le personnage est tourn& vers la droite, -1 si il est tourn& vers la gauche
	 */
	public int getDirerction()
	{
		return this.direction;
	}
	
	public int getScore(){
		return this.score;
	}


	@Override
	public void setAccY(double i) {
		accelY=i;
	}


	@Override
	public void setSpeedY(double i) {
		speedY=i;
		
	}


	@Override
	public void setInCol(boolean b) {
		inCol=b;
		
	}


	@Override
	public void setposJump(boolean b) {
		posjump=b;
		
	}
}
