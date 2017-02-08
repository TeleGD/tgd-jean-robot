package fr.decor;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import fr.characters.BasicPlayer;
import fr.characters.Player;
import fr.game.Game;
import fr.util.Collisions;

public class ElevatorTrap extends Plateform {
	
	double vitesse=3;
	boolean mvmnt;
	
	public ElevatorTrap(int indexX, int indexY, int sizeX, int sizeY){
		super(indexX,indexY,sizeX,sizeY);
	}
	
	public ElevatorTrap(String ligne) {
		super(ligne);
		String[] s=ligne.substring(ligne.indexOf(" ")+1).split(";");
		vitesse = Double.parseDouble(s[4]);
		mvmnt = Boolean.parseBoolean(s[5]);
	}
	
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.magenta);
		g.fillRect((float) x, (float) y, (float) width, (float) height);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO
		//this.newX = this.x;
		//this.newY = this.y;
	}
	
	private boolean activate(){
		Player player = fr.game.World.getPlayer();
		return false;	
	}

	public double getVitesse() {
		return vitesse;
	}

	public void setVitesse(double vitesse) {
		this.vitesse = vitesse;
	}

	public boolean isMvmnt() {
		return mvmnt;
	}

	public void setMvmnt(boolean mvmnt) {
		this.mvmnt = mvmnt;
	}
	
	@Override
	public String parseString() {
		return "ElevatorTrap "+getX()+ ";"+ getY()+";"+getWidth()+";"+getHeight()+";"+vitesse+";"+mvmnt;
	}
	@Override
	public Plateform copy() {
		ElevatorTrap p=new ElevatorTrap((int)x/Game.DENSITE_X,(int)y/Game.DENSITE_Y,(int)newX/Game.DENSITE_X,(int) (newY/Game.DENSITE_Y));
		p.height=this.height;
		p.width=this.width;
		p.vitesse=this.vitesse;
		p.mvmnt=this.mvmnt;
		return p;
	}
}
