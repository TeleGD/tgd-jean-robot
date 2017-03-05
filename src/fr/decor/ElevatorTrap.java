package fr.decor;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;
import fr.game.Game;

/**
 * Plateforme qui au bout d'un certain temps resté dessus, finit par tomber
 *  - DEFAUT_TIME_BEFORE_FALL : Le temps a attendre avant que la plateforme cede (tps en nb de frame)
 *  - DEFAUT_SPEED_FALL : La vitesse de chute de la plateforme 
 *
 */
public class ElevatorTrap extends Plateform {
	
	private static final int DEFAUT_TIME_BEFORE_FALL = 20;
	private static final double DEFAUT_SPEED_FALL = 3.0;
	
	private double vitesse=DEFAUT_SPEED_FALL;
	private int restingTimeBeforeFall=DEFAUT_TIME_BEFORE_FALL;
	
	private boolean isFalling, isInCollision;

	public ElevatorTrap(int indexX, int indexY, int sizeX, int sizeY){
		super(indexX,indexY,sizeX,sizeY);
	}
	/**
	 * Constructeur qui permet de retrouver un objet plateforme a partir d'une ligne de chaine de caracteèr
	 * enregistré dans un niveau.
	 * @param ligne ligne à parser en objet ElevatorTrap
	 */
	public ElevatorTrap(String ligne) {
		super(ligne);
		vitesse = Double.parseDouble(ligne.substring(ligne.indexOf(";")+1));
	}
	
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.magenta);
		g.fillRect((float) x, (float) y, (float) width, (float) height);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(isInCollision){
			restingTimeBeforeFall--;
		}
		
		if(restingTimeBeforeFall==0)
		{
			this.setY(this.getVitesse()+(this.getY()));
			this.setFalling(true);
		}
		
		if(isFalling)
		{
			this.setY(this.getVitesse()+(this.getY()));
		}
	}
	
	private void setFalling(boolean b) {
		isFalling=b;
	}

	@Override
	public void collPlayer(Player player){
		if(fr.util.Collisions.colPlayerPlateform(player,this)==2 && !isFalling){
			this.isInCollision=true;
			
			player.setY(this.getY() - player.getHeight());
			player.setAccY(0);
			player.setSpeedY(0);
			player.setInCol(true);
			player.setposJump(true);
		}
	}
	

	public double getVitesse() {
		return vitesse;
	}

	public void setVitesse(double vitesse) {
		this.vitesse = vitesse;
	}
	
	
	
	@Override
	public String parseString() {
		return "ElevatorTrap :  "+getX()+ ";"+ getY()+";"+getWidth()+";"+getHeight()+";"+getVitesse();
	}
	
	@Override
	public Plateform copy() {
		ElevatorTrap p=new ElevatorTrap((int)x/Game.DENSITE_X,(int)y/Game.DENSITE_Y,(int)x/Game.DENSITE_X,(int) (y/Game.DENSITE_Y));
		p.height=this.height;
		p.width=this.width;
		p.vitesse=this.vitesse;
		return p;
	}
}
