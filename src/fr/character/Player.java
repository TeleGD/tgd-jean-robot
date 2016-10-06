package fr.character;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.menus.MenuFinPartie;
import fr.util.Movable;
import fr.util.Rectangle;

public class Player extends Movable implements Rectangle {
	protected double width, height;
	private boolean colplat;// y a t il eu une coll avec une plateforme a la
	// derniere frame
	private boolean vertcolthis;// y a t il eu col avec une plateforme a cette
	// frame
	private boolean upPress, leftPress, rightPress, droitegauche, downPress;
	private int bas = 600;
	private int life;
	private boolean invincible=true;// le joueur est invincible (apr�ｿｽs avoir perdu
	// une vie par exemple)
	private long timeInvincibleDying=3000;// temps d'invincibilit�ｿｽ apr�ｿｽs une mort
	private long timeOfDeath;
	private double comptInvincible;//compteur servant au clignotement durant l'invincibilit�ｿｽ apr�ｿｽs la mort
	protected boolean leftclick=false;
	
	public Player() {
		this.x = 150;
		this.y = 0;// 250-32;
		this.width = 16;
		this.height = 32;
		this.speedX = 0;
		this.speedY = 0;
		this.accelY = gravity;
		this.accelX = 0;
		this.colplat = false;
		this.invincible = false;
		this.timeOfDeath = -3000;
		this.life=3;
	}
	public Player(int x, int y,int width,int height) {
		this(x,y);
		this.width=width;
		this.height=height;
	}
	
	public Player(int x, int y) {
		this.x=x;
		this.y=y;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if((System.currentTimeMillis()-this.timeOfDeath<this.timeInvincibleDying)&&(this.comptInvincible>=5)){
			if((this.comptInvincible>=10)){
				this.comptInvincible=0;
			}
		}else{
			g.setColor(Color.green);
			g.fillRect((float) x, (float) y, (float) width, (float) height);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(System.currentTimeMillis()-this.timeOfDeath<this.timeInvincibleDying){invincible=true;}else{invincible=false;}
		if(!invincible){this.comptInvincible=0;}else{this.comptInvincible+=1;}

		horizontalMove();
		moveX(delta);
		this.newx = x + speedX * delta;
		this.newy = y + speedY * delta;
		this.speedY += accelY;
		verticalMove();
		moveY(delta);

	}

	// Mouvemnts************************************************************************
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

	public void verticalMove() {
		this.vertcolthis = false;
		posjump = false;
		for (int i = 0; i < fr.main.World.getPlatforms().size(); i++) {
			if ((fr.main.World.getPlatforms().get(i).collPlayer(this)) && (!downPress)) {
				this.y = fr.main.World.getPlatforms().get(i).getY() - this.height;
				this.accelY = 0;
				this.speedY = 0;
				colplat = true;
				vertcolthis = true;
				posjump = true;
			}
		}
		if (!invincible) {
			for (int i = 0; i < fr.main.World.getEnemies().size(); i++) {
				fr.main.World.getEnemies().get(i).collPlayer(this);
				if (fr.main.World.getEnemies().get(i).getLife() == 0) {
					fr.main.World.getEnemies().remove(i);
				}
			}
		}
		if (isTooLow()) {
			y = bas - height;
			speedY = 0;
			vertcolthis = true;
			posjump = true;
		}
		if (posjump && upPress) {
			jump();
		}
		if (!vertcolthis) {
			this.accelY = gravity;
			colplat = false;
		}
		this.speedY += this.accelY;
	}

	public double getnewY() {
		return newy;
	}

	public double getnewX() {
		return newx;
	}

	public boolean getcolplat() {
		return this.colplat;
	}

	public void jump() {
		this.speedY = 0;
		this.accelY = -this.jumppower;
		this.vertcolthis = true;
	}

	private boolean isTooLow() {
		if (speedY < 0) {
			return false;
		}
		if (newy + height < bas) {
			return false;
		}
		return true;
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
			leftPress = false;
			break;

		case Input.KEY_D:
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
			leftPress = true;
			droitegauche = false;
			break;
		case Input.KEY_D:
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
		this.comptInvincible=0;
		this.timeOfDeath = System.currentTimeMillis();
		if (life == 0) {
			fr.main.World.game.enterState(MenuFinPartie.ID, new FadeOutTransition(),
					new FadeInTransition());
		}
	}

	public boolean isInvincible(){
		return invincible;
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
		this.invincible = false;
		this.timeOfDeath = -3000;
		this.life=3;
	}
}
