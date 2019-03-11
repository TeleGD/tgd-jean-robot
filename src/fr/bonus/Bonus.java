package fr.bonus;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.*;
import fr.characters.Player;


public abstract class Bonus extends Entity {

	protected int duration;
	protected double tempsActivation;
	protected Player player;

	public int getDuration(){
		return duration;
	}

	public void setDuration(int duration){
		this.duration=duration;
	}

	public double getTempActivation(){
		return this.tempsActivation;
	}

	public void setTempsActivation(double tempsActivation){
		this.tempsActivation=tempsActivation;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setColor(Color.pink);
		g.fillRect((float) x,(float) y, (float) width,(float) height);
	}


	abstract public void comportment(Player player);

}
