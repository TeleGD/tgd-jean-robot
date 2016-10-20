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
import fr.decor.Plateform;
import fr.game.Game;
import fr.menus.MenuFinPartie;
import fr.util.Collisions;
import fr.util.Entity;
import fr.util.Movable;
import fr.util.Rectangle;


public class Player extends Movable implements Rectangle {
	
	
	private boolean colplat;// y a t il eu une coll avec une plateforme a la
	// derniere frame
	private boolean vertcolthis;// il  y a eu une plateforme en dessous
	private boolean upPress, leftPress, rightPress, droitegauche, downPress;
	private int life;
	private long timekillableDying=3000;// temps d'invincibilite d une mort
	private long timeOfDeath;
	private double comptkillable;//compteur servant au clignotement durant l'invincibilite suivant la mort
	protected boolean leftclick=false;
	protected BeCollision coli = new CanPassByBelow();
	
	public Player() {
		this.x=150;
		this.y=150;
		this.height=64;
		this.width=32;
		this.speedX = 0;
		this.speedY = 0;
		this.accelY = 0;
		this.accelX = 0;
		this.colplat = false;
		this.timeOfDeath = -3000;
		this.life=3;
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		/*if((System.currentTimeMillis()-this.timeOfDeath<this.timekillableDying)&&(this.comptkillable>=5)){
			if((this.comptkillable>=10)){
				this.comptkillable=0;
			}
		}else{*/
			g.setColor(Color.green);
			g.fillRect((float) x, (float) y, (float) width, (float) height);
		//	}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(System.currentTimeMillis()-this.timeOfDeath<this.timekillableDying){killable=false;}else{killable=true;}
		if(!killable){this.comptkillable=0;}else{this.comptkillable+=1;}
		
		 this.posjump = this.updatePosJump(); //verifie la possibilite de sauter
		
		
		//FUCK LA PHYSIQUE?? JE FAIS COMMENT DU COUP???
		//RE FUCK LA PHYSIQUE????
		
		//if(Math.abs(this.speedY) > 1) speedY = 1; // limitation de vitesse.
		altMove();
		this.speedY += accelY;
		this.newX = x + speedX * delta;
		this.newY = y + speedY * delta;
		System.out.println("vy = "+speedY+"; vx = "+speedX+"; y = "+y+"; x = "+x);
		System.out.println("----------------------------------------------------");
		moveX(delta);
		moveY(delta);
		
		this.colplat = this.vertcolthis;
	}

	// Mouvements************************************************************************

	/*private boolean updatePosJump() {
		// TODO Auto-generated method stub
		return false;
	}
*/
	private void altMove(){
		int col;
		Plateform plat;
		
		if (isTooLow()) {
			//le personnage meurt
			fr.game.World.game.enterState(MenuFinPartie.ID);//d, new FadeOutTransition(),new FadeInTransition());
		}
		System.out.print("avant touche fleche");
		System.out.println("vy = "+speedY+"; vx = "+speedX+"; y = "+y+"; x = "+x);
		
		horizontalMove();
		verticalMove();
		System.out.print("apres touche fleche");
		System.out.println("vy = "+speedY+"; vx = "+speedX+"; y = "+y+"; x = "+x);
		BeCollision.altMoveByCollision(this, this.coli);
		System.out.print("apres collision");
		System.out.println("vy = "+speedY+"; vx = "+speedX+"; y = "+y+"; x = "+x);
		
		/* Mais qu'est que c'est que ca?
		if(rightPress && !colD) speedX=0.3;
		else if(leftPress && !colG) speedX=-.3;
		else {
			System.out.println("NOFUNZ");
			speedX=0;
		}
		*/
		
		if (this.posjump && upPress) {
			jump();
		}
		
	}

