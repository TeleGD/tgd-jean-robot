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
import fr.menus.MenuFinPartie;
import fr.util.Movable;


public class BasicPlayer extends Movable implements Player {
	
	private boolean pad1,pad2,pad3,pad4,pad6,pad7,pad8,pad9,leftright,downup;
	private boolean inCol;
	private boolean upPress, leftPress, rightPress, droitegauche;
	private int life;
	private long timeOfDeath;
	protected boolean leftclick=false;
	protected BeCollision coli = new CanBounce();
	protected int[][] tv;
	protected double speed; 
	private int jumpLeft;
	
	//variables d'image
	private Image[] imageDroite=new Image[3];
	private Image[] imageGauche=new Image[3];

	private Image currentImage;
	private int currentIndexImage,compt;
	
	public BasicPlayer() {
		this.x=100;
		this.y=0;
		this.newX =  this.x;
		this.newY = this.y;
		this.height=64;
		this.width=32;
		this.speedX = 0;
		this.speedY = 0;
		this.accelY = 0;
		this.timeOfDeath = -3000;
		this.life=3;
		this.gravity=0.1;
		this.jumppower=1;
		this.posjump=false;
		this.jumpLeft=0;
		this.compt =0;
		this.currentIndexImage=2;
		
		
		try{
			imageDroite[0]= new Image("img/Player/droite_piedDroit.png");
			imageDroite[1]= new Image("img/Player/droite_Central.png");
			imageDroite[2]= new Image("img/Player/droite_piedGauche.png");
			
			imageGauche[0]= new Image("img/Player/gauche-piedDroit.png");
			imageGauche[1]= new Image("img/Player/gauche-Central.png");
			imageGauche[2]= new Image("img/Player/gauche-piedGauche.png");
			
			currentImage=imageDroite[currentIndexImage];
		}catch (Exception e){
			System.out.println("Attention les images ne peuvent être chargées correctement pour le player");
		}
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.drawImage(currentImage, (float)x, (float)y);
		g.drawString(""+life+" vies", (float)x, (float)y-40);
			
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
	
	
	private void horizontalMove() {
		speedX = 0;
		if ((leftPress && !rightPress) || (leftPress && rightPress && !droitegauche)) {
			if (x > 0) {
				speedX = -0.5;
			}

		}
		if ((!leftPress && rightPress) || (leftPress && rightPress && droitegauche)) {
			if (x < 1280 - width) {

				speedX = 0.5;
			}
		}
	}
	
	private void verticalMove2(StateBasedGame game) {
		posjump = false;
		inCol=false;
		for (int i = 0; i < fr.game.World.getPlateforms().size(); i++) {
			
			if (fr.util.Collisions.isCollisionY(this, fr.game.World.getPlateforms().get(i), 0)==-1) {
				this.y = fr.game.World.getPlateforms().get(i).getY() - this.height;
				this.accelY = 0;
				this.speedY = 0;
				jumpLeft=1;
				inCol=true;
				posjump = true;
			}
		}
		if (isTooLow()) {
			loose(game);
		}
		if (posjump && (pad7 || pad8 || pad9) ) {
			jump();
		} else if (!inCol) {
			this.accelY = gravity;
		} else if ((pad7 || pad8 || pad9) && jumpLeft>=0){
			jumpLeft-=1;
			jump();
		}
		this.speedY += this.accelY;
		
	}
	
	
	public void verticalMove(StateBasedGame game) {
		posjump = false;
		inCol=false;
		for (int i = 0; i < fr.game.World.getPlateforms().size(); i++) {
			
			if (fr.util.Collisions.isCollisionY(this, fr.game.World.getPlateforms().get(i), 0)==-1) {
				this.y = fr.game.World.getPlateforms().get(i).getY() - this.height;
				this.accelY = 0;
				this.speedY = 0;
				jumpLeft=1;
				inCol=true;
				posjump = true;
			}
		}
		if (isTooLow()) {
			loose(game);
		}
		if (posjump && upPress ) {
			jump();
		} else if (!inCol) {
			this.accelY = gravity;
		} else if (upPress && jumpLeft>=0){
			jumpLeft-=1;
			jump();
		}
		this.speedY += this.accelY;
	}
	
	public void jump() {
		this.speedY = 0;
		this.accelY = -this.jumppower;
		inCol=false;
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
		case Input.KEY_UP:
			upPress = false;
			break;

		case Input.KEY_DOWN:
			break;

		case Input.KEY_LEFT:
			leftPress = false;
			break;

		case Input.KEY_RIGHT:
			rightPress = false;
			break;
		case Input.KEY_NUMPAD1:
			pad1 = false;
			downup=true;
			leftright=false;
			break;
		case Input.KEY_NUMPAD2:
			pad2 = false;
			downup=true;
			break;
		case Input.KEY_NUMPAD3:
			pad3 = false;
			downup=true;
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
			downup=false;
			pad7 = false;
			break;
		case Input.KEY_NUMPAD8:
			downup=false;
			pad8 = false;
			break;
		case Input.KEY_NUMPAD9:
			pad9 = false;
			downup=false;
			leftright=true;
			break;

		}
			
		
	}

	public void keyPressed(int key, char c) {
		switch (key) {
		
		//mouvement
		case Input.KEY_UP:	
			upPress=true;
			break;

		case Input.KEY_DOWN:
			break;

		case Input.KEY_LEFT:
			leftPress = true;
			droitegauche = false;
			break;
		case Input.KEY_RIGHT:
			rightPress = true;
			droitegauche = true;
			break;
		case Input.KEY_NUMPAD1:
			pad1 = true;
			break;
		case Input.KEY_NUMPAD2:
			pad2 = true;
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
		this.upPress = false;
		this.rightPress = false;
		this.leftPress = false;
	}
	
	
	public int getType(){
		return 0;
	}
	
	
	//chooseimg pour choisir l'image a afficher en fonction de la direction
	private void chooseImg(){
		if (speedX>0){
			currentIndexImage++;
			if(currentIndexImage==3){
				currentImage=imageDroite[1];
				currentIndexImage=-1;
			}
			else{
				currentImage=imageDroite[currentIndexImage];
			}

		}
		if (speedX<0){
			currentIndexImage++;
			if(currentIndexImage==3){
				currentImage=imageGauche[1];
				currentIndexImage=-1;
			}
			else{
				currentImage=imageGauche[currentIndexImage];
			}
			

		}
	}
}
