package fr.characters;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.Behavior.BeCollision;
import fr.Behavior.CanPassByBelow;
import fr.Behavior.CanBounce;
import fr.Behavior.CanPassBySide;
import fr.decor.Plateform;
import fr.game.Game;
import fr.game.World;
import fr.menus.MenuFinPartie;
import fr.util.Collisions;
import fr.util.Entity;
import fr.util.Movable;
import fr.util.Rectangle;


public class Player extends Movable implements Rectangle {
	
	private boolean upPress, leftPress, rightPress, droitegauche, downPress;
	private int life;
	private long timekillableDying=3000;// temps d'invincibilite d une mort
	private long timeOfDeath;
	private double comptkillable;//compteur servant au clignotement durant l'invincibilite suivant la mort
	protected boolean leftclick=false;
	protected BeCollision coli = new CanBounce();
	protected int[][] tv;
	protected double speed; 
	//Pour unifier la vitesse, delta c'est le mal
	private double fallTime; //Pour pas avoir besoin de l'acc�l�ration et simplifier la gestion de la ch�te !
	private double jumpTime; //Pour g�rer les sauts
	private boolean allowJump=false; //pour eviter de sauter en boucle si la touche reste enfoncee
	
	public Player() {
		this.fallTime=0;
		this.jumpTime=1000;
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
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
			g.setColor(Color.green);
			g.fillRect((float) x, (float) y, (float) width, (float) height);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.x = this.newX;
		this.y = this.newY;
		if(System.currentTimeMillis()-this.timeOfDeath<this.timekillableDying){killable=false;}else{killable=true;}
		if(!killable){this.comptkillable=0;}else{this.comptkillable+=1;}
		altMove();
		this.newX = x + speedX;
		this.newY = y + speedY;
	}
	
	public void loose(StateBasedGame game){
		game.enterState(fr.menus.Mainmenu.ID, new FadeOutTransition(),
		new FadeInTransition());
	}

	// Mouvements************************************************************************

	private void altMove(){
		/**
		 * Fonction qui g�re le mouvement
		 */
		if (isTooLow()) {
			fr.game.World.game.enterState(MenuFinPartie.ID);//d, new FadeOutTransition(),new FadeInTransition());
			reset();
		}
		horizontalMove();
		verticalMove();
	}

	private void horizontalMove() {
		/**
		 * Fonction qui permet de g�rer le d�placement lat�ral
		 */
		speedX = 0;
		boolean bougeable = true;
		if ((leftPress && !rightPress) || (leftPress && rightPress && !droitegauche)) {
			speedX=-speed;
			this.tv = BeCollision.altMoveByCollision(this, this.coli);
			if (true){
				for (int i = 0; i < tv.length; i++) {
					if (tv[i][0] == 1){
						bougeable = false;
					}
				}
				speedX=0;
				if (bougeable){
					speedX=-speed;
				}
			}
		}
		if ((!leftPress && rightPress) || (leftPress && rightPress && droitegauche)) {
			speedX=speed;
			this.tv = BeCollision.altMoveByCollision(this, this.coli);
			if (true){
				for (int i = 0; i < tv.length; i++) {
					if (tv[i][0] == -1){
						bougeable = false;
					} 
				}
				speedX=0;
				if (bougeable){
					speedX=speed;
				}
			}
		}	
	}

	public void verticalMove() {
		/**
		 * Fonction qui permet de g�rer la ch�te et les sauts 
		 */
		speedY = 0;
		boolean bougeable = true;
		//Ici on g�re les sauts
		if ((upPress && !downPress) && (this.jumpTime<25*this.jumppower) && (allowJump)) { //on limite la taille max du saut tout en permettant au joueur de g�rer la puissance de ses sauts :D
			//speedY= -speed * Math.sqrt((16/(2+jumpTime)));
			speedY= -speed * Math.sqrt((20/(1+jumpTime)));
			this.tv = BeCollision.altMoveByCollision(this, this.coli);
			// On gère ici le fait que le joueur ne peut pas sauter à travers une plateforme
			if (true){
				for (int i = 0; i < tv.length; i++) {
					if (tv[i][1] == 1){
						bougeable = false;
						if (!(this.coli instanceof CanPassByBelow)){
							speedY=0;
							this.jumpTime = 25*this.jumppower;
						}
						else{
							speedY=-speed * Math.sqrt((16/(1+jumpTime)));
							this.jumpTime += 1;
						}
					}
				}
				if (bougeable){
					speedY= -speed * Math.sqrt((16/(1+jumpTime))); // pour un saut plus classe
					this.jumpTime += 1;
				}
			}	
		}
		//La on gere la chute
		else {
			//Deplace l'entity en bas de la plateforme si elle est bloquée
			if(true){
				for (int i = 0; i < fr.game.World.getPlateforms().size(); i++) {
					if (Collisions.inCollision(this, fr.game.World.getPlateforms().get(i))){
						speedY = 0;
						this.y = fr.game.World.getPlateforms().get(i).getY()+fr.game.World.getPlateforms().get(i).getHeight();
						this.jumpTime = 1000;
					}
				}
			}
			speedY= this.gravity * this.fallTime;
			this.tv = BeCollision.altMoveByCollision(this, this.coli);
			if (true){
				for (int i = 0; i < tv.length; i++) {
					if (tv[i][1] == -1){
						bougeable = false;
						this.jumpTime=0; //On permet les sauts
						this.fallTime= this.fallTime/2; //On met �a pour donner l'impression que le personnage touche la plateforme ! Faudrait rendre �a plus propre
					}
				}
				speedY=0;
				if (bougeable){
					speedY = this.gravity * this.fallTime;
					fallTime += 3;
					
				}
			}
		}	

	}
	
	
	private boolean isTooLow() { //renvoie true si le personne touche le bas de l'ecran
		if (this.getSpeedY() < 0) {
			return false;
		}
		if (this.getNewY() + this.getHeight() < 2000) {
			return false;
		}
		return true;
	}
	
