package fr.decor;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import fr.characters.Player;
import fr.util.Collisions;

public class ElevatorTrap extends Plateform {
	
	double vitesse;
	boolean mvmnt;
	
	ElevatorTrap(int a, int b, int c, int d){
		super(a,b,c,d);
	}
	
	public ElevatorTrap(String ligne) {
		super(ligne);
		String[] s=ligne.substring(ligne.indexOf(" ")+1).split(";");
		vitesse = Double.parseDouble(s[4]);
		mvmnt = false;
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
	
	
	
}
