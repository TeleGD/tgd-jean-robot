package fr.decor;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;

import fr.character.Player;
import fr.util.collisions;

public class Platform {
	
	//up-right pos
	public int x;
	public int y;
	
	//sub-block
	public int sizeX;
	public int sizeY;
	
	//center pos
	public int centerX;
	public int centerY;

	//color
	public Color color;
	
	//constructor
	public Platform(){
		
	}
	
	//render method
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		
	}
	
	//update method
	protected void update(GameContainer container, StateBasedGame game, int delta) {
		
	}
	
	//collision 
	public boolean collPlayer(Player player) {
		return collisions.isCollisionplayer1plateform(player,this, 0 ); //delta
	}

	
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