	/*private void horizontalMove() {
		int col;		
		for (int i = 0; i < fr.game.World.getPlateforms().size(); i++){
			col = Collisions.isCollisionX(this, fr.game.World.getPlateforms().get(i));
			if(col == 0){
				// Si on a une collision, et qu'elle n'est pas directement par le bas ni par le haut.
				// (les collisions verticales renvoient 2 ou 4, et pas de collision renvoie 0)
				speedX = 0;
			}else{

				if(rightPress)speedX=0.3;
				else if(leftPress)speedX=-.3;
				else speedX=0;
			}
		}
		
		
	
	}
*/

/*	private void verticalMove() {

		if (isTooLow()) {
			//le personnage meurt
			fr.game.World.game.enterState(MenuFinPartie.ID);//d, new FadeOutTransition(),new FadeInTransition());
		}
		
		int col;
		for (int i = 0; i < fr.game.World.getPlateforms().size(); i++){
			col = Collisions.isCollisionY(this, fr.game.World.getPlateforms().get(i));
			
			if( col == 1 ){
				// S'il y a une collision par le bas du joueur.
				speedY = 0;
				posjump = true;
				//System.out.println("Y ColID : "+col+"; JUMP ? "+posjump+" UP? "+upPress);

			}
		}
		
		//System.out.println("can jump : "+posjump+" & Z pressed : "+upPress);
		if (this.posjump && upPress) {
			jump();
		}
		
	}	
*/
//  Coucou PA, ici Arthur. On me dit dans l'oreille que tu n'es pas PA, mais Aurelien. Desole pour l'accent (et pour l'erreur).
//Je comprenais pas ton code (vu que les collisions de PA sont au dela de ma simple condition d'humain), alors je l'ai commente. Bisous.
	private void horizontalMove() {
		speedX = 0;
		if ((leftPress && !rightPress) || (leftPress && rightPress && !droitegauche)) {
			speedX = 0;
			for (int i = 0; i < fr.game.World.getPlateforms().size(); i++) {
				if(Collisions.isCollisionX(this, fr.game.World.getPlateforms().get(i)) != -1){
					speedX = -0.5;
				}
			}	
		}
		if ((!leftPress && rightPress) || (leftPress && rightPress && droitegauche)) {
			speedX = 0;
			for (int i = 0; i < fr.game.World.getPlateforms().size(); i++) {
				if(Collisions.isCollisionX(this, fr.game.World.getPlateforms().get(i)) != 1){
					speedX = 0.5;
				}
			}	
		}
		
	}

	public void verticalMove() {
		// verticolthis et posjump sont actualisÃ©s dans updatePosJump qui est appelÃ© dans update
		// collisionx(a,b), -1 -> a a gauche de b, 0 rien, 1 : a A droite de b
		// collisiony(a,b) , -1 -> a au dessus de b , 0 rien, 1 -> a en dessous de b
		
		
		if (isTooLow()) {
			//le personnage meurt
			fr.game.World.game.enterState(MenuFinPartie.ID);//d, new FadeOutTransition(),new FadeInTransition());
		}
		
		if (this.posjump && upPress) {
			jump();
		}
		
		if (!vertcolthis) {	//si le personnage est en l'air, on accelere sa chute
			this.accelY = gravity;
		}
		
		this.speedY += this.accelY;
		
		if(vertcolthis){
			System.out.println("bouh");
		}
	}
	
	public boolean updatePosJump(){ //renvoie true si le personnage a la possibilite de sauter
		boolean mem = false;
		this.vertcolthis = false;
		for (int i = 0; i < fr.game.World.getPlateforms().size(); i++) {
			this.vertcolthis = (Collisions.isCollisionY(this, fr.game.World.getPlateforms().get(i))!=0 || Collisions.isCollisionX(this, fr.game.World.getPlateforms().get(i))!=0 || this.vertcolthis);
			
			if ((Collisions.isCollisionY(this, fr.game.World.getPlateforms().get(i)) == 1)) {
				//si une plateforme au dessus : on saute pas
				this.accelY = 0;
				this.speedY = 0;
				return false;
			}
			
			if ((Collisions.isCollisionY(this, fr.game.World.getPlateforms().get(i)) == -1)) {
				//si plateforme en dessous : mem = true
				this.accelY = 0;
				this.speedY = 0;
				mem = true;
			} 
		}
		return mem;
	}

	private boolean isTooLow() { //renvoie true si le personne touche le bas de l'ecran
		if (this.getSpeedY() < 0) {
			return false;
		}
		if (this.getNewY() + this.getHeight() < 720) {
			return false;
		}
		return true;
	}

	public boolean getcolplat() {
		return this.colplat;
	}

	public void jump() {
		this.speedY = 0;
		this.accelY = -this.jumppower;
		this.vertcolthis = true;
		this.posjump = false;
	}

	// Les touches*******************************************************
	public void keyReleased(int key, char c) {

		switch (key) {
		case Input.KEY_Z:
			upPress = false;
			break;

		case Input.KEY_S:
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
		}
	}

	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_Z:
			upPress = true;
			break;

		case Input.KEY_S:
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
		this.x = 150;
		this.y = 0;// 250-32;
		this.width = 16;
		this.height = 32;
		this.speedX = 0;
		this.speedY = 0;
		this.accelY = gravity;
		this.accelX = 0;
		colplat = false;
		this.killable = false;
		this.timeOfDeath = -3000;
		this.life=3;
	}

}