	//appelee lors du relachement de la touche saut : empeche les double sauts
	private void jumped(){ 
		this.jumpTime = 1000*this.jumppower;
	}
	

	
	// Les touches*******************************************************
	public void keyReleased(int key, char c) {

		switch (key) {
		case Input.KEY_Z:
		case Input.KEY_UP:
			upPress = false;
			allowJump=true;
			break;

		case Input.KEY_DOWN:
			downPress = false;
			break;

		case Input.KEY_Q:
		case Input.KEY_LEFT:
			leftPress = false;
			break;

		case Input.KEY_D:
		case Input.KEY_RIGHT:
			rightPress = false;
			break;
			
			
		//clavier numerique
		//deplacement via le clavier numerique
		//bas + gauche
		case Input.KEY_NUMPAD1:
			downPress = false;
			leftPress = false;
			break;
		//bas
		case Input.KEY_NUMPAD2:
			downPress = false;
			break;
		//bas + droite
		case Input.KEY_NUMPAD3:
			downPress = false;
			rightPress = false;
			break;
		//gauche
		case Input.KEY_NUMPAD4:
			leftPress = false;
			break;
		//droite
		case Input.KEY_NUMPAD6:
			rightPress = false;
			break;
		//haut + gauche
		case Input.KEY_NUMPAD7:
			jumped();
			upPress = false;
			leftPress = false;
			break;
		//haut
		case Input.KEY_NUMPAD8:
			jumped();
			upPress = false;
			break;
		//haut+droite
		case Input.KEY_NUMPAD9:
			jumped();
			upPress = false;
			rightPress = false;
			break;
		}
	}

	public void keyPressed(int key, char c) {
		switch (key) {
		
		//mouvement
		case Input.KEY_Z:
		case Input.KEY_UP:	
			upPress = true;
			break;

		case Input.KEY_DOWN:
			downPress = true;
			break;

		case Input.KEY_Q:
		case Input.KEY_LEFT:
			leftPress = true;
			droitegauche = false;
			break;
		case Input.KEY_D:
		case Input.KEY_RIGHT:
			rightPress = true;
			droitegauche = true;
			break;
			
		//deplacement via le clavier numerique
		//bas + gauche
		case Input.KEY_NUMPAD1:
			downPress = true;
			leftPress = true;
			droitegauche = false;
			break;
		//bas
		case Input.KEY_NUMPAD2:
			downPress = true;
			break;
		//bas + droite
		case Input.KEY_NUMPAD3:
			downPress = true;
			rightPress = true;
			droitegauche = true;
			break;
		//gauche
		case Input.KEY_NUMPAD4:
			leftPress = true;
			droitegauche = false;
			break;
		//droite
		case Input.KEY_NUMPAD6:
			rightPress = true;
			droitegauche = true;
			break;
		//haut + gauche
		case Input.KEY_NUMPAD7:
			if(!upPress)
				upPress = true;
			leftPress = true;
			droitegauche = false;;
			break;
		//haut
		case Input.KEY_NUMPAD8:
			if(!upPress)
				upPress = true;
			break;
		//haut+droite
		case Input.KEY_NUMPAD9:
			if(!upPress)
				upPress = true;
			rightPress = true;
			droitegauche = true;
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

	//souris**********************************************************************
	public void mousePressed(int button, int x,int y){
	}

	// Autres
	// **************************************************************************
	public void lifelost() {
		this.life -= 1;
		this.comptkillable=0;
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
		this.fallTime=0;
		this.jumpTime=1000;
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
		this.downPress = false;
		this.rightPress = false;
		this.leftPress = false;
	}

}
